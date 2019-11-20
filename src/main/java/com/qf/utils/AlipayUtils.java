package com.qf.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.qf.config.AlipayConfig;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Component
public class AlipayUtils {

    public String pay() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl,AlipayConfig.app_id,AlipayConfig.merchant_private_key,"json","utf-8",AlipayConfig.alipay_public_key,"RSA2");
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = formatter.format(new Date());
        String out_trade_no=format+randomCode();
        String timeout_express = "2m";
        System.out.println(out_trade_no);
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\""+out_trade_no+"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":1000," +
                "    \"subject\":\"物业费\"," +
                "    \"body\":\"物业费\"," +
                "    \"timeout_express\":\""+ timeout_express +"\" ,"+
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088102179143143\"" +
                "    }"+
                "  }");
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        String form="";
        form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        return form;
    }

    public static String randomCode(){
        Random random=new Random();
        StringBuffer str=new StringBuffer();
        for (int i=0;i<6;i++){
            int i1 = random.nextInt(9);
            str.append(i1);
        }
        return str.toString();
    }
}
