@Entity
@Table(name = users)
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @CreationTimestamp
    @Column(name = "created_at")    // DB column name
    private LocalDateTime createdAt; // Java variable name

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Changed to camelCase
}