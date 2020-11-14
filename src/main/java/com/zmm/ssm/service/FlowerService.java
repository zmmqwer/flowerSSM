package com.zmm.ssm.service;

import com.zmm.ssm.bean.Flower;
import com.zmm.ssm.bean.QueryVo;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: ssm
 * @Package: com.zmm.ssm.service
 * @Description: java类作用描述
 * @Author: 周铭明
 * @CreateDate: 2020/11/10 0010 20:49
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface FlowerService {
    //列表查询、分页查询、多条件复杂查询
    List<Map<String,String>> list(QueryVo queryVo);

    void addOrUpdateFlower(Flower flower);

    Flower queryByFid(String fid);

    void deleteBatch(String fids);
}
