package com.intelasers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
@EnableJpaAuditing
//@ImportResource(locations = "classpath:/spring/*.xml")
public class
IntelasersApplication {

	public static void main(String[] args) throws UnknownHostException {
//		SpringApplication.run(IntelasersApplication.class, args);

		ConfigurableApplicationContext application = SpringApplication.run(IntelasersApplication.class, args);
		Environment env=application.getEnvironment();
		String ip= InetAddress.getLocalHost().getHostAddress();
		String port=env.getProperty("server.port");
		String path=env.getProperty("server.servlet.context-path");
		if(StringUtils.isEmpty(path)){
			path="";
		}
		log.info("\n----------------------------------------------------------\n\t" +
				"Application  is running! Access URLs:\n\t" +
				"Local访问网址: \t\thttp://localhost:" + port + path + "\n\t" +
				"External访问网址: \thttp://" + ip + ":" + port + path + "\n\t" +
				"----------------------------------------------------------");
	}
}
