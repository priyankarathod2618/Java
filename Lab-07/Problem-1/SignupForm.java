public class SignupForm {

    @NotBlank
    @MaxLength(30)
    private String username;

    @NotBlank
    @MaxLength(50)
    private String email;

    @NotBlank
    @MaxLength(20)
    private String password;

    public SignupForm(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}