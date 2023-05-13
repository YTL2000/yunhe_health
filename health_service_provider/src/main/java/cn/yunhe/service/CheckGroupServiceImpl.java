package cn.yunhe.service;

import cn.yunhe.constant.RedisConstant;
import cn.yunhe.entity.PageResult;
import cn.yunhe.entity.QueryPageBean;
import cn.yunhe.exception.BusinessException;
import cn.yunhe.mapper.CheckGroupAndItemMapper;
import cn.yunhe.mapper.CheckGroupMapper;
import cn.yunhe.mapper.SetmealCheckgroupMapper;
import cn.yunhe.pojo.CheckGroup;
import cn.yunhe.pojo.CheckGroupAndItem;
import cn.yunhe.pojo.SetmealCheckgroup;
import cn.yunhe.util.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 检查组service层
 *
 * @version 1.0
 * @auther YTL
 * @className CheckGroupServiceImpl
 * since 1.0
 */
@Slf4j
@Service
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupMapper checkGroupMapper;
    @Autowired
    private CheckGroupAndItemMapper checkGroupAndItemMapper;
    @Autowired
    private SetmealCheckgroupMapper setmealCheckgroupMapper;
    //    引入redis的key
    String key = RedisConstant.QUERY_CHECKGROUPS_KEY;
    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public void add(Integer[] checkitemIds, CheckGroup checkGroup) {
        RedisUtil.del(redisTemplate, key);
        if (checkGroup != null && StringUtils.isEmpty(checkGroup.getName())) {
            throw new BusinessException("该检查项存在问题请检查!");
        }
        //判断检查组名称编号是否唯一
        if (checkCode(checkGroup.getCode())) {
            throw new BusinessException("该检查项编码已存在!");
        }
        if (checkName(checkGroup.getName())) {
            throw new BusinessException("该检查项名称已存在!");
        }
        if (checkHelpCode(checkGroup.getHelpCode())) {
            throw new BusinessException("该检查项助记码已存在!");
        }
        //添加检查组
        checkGroupMapper.insert(checkGroup);
        //检查组id
        Integer groupId = checkGroup.getId();
        //添加检查组对应检查项
        if (checkitemIds != null && checkitemIds.length > 0) {
            for (Integer checkitemId : checkitemIds) {
                CheckGroupAndItem checkGroupAndItem = new CheckGroupAndItem(groupId, checkitemId);
                checkGroupAndItemMapper.insert(checkGroupAndItem);
            }

        }
    }


    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
//        分页查询条件
        String queryString = queryPageBean.getQueryString();
        Page<CheckGroup> checkGroupPage = new Page<>(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        LambdaQueryWrapper<CheckGroup> checkGroupLQW = new LambdaQueryWrapper<>();
//        按照分页条件拼接sql语句
        if (!StringUtils.isEmpty(queryString)) {
            checkGroupLQW.eq(CheckGroup::getCode, queryString)
                    .or()
                    .eq(CheckGroup::getName, queryString)
                    .or()
                    .eq(CheckGroup::getHelpCode, queryString);

        }
        IPage<CheckGroup> checkGroupIPage = checkGroupMapper.selectPage(checkGroupPage, checkGroupLQW);
        return new PageResult(checkGroupIPage.getTotal(), checkGroupIPage.getRecords());
    }

    @Override
    public List<CheckGroup> findAll() {
        List<CheckGroup> checkGroupList;
        if (redisTemplate.hasKey(key)){
            checkGroupList= (List<CheckGroup>) redisTemplate.opsForValue().get(key);
        }else {
            checkGroupList=checkGroupMapper.selectList(null);
        }
        return checkGroupList;
    }

    @Override
    @Transactional
    public void delete(Integer id) {

        if (id == null) {
            throw new BusinessException("此检查组不存在!");
        }
        RedisUtil.del(redisTemplate, key);
        //查询检查组是否在检查套餐中存在
        Integer count = setmealCheckgroupMapper.selectCount(new LambdaQueryWrapper<SetmealCheckgroup>().eq(SetmealCheckgroup::getCheckgroupId, id));
        if (count > 0) {
            throw new BusinessException("该检查组在检查套餐中存在!不可删除");
        }
        //先删除中间表
        checkGroupAndItemMapper.delete(new LambdaQueryWrapper<CheckGroupAndItem>().eq(CheckGroupAndItem::getCheckgroupId, id));
        //再删除检查组
        checkGroupMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void edit(Integer[] checkitemIds, CheckGroup checkGroup) {
        //清缓存
        RedisUtil.del(redisTemplate, key);
        //更新检查组信息
        checkGroupMapper.updateById(checkGroup);
        //获取检查组id
        Integer id = checkGroup.getId();
        //根据检查组id删除中间表对应检查项
        checkGroupAndItemMapper.delete(new LambdaQueryWrapper<CheckGroupAndItem>().eq(CheckGroupAndItem::getCheckgroupId, id));
        //根据检查组id添加中间表对应检查项
        if (checkitemIds != null && checkitemIds.length > 0) {
            for (Integer checkitemId : checkitemIds) {
                CheckGroupAndItem checkGroupAndItem = new CheckGroupAndItem(id, checkitemId);
                checkGroupAndItemMapper.insert(checkGroupAndItem);
            }
        }
    }

    @Override
    @Transactional
    public Integer[] findCheckItemIdsByCheckGroupId(Integer id) {
        //拿到检查组id去检查项和检查组中间表查询
        List<CheckGroupAndItem> checkGroupAndItems = checkGroupAndItemMapper.selectList(
                new LambdaQueryWrapper<CheckGroupAndItem>().eq(CheckGroupAndItem::getCheckgroupId, id));
//        拿到中间表对象得到对应的检查项对象
        if (checkGroupAndItems != null && checkGroupAndItems.size() > 0) {
            Integer[] ids = new Integer[checkGroupAndItems.size()];
            for (int i = 0; i < checkGroupAndItems.size(); i++) {
                CheckGroupAndItem checkGroupAndItem = checkGroupAndItems.get(i);
                Integer checkitemId = checkGroupAndItem.getCheckitemId();
                ids[i] = checkitemId;
            }
            return ids;
        }
        return null;
    }

    @Override
    public CheckGroup findById(Integer id) {
        if (id == null) {
            throw new BusinessException("查询检查组id不能为空");
        }
        CheckGroup checkGroup = null;
        if (redisTemplate.hasKey(key)) {
            checkGroup = (CheckGroup) redisTemplate.opsForValue().get(key);
        } else {
            checkGroup = checkGroupMapper.selectById(id);
        }
        return checkGroup;
    }

    //    判断编码是否唯一
    public boolean checkCode(String code) {
        Integer integer = checkGroupMapper.selectCount(new LambdaQueryWrapper<CheckGroup>().eq(CheckGroup::getCode, code));
        return integer > 0;
    }

    //    判断名称是否唯一
    public boolean checkName(String Name) {
        Integer integer = checkGroupMapper.selectCount(new LambdaQueryWrapper<CheckGroup>().eq(CheckGroup::getName, Name));
        return integer > 0;
    }

    //    判断助记码是否唯一
    public boolean checkHelpCode(String HelpCode) {
        Integer integer = checkGroupMapper.selectCount(new LambdaQueryWrapper<CheckGroup>().eq(CheckGroup::getHelpCode, HelpCode));
        return integer > 0;
    }
}
