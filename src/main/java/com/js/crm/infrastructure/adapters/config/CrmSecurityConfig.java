package com.js.crm.infrastructure.adapters.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class CrmSecurityConfig {

    private static final PathPatternRequestMatcher.Builder builder = PathPatternRequestMatcher.withDefaults();

    private static final RequestMatcher crmApiMatcher = new OrRequestMatcher(
            builder.matcher("/customer/**"),
            builder.matcher("/custommers/**")
    );

    @Bean
    static MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
        return new DefaultMethodSecurityExpressionHandler();
    }

    public static class CrmAPI {
        private static final String NOT_USED = "not_used";

        @Value("crm.security.basic-auth.api.user")
        private String apiUserName;

        @Value("crm.security.basic-auth.api.user")
        private String apiUserPassword;

        @Bean
        public SecurityFilterChain filterChainCrmApi(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.csrf(csrf -> csrf.ignoringRequestMatchers(crmApiMatcher));

            httpSecurity.securityMatcher(crmApiMatcher);

            httpSecurity.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());

            httpSecurity.httpBasic(Customizer.withDefaults());

            return httpSecurity.build();
        }

        @Bean
        public UserDetailsService userDetailsService() {
            UserDetails userDetails = User.withDefaultPasswordEncoder()
                    .username(apiUserName)
                    .password(apiUserPassword)
                    .roles("not_used")
                    .build();

            return new InMemoryUserDetailsManager(userDetails);
        }
    }
}
