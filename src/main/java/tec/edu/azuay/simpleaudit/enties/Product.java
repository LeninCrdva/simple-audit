package tec.edu.azuay.simpleaudit.enties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tec.edu.azuay.simpleaudit.listeners.AuditProductListener;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
@EntityListeners(AuditProductListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codeProduct;
    private String name;
    private String category;
    private Double price;
}
