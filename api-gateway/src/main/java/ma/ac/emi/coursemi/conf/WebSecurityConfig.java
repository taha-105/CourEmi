package ma.ac.emi.coursemi.conf;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	@Autowired
	private UserDetailsService jwtUserDetailsService;
	@Autowired
	private AuthenticationManager authentificationManager;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		//httpSecurity.csrf().disable();
		//httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//httpSecurity.authorizeRequests().antMatchers("/categories/**").permitAll();
		//httpSecurity.authorizeRequests().antMatchers("/login/**").permitAll();
		//httpSecurity.authorizeRequests().anyRequest().authenticated();
	    //httpSecurity.addFilter(new JWTAuthentificationFilter(authenticationManager()));
	    //httpSecurity.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		// We don't need CSRF for this example
				httpSecurity.csrf().disable();
				 httpSecurity.authorizeRequests().antMatchers("/login").permitAll();
				 //httpSecurity.authorizeRequests().antMatchers("/categories/**").permitAll();
				 //httpSecurity.authorizeRequests().antMatchers("/users/**").permitAll();
				httpSecurity.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);		
	}
	
}