package com.sunyue.test.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class TestUser implements Serializable {
    private String id;
    private String name;
    private Integer age;
    private Date bir;
}
