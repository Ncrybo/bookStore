package com.bobo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Book implements Serializable {
    private Integer id;
    private String name;
    private String author;
    private String publisher;
    private Float price;
    private String img;
    private String description;
    private Integer category;
    private Integer amount;
}
