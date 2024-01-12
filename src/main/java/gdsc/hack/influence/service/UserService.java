package gdsc.hack.influence.service;

import gdsc.hack.influence.auth.jwt.persistence.TokenPersistenceAdapter;
import gdsc.hack.influence.auth.jwt.util.JwtTokenProvider;
import gdsc.hack.influence.common.exception.BaseException;
import gdsc.hack.influence.common.exception.error.UserErrorCode;
import gdsc.hack.influence.domain.user.Email;
import gdsc.hack.influence.domain.user.Password;
import gdsc.hack.influence.domain.user.User;
import gdsc.hack.influence.dto.LoginResponse;
import gdsc.hack.influence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static gdsc.hack.influence.domain.user.Password.ENCODER;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserFindService userFindService;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenPersistenceAdapter tokenPersistenceAdapter;

    @Transactional
    public Long signUp(User user) {
        User saveMember = userRepository.save(user);

        return saveMember.getUserIdx();
    }

    @Transactional
    public LoginResponse login(String email, String password) {
        Email userEmail = Email.builder()
                .value(email)
                .build();
        User user = userFindService.findByMemEmail(userEmail);
        comparePassword(password, user.getPassword());

        String accessToken = jwtTokenProvider.generateAccessToken(user.getUserIdx());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getUserIdx());
        tokenPersistenceAdapter.synchronizeRefreshToken(user.getUserIdx(), refreshToken);

        return new LoginResponse(
                user.getUserIdx(),
                user.getNickname(),
                user.getEmail().getValue(),
                accessToken,
                refreshToken,
                user.getConditions()
        );
    }

    private void comparePassword(String password, Password memPassword) {
        if(!memPassword.isSamePassword(password, ENCODER)) {
            throw BaseException.type(UserErrorCode.PASSWORD_MISMATCH);
        }
    }
}
