

package com.recommendation.apigateway.config;

        import org.springframework.cloud.gateway.route.RouteLocator;
        import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // User Service Routes
                .route("user-service-route", r -> r
                        .path("/api/users/**")
                        .uri("lb://user-service"))

                // Content Service Routes
                .route("content-service-route", r -> r
                        .path("/api/content/**")
                        .uri("lb://content-service"))

                // Recommendation Service Routes
                .route("recommendation-service-route", r -> r
                        .path("/api/recommendations/**")
                        .uri("lb://recommendation-service"))
                .build();
    }
}