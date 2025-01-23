
@Data
public class CreateUserDTO{
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters")
    private String password;

    @NotBlank
    @Size(max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9._-]{3,50}$", message = "Username must be alphanumeric")
    private String username;

    @NotBlank
    @Email
    private String email;
}


@Data
public class UserProfileDTO{

    private UUID id;
    private String username;
    private String email;
    private LocalDateTime createdAt;
}

@Data
public class updateUserDTO{
    @NotBlank(message = "Current password is required")
    private String currentPassword;

    @NotBlank(message = "New password is required")
    @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters")
    private String newPassword;

    @NotBlank(message = "Password confirmation is required")
    private String confirmPassword;


}