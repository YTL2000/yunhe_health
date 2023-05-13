package cn.yunhe.service;

import cn.yunhe.entity.PageResult;
import cn.yunhe.entity.QueryPageBean;
import cn.yunhe.exception.BusinessException;
import cn.yunhe.pojo.CheckItem;

import java.util.List;

/**
 * 检查项接口
 * @version 1.0
 * @auther YTL
 * @className CheckItemService
 * since 1.0
 */
public interface CheckItemService {
    void add(CheckItem checkItem) throws BusinessException;

    PageResult findPage(QueryPageBean queryPageBean) throws BusinessException;

    void delete(Integer id) throws BusinessException;

    CheckItem findById(Integer id) throws BusinessException;

    void edit(CheckItem checkItem) throws BusinessException;

    List<CheckItem> findAll() throws BusinessException;
}
