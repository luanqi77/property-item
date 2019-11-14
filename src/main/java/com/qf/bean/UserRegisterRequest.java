package com.qf.bean;

import com.qf.domain.User;
import lombok.Data;

/**
 * @author 张正
 * @version 1.0
 * @date 2019/11/12 15:33
 */
@Data
public class UserRegisterRequest {
    private User user;
    private int houseId;
}
