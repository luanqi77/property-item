package com.qf;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.DecimalFormat;
import java.text.Format;


public class testData {
    @Test
    public void test(){
        Integer i=1;
        Integer j=2;
        Format decimalFormat = new DecimalFormat("0.00");
        String format = decimalFormat.format((double) i / j);
        System.out.println(format);
    }

}
