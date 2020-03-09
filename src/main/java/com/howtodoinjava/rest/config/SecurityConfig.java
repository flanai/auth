package com.howtodoinjava.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {

        http.authorizeRequests()
                .antMatchers("/flow").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();
        //.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").invalidateHttpSession(true)        // set invalidation state when logout
        //      .deleteCookies("JSESSIONID") ;

        /*http
         .csrf().disable()
         .authorizeRequests().anyRequest().authenticated()
         .and()
         .httpBasic();*/

        // !!!!!!!!!!!!!!!! pour tester les ws :
        // curl http://localhost:8080 -u admin:pass
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception
    {
        auth.inMemoryAuthentication().withUser("admin").password("{noop}pass").roles("USER");
    }


    /*

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("pass").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("vaquar").password("pass").roles("USER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/api/**").hasAnyRole("ADMIN","USER").and().httpBasic();//.and().headers().disable();
        //.and().formLogin();
    }
}
     */
}