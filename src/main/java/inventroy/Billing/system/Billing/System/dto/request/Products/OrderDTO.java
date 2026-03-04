package inventroy.Billing.system.Billing.System.dto.request.Products;

import inventroy.Billing.system.Billing.System.dto.Responce.products.FindByNameSize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String email;
    private List<FindByNameSize.OrderItemDTO> orderItemDTOs;
    private LocalDateTime orderDate;
}
