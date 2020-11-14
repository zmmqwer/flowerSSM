package com.zmm.ssm.mapper;

import com.zmm.ssm.bean.Flower;
import com.zmm.ssm.bean.QueryVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface FlowerMapper extends Mapper<Flower> {
    //列表查询、分页查询、多条件复杂查询
    List<Map<String, String>> list(QueryVo queryVo);


}
