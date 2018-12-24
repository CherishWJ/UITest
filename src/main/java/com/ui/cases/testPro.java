package com.ui.cases;
import com.ui.util.PropertiesUtil;


public class testPro {
    public static void main(String[] args) {
        String url = PropertiesUtil.getRootUrl();
        String filePath=PropertiesUtil.getCaseFilePath();
        System.out.println(url);
        System.out.println(filePath);
    }
}
