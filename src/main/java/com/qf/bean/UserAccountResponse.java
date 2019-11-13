package com.qf.bean;

import lombok.Data;

import java.util.List;

/**
 * @author 张正
 * @version 1.0
 * @date 2019/11/13 11:33
 */
@Data
public class UserAccountResponse {
    private List<UserAccount> userAccounts;
    private Long total;
}
