package com.ssm.base.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by ReedMi on 2017/3/22.
 */
@Getter
@Setter
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String age;
    private Date birthDay;
}
