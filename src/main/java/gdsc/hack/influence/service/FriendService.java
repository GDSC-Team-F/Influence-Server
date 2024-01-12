package gdsc.hack.influence.service;

import gdsc.hack.influence.domain.Request.Friend;
import gdsc.hack.influence.domain.Request.RandCode;
import gdsc.hack.influence.domain.user.User;
import gdsc.hack.influence.repository.FriendRepository;
import gdsc.hack.influence.repository.RandCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;
    private final RandCodeRepository randCodeRepository;
    private final UserFindService userFindService;

    @Transactional
    public String generate(Long userId) {
        System.out.println(userId);
        int codeLength = 8; // 랜덤 코드 생성을 위한 길이 설정
        String randomCode = generateRandomString(codeLength); // 랜덤 코드 생성

        User user = userFindService.findByMemId(userId);
        RandCode randCode = RandCode.create(randomCode, user);

        randCodeRepository.save(randCode);

        return randomCode;
    }

    @Transactional
    public Long request(Long userId, String randCode) {
        User requester = userFindService.findByMemId(userId);
        RandCode generatedRandCode = randCodeRepository.findByRandCode(randCode).get();

        Friend friend = Friend.create(requester, generatedRandCode.getUser(), generatedRandCode.getRandCode());
        Friend savedFriend = friendRepository.save(friend);

        return savedFriend.getRequestIdx();
    }

    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRabcdefghijklmnoxyz0123456789";

        StringBuilder randomStringBuilder = new StringBuilder(length);

        // 랜덤 코드 생성
        for (int i = 0; i < length; i++) {
            int index = new Random().nextInt(characters.length());
            randomStringBuilder.append(characters.charAt(index));
        }

        return randomStringBuilder.toString();
    }
}
