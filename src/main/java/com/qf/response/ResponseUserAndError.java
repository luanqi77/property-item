package com.qf.response;

import com.qf.domain.User;
import lombok.Data;

/**
 * @PackageName:com.qf.response;
 * @ClassName:ResponseUserAndError;
 * @author:马浩雲
 * @date2019/11/1310:26
 */
@Data
public class ResponseUserAndError {
    private User user;
    private String error;
    private String openId;
    private String succeed;
}
