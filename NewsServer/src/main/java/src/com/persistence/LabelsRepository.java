package src.com.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import src.com.domain.Label;

public interface LabelsRepository extends JpaRepository<Label, Integer> {
}
