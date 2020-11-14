package com.zmm.ssm.service;

import com.zmm.ssm.bean.Category;

import java.util.List;

/**
 * @ProjectName: ssm
 * @Package: com.zmm.ssm.service
 * @Description: java类作用描述
 * @Author: 周铭明
 * @CreateDate: 2020/11/11 0011 10:20
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface CategoriesService {
    List<Category> queryCategories();
}
