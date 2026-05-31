package inventroy.Billing.system.Billing.System.services.impl;

import inventroy.Billing.system.Billing.System.dto.Responce.SupplierResponce;
import inventroy.Billing.system.Billing.System.dto.Responce.products.CustomerEmailLogo;
import inventroy.Billing.system.Billing.System.dto.request.Email.EmailDto;
import inventroy.Billing.system.Billing.System.dto.request.Email.SendEmailRequest;
import inventroy.Billing.system.Billing.System.dto.request.users.UserResponse;
import inventroy.Billing.system.Billing.System.entity.SupplierEmailLog;
import inventroy.Billing.system.Billing.System.entity.Suppliers;
import inventroy.Billing.system.Billing.System.entity.Users;
import inventroy.Billing.system.Billing.System.repository.SupplierRepo;
import inventroy.Billing.system.Billing.System.repository.UserRepo;
import inventroy.Billing.system.Billing.System.util.RequestApi;
import inventroy.Billing.system.Billing.System.util.ResponceApi;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class SupplierService {
    private final SupplierRepo supplierRepo;
    private final Mail mail;
    private final CustomerEmailLogo customerEmailLogo;
    private  final  UserRepo userRepo;


    public SupplierService(SupplierRepo supplierRepo, Mail mail, CustomerEmailLogo customerEmailLogo, UserRepo userRepo) {
        this.supplierRepo = supplierRepo;
        this.mail = mail;
        this.customerEmailLogo = customerEmailLogo;
        this.userRepo = userRepo;
    }


    public RequestApi<?> findAllSuppliers(Principal principal) {
        Users users1 = userRepo.findByEmail(principal.getName());
        if (users1 == null) {
            return ResponceApi.error(1, "User not found");
        }
        List<Suppliers> user1 = supplierRepo.findAll();

        List<SupplierResponce> responseDTOs = user1.stream().map(u -> {
            SupplierResponce dto = new SupplierResponce();
            dto.setSupplierName(u.getSupplierName());
            dto.setCompanyName(u.getCompanyName());
            dto.setEmail(u.getEmail());
            dto.setPhoneNumber(u.getPhoneNumber());
            dto.setAddress(u.getAddress());
            dto.setLocation(u.getLocation());
            dto.setDistance(u.getDistance());
            return dto;
        }).collect(Collectors.toList());

        return ResponceApi.success(1, "Supplier found successfully", responseDTOs);
    }

    public RequestApi<?> findSupplierByEmail(EmailDto  emailDto) {

        Suppliers supplier = supplierRepo.findByEmail(emailDto.getEmail());

        if (supplier == null) {
            return ResponceApi.error(1, "Supplier not found");
        }
        SupplierEmailLog supplierEmailLog= customerEmailLogo.mapToSupplierEntity(supplier,emailDto.getRemark());
        SendEmailRequest sendEmailRequest=new SendEmailRequest();
        sendEmailRequest.setReciverEmail(supplier.getEmail());
        sendEmailRequest.setSubject("low product notification");
        sendEmailRequest.setMessage(supplierEmailLog.getTemplate());
        mail.sendHtmlMail(sendEmailRequest);


        return ResponceApi.success(1, "Supplier found successfully", supplier);
    }
}
