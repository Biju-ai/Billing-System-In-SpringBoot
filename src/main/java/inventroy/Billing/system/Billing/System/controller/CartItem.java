package inventroy.Billing.system.Billing.System.controller;

import inventroy.Billing.system.Billing.System.dto.request.Products.DeleteCartItemById;
import inventroy.Billing.system.Billing.System.dto.request.carts.CartItems;
import inventroy.Billing.system.Billing.System.services.impl.CartsService;
import inventroy.Billing.system.Billing.System.util.RequestApi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/billing")
public class CartItem {

    private final CartsService cartsService;

    public CartItem(CartsService cartsService) {
        this.cartsService = cartsService;
    }


    //    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addtocart")
    public RequestApi<?> addtoCart(@RequestBody CartItems cartItem, Principal principal) {
        return cartsService.addToCart(cartItem, principal);
    }

    @PostMapping("/mycart")
    public RequestApi<?> mycart(Principal principal) {
        return cartsService.myCarts(principal);
    }

    @PostMapping("/deletedbycartid")
    public RequestApi<?> deletedbycartid(@RequestBody DeleteCartItemById deleteCartItemById, Principal principal) {
        return cartsService.deleteCartId(deleteCartItemById, principal);
    }

    @PostMapping("/orders")
    public RequestApi<?> Orders(Principal principal) {
        return cartsService.orders(principal);
    }
}
