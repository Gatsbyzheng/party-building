package cn.cdcyy.partybuilding.webkanban;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "cn.cdcyy.partybuilding.dao.repository")
@EntityScan(basePackages = "cn.cdcyy.partybuilding.dao.entity")
public class WebKanbanApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebKanbanApplication.class, args);
	}

}
