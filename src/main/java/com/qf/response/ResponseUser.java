package com.qf.response;

import lombok.Data;

import java.util.List;

@Data
public class ResponseUser<T> {
    private List<T> list;
    private Long total;
}
