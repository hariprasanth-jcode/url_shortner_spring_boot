package com.url_shortner.url_shortner_project.entity;


import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class Base62Converter {

    private static final String Base62="0123456789qwertyiopasdfghjklzxcvbnmQAZXSWEDCVFRTGBNHYUJMKIOLP";

    private static final int Base=Base62.length();//62

    private static final SecureRandom random=new SecureRandom();


    public String generateRandomCode(int length){// 7
        StringBuilder sb=new StringBuilder(length);
        for(int i=0;i<length;i++){//7
            sb.append(Base62.charAt(random.nextInt(Base)));// 62
        }
        return sb.toString();
    }



}
