package com.example.sem8.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityJBDC {
    @Autowired PasswordEncoder passwordEncoder;
    @Autowired private DataSource dataSource;

    @Bean
    UserDetailsService userDetailsService() {
        JdbcUserDetailsManager manager= new JdbcUserDetailsManager(dataSource);
        manager.setRolePrefix("ROLE_");
        manager.setUsersByUsernameQuery("select nombre as username, "
                + "clave as password, estado as enabled "
                + "from usuario as users where nombre = ?");
        manager.setAuthoritiesByUsernameQuery("select nombre as username, rol as authority "
                + "from usuario as authorities where nombre = ?");
        return manager;
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests((request)->
                        request
                                .requestMatchers("/css/**").permitAll()
                                .requestMatchers("/js/**").permitAll()
                                .requestMatchers("/poblar").permitAll()
                                .requestMatchers("/img/**").permitAll()
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/usuario/denegado").permitAll()
                                .requestMatchers("/*/index").permitAll()
                                .requestMatchers("/*/nuevo").hasRole("ADMIN")
                                .requestMatchers("/*/editar**").hasRole("ADMIN")
                                .requestMatchers("/*/eliminar**").hasRole("ADMIN")
                                .requestMatchers("/*/matricular**").hasAnyRole("ADMIN","USER")
                                .anyRequest().authenticated()
                                )
                .exceptionHandling((exceptionHandling)->
                        exceptionHandling
                                .accessDeniedPage("/usuario/denegado"))
                .formLogin(form->
                        form
                                .permitAll()
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .loginPage("/usuario/login")
                                .failureUrl("/usuario/denegado")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/",true)
                )

                .logout(logout->logout.permitAll().logoutSuccessUrl("/login"))
                .build();

    }


}

