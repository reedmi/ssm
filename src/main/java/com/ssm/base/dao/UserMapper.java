package com.ssm.base.dao;

import com.ssm.base.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by ReedMi on 2017/3/22.
 */
public interface UserMapper {

    @Select("select id, name, age, birth_day from tb_user;")
    public List<User> findAll();

    @Select("select id, name, age, birth_day from tb_user where id = #{id}")
    public User findById(Long id);

    @Delete("delete from tb_user where id = #{id}")
    public void delete(Long id);
}
