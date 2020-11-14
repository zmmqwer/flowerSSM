package com.zmm.ssm.controller;

import com.zmm.ssm.bean.Category;
import com.zmm.ssm.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ProjectName: ssm
 * @Package: com.zmm.ssm.controller
 * @Description: java类作用描述
 * @Author: 周铭明
 * @CreateDate: 2020/11/11 0011 10:18
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class CategoryController {
    @Autowired
    private CategoriesService categoriesService;
    //查询所有鲜花种类
    @RequestMapping("/queryCategories")
    @ResponseBody
    public List<Category> queryCategories(){
        return categoriesService.queryCategories();

    }
}
