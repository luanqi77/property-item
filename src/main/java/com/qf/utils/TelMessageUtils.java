package com.qf.utils;

import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class TelMessageUtils {
    public String sendMessage(String tel) {
        ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com", "103150", "092e748f-80cf-4aab-98b6-67e91ce7742c");
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
