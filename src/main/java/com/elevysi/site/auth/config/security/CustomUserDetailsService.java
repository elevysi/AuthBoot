package com.elevysi.site.auth.config.security;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.elevysi.site.auth.entity.User;
import com.elevysi.site.auth.service.UserService;

//@Service
public class CustomUserDetailsService{
//public class CustomUserDetailsService implements UserDetailsService{
	
//	@Autowired
//	private UserService userService;
//	
//	@Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        User user = userService.loadUserByUsername(username);
//        boolean enabled = true;
//        boolean accountNonExpired = true;
//        boolean credentialsNonExpired = true;
//        boolean accountNonLocked = true;
//        
//        
//
//        if (user == null) {
//            // Not found...
//            throw new UsernameNotFoundException(
//                    "User " + username + " not found.");
//        }
//
//        if (user.getRoles() == null || user.getRoles().isEmpty()) {
//            // No Roles assigned to user...
//            throw new UsernameNotFoundException("User not authorized.");
//        }
//
//
//        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
//        for (Role role : account.getRoles()) {
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getCode()));
//        }
//
//        User userDetails = new User(user.getUsername(),
//        		user.getPassword(), true,
//                !account.isExpired(), !account.isCredentialsexpired(),
//                !account.isLocked(), grantedAuthorities);
//
//        return userDetails;
//    }
//	
//	/**
//	 * Converts a numerical role to an equivalent list of roles
//	 * @param role the numerical role
//	 * @return list of roles as as a list of {@link String}
//	 */
//    public List<String> treatRoles(Set<Role> roles) {
//    	List<String> security_roles = new ArrayList<String>();
//    	
//    	for (Role userRole : roles) {
//    		security_roles.add(userRole.getName());
//		}
//    	
//    	return security_roles;
//    }
//    
//    /**
//	 * Wraps {@link String} roles to {@link SimpleGrantedAuthority} objects
//	 * @param roles {@link String} of roles
//	 * @return list of granted authorities
//	 */
//    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//         
//        for (String role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role));
//        }
//        return authorities;
//    }
	
}
