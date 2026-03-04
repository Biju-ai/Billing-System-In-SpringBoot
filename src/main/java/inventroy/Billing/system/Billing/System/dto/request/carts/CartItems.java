package inventroy.Billing.system.Billing.System.dto.request.carts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItems {
    private int quantity;
    private String productname;
    private Character size;
}
