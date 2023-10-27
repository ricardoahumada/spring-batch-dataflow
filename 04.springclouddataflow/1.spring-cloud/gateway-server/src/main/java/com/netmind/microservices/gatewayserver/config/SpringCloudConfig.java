package com.netmind.microservices.gatewayserver.config;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

 @Configuration
public class SpringCloudConfig {
    @Bean
	public GlobalFilter globalFilter() {
		return (exchange, chain) -> {
			System.out.println("First Global filter");
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				System.out.println("Second Global filter");
			}));
		};
	}

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/orders/**")
                        .uri("lb://orders-service/"))
                .route(r -> r.path("/products/**")
                         .filters(f ->
                                 f.addRequestHeader("added-request-header", "added-request-header-value")
                                 .addResponseHeader("added-response-header", "added-response-header-value")
                         )
                        .uri("lb://products-service/"))

                .build();
    }

}