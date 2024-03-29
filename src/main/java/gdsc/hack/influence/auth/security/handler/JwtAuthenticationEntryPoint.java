package gdsc.hack.influence.auth.security.handler;

import gdsc.hack.influence.common.exception.BaseException;
import gdsc.hack.influence.common.exception.GlobalErrorCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serial;
import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    @Serial
    private static final long serialVersionUID = -6805510976485703958L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        throw BaseException.type(GlobalErrorCode.UNAUTHORIZED);
    }
}