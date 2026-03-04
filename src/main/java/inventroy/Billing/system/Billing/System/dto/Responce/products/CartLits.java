package inventroy.Billing.system.Billing.System.dto.Responce.products;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartLits {
    private Long id;
    private String productname;
    private int quantity;
    private int productprice;
    private double totalprice;
    private Character size;
}
