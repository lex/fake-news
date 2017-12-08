package pw.yuuh.genuinenews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pw.yuuh.genuinenews.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
