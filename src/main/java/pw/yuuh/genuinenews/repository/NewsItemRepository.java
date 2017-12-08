package pw.yuuh.genuinenews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pw.yuuh.genuinenews.domain.NewsItem;

public interface NewsItemRepository extends JpaRepository<NewsItem, Long> {
}
