package gdsc.hack.influence.repository;

import gdsc.hack.influence.domain.Request.Friend;
import gdsc.hack.influence.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findByRequester(User requester);

//    @Query("SELECT f.responder AS responder FROM Friend f WHERE f.requester = :requester ")
//    List<FriendListResponse> findResponderByRequester(@Param("requester") User requester);
}
