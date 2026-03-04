package inventroy.Billing.system.Billing.System.services.impl;

import inventroy.Billing.system.Billing.System.dto.request.Products.DeleteCartItemById;
import inventroy.Billing.system.Billing.System.dto.request.Products.OrderDTO;
import inventroy.Billing.system.Billing.System.dto.request.Products.ProductDTO;
import inventroy.Billing.system.Billing.System.dto.request.carts.CartItems;
import inventroy.Billing.system.Billing.System.dto.Responce.products.CartLits;
import inventroy.Billing.system.Billing.System.dto.Responce.products.FindByNameSize;
import inventroy.Billing.system.Billing.System.entity.Users;
import inventroy.Billing.system.Billing.System.entity.products.*;
import inventroy.Billing.system.Billing.System.repository.*;
import inventroy.Billing.system.Billing.System.util.RequestApi;
import inventroy.Billing.system.Billing.System.util.ResponceApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CartsService {
    private final UserRepo userRepo;
    private final ProductRepo productRepo;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartrepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepo orderRepo;

    public CartsService(UserRepo userRepo, ProductRepo productRepo, CartItemRepository cartItemRepository, CartRepository repository, OrderItemRepository orderItemRepository, OrderRepo orderRepo) {
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.cartItemRepository = cartItemRepository;
        this.cartrepository = repository;
        this.orderItemRepository = orderItemRepository;
        this.orderRepo = orderRepo;
    }

    //    @Transactional
    public RequestApi<?> addToCart(CartItems cartitem, Principal principal) {
        try {
            if (cartitem.getQuantity() <= 0) {
                return ResponceApi.error(0, "Quantity should not be greater than 0");
            }
            Users user = userRepo.findByEmail(principal.getName());

            if (user == null) {
                return ResponceApi.success(0, "User not found found");
            }
            Cart cart = cartrepository.findByUser(user);
            if (cart == null) {
                Cart cart1 = new Cart();
                cart1.setUser(user);
                cart = cartrepository.save(cart1);
            }


            List<Products> products;

            if (cartitem.getSize() != null) {
                products = productRepo.findByProductnameAndSizeAndDeletedFalse(cartitem.getProductname(), cartitem.getSize());
            } else {
                products = productRepo.findByProductnameAndDeletedFalse(cartitem.getProductname());
            }
            if (products.isEmpty()) {
                return ResponceApi.error(0, "Product not found");
            }
            Products product = products.get(0);

            List<CartItem> existItem = cartItemRepository.findByCartAndProduct(cart, product);
            CartItem cartItem;
            if (!existItem.isEmpty()) {
                cartItem = existItem.getFirst();
                int newquantity = existItem.getFirst().getQuantity() + cartitem.getQuantity();
                cartItem.setQuantity(newquantity);
                cartItem.setTotalprice(products.get(0).getProductprice() * newquantity);
                cartItemRepository.save(cartItem);
                log.info("Cart item added successfully");
            } else {
                CartItem cartItme = new CartItem();
                cartItme.setCart(cart);
                cartItme.setProduct(product);
                cartItme.setQuantity(cartitem.getQuantity());
                cartItme.setTotalprice(products.get(0).getProductprice() * cartitem.getQuantity());
                cartItemRepository.save(cartItme);
            }
            log.info("Cart item added successfully 12345");
            return ResponceApi.success(1, "the item has been added successfully ");
        } catch (Exception e) {
            return ResponceApi.error(0, "something went wrong");
        }
    }

    public RequestApi<?> myCarts(Principal principal) {
        Users user = userRepo.findByEmail(principal.getName());
        if (user == null) {
            return ResponceApi.error(0, "User not found found");
        }
        Cart cart = cartrepository.findByUser(user);
        if (cart == null) {
            return ResponceApi.error(0, "Cart not found");
        }

        List<CartItem> cartItems = cartItemRepository.findByCart(cart);
        if (cartItems == null) {
            return ResponceApi.error(0, "Cart items not found");
        }

        List<CartLits> cartLits = cartItems.stream().map(item -> {
            CartLits cartLit = new CartLits();
            cartLit.setId(item.getId());
            cartLit.setTotalprice(item.getTotalprice());
            cartLit.setQuantity(item.getQuantity());

            Products products = item.getProduct();
            cartLit.setProductname(products.getProductname());
            cartLit.setProductprice(products.getProductprice());
            cartLit.setQuantity(item.getQuantity());
            cartLit.setSize(item.getProduct().getSize());
            return cartLit;
        }).toList();


        double totalAmount = cartItems.stream()
                .mapToDouble(CartItem::getTotalprice)
                .sum();

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("cartItems", cartLits);
        responseData.put("totalAmount", totalAmount);

        return ResponceApi.success(1, "Cart selected cart total successfully", responseData);
    }


    public RequestApi<?> deleteCartId(DeleteCartItemById deleteCartItemById, Principal principal) {
        Users user = userRepo.findByEmail(principal.getName());
        if (user == null) {
            return ResponceApi.error(0, "User not found found");
        }
        Cart cart = cartrepository.findByUser(user);
        if (cart == null) {
            return ResponceApi.error(0, "Cart not found");
        }

        Optional<CartItem> cartItems = cartItemRepository.findById(deleteCartItemById.getId());
        if (cartItems.isEmpty()) {
            return ResponceApi.error(0, "Cart items not found");
        }

        CartItem cartItem = cartItems.get();

        // Security: check that the item belongs to this user's cart
        if (!cartItem.getCart().getId().equals(cart.getId())) {
            return ResponceApi.error(0, "This item doesn't belong to your cart");
        }

        cartItemRepository.delete(cartItem);
        return ResponceApi.success(1, "Cart item has been deleted successfully");
    }

    public RequestApi<?> orders(Principal principal) {
        Users user = userRepo.findByEmail(principal.getName());
        if (user == null) {
            return ResponceApi.error(0, "User not found found");
        }
        Cart cart = cartrepository.findByUser(user);
        if (cart == null) {
            return ResponceApi.error(0, "Cart not found");
        }

        //cretate new orders
        Order orde = new Order();
        orde.setUser(user);
        orde.setOrderdate(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();
        double totalamount = 0;
        for (CartItem cartItme : cartItemRepository.findByCart(cart)) {

            Products products = cartItme.getProduct();

            // calculate remaining product

            int remainingStock = products.getProductquantity() - cartItme.getQuantity();
            if (remainingStock < 0) {
                return ResponceApi.error(0, "Product quantity is not enough", products.getProductname());
            }

            //store in data base
            products.setProductquantity(remainingStock);
            productRepo.save(products);


            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(cartItme.getQuantity());
            orderItem.setProduct(products);
            orderItem.setPrice(cartItme.getTotalprice());
            orderItem.setOrder(orde);
            orderItems.add(orderItem);
            totalamount = totalamount + orderItem.getPrice() * cartItme.getQuantity();
        }
        orde.setTotalamount(totalamount);
        orde.setOrderItemList(orderItems);

        // save in orders item
        Order save = orderRepo.save(orde);

        //  clean cartitme after placing order

        cart.getCartItem().clear();
        cartrepository.save(cart);

        List<FindByNameSize.OrderItemDTO> orderItemDTOs = orderItems.stream().map(item -> {
            Products p = item.getProduct();
            ProductDTO productDTO = new ProductDTO(p.getProductid(), p.getProductname(), p.getProductprice());
            return new FindByNameSize.OrderItemDTO(item.getQuantity(), item.getPrice(), productDTO);
        }).collect(Collectors.toList());

        OrderDTO orderDTO = new OrderDTO(save.getUser().getEmail(), orderItemDTOs, save.getOrderdate());

        return ResponceApi.success(1, "order has been saved successfully", orderDTO);
    }

}
