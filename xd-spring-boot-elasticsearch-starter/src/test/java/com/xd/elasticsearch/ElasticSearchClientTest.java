package com.xd.elasticsearch;

import static org.junit.jupiter.api.Assertions.*;

class ElasticSearchClientTest {

    public static void main(String[] args){
        ElasticSearchClient client=new ElasticSearchClient("http://103.207.165.4:9200/_sql?format=json");
        String result= client.post("select id,nickname from user");
        System.out.println(result);
    }
}