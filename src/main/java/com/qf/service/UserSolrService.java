package com.qf.service;


import com.qf.bean.PageBean;
import com.qf.bean.UserAccountResponse;

/**
 * @author 张正
 * @version 1.0
 * @date 2019/11/13 11:25
 */
public interface UserSolrService {
    String dataIntoSolrFromDb();
    UserAccountResponse queryUserAccountsByPage(PageBean pageBean);
}
