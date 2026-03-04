package inventroy.Billing.system.Billing.System.entity.products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productid;

    @Column(name = "product_name", nullable = false, length = 100)  // Note: fixed column name
    private String productname;

    @Column(name = "product_price", nullable = false)
    private int productprice;

    @Column(name = "product_category", nullable = false, length = 100)
    private String productcategory;

    @Column(name = "stock_levels", nullable = false, length = 100)
    private int stocklevels;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expirydate;

    @Column(name = "product_quantity", length = 100)
    private int productquantity;

    @Column(name = "size")
    private Character size;

    @Column(name="low_stock_threshold",nullable = false)
    private int lowstockthreshold;

    @Column(name="expiry_alert_days", nullable = false)
    private int expiryalertdays;

    @JsonIgnore
    @Column(name ="is_deleted")
    private Boolean deleted;

}
