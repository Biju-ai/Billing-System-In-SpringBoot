package inventroy.Billing.system.Billing.System.dto.Responce.products;

import inventroy.Billing.system.Billing.System.dto.request.Products.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class FindByNameSize {
    private Character size;
    private String productname;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItemDTO {
        private int quantity;
        private double price;
        private ProductDTO product;
    }
}
