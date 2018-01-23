package com.elevysi.site.auth.config.security.jwt;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

public class JWTOAuth2Config extends AuthorizationServerConfigurerAdapter{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private TokenStore tokenStore;
	
	@Autowired
	private DefaultTokenServices defaultTokenServices;
	
	@Autowired
	private JwtAccessTokenConverter jwtAccessTokenConverter;
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
//		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtTokenEnhancer, ));
		endpoints
			.tokenStore(tokenStore)
			.accessTokenConverter(jwtAccessTokenConverter)
			.userDetailsService(userDetailsService);
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
		clients.inMemory()
		.withClient("elevysioauth")
		.secret("pass")
		.authorizedGrantTypes("refresh_token", "password", "client_credentials")
		.scopes("webclient", "mobileclient");
	}

}
