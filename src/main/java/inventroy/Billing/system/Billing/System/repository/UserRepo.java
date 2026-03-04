package inventroy.Billing.system.Billing.System.repository;

import inventroy.Billing.system.Billing.System.entity.Users;
import jakarta.validation.constraints.NotBlank;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,Long> {
    Users findByEmail(String username);


    Users findByVerificationToken(@NotBlank(message = "unique token") String token);
}
