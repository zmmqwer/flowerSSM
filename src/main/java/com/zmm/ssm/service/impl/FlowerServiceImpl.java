package com.zmm.ssm.service.impl;

import com.zmm.ssm.bean.Flower;
import com.zmm.ssm.bean.QueryVo;
import com.zmm.ssm.mapper.FlowerMapper;
import com.zmm.ssm.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: ssm
 * @Package: com.zmm.ssm.service.impl
 * @Description: java类作用描述
 * @Author: 周铭明
 * @CreateDate: 2020/11/10 0010 20:50
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service("flowerService")
public class FlowerServiceImpl implements FlowerService {
    @Autowired
    private FlowerMapper flowerMapper;
    //列表查询、分页查询、多条件复杂查询
    @Override
    public List<Map<String, String>> list(QueryVo queryVo) {
        return flowerMapper.list(queryVo);
    }
    //添加或者修改鲜花
    @Override
    public void addOrUpdateFlower(Flower flower) {
        //判断 如果选中那个勾选框 fid 就是为修改 不然为添加
        //添加的时候flower没有fid，修改的时候有fid
        if(flower.getFid()!=null && !"".equals(flower.getFid())){
            //修改鲜花
            flowerMapper.updateByPrimaryKeySelective(flower);
        }else {
            //添加鲜花
            flowerMapper.insertSelective(flower);
        }
    }
    //根据主键查询鲜花
    @Override
    public Flower queryByFid(String fid) {
        return flowerMapper.selectByPrimaryKey(fid);
    }

    @Override
    public void deleteBatch(String fids) {
        //将勾中的主键为数组拼接成字符串
        String[] split = fids.split(",");
        for (String fid : split) {
            flowerMapper.deleteByPrimaryKey(fid);
        }

    }
}
