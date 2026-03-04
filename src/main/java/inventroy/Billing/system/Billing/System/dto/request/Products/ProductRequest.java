package inventroy.Billing.system.Billing.System.dto.request.Products;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class ProductRequest {

    private String productname;

    private int productprice;

    private String productcategory;

    private int stocklevels;

    private LocalDate expirydate;

    private int productquantity;

    private Character size;

    private int lowstockthreshold;

    private int expiryalertdays;

}

