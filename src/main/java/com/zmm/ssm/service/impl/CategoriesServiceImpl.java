package com.zmm.ssm.service.impl;

import com.zmm.ssm.bean.Category;
import com.zmm.ssm.mapper.CategoriesMapper;
import com.zmm.ssm.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: ssm
 * @Package: com.zmm.ssm.service.impl
 * @Description: java类作用描述
 * @Author: 周铭明
 * @CreateDate: 2020/11/11 0011 10:20
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service("categoriesService")
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    private CategoriesMapper categoriesMapper;
    //查询所有鲜花种类
    @Override
    public List<Category> queryCategories() {
        return categoriesMapper.selectAll();
    }
}
