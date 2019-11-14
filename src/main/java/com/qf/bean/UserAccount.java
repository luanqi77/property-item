package com.qf.bean;


import lombok.Data;

/**
 * @author 张正
 * @version 1.0
 * @date 2019/11/13 9:46
 */
@Data
public class UserAccount {
    private Integer userId;
    private Integer houseId;
    private String realName;
    private String tel;
    private Integer build;
    private Double money;
}
