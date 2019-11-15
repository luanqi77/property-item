package com.qf.utils;

import com.qf.domain.User;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class TelMessageUtils {
    @Autowired
    private ZhenziSmsClient client;
    public String sendMessage(String tel) {
        String result = null;
        try {
            String checkCOde = randomCode();
            result = client.send(tel, "您的验证码为"+checkCOde+",验证码在五分钟内有效。");
            System.out.println("验证码是"+checkCOde);
            return checkCOde;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String warnMessage(List<User> users){
        for (User user : users) {
            try {
                String message=user.getRealname()+"，您的余额不足，请及时充值物业管理费用";
                //client.send(user.getTel(),message);
                System.out.println(user.getRealname()+"余额不足,电话："+user.getTel());
            } catch (Exception e) {
                e.printStackTrace();
                return "fail";
            }
        }
        return "success";
    }

    public static String randomCode(){
        Random random=new Random();
        StringBuffer st=new StringBuffer();
        for (int i=0;i<6;i++){
            int c=random.nextInt(9);
            st.append(c);
        }
        return st.toString();
    }

}
