package com.zmm.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zmm.ssm.bean.Flower;
import com.zmm.ssm.bean.QueryVo;
import com.zmm.ssm.service.FlowerService;
import com.zmm.ssm.util.PageHelpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: ssm
 * @Package: com.zmm.ssm.controller
 * @Description: java类作用描述
 * @Author: 周铭明
 * @CreateDate: 2020/11/10 0010 20:48
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class FlowerController {
    //定义一个静态常量值
    private final static int pageSize=4;
    @Autowired
    private FlowerService flowerService;
    //列表查询、分页查询、多条件复杂查询
    @RequestMapping("index")
    public String index(Model model, @RequestParam(defaultValue = "1")int page, QueryVo queryVo){
        //等同于limit a,b  调用这个方法PageHelper.startPage
        PageHelper.startPage(page,pageSize);
        //创建列表查询、分页查询、多条件复杂查询的方法 list 传入queryVo这个自定义的对象
        //返回list集合 泛型为Map<String,String>
        List<Map<String,String>> flowers =flowerService.list(queryVo);
        //new PageInfo  这个分页插件对象 将查询到的花的信息放到这个分页插件中 返回集合 pageinfo
        PageInfo<Map<String, String>> pageInfo = new PageInfo<>(flowers);
        //Map: 讲前台所有的查询条件封装到map中 为了解决点击页码查询丢失这种情况
        Map<String, Object> params = new HashMap<>();
        //往集合中添加查询条件
        params.put("cid",queryVo.getCid());//cid 花的种类
        params.put("fname",queryVo.getFname());//fname 花的名字
        params.put("start",queryVo.getStart());//start 起始价格
        params.put("end",queryVo.getEnd());//end 结束价格
        //向前台返回页码字符串
        /**
         * 参数1:url:每次点击页码调用后台的地址
         * 参数2:pageInfo:数据
         * 参数3:paramMap:用于查询的时候封装查询参数
         */
        String pages = PageHelpUtil.bootStrapPage("/ssm/index",pageInfo,params);
        //往前台传数据
        model.addAttribute("flowers",pageInfo.getList());
        model.addAttribute("pages",pages);
        //查询条件回显
        model.addAttribute("queryVo",queryVo);
        return "index";
    }
    //添加或者修改鲜花
    @RequestMapping("/addOrUpdateFlower")
    public String addOrUpdateFlower(Flower flower){
        flowerService.addOrUpdateFlower(flower);
        return "forward:/index";//因为要转发到当前页 所以要加forward
    }
    //根据主键查询鲜花
    @RequestMapping("/queryByFid")
    @ResponseBody
    public Flower queryByFid(String fid){
        Flower flower=flowerService.queryByFid(fid);
        return flower;
    }
    //单删和批量删除
    @RequestMapping("/deleteBatch")
    public String deleteBatch(String fids){
        flowerService.deleteBatch(fids);
        return "forward:/index";//因为要转发到当前页 所以要加forward
    }



}
