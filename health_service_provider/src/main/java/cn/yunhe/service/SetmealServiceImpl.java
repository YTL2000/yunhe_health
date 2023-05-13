package cn.yunhe.service;

import cn.yunhe.entity.PageResult;
import cn.yunhe.entity.QueryPageBean;
import cn.yunhe.exception.BusinessException;
import cn.yunhe.mapper.SetmealCheckgroupMapper;
import cn.yunhe.mapper.SetmealMapper;
import cn.yunhe.pojo.Setmeal;
import cn.yunhe.pojo.SetmealCheckgroup;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @version 1.0
 * @auther YTL
 * @className SetmealServiceImpl
 * since 1.0
 */
@Service
public class SetmealServiceImpl implements SetmealService{
    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private SetmealCheckgroupMapper setmealCheckgroupMapper;

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //得到查询条件
        String queryString = queryPageBean.getQueryString();
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isEmpty(queryString)){
            setmealLambdaQueryWrapper
                    .eq(Setmeal::getCode,queryString).or()
                    .like(Setmeal::getName,queryString).or()
                    .eq(Setmeal::getHelpCode,queryString);
        }
        Page<Setmeal> page = new Page<>(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        IPage<Setmeal> setmealIPage = setmealMapper.selectPage(page, setmealLambdaQueryWrapper);
        return new PageResult(setmealIPage.getTotal(),setmealIPage.getRecords());
    }

    @Override
    @Transactional
    public void add( Integer[] checkgroupIds,Setmeal setmeal) throws BusinessException {
        String code = setmeal.getCode();
        String name = setmeal.getName();
        String helpCode = setmeal.getHelpCode();
        //检查名称编码助记码是否唯一
        if (checkCode(code)){
            throw new BusinessException("此检查编码已存在");
        }
        if (checkName(name)){
            throw new BusinessException("此检查套餐名称已存在");
        }
        if (checkHelpCode(helpCode)){
            throw new BusinessException("此检查助记码已存在");
        }
//        if (checkgroupIds!=null&&checkgroupIds.length>0){
//            throw new BusinessException("添加检查套餐时所包含的检查项不能为空！");
//        }
        if (checkgroupIds==null){
            throw new BusinessException("添加检查套餐时所包含的检查项不能为空！");
        }
        if (checkgroupIds.length<=0){
            throw new BusinessException("添加检查套餐时所包含的检查项不能为空！");
        }
        setmealMapper.insert(setmeal);

        Setmeal setmeal1 = setmealMapper.selectOne(new LambdaQueryWrapper<Setmeal>().eq(Setmeal::getName, name));
        Integer id = setmeal1.getId();
        System.out.println("============="+id);
        //新增套餐添加检查组
        for (Integer checkgroupId : checkgroupIds) {
            SetmealCheckgroup setmealCheckgroup=new SetmealCheckgroup(id,checkgroupId);
            setmealCheckgroupMapper.insert(setmealCheckgroup);
        }
    }
    public boolean checkCode(String code){

        return setmealMapper.selectCount(new LambdaQueryWrapper<Setmeal>().eq(Setmeal::getCode,code))>0;
    };
    public boolean checkName(String name){
        return setmealMapper.selectCount(new LambdaQueryWrapper<Setmeal>().eq(Setmeal::getName,name))>0;
    };
    public boolean checkHelpCode(String helpCode){
        return setmealMapper.selectCount(new LambdaQueryWrapper<Setmeal>().eq(Setmeal::getHelpCode,helpCode))>0;
    };
}
