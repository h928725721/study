package com.candy;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description : 启动类
 * @author yhit
 * @since 2019-02-26 16:56:36
 **/
@SpringBootApplication(scanBasePackages="com.candy")
public class App implements CommandLineRunner, DisposableBean{

	private static final Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(App.class);
		springApplication.run(args);
	}

	@Override
	public void run(String... args) {
		log.info("服务------>>启动成功");
	}

	@Override
	public void destroy() {
		log.info("服务------>>关闭成功");
	}
}
