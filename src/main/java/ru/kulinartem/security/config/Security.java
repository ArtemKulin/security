package ru.kulinartem.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kulinartem.security.config.handler.SuccessHandler;


@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final SuccessHandler successHandler;

    @Autowired
    public Security(@Qualifier("userDetailServiceImpl") UserDetailsService userDetailsService
            , SuccessHandler successHandler) {
        this.userDetailsService = userDetailsService;
        this.successHandler = successHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
                .antMatchers("/user").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
                .antMatchers("/new").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/edit").access("hasRole('ROLE_ADMIN')")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .permitAll()
                .usernameParameter("j_username")
                .passwordParameter("j_password");
                //.successHandler(successHandler);
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
