package gdsc.hack.influence.service;

import gdsc.hack.influence.domain.Request.Friend;
import gdsc.hack.influence.domain.user.Email;
import gdsc.hack.influence.domain.user.User;
import gdsc.hack.influence.dto.FriendListResponse;
import gdsc.hack.influence.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;
    private final UserFindService userFindService;

    @Transactional
    public Long invite(Long userId, String email) {
        Email responserEmail = Email.builder()
                .value(email)
                .build();

        User responser = userFindService.findByEmail(responserEmail);

        User requester = userFindService.findByMemId(userId);

        Friend friend = Friend.create(requester, responser);

        Friend savedfriend = friendRepository.save(friend);

        return savedfriend.getRequestIdx();
    }

    @Transactional
    public List<FriendListResponse> get(Long userId) {
        User user = userFindService.findByMemId(userId);
        List<Friend> friendList = friendRepository.findByRequester(user);
        int size = friendList.size();

        System.out.println(friendList.size());

        List<FriendListResponse> friendLists = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Friend friend = friendList.get(i);
            friendLists.add(new FriendListResponse(
                    friend.getResponder().getUserIdx(),
                    friend.getResponder().getNickname(),
                    friend.getResponder().getImage()
            ));
        }

        return friendLists;
    }


}
