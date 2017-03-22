package com.ssm.base.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by ReedMi on 2017/3/22.
 */
@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private String age;
    private Date birthDay;
}
