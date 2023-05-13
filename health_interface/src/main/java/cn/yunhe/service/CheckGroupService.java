package cn.yunhe.service;

import cn.yunhe.entity.PageResult;
import cn.yunhe.entity.QueryPageBean;
import cn.yunhe.exception.BusinessException;
import cn.yunhe.pojo.CheckGroup;
import org.apache.poi.sl.usermodel.Background;

import java.util.List;

/**
 * 检查组service
 * @version 1.0
 * @auther YTL
 * @className CheckGroupService
 * since 1.0
 */
public interface CheckGroupService {
    void add(Integer[] checkitemIds, CheckGroup checkGroup) throws BusinessException;

    PageResult findPage(QueryPageBean queryPageBean) throws BusinessException;

    CheckGroup findById(Integer id);

    Integer[] findCheckItemIdsByCheckGroupId(Integer id);

    void edit(Integer[] checkitemIds, CheckGroup checkGroup);

    void delete(Integer id);

    List<CheckGroup> findAll();
}
