package com.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

@Configuration
@EnableRedisHttpSession()
public class RootConfig {
	@Bean
	@Profile("prod")
	public JedisConnectionFactory connectionProdFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName("10.154.95.60");
		jedisConnectionFactory.setPort(6379);
		jedisConnectionFactory.setUsePool(true);
		return jedisConnectionFactory;
	}
	
	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		CookieHttpSessionStrategy strategy = new CookieHttpSessionStrategy();
		strategy.setCookieName("JSESSIONID");
		return strategy;
	}

	@Bean
	public TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(10);
		return scheduler;
	}

}
