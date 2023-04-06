package adopet.api.user;

public record UserListData(
    Long id,
    String name,
    String email) {

    public UserListData(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }

}
