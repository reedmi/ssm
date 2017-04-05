package com.ssm.base.dao;

import com.ssm.base.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ReedMi on 2017/3/22.
 */
public interface UserMapper {

    @Select("select id, name, age, birth_day from tb_user;")
    List<User> findAll();

    @Select("select id, name, age, birth_day from tb_user where id = #{id}")
    User findById(Long id);

    @Delete("delete from tb_user where id = #{id}")
    Integer delete(Long id);

    @Select("select * from tb_user where id in(${ids})")
    List<User> findIn(@Param("ids") String ids);

}
