package inventroy.Billing.system.Billing.System.repository;

import inventroy.Billing.system.Billing.System.entity.products.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
