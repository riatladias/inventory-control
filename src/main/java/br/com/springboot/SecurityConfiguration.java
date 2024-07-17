package br.com.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration // Indica que essa classe é uma classe de configuração
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    // Método de criptografia de senha
    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Método de autenticação de usuário
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // Usuários em memória
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("12345"))
                .roles("USUARIO")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("12345"))
                .roles("USUARIO", "ADMININISTRADOR")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    // CONFIGURAÇÕES DAS PERMISSÕES DE ACESSO DO GRUPO
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                        .requestMatchers(HttpMethod.GET, "/nota-entrada").hasRole("ADMININISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/nota-saida").hasRole("ADMININISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/estoque").hasRole("ADMININISTRADOR")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form // Especificação do login
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout // após realizar o logout, será redirecionado para tela de login
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login")
                );
        return http.build();
    }
}
