package gdsc.hack.influence.service;

import gdsc.hack.influence.auth.jwt.persistence.TokenPersistenceAdapter;
import gdsc.hack.influence.auth.jwt.util.JwtTokenProvider;
import gdsc.hack.influence.common.exception.BaseException;
import gdsc.hack.influence.common.exception.error.UserErrorCode;
import gdsc.hack.influence.domain.user.Email;
import gdsc.hack.influence.domain.user.Password;
import gdsc.hack.influence.domain.user.User;
import gdsc.hack.influence.dto.LoginResponse;
import gdsc.hack.influence.dto.MyPageResponse;
import gdsc.hack.influence.repository.InjectionRepository;
import gdsc.hack.influence.repository.ShotRepository;
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
    private final ShotRepository shotRepository;
    private final InjectionRepository injectionRepository;

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
        User user = userFindService.findByEmail(userEmail);
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

    @Transactional
    public MyPageResponse getInfo(Long userId) {
        User user = userFindService.findByMemId(userId);
        Long shotCnt = shotRepository.countByUser(user);
        System.out.println(shotCnt);
        Long injectionCnt = injectionRepository.countBy();
        System.out.println(injectionCnt);
        float myPercent = ((float) shotCnt / injectionCnt) * 100.0f;

        return new MyPageResponse(
                user.getUserIdx(),
                user.getNickname(),
                user.getEmail().getValue(),
                user.getConditions(),
                user.getAddress(),
                user.getGender(),
                user.getAge(),
                user.getImage(),
                myPercent
        );
    }

    private void comparePassword(String password, Password memPassword) {
        if(!memPassword.isSamePassword(password, ENCODER)) {
            throw BaseException.type(UserErrorCode.PASSWORD_MISMATCH);
        }
    }
}
