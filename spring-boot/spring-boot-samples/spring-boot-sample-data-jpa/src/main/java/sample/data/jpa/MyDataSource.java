package sample.data.jpa;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyDataSource {
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	
	@Value("${jdbc.username}")
	private String username;
	
	@Value("${jdbc.password}")
	private String password;
	
	@Bean
	public DataSource getDataSource(){
		DataSourceBuilder builder=DataSourceBuilder.create();
		builder.url("jdbc:sqlite:"+MyDataSource.class.getResource("/dbdemos.db3").getPath());
		builder.driverClassName(driverClassName);
		builder.username(username);
		builder.password(password);
		return builder.build();
		
	}

}
