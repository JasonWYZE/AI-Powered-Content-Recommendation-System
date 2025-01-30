@Service

public class UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;  // Add this as dependency

    @Autowired
    public UserService(UserReposiotry userReposiotry, PasswordEncoder passwordEncoder){
        this.userReposiotry = userReposiotry;
        this.passwordEncoder = passwordEncoder;
    }

    public UserProfileDTO getUserProfile(UUID userId) {
        User user = findById(userId);
        return convertToDTO(user);
    }

    private UserProfileDTO convertToDTO(User user) {
        UserProfileDTO dto = new UserProfileDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }


    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }
    public User createUser(UserDTO createUserDTO){

        if (userReposiotry.existsByEmail(createUserDTO.getEmail())){
            throw new UserAlreadyExistsException("User with email already exists");
        }
        User user = new User();
        user.setUsername(createUserDTO.getUsername());
        user.setEmail(createUserDTO.getEmail());
        user.setPasswordHash(passwordEncoder.encode(createUserDTO.getPassword()));
        User saveUser = userRepository.save(user);

        return saveUser
    }


    public User findUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));
    }

    public void updatePassword(UUID userID, String currentPassword, String newPassword){
        User user = userReposiotry.findById(userID)
                .orElseThrow -> new UserNotFoundException("User with email " + email + " not found"));
        // Verify current password
        if (!passwordEncoder.matches(currentPassword, user.getPasswordHash())) {
            throw new InvalidPasswordException("Current password is incorrect");
        }// Using verifyPassword would do TWO database lookups:

        user.setPasswordHash(passwordEncoder.encode(newPassword);
        userRepository.save(user);
    }

    public boolean verifyPassword(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));
        return passwordEncoder.matches(password, user.getPasswordHash());
    }

}