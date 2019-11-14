package com.qf.service.impl;

import com.qf.bean.PageBean;
import com.qf.bean.UserAccount;
import com.qf.bean.UserAccountResponse;
import com.qf.service.BackstageService;
import com.qf.service.UserSolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 张正
 * @version 1.0
 * @date 2019/11/13 11:41
 */
@Service
public class UserSolrServiceImpl implements UserSolrService {
    @Autowired
    private BackstageService backstageService;
    @Autowired
    private HttpSolrServer solrServer;
    @Override
    public String dataIntoSolrFromDb() {
//        查询数据库
        List<UserAccount> all = backstageService.findUserAccount();
//        将每条数据转成文档并写入solr索引库中
        try {
            for (UserAccount userAccount : all) {
//                创建文档对象
                SolrInputDocument document = new SolrInputDocument();
//                给文档对象的属性赋值
                document.setField("id", userAccount.getUserId()+"");
                document.setField("houseId", userAccount.getHouseId());
                document.setField("realName", userAccount.getRealName());
                document.setField("tel", userAccount.getTel());
                document.setField("build", userAccount.getBuild());
                document.setField("money", userAccount.getMoney());
//                将文档写入solr索引库中

                solrServer.add(document);

            }
//            提交对solr的操作（使生效）
            solrServer.commit();
            UserAccountResponse userAccountResponse = new UserAccountResponse();
            userAccountResponse.setTotal((long)all.size());
            userAccountResponse.setUserAccounts(all);
            System.out.println("导入索引库");
            return "success";
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public UserAccountResponse queryUserAccountsByPage(PageBean pageBean) {
        List<UserAccount> userAccounts=new ArrayList<>();
        SolrQuery solrQuery = new SolrQuery();
        if (pageBean.getKeywords()==null||pageBean.getKeywords()==""){
            pageBean.setKeywords("*");
        }
        solrQuery.setQuery("account_keywords:"+pageBean.getKeywords());
        solrQuery.setStart((pageBean.getCurrentPage()-1)*pageBean.getPageSize());
        solrQuery.setRows(pageBean.getPageSize());
        try {
            QueryResponse query = solrServer.query(solrQuery);
            SolrDocumentList documents = query.getResults();
            Long total=documents.getNumFound();
            for (SolrDocument document : documents) {
                UserAccount userAccount = new UserAccount();
                userAccount.setUserId(Integer.parseInt((String) document.getFieldValue("id")));
                userAccount.setHouseId((Integer)document.getFieldValue("houseId"));
                userAccount.setRealName((String) document.getFieldValue("realName"));
                userAccount.setTel((String) document.getFieldValue("tel"));
                userAccount.setBuild((Integer) document.getFieldValue("build"));
                userAccount.setMoney((Double) document.getFieldValue("money"));
                userAccounts.add(userAccount);
            }
            UserAccountResponse userAccountResponse = new UserAccountResponse();
            userAccountResponse.setUserAccounts(userAccounts);
            userAccountResponse.setTotal(total);
            //从索引中获得
            return userAccountResponse;

        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
