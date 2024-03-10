package com.codegym.maccenter.configuration;

import com.codegym.maccenter.service.impl.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Autowired
	CustomUserDetailService customUserDetailService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				//permit all url
				.authorizeRequests().antMatchers(
						"/",
						"/shop",
						"/register",
						"/login")
				.permitAll()

				.antMatchers("/images/**").authenticated()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/home/**").hasRole("USER")

				.anyRequest()
				.authenticated()


				//check loginForm
				.and()
				.formLogin()
				.loginPage("/login")
				.usernameParameter("email")
				.passwordParameter("password")
				.defaultSuccessUrl("/")
				.failureUrl("/login?error= true")

				//when you logout
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login")
				.invalidateHttpSession(true)
				.deleteCookies("cookiesTien")

				//declare exeption
				.and()
				.exceptionHandling()
				.accessDeniedPage("/403")

				//thymeleaf already has token, so disable csrf
				.and()
				.csrf()
				.disable();

		http.headers()
				.frameOptions()
				.disable();
	}//config authenication & authorization

	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}//ma hoa password

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService).passwordEncoder(bCryptPasswordEncoder());
	}//chon class quan li thong tin account

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
				"/resources/**",
				"/static/**",
				"/images/**",
				"/productImages/**",
				"/css/**",
				"/Accessories/**",
				"/Adv_Images/**",
				"/App_Images/**",
				"/Audio/**",
				"/Cable/**",
				"/icon/**",
				"/MacFamily/**",
				"/Storage/**",
				"/adv_Images/**",

				"/js/**");
	}
}
