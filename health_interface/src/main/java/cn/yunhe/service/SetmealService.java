package cn.yunhe.service;

import cn.yunhe.entity.PageResult;
import cn.yunhe.entity.QueryPageBean;
import cn.yunhe.pojo.Setmeal;

/**
 * 检查套餐业务层
 * @version 1.0
 * @auther YTL
 * @className SetmealService
 * since 1.0
 */
public interface SetmealService {

    void add(Integer[] checkgroupIds,Setmeal setmeal);

    PageResult findPage(QueryPageBean queryPageBean);
}
