package com.hetangyuese.netty;

import com.hetangyuese.netty.controller.HtServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: netty-root
 * @description: 启动类
 * @author: hetangyuese
 * @create: 2019-10-28 16:47
 **/
@SpringBootApplication
public class HetangyueseApplication implements CommandLineRunner {

    @Autowired
    private HtServer htServer;

    public static void main(String[] args) {
        SpringApplication.run(HetangyueseApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        htServer.start(9000);
    }
}
