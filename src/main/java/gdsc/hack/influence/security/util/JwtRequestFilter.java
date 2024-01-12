package gdsc.hack.influence.security.util;

import gdsc.hack.influence.common.exception.BaseException;
import gdsc.hack.influence.common.exception.GlobalErrorCode;
import gdsc.hack.influence.common.exception.error.UserErrorCode;
import gdsc.hack.influence.domain.user.User;
import gdsc.hack.influence.jwt.util.JwtTokenProvider;
import gdsc.hack.influence.security.domain.CustomUserDetails;
import gdsc.hack.influence.security.dto.UserDetailsDto;
import gdsc.hack.influence.service.UserFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserFindService userFindService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            if (jwtTokenProvider.validateToken(token)) {
                Long memId = jwtTokenProvider.getId(token);
                User user = userFindService.findByMemId(memId);

                if (user != null) {
                    CustomUserDetails customUserDetails = new CustomUserDetails(new UserDetailsDto(user));
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            customUserDetails, "", customUserDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } else {
                    throw BaseException.type(UserErrorCode.USER_NOT_FOUND);
                }
            } else {
                throw BaseException.type(GlobalErrorCode.INVALID_TOKEN);
            }
        }

        filterChain.doFilter(request, response);
    }
}