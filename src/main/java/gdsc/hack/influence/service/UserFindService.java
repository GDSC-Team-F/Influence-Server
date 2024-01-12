package gdsc.hack.influence.service;

import gdsc.hack.influence.common.exception.BaseException;
import gdsc.hack.influence.common.exception.error.UserErrorCode;
import gdsc.hack.influence.domain.user.Email;
import gdsc.hack.influence.domain.user.User;
import gdsc.hack.influence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserFindService {
    private final UserRepository userRepository;

    public User findByMemId(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> BaseException.type(UserErrorCode.USER_NOT_FOUND));
    }

    public User findByMemEmail(Email email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> BaseException.type(UserErrorCode.USER_NOT_FOUND));
    }
}