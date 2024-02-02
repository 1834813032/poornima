package com.greatLearning.collegeFest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.greatLearning.collegeFest.serviceImpl.UserDetailsServiceImpl;

@SuppressWarnings("deprecation")
@Configuration
/*@EnableWebSecurity*/
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public UserDetailsService getDetailsService() {
		return new  UserDetailsServiceImpl();
	}

    /*private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
*/
   /* @Bean
    public UserDetailsService userDetailsService() {
        org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl jdbcDaoImpl = new org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl();
        jdbcDaoImpl.setDataSource(dataSource);
        return jdbcDaoImpl;
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
   /* public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    .antMatchers("/students").hasAnyRole("USER", "ADMIN")
                    .antMatchers("/students/add").hasAnyRole("USER", "ADMIN")
                    .antMatchers("/students/edit/**").hasRole("ADMIN")
                    .antMatchers("/students/delete/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            )
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/student/list")
                    .loginProcessingUrl("/authenticateTheUser")
                    .permitAll()
            )
            .logout(logout -> logout.permitAll())
            .exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedPage("/access-denied"));

        return http.build();
    }*/
    @Bean
    public AuthenticationProvider getProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(getDetailsService());
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	@Override
	public void configure(AuthenticationManagerBuilder builder) {
		builder.authenticationProvider(getProvider());
	}
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/students/list","/students/add","/students/save")
		.hasAnyAuthority("ADMIN","USER")
		.antMatchers("/students/edit","/students/delete")
		.hasAuthority("ADMIN")
		.anyRequest().authenticated()
		.and().formLogin().loginProcessingUrl("/login")
		.defaultSuccessUrl("/students/list").permitAll()
		.and().logout().logoutSuccessUrl("/login").permitAll()
		.and().exceptionHandling().accessDeniedPage("/students/403")
		.and().csrf().and().cors().disable();
	}
}
