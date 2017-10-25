package com.management.product.config;

import com.management.product.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.management.product.service")
@PropertySource("classpath:security.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${request.admin}")
    private String requestAdmin;

    @Value("${request.login}")
    private String requestLogin;

    @Value("${request.access-denied-page}")
    private String requestAccessDeniedPage;

    @Value("${request.default-success}")
    /*private*/ String requestDefaultSuccess;

    @Value("${request.default-success.always}")
    private boolean alwaysUseDefaultSuccess;

    @Value("${parameter.username}")
    private String parameterUsername;

    @Value("${parameter.password}")
    private String parameterPassword;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .logout()
                .invalidateHttpSession(false)
                .and()
                .authorizeRequests()
                .antMatchers(this.requestAdmin)
                .hasRole(UserRole.ADMIN.name())
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage(this.requestLogin)
                .usernameParameter(this.parameterUsername)
                .passwordParameter(this.parameterPassword)
                .defaultSuccessUrl(this.requestDefaultSuccess, this.alwaysUseDefaultSuccess)
                .and()
                .exceptionHandling()
                .accessDeniedPage(this.requestAccessDeniedPage)
                .and()
                .csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
    }
}
