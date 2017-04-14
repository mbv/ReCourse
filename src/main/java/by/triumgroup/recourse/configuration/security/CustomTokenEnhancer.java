package by.triumgroup.recourse.configuration.security;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer {


    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        // you can set some additional information with oauth tokens here, example is provided below

        /*
        final Map<String, Object> additionalInfo = new HashMap<>();
        Object principal = oAuth2Authentication.getPrincipal();
        if (principal instanceof org.springframework.security.core.userdetails.User) {
            String userName = ((User) principal).getUsername();
            additionalInfo.put("status", "ok");
        }
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
        */
        return oAuth2AccessToken;
    }
}
