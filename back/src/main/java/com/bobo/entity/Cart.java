package com.bobo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Cart implements Serializable {
    private Integer gid;
    private Integer amount;
}
