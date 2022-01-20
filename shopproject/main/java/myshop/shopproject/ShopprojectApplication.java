package myshop.shopproject;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.management.MXBean;
import javax.persistence.EntityManager;

@SpringBootApplication
public class ShopprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopprojectApplication.class, args);
	}

	// JPA QueryFactory Bean 등록
//	@Bean
//	JPAQueryFactory jpaQueryFactory(EntityManager em) {
//		return new JPAQueryFactory(em);
//	}

}
