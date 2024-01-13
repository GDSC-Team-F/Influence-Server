package gdsc.hack.influence.service;

import gdsc.hack.influence.domain.Request.Friend;
import gdsc.hack.influence.domain.Shot.Shot;
import gdsc.hack.influence.domain.injection.Injection;
import gdsc.hack.influence.domain.user.User;
import gdsc.hack.influence.dto.InjectionResponse;
import gdsc.hack.influence.repository.FriendRepository;
import gdsc.hack.influence.repository.InjectionRepository;
import gdsc.hack.influence.repository.ShotRepository;
import gdsc.hack.influence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InjectionService {
    private final InjectionRepository injectionRepository;
    private final UserRepository userRepository;
    private final FriendRepository friendRepository;
    private final ShotRepository shotRepository;
    private final UserFindService userFindService;

    @Transactional
    public Long vaccinated(Long userId, Long vaccineId) {
        User user = userFindService.findByMemId(userId);
        Injection injection = injectionRepository.findById(vaccineId).get();
        Shot shot = Shot.create(user, injection);

        Shot savedShot = shotRepository.save(shot);
        return savedShot.getShotIdx();
    }

    public InjectionResponse findById(Long userId, Long id) {
        User user = userRepository.findById(userId).get();
        List<User> friends = findFriends(user);
        Injection injection = injectionRepository.findById(id).get();

        return getInjectionResponse(injection, user, friends);
    }

    public List<InjectionResponse> findAll(Long userId) {
        User user = userRepository.findById(userId).get();
        List<User> friends = findFriends(user);
        List<Injection> injections = injectionRepository.findAll();
        List<InjectionResponse> response = new ArrayList<>();

        for(Injection injection : injections) {
            response.add(getInjectionResponse(injection, user, friends));
        }

        return List.copyOf(response);
    }

    private InjectionResponse getInjectionResponse(Injection injection, User user, List<User> friends) {
        boolean isInjected = shotRepository.existsByUserAndInjection(user, injection);

        List<Long> friendsInjected = new ArrayList<>();
        List<Long> friendsNotInjected = new ArrayList<>();
        for(User friend : friends) {
            if(shotRepository.existsByUserAndInjection(friend, injection)) {
                friendsInjected.add(friend.getUserIdx());
            } else {
                friendsNotInjected.add(friend.getUserIdx());
            }
        }

        return InjectionResponse.of(injection, isInjected,
                friendsInjected, friendsNotInjected);
    }

    private List<User> findFriends(User user) {
        return friendRepository.findByRequester(user)
                .stream()
                .map(Friend::getResponder)
                .toList();
    }
}