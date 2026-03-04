package inventroy.Billing.system.Billing.System.entity;

import inventroy.Billing.system.Billing.System.entity.products.Roles;
import inventroy.Billing.system.Billing.System.util.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long usersid;

    @Column(name ="first_name",nullable = false ,length=50)
    private String firstname;

    @Column(name ="last_name",nullable = false ,length=50)
    private String lastname;

    @Column(name ="email",nullable = false ,length=100,unique = true)
    private String email;

    @Column(name="phone_number",nullable = false ,length=10)
    private String phonenumber;

    @Column(name="password",nullable = false ,length=100)
    private String password;

    @Column(name="date_of_birth",nullable = false ,length=100)
    private LocalDate dateofbirth;

    @Column(name="gender",nullable = false ,length=50)
    private String gender;

    @Column(name="verification_token",nullable = false ,length=100)
    private String verificationToken;

    @Column(name="token_expire_date")
    private LocalDateTime tokenExpireDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name="is_admin")
    private  boolean isAdmin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="role_id")
    private Roles role;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        String authority = "ADMIN".equals(role.getRole()) ? "ROLE_ADMIN" : "ROLE_CASHIER";
        return List.of(new SimpleGrantedAuthority(authority));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
