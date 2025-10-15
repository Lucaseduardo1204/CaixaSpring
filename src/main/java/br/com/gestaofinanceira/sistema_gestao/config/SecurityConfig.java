package br.com.gestaofinanceira.sistema_gestao.config;

// Note que o import do AntPathRequestMatcher foi removido
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Os outros beans (PasswordEncoder, AuthenticationManager) podem continuar aqui,
    // eles não foram mostrados na sua imagem, mas não precisam mudar.

    // =======================================================================================
    // BEAN DE SEGURANÇA PARA AMBIENTE DE DESENVOLVIMENTO
    // =======================================================================================
    @Bean
    @Profile("dev")
    public SecurityFilterChain devSecurityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("### CARREGANDO CONFIGURAÇÃO DE SEGURANÇA PARA DESENVOLVVIMENTO (DEV) ###");
        http
                .authorizeHttpRequests(authorize -> authorize
                        // Libera TUDO para facilitar os testes
                        .anyRequest().permitAll()
                )
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }

    // =======================================================================================
    // BEAN DE SEGURANÇA PARA AMBIENTE DE PRODUÇÃO
    // =======================================================================================
    @Bean
    @Profile("!dev")
    public SecurityFilterChain productionSecurityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("### CARREGANDO CONFIGURAÇÃO DE SEGURANÇA PARA PRODUÇÃO ###");
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Permissões restritas de produção
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}