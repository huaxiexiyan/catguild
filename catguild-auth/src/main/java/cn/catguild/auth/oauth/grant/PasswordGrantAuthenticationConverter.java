package cn.catguild.auth.oauth.grant;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiyan
 * @date 2023/5/30 17:53
 */
public class PasswordGrantAuthenticationConverter implements AuthenticationConverter {

  private static MultiValueMap<String, String> getParameters(HttpServletRequest request) {
    Map<String, String[]> parameterMap = request.getParameterMap();
    MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>(parameterMap.size());
    parameterMap.forEach(
        (key, values) -> {
          if (values.length > 0) {
            for (String value : values) {
              parameters.add(key, value);
            }
          }
        });
    return parameters;
  }

  @Nullable
  @Override
  public Authentication convert(HttpServletRequest request) {
    // grant_type (REQUIRED)
    String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
    if (!"password".equals(grantType)) {
      return null;
    }

    Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();

    MultiValueMap<String, String> parameters = getParameters(request);

    // username \ password (REQUIRED)
    String username = parameters.getFirst(OAuth2ParameterNames.USERNAME);
    String password = parameters.getFirst(OAuth2ParameterNames.PASSWORD);
    if (!StringUtils.hasText(username)
        || parameters.get(OAuth2ParameterNames.USERNAME).size() != 1
        || !StringUtils.hasText(password)
        || parameters.get(OAuth2ParameterNames.PASSWORD).size() != 1) {
      throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_REQUEST);
    }

    Map<String, Object> additionalParameters = new HashMap<>();
    parameters.forEach(
        (key, value) -> {
          if (!key.equals(OAuth2ParameterNames.GRANT_TYPE)
              && !key.equals(OAuth2ParameterNames.CLIENT_ID)
              && !key.equals(OAuth2ParameterNames.CODE) ) {
            additionalParameters.put(key, value.get(0));
          }
        });

    return new PasswordGrantAuthenticationToken(
        username, password, grantType, clientPrincipal, additionalParameters);
  }
}
