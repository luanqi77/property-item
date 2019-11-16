package com.qf.bean;


import lombok.Data;

/**
 * @author 张正
 * @version 1.0
 * @date 2019/11/13 11:39
 */
@Data
public class PageBean {
    private Integer currentPage;
    private Integer pageSize;
    private Integer startIndex;
    private Integer endIndex;
    private String keywords;

}

