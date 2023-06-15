package priv.peixinyi.tt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用启动
 *
 * @author peixinyi
 */
@SpringBootApplication
@MapperScan("priv.peixinyi.tt.mapper")
public class ApplicationRun {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class, args);
    }

}
