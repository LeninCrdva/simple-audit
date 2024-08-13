package tec.edu.azuay.simpleaudit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tec.edu.azuay.simpleaudit.enties.Product;

public interface IProductRepository extends JpaRepository<Product, Integer> {
}
