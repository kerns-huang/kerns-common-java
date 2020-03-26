package com.xd.web.beans;

import lombok.Data;

public class BeanUtilsTest {

    public static void main(String[] args){
        User u1=new User();
        u1.setId(123);
        u1.setAddress("address");
        u1.setName("care");
        User u2=new User();
        u2.setId(123);
        u2.setName("care2");
        BeanUtils.copyNotNullProperties(u2,u1);
        System.out.println("care2".equals(u1.getName()));
        System.out.println("address".equals(u1.getAddress()));
    }

    @Data
    static class User{

        private Integer id;

        private String name;

        private String address;

    }
}