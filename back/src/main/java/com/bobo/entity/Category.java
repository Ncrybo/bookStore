package com.bobo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Category implements Serializable {
    private Integer id;
    private String name;
}
