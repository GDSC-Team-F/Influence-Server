package gdsc.hack.influence.auth.security.service;

import gdsc.hack.influence.auth.security.domain.CustomUserDetails;
import gdsc.hack.influence.common.exception.error.UserErrorCode;
import gdsc.hack.influence.domain.user.Email;
import gdsc.hack.influence.domain.user.User;
import gdsc.hack.influence.repository.UserRepository;
import gdsc.hack.influence.auth.security.dto.UserDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Email userEmail = Email.builder()
                .value(email)
                .build();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException(UserErrorCode.USER_NOT_FOUND.getMessage()));

        return new CustomUserDetails(new UserDetailsDto(user));
    }
}