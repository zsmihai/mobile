package src.com.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import src.com.domain.NewsObject;

public interface NewsRepository extends JpaRepository<NewsObject, Integer> {
}
