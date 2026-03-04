package inventroy.Billing.system.Billing.System.repository;

import inventroy.Billing.system.Billing.System.entity.Users;
import inventroy.Billing.system.Billing.System.entity.products.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByUser(Users user);



}
