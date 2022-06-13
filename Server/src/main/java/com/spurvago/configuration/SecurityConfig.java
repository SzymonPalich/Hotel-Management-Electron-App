package com.spurvago.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spurvago.server.security.JsonObjectAuthenticationFilter;
import com.spurvago.server.security.JwtAuthorizationFilter;
import com.spurvago.server.security.RestAuthenticationFailureHandler;
import com.spurvago.server.security.RestAuthenticationSuccessHandler;
import com.spurvago.server.employee.EmployeeDetailsServiceImplementation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;
    private final ObjectMapper objectMapper;
    private final RestAuthenticationFailureHandler failureHandler;
    private final RestAuthenticationSuccessHandler successHandler;
    private final String secret;

    public SecurityConfig(DataSource dataSource, ObjectMapper objectMapper, RestAuthenticationFailureHandler failureHandler,
                          RestAuthenticationSuccessHandler successHandler,
                          @Value("${jwt.secret}") String secret) {
        this.dataSource = dataSource;
        this.objectMapper = objectMapper;
        this.failureHandler = failureHandler;
        this.successHandler = successHandler;
        this.secret = secret;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new EmployeeDetailsServiceImplementation();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                // TODO: Usunąć, przy implementacji uprawnień
//                .antMatchers("/**").permitAll()
                .antMatchers("/api/auth/login").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("webjars/**").permitAll()
                .antMatchers("/swagger-resources").permitAll()
                .antMatchers("h2-console/**").permitAll()
                .antMatchers("configuration/ui").permitAll()
                .antMatchers("configuration/security").permitAll()
                .antMatchers("/swagger-ui/index.html").permitAll()
                .antMatchers("/swagger-ui/").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/swagger-resources/configuration/ui").permitAll()
                .antMatchers("/swagger-resources/configuration/**").permitAll()
//                .antMatchers("/api/**").hasRole("MANAGER")
                .antMatchers("/api/accommodation/**").hasAnyRole("MANAGER", "RECEPTIONIST")
                .antMatchers("/api/accommodation").hasAnyRole("MANAGER", "RECEPTIONIST")
                .antMatchers("/api/client/**").hasAnyRole("MANAGER", "RECEPTIONIST")
                .antMatchers("/api/client").hasAnyRole("MANAGER", "RECEPTIONIST")
                .antMatchers("/api/employee/**").hasAnyRole("MANAGER")
                .antMatchers("/api/employee").hasAnyRole("MANAGER")
                .antMatchers("/api/maid_ticket/**").hasAnyRole("MANAGER", "MAID", "RECEPTIONIST")
                .antMatchers("/api/maid_ticket?**").hasAnyRole("MANAGER", "MAID", "RECEPTIONIST")
                .antMatchers("/api/maintenance_ticket/**").hasAnyRole("MANAGER", "TECHNICIAN", "RECEPTIONIST")
                .antMatchers("/api/maintenance_ticket").hasAnyRole("MANAGER", "TECHNICIAN", "RECEPTIONIST")
                .antMatchers("/api/product/**").hasAnyRole("MANAGER", "MAID")
                .antMatchers("/api/product").hasAnyRole("MANAGER", "MAID")
                .antMatchers("/api/room/**").hasAnyRole("MANAGER", "RECEPTIONIST", "TECHNICIAN")
                .antMatchers("/api/room").hasAnyRole("MANAGER", "RECEPTIONIST", "TECHNICIAN")
                .antMatchers("/api/room_type/**").hasAnyRole("MANAGER", "RECEPTIONIST")
                .antMatchers("/api/room_type").hasAnyRole("MANAGER", "RECEPTIONIST")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(authenticationFilter())
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userDetailsService(), secret))
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint((HttpStatus.UNAUTHORIZED)));
        http.cors().configurationSource(x->{
           var cors = new CorsConfiguration();
           cors.setAllowedOrigins(List.of("http://localhost:8080"));
           cors.setAllowedMethods(List.of("GET", "POST", "DELETE", "PUT"));
           cors.setAllowedHeaders(List.of("*"));
           cors.setAllowCredentials(Boolean.TRUE);
           cors.setExposedHeaders(List.of("Authorization"));
           return cors;
        });
    }

    public JsonObjectAuthenticationFilter authenticationFilter() throws Exception {
        JsonObjectAuthenticationFilter authenticationFilter = new JsonObjectAuthenticationFilter(objectMapper);
        authenticationFilter.setFilterProcessesUrl("/api/auth/login");
        authenticationFilter.setAuthenticationSuccessHandler(successHandler);
        authenticationFilter.setAuthenticationFailureHandler(failureHandler);
        authenticationFilter.setAuthenticationManager(super.authenticationManager());
        return authenticationFilter;
    }

    @Bean
    public UserDetailsManager userDetailsManager() {
        return new JdbcUserDetailsManager(dataSource);
    }
}