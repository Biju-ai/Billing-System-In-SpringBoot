package inventroy.Billing.system.Billing.System.repository;

import inventroy.Billing.system.Billing.System.entity.products.Cart;
import inventroy.Billing.system.Billing.System.entity.products.CartItem;
import inventroy.Billing.system.Billing.System.entity.products.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    List<CartItem> findByCartAndProduct(Cart cart, Products product);

    List<CartItem> findByCart(Cart cart);


//

}
