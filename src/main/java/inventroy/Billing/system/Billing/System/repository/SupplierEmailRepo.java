package inventroy.Billing.system.Billing.System.repository;

import inventroy.Billing.system.Billing.System.entity.SupplierEmailLog;
import inventroy.Billing.system.Billing.System.entity.Suppliers;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierEmailRepo extends JpaRepository<SupplierEmailLog,Long> {
}
