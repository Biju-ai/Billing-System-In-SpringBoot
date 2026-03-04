package inventroy.Billing.system.Billing.System.entity.products;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orders_id")
    private Long ordersId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name="price")
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="products_id")
    private Products product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    @JsonBackReference//  prevents recursion
    private Order order;

}
