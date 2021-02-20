package cn.shopping.lstsm_kgc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("cn.shopping.lstsm_kgc.mapper")
public class LstsmKgcApplication {

    public static void main(String[] args) {
        SpringApplication.run(LstsmKgcApplication.class, args);
    }

}
