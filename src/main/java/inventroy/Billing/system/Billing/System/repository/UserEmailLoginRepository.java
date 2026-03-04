package inventroy.Billing.system.Billing.System.repository;

import inventroy.Billing.system.Billing.System.entity.UserEmailLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEmailLoginRepository extends JpaRepository<UserEmailLog,Long> {
}
