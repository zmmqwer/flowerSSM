package com.zmm.ssm.bean;

/**
 * @ProjectName: ssm
 * @Package: com.zmm.ssm.bean
 * @Description: java类作用描述
 * @Author: 周铭明
 * @CreateDate: 2020/11/10 0010 20:59
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
//自定义pojo 用于1、查询条件复杂(可能是多个查询条件，可能是多表的查询)2.很少变动

public class QueryVo {
    private String fname;//花的名字
    private String start;//起始价格
    private String end;//结束价格
    private String cid;//编号牌

    @Override
    public String toString() {
        return "QueryVo{" +
                "fname='" + fname + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", cid='" + cid + '\'' +
                '}';
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
