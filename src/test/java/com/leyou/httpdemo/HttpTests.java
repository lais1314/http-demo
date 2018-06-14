package com.leyou.httpdemo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leyou.httpdemo.pojo.User;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author: HuYi.Zhang
 * @create: 2018-05-06 09:53
 **/
public class HttpTests {

    CloseableHttpClient httpClient;

    @Before
    public void init() {
        httpClient = HttpClients.createDefault();
    }

    @Test
    public void testGet() throws IOException {
        HttpGet request = new HttpGet("http://www.baidu.com");
        String response = this.httpClient.execute(request, new BasicResponseHandler());
        System.out.println(response);
    }

    @Test
    public void testPost() throws IOException {
        HttpGet request = new HttpGet("http://www.oschina.net/");
        request.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        String response = this.httpClient.execute(request, new BasicResponseHandler());
        System.out.println(response);
    }

    @Test
    public void testGetPojo() throws IOException {
        HttpGet request = new HttpGet("http://localhost:8080/all");
        String response = this.httpClient.execute(request, new BasicResponseHandler());
        System.out.println(response);
    }

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testJson() throws IOException {
        User user = new User();
        user.setAge(12);
        user.setId(1l);
        user.setName("zhangsan");

        String s = mapper.writeValueAsString(Arrays.asList(user,user));
        System.out.println(s);
        List<User> users = mapper.readValue(s, mapper.getTypeFactory().constructCollectionType(List.class, User.class));
        System.out.println(users);

        List<User> users1 = mapper.readValue(s, new TypeReference<List<User>>() {
        });

        System.out.println(users1);

    }


}
