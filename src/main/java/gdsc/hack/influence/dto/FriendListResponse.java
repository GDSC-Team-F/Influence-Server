package gdsc.hack.influence.dto;

public record FriendListResponse(
        Long userId,
        String name,
        Integer image,
        Float userPercent
) {
}
