package com.spring.site.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Board {
    private int no;
    private String title;
    private String content;
    private String file;
    private int writer;
    private Date date;

}
