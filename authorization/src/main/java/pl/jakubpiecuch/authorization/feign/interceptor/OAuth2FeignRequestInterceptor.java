package pl.jakubpiecuch.authorization.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public class OAuth2FeignRequestInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_TOKEN_TYPE = "Bearer";

    private final AccessTokenProvider accessTokenProvider;
    private final ClientCredentialsResourceDetails clientCredentialsResourceDetails;

    public OAuth2FeignRequestInterceptor(AccessTokenProvider accessTokenProvider,
                                         ClientCredentialsResourceDetails clientCredentialsResourceDetails) {
        this.accessTokenProvider = accessTokenProvider;
        this.clientCredentialsResourceDetails = clientCredentialsResourceDetails;
    }

    @Override
    public void apply(RequestTemplate template) {
        AccessTokenRequest parameters = new DefaultAccessTokenRequest();
        OAuth2AccessToken oAuth2AccessToken = accessTokenProvider.obtainAccessToken(clientCredentialsResourceDetails, parameters);
        template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE,
                oAuth2AccessToken.getValue()));
    }
}
