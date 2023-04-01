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
@ConditionalOnProperty(name = "security.oauth", havingValue = "false")
public class ApplicationSecurity {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationSecurity.class);

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                return userRepo.findByEmail(email)
                        .orElseThrow(
                                () -> new UsernameNotFoundException(
                                        "User " + email + " not found"
                                )
                        );
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig
    ) throws Exception {
        logger.info("Entra authenticationManager!!!!");
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//        http.authenticationProvider(authProvider());

        http.authorizeRequests()
                .antMatchers("/auth/login",
                        "/docs/**",
                        "/users",
                        "/h2-ui/**",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**"
                ).permitAll() // HABILITAR ESPACIOS LIBRES
//                .antMatchers("/**").permitAll() // BARRA LIBRE
//                .antMatchers("/products/**").hasAuthority(ERole.USER.name())
                .antMatchers(HttpMethod.GET, "/**/**").hasAnyAuthority(ERole.USER.name(),ERole.ADMIN.name()) //Para acceder a recibos debe ser USER
                .antMatchers("/**/**").hasAnyAuthority(ERole.ADMIN.name()) //admin puede hacer de todo
                .anyRequest().authenticated();

        http.headers().frameOptions().sameOrigin();

        http.exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                );

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}