package es.netmind.banana_invoices.config;

import es.netmind.banana_invoices.jwt.JwtTokenFilter;
import es.netmind.banana_invoices.models.ERole;
import es.netmind.banana_invoices.persistence.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;


@Configuration
@Profile("prod")
@ConditionalOnProperty(name = "security.oauth", havingValue = "true")
public class ApplicationOAuthSecurity {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationOAuthSecurity.class);

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/**/**").authenticated()
                .mvcMatchers("/**/**").hasAuthority("SCOPE_invoices")
                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }
}