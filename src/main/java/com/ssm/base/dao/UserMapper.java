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

    List<User> findAll();

    User findById(Long id);

    Integer delete(Long id);

    List<User> findIn(@Param("ids") String ids);

    List<User> findBirthDayBetween(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
