package inventroy.Billing.system.Billing.System.services.impl;

import inventroy.Billing.system.Billing.System.dto.request.Email.SendEmailRequest;
import inventroy.Billing.system.Billing.System.dto.request.Products.DeleteProductById;
import inventroy.Billing.system.Billing.System.dto.request.users.ProfileResponce;
import inventroy.Billing.system.Billing.System.dto.request.users.UploadPassword;
import inventroy.Billing.system.Billing.System.dto.request.users.UserRequest;
import inventroy.Billing.system.Billing.System.dto.request.users.UserResponse;
import inventroy.Billing.system.Billing.System.dto.Responce.LoginRespon;
import inventroy.Billing.system.Billing.System.dto.Responce.UserLogin;
import inventroy.Billing.system.Billing.System.dto.Responce.products.CustomerEmailLogo;
import inventroy.Billing.system.Billing.System.entity.UserEmailLog;
import inventroy.Billing.system.Billing.System.entity.Users;
import inventroy.Billing.system.Billing.System.entity.products.Roles;
import inventroy.Billing.system.Billing.System.repository.RolesRepo;
import inventroy.Billing.system.Billing.System.repository.UserRepo;
import inventroy.Billing.system.Billing.System.services.UserService;
import inventroy.Billing.system.Billing.System.util.RequestApi;
import inventroy.Billing.system.Billing.System.util.ResponceApi;
import inventroy.Billing.system.Billing.System.util.enums.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.rmi.server.LogStream.log;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final Mail mail;
    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RolesRepo rolesRepo;
    private final CustomerEmailLogo customerEmailLogo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(Mail mail, UserRepo userRepo, JwtService jwtService, AuthenticationManager authenticationManager, RolesRepo rolesRepo, CustomerEmailLogo customerEmailLogo,PasswordEncoder passwordEncoder) {
        this.mail = mail;
        this.userRepo = userRepo;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.rolesRepo = rolesRepo;
        this.customerEmailLogo = customerEmailLogo;
        this.passwordEncoder = passwordEncoder;
    }


    public RequestApi<?> inserUser(UserRequest userRequest) {
        // 🔹 Get currently authenticated user

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getAuthorities() == null) {
            throw new AccessDeniedException("No authentication information available.");
        }

        // 🔹 Check if current user has ROLE_ADMIN

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> "ROLE_ADMIN".equals(auth.getAuthority()));

        if (!isAdmin) {
            throw new AccessDeniedException("Only admins can create new cashiers.");
        }
        Users user = new Users();
        user.setFirstname(userRequest.getFirstname());
        user.setLastname(userRequest.getLastname());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setDateofbirth(userRequest.getDateofbirth());
        user.setPhonenumber(userRequest.getPhonenumber());
        user.setGender(userRequest.getGender());
        Optional<Roles> roles = rolesRepo.findById(userRequest.getRole());
        user.setRole(roles.get());
        user.setStatus(Status.PENDING);
        user.setAdmin(false);

        // generating the token

        String token = UUID.randomUUID().toString();
        user.setVerificationToken(token);
        user.setTokenExpireDate(LocalDateTime.now().plusMinutes(60));
        userRepo.save(user);


        //Send email to the users

        UserEmailLog customerEmailLogo1 = customerEmailLogo.mapToEntity(user);
        SendEmailRequest sendEmailRequest = new SendEmailRequest();
        sendEmailRequest.setReciverEmail(userRequest.getEmail());
        sendEmailRequest.setMessage(customerEmailLogo1.getMessage());
        sendEmailRequest.setSubject("register successfully");


        mail.sendHtmlMail(sendEmailRequest);
        return ResponceApi.success(1, "User inserted successfully");
    }

    public RequestApi<?> findAllUsers(Principal principal) {
        Users user = userRepo.findByEmail(principal.getName());
        if (user == null) {
            return ResponceApi.error(1, "User not found");
        }
        List<Users> user1 = userRepo.findAll();

        List<UserResponse> responseDTOs = user1.stream().map(u -> {
            UserResponse dto = new UserResponse();
            dto.setFirstname(u.getFirstname());
            dto.setLastname(u.getLastname());
            dto.setEmail(u.getEmail());
            dto.setPhonenumber(u.getPhonenumber());
            dto.setGender(u.getGender());
            dto.setDateofbirth(u.getDateofbirth());
            dto.setRole(u.getRole());
            dto.setStatus(u.getStatus());
            return dto;
        }).collect(Collectors.toList());

        return ResponceApi.success(1, "Users found successfully", responseDTOs);
    }

    public RequestApi<?> uploadPassword(UploadPassword uploadPassword) {
        Users user = userRepo.findByVerificationToken(uploadPassword.getToken());
        if (user == null) {
            return ResponceApi.error(1, "User not found");
        }

        if (user.getTokenExpireDate() == null || user.getTokenExpireDate().isBefore(LocalDateTime.now())) {
            return ResponceApi.error(1, "Token expired");
        }
        if (!uploadPassword.getNewPassword().equals(uploadPassword.getConfirmPassword())) {
            return ResponceApi.error(1, "New password does not match");
        }


        user.setPassword(uploadPassword.getNewPassword());
        user.setStatus(Status.ACTIVE);
        userRepo.save(user);
        return ResponceApi.success(1, "Password uploaded successfully");
    }

    public RequestApi<?> deleteById(DeleteProductById deleteProductById) {
        Optional<Users> user = userRepo.findById(deleteProductById.getProductid());
        if (user.isPresent()) {
            userRepo.deleteById(deleteProductById.getProductid());
            return ResponceApi.success(1, "User deleted successfully");
        }
        return ResponceApi.error(0, "User not found");
    }

    public RequestApi<?> login(UserLogin userLogin) {

        try {

            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    userLogin.getEmail(),
                                    userLogin.getPassword()
                            )
                    );

            System.out.println("Inside me 1");

            UserDetails userDetails =
                    (UserDetails) authentication.getPrincipal();

            System.out.println("after user detials");

            String token = jwtService.generateToken(userDetails);

            LoginRespon loginResponse = new LoginRespon();
            loginResponse.setToken(token);
            loginResponse.setRole(jwtService.extractRole(token));

            return ResponceApi.success(
                    1,
                    "User logged in successfully",
                    loginResponse
            );

        } catch (BadCredentialsException e) {

            return ResponceApi.error(0, "Invalid email or password");

        } catch (Exception e) {

            return ResponceApi.error(0, e.getMessage());
        }
    }

    public RequestApi<?> profile(Principal principal) {
        Users users = userRepo.findByEmail(principal.getName());
        if (users == null) {
            log("nono");
            return ResponceApi.error(1, "User not found");
        }
        ProfileResponce profileResponce = new ProfileResponce();
        profileResponce.setFirstname(users.getFirstname());
        profileResponce.setLastname(users.getLastname());
        profileResponce.setEmail(users.getEmail());
        profileResponce.setPhonenumber(users.getPhonenumber());
        profileResponce.setGender(users.getGender());
        profileResponce.setDateofbirth(users.getDateofbirth());
        profileResponce.setStatus(users.getStatus());
        profileResponce.setRole(users.getRole());

        return ResponceApi.success(1, "User profile successfully", profileResponce);
    }


}
