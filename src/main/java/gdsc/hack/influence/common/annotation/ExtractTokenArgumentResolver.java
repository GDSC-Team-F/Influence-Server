package gdsc.hack.influence.common.annotation;

import gdsc.hack.influence.common.exception.BaseException;
import gdsc.hack.influence.common.exception.GlobalErrorCode;
import gdsc.hack.influence.auth.jwt.util.AuthorizationExtractor;
import gdsc.hack.influence.auth.jwt.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
public class ExtractTokenArgumentResolver implements HandlerMethodArgumentResolver {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ExtractToken.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        String token = AuthorizationExtractor.extractToken(request)
                .orElseThrow(() -> BaseException.type(GlobalErrorCode.INVALID_PERMISSION));

        validateTokenIntegrity(token);

        return token;
    }

    private void validateTokenIntegrity(String token) {
        jwtTokenProvider.validateToken(token);
    }
}