package com.cq.scheduler.server.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 客户经理 Mapper
 */
public interface ProductMapper {

    // 根据用户名 or 手机号 or 邮箱查询用户信息
    @Select("select id from t_product")
    List<String> selectAllProductId();
}
