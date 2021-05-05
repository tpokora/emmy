package org.tpokora.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.tpokora.config.security.filter.CustomFilter;
import org.tpokora.persistance.repositories.users.UserRepository;

import static org.tpokora.config.security.SecurityMatchers.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String HOME = "/home";

    final UserDetailsService userDetailsService;
    private EmmyBasicAuthenticationEntryPoint emmyBasicAuthenticationEntryPoint;

    public SecurityConfiguration(UserDetailsService userDetailsService,
                                 EmmyBasicAuthenticationEntryPoint emmyBasicAuthenticationEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.emmyBasicAuthenticationEntryPoint = emmyBasicAuthenticationEntryPoint;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http
                .authorizeRequests()
                .antMatchers(STATIC_FILES_MATCHERS).permitAll()
                .antMatchers(ALL_ACCESS_MATCHERS).permitAll()
                .antMatchers(HttpMethod.GET, SWAGGER_MATCHERS).permitAll()
                .antMatchers(ADMIN_ONLY_MATCHERS).hasRole("ADMIN")
                .and()
                .httpBasic()
                .authenticationEntryPoint(emmyBasicAuthenticationEntryPoint);

        http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}
