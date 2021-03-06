package com.elevysi.site.auth.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class AppConfig extends ResourceServerConfigurerAdapter{
	
//	private static final String RESOURCE_ID = "my_rest_api";
//    
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        resources.resourceId(RESOURCE_ID).stateless(false);
//    }
	
	
	@Bean
	protected ResourceServerConfiguration adminResources(){

		ResourceServerConfiguration resource = new ResourceServerConfiguration() {	
			// Switch off the Spring Boot @Autowired configurers
			public void setConfigurers(List<ResourceServerConfigurer> configurers) {
				super.setConfigurers(configurers);
			}
		};

		resource.setConfigurers(Arrays.<ResourceServerConfigurer> asList(new ResourceServerConfigurerAdapter() {

			@Override
			public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
				resources.resourceId("oauth2/admin");
			}

			@Override
			public void configure(HttpSecurity http) throws Exception {
				http.antMatcher("/admin/**").authorizeRequests().anyRequest()
						.access("#oauth2.hasScope('read')");
			}

		}));
		resource.setOrder(9);

		return resource;

	}
	
	@Bean
	protected ResourceServerConfiguration otherResources() {

		ResourceServerConfiguration resource = new ResourceServerConfiguration() {
			// Switch off the Spring Boot @Autowired configurers
			public void setConfigurers(List<ResourceServerConfigurer> configurers) {
				super.setConfigurers(configurers);
			}
		};

		resource.setConfigurers(Arrays.<ResourceServerConfigurer> asList(new ResourceServerConfigurerAdapter() {

			@Override
			public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
				resources.resourceId("oauth2/other");
			}

			@Override
			public void configure(HttpSecurity http) throws Exception {
				http.authorizeRequests().anyRequest().access("#oauth2.hasScope('trust')");
				
//				http.requestMatchers().antMatchers("/api/**").and().authorizeRequests()
//                .antMatchers("/api/**").authenticated();
			}
		}));
		resource.setOrder(10);

		return resource;

	}
}
