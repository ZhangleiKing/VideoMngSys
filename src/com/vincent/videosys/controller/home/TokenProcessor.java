package com.vincent.videosys.controller.home;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import sun.misc.BASE64Encoder;

public class TokenProcessor {

    /*
     *�������ģʽ����֤��Ķ������ڴ���ֻ��һ����
     *1������Ĺ��캯��˽��
     *2���Լ�����һ����Ķ���
     *3�������ṩһ�������ķ�����������Ķ���
     */
    private TokenProcessor(){}
    
    private static final TokenProcessor instance = new TokenProcessor();
    
    /**
     * ������Ķ���
     * @return
     */
    public static TokenProcessor getInstance(){
        return instance;
    }
    
    /**
     * ����Token
     * Token��Nv6RRuGEVvmGjB+jimI/gw==
     * @return
     */
    public String makeToken(){  //checkException
        //  7346734837483  834u938493493849384  43434384
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
        //����ָ��   128λ��   16���ֽ�  md5
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] =  md.digest(token.getBytes());
            //base64����--��������Ʊ��������ַ�   adfsdfsdfsf
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
