package cn.seven.dailypusher;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wtk
 */
@MapperScan(value = "cn.seven", annotationClass = Mapper.class)
@ComponentScan("cn.seven") // 扫描其他子模块的组件
@SpringBootApplication()
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}