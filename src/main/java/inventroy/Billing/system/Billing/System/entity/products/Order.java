package inventroy.Billing.system.Billing.System.entity.products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import inventroy.Billing.system.Billing.System.entity.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="orders")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long orderid;

    @Column(name="total_amount")
    private double totalamount;

@Column(name="order_date")
    private LocalDateTime orderdate;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Users user;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    @JsonManagedReference// forward direction
    private List<OrderItem> orderItemList;

}
