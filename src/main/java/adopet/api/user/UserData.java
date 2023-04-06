package adopet.api.user;

public record UserData(
    String name,
    String email) {

    public UserData(User user) {
        this(user.getName(), user.getEmail());
    }

}
