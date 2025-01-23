@Repository


public interface UserRepository extends JpaRepository<User, UUID>{
    
}
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Find by unique field
    Optional<Book> findByIsbn(String isbn);

    // Check if exists
    boolean existsByIsbn(String isbn);

    // Find multiple by condition
    List<Book> findByAuthor(String author);

    // Custom query with multiple conditions
    List<Book> findByAuthorAndPublishedYearGreaterThan(String author, int year);
}