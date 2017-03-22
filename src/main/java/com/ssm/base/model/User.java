package com.ssm.base.model;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by ReedMi on 2017/3/22.
 */
@Getter
@Setter
@TableName(value = "tb_user")
public class User {
    private Long id;
    private String name;
    private String age;
    private Date birthDay;
}
