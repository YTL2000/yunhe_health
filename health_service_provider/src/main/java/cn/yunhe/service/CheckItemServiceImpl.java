package cn.yunhe.service;

import cn.yunhe.entity.PageResult;
import cn.yunhe.entity.QueryPageBean;
import cn.yunhe.entity.Result;
import cn.yunhe.exception.BusinessException;
import cn.yunhe.mapper.CheckGroupAndItemMapper;
import cn.yunhe.mapper.CheckItemMapper;
import cn.yunhe.pojo.CheckGroupAndItem;
import cn.yunhe.pojo.CheckItem;
import com.alibaba.nacos.api.config.filter.IFilterConfig;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 检查项接口实现类
 * @version 1.0
 * @auther YTL
 * @className CheckItemServiceImpl
 * since 1.0
 */
@Service
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemMapper checkItemMapper;
    @Autowired
    private CheckGroupAndItemMapper checkGroupAndItemMapper;
    @Override
    public void add(CheckItem checkItem) throws BusinessException {
        if (checkItem.getName()==null|| StringUtils.isEmpty(checkItem.getCode())) {
            throw new BusinessException("请检查你输入的检查项名称以及编号！");
        }
        if (checkName(checkItem.getName())||checkCode(checkItem.getCode())){
            throw new BusinessException("你所输入的检查项名称和编号已经存在！");
        }
        checkItemMapper.insert(checkItem);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean)throws BusinessException  {
        //获得分页查询条件
        String queryString = queryPageBean.getQueryString();
        LambdaQueryWrapper<CheckItem> checkItemLQW = new LambdaQueryWrapper<>();
        //将pageNum，pageSize设置page对象中
        Page<CheckItem> checkItemPage = new Page<>(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //按照分页查询条件拼接SQL语句
        if (!StringUtils.isEmpty(queryString)){
            checkItemLQW.like(CheckItem::getName,queryString)
                    .or()
                    .eq(CheckItem::getCode,queryString);
        }
        IPage<CheckItem> checkItemIPage = checkItemMapper.selectPage(checkItemPage, checkItemLQW);

        return new PageResult(checkItemIPage.getTotal(),checkItemIPage.getRecords());
    }

    @Override
    public CheckItem findById(Integer id) throws BusinessException {
        if (id==null){
            throw new BusinessException("id不能为空！");
        }
        return checkItemMapper.selectById(id);
    }

    @Override
    public void edit(CheckItem checkItem) throws BusinessException  {
        //获得编辑框中的新的code和name
        String code = checkItem.getCode();
        String name = checkItem.getName();
        CheckItem tag = checkItemMapper.selectById(checkItem.getId());
        if (tag==null){
            throw new BusinessException("体检项不存在！");
        }
        String tagName = tag.getName();
        String tagCode = tag.getCode();
        if (!name.equals(tagName)&&checkName(name)||(!code.equals(tagCode)&&checkCode(code))){
            throw new BusinessException("检查项编码或名称已存在");
        }
        checkItemMapper.updateById(checkItem);
    }

    @Override
    public void delete(Integer id) {
        CheckItem checkItem = checkItemMapper.selectById(id);
        if (checkItem==null){
            throw new BusinessException("此检查项不存在！");
        }
        Integer integer = checkGroupAndItemMapper.selectCount(
                new LambdaQueryWrapper<CheckGroupAndItem>()
                        .eq(CheckGroupAndItem::getCheckitemId,id));
        checkItemMapper.deleteById(id);
        if (integer>0){
            throw new BusinessException("此检查项存在检查组中，不能删除！");
        }
        checkGroupAndItemMapper.deleteById(id);

    }

    /**
     * 检查名称是否已经存在
     * @param name
     * @return
     */
    public Boolean checkName(String name){
        LambdaQueryWrapper<CheckItem> checkItemLQW = new LambdaQueryWrapper<CheckItem>()
                .eq(CheckItem::getName,name);
        checkItemMapper.selectCount(checkItemLQW);
        return checkItemMapper.selectCount(checkItemLQW)>0;
    }

    /**
     * 检查编号是否已经存在
     * @param code
     * @return
     */
    public Boolean checkCode(String code){
        LambdaQueryWrapper<CheckItem> checkItemLQW = new LambdaQueryWrapper<CheckItem>()
                .eq(CheckItem::getCode,code);
        checkItemMapper.selectCount(checkItemLQW);
        return checkItemMapper.selectCount(checkItemLQW)>0;
    }
}
