package tec.edu.azuay.simpleaudit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tec.edu.azuay.simpleaudit.enties.History;

public interface IHistoryRepository extends JpaRepository<History, Integer> {
}
