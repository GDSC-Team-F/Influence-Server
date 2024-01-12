package gdsc.hack.influence.jwt.service;

import gdsc.hack.influence.common.exception.BaseException;
import gdsc.hack.influence.common.exception.GlobalErrorCode;
import gdsc.hack.influence.jwt.dto.TokenResponse;
import gdsc.hack.influence.jwt.persistence.TokenPersistenceAdapter;
import gdsc.hack.influence.jwt.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenReissueService {
    private final TokenPersistenceAdapter tokenPersistenceAdapter;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse reissueTokens(Long memId, String refreshToken) {
        if (!tokenPersistenceAdapter.isExistsRefreshToken(memId, refreshToken)) {
            throw BaseException.type(GlobalErrorCode.INVALID_TOKEN);
        }

        String newAccessToken = jwtTokenProvider.generateAccessToken(memId);
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(memId);

        tokenPersistenceAdapter.manageRefreshTokenRotation(memId, newRefreshToken);

        return new TokenResponse(newAccessToken, newRefreshToken);
    }
}
