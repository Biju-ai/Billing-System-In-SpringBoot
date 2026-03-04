package inventroy.Billing.system.Billing.System.controller;

import inventroy.Billing.system.Billing.System.dto.request.Products.DeleteProductById;
import inventroy.Billing.system.Billing.System.dto.request.users.UploadPassword;
import inventroy.Billing.system.Billing.System.dto.request.users.UserRequest;
import inventroy.Billing.system.Billing.System.dto.Responce.UserLogin;
import inventroy.Billing.system.Billing.System.services.impl.UserServiceImpl;
import inventroy.Billing.system.Billing.System.util.RequestApi;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/billing/")
public class UsersController {
    private final UserServiceImpl userService;

    public UsersController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/insert/user")
    public RequestApi<?> addUser(@RequestBody UserRequest userRequest) {
        return userService.inserUser(userRequest);
    }

    @PostMapping("/findAllUser")
    public RequestApi<?> findAllUsers( Principal principal) {
        return userService.findAllUsers(principal);
    }

    @PostMapping("/deleteById")
    public RequestApi<?> deleteUserById(@RequestBody DeleteProductById deleteProductById) {
        return userService.deleteById(deleteProductById);
    }


    @PostMapping("/setPassword")
    public RequestApi<?> uploadPassword(@RequestBody  UploadPassword uploadPassword) {
        return userService.uploadPassword(uploadPassword);
    }

    @PostMapping("/login")
    public RequestApi<?> login(@RequestBody UserLogin userLogin) {
        return userService.login(userLogin);
    }

    @PostMapping("/profile")
    public RequestApi<?> profile( Principal principal) {
        return userService.profile(principal);
    }

}

