package tec.edu.azuay.simpleaudit.listeners;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import tec.edu.azuay.simpleaudit.enties.History;
import tec.edu.azuay.simpleaudit.enties.Product;
import tec.edu.azuay.simpleaudit.repository.IHistoryRepository;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor(onConstructor_ = @__(@Lazy))
public class AuditProductListener {

    private final IHistoryRepository historyRepository;

    @PrePersist
    private void prePersist(Product product) {
        createHistory(product, "CREATE");
    }

    @PreUpdate
    private void preUpdate(Product product) {
        createHistory(product, "UPDATE");
    }

    @PreRemove
    private void preRemove(Product product) {
        createHistory(product, "DELETE");
    }

    void createHistory(Product product, String action) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        History history = new History();
        history.setUsername(username);
        history.setName(product.getName());
        history.setAction(action);
        history.setDate(LocalDateTime.now());

        historyRepository.save(history);
    }
}
