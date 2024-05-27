package org.myproject.test_security.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Cette méthode permet de configurer les chaines de filtres de securité HTTP
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http
               .authorizeHttpRequests(
                       auth -> {
                           auth.requestMatchers("/home").permitAll(); //Autorise l'accès à la page /home à tout le monde
                           auth.requestMatchers("/admin").hasRole("ADMIN"); //Autorise l'accès à la page /admin et avec le role ADMIN
                           auth.requestMatchers("/user").hasRole("USER");   //Autorise l'accès à la page /user et avec le role USER
                           auth.anyRequest().authenticated(); //Toutes les autres requêtes doivent être authentifiés
                       })
               .formLogin(Customizer.withDefaults()).build();
    }

    @Bean
    /**
     * Cette méthode permet de configurer les utilisateurs en memoire
     * @return UserDetailsService
     */
    public UserDetailsService users() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN", "USER")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }


    @Bean
    /**
     * Cette méthode permet de configurer le cryptage du mot de passe
     * @return BCryptPasswordEncoder
     */
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

