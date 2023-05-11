package cn.yunhe.service;

import cn.yunhe.entity.PageResult;
import cn.yunhe.entity.QueryPageBean;
import cn.yunhe.entity.Result;
import cn.yunhe.exception.BusinessException;
import cn.yunhe.pojo.CheckItem;

/**
 * 检查项接口
 * @version 1.0
 * @auther YTL
 * @className CheckItemService
 * since 1.0
 */
public interface CheckItemService {
    void add(CheckItem checkItem) throws BusinessException;

    PageResult findPage(QueryPageBean queryPageBean);

    void delete(Integer id);

    CheckItem findById(Integer id);

    void edit(CheckItem checkItem);
}
