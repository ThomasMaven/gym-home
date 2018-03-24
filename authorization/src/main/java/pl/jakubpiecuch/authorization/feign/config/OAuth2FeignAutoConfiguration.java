package pl.jakubpiecuch.authorization.feign.config;

import feign.Feign;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import pl.jakubpiecuch.authorization.feign.interceptor.OAuth2FeignRequestInterceptor;

@Configuration
@ConditionalOnClass({ Feign.class })
@ConditionalOnProperty(value = "feign.oauth2.enabled", matchIfMissing = true)
public class OAuth2FeignAutoConfiguration {

    private final AccessTokenProvider accessTokenProvider;
    private final ClientCredentialsResourceDetails clientCredentialsResourceDetails;

    public OAuth2FeignAutoConfiguration(AccessTokenProvider accessTokenProvider,
                                        ClientCredentialsResourceDetails clientCredentialsResourceDetails) {
        this.accessTokenProvider = accessTokenProvider;
        this.clientCredentialsResourceDetails = clientCredentialsResourceDetails;
    }

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return new OAuth2FeignRequestInterceptor(this.accessTokenProvider, this.clientCredentialsResourceDetails);
    }
}
