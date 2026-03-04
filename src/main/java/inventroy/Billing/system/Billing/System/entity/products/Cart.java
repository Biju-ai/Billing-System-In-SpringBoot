package inventroy.Billing.system.Billing.System.entity.products;

import inventroy.Billing.system.Billing.System.entity.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="cart")
public class Cart {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

 @OneToOne(cascade =CascadeType.ALL)
    @JoinColumn(name="userlogin_id")
    private Users user;

 @OneToMany(mappedBy ="cart",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CartItem> cartItem =new ArrayList<>();

}
