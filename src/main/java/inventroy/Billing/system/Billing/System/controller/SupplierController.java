package inventroy.Billing.system.Billing.System.controller;

import inventroy.Billing.system.Billing.System.dto.request.Email.EmailDto;
import inventroy.Billing.system.Billing.System.dto.request.users.UserRequest;
import inventroy.Billing.system.Billing.System.services.impl.SupplierService;
import inventroy.Billing.system.Billing.System.util.RequestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/billing/")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/supplier/all")
    public RequestApi<?> allSupplier(Principal principal) {
        return supplierService.findAllSuppliers(principal);
    }

    @PostMapping("/supplier/email")
    public RequestApi<?> addUser(@RequestBody EmailDto emailDto) {
        return supplierService.findSupplierByEmail(emailDto);
    }
}
