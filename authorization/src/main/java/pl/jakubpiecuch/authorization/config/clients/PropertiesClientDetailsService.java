package pl.jakubpiecuch.authorization.config.clients;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import pl.jakubpiecuch.authorization.config.properties.OAuth2Properties;

import java.util.Optional;

@Service("propertiesClientDetailsService")
public class PropertiesClientDetailsService implements ClientDetailsService {

    private static final String CLIENT_NOT_FOUND = "Client for given id[%s] was not found";
    private final OAuth2Properties oAuth2Properties;

    public PropertiesClientDetailsService(OAuth2Properties oAuth2Properties) {
        this.oAuth2Properties = oAuth2Properties;
    }


    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        final Optional<BaseClientDetails> details = this.oAuth2Properties.getClients()
                .stream().filter(detail -> detail.getClientId().equals(clientId)).findAny();
        if (details.isPresent()) {
            return details.get();
        }
        throw new ClientRegistrationException(String.format(CLIENT_NOT_FOUND, clientId));
    }
}
