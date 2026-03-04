package inventroy.Billing.system.Billing.System.repository;

import inventroy.Billing.system.Billing.System.entity.products.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Long> {
}
