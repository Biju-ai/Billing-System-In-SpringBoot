package inventroy.Billing.system.Billing.System.dto.request.Products;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ModifyProductById {
    private Long productid;

    private String productname;

    private String productcategory;

    private int productprice;

    private int stocklevels;

    private LocalDate expirydate;

    private int productquantity;

    private Character size;
}
