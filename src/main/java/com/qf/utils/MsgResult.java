package com.qf.utils;

import lombok.Data;

@Data
public class MsgResult {
    private String status;
    private String message;
    private Object data;
}
