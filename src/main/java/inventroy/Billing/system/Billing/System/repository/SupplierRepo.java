package inventroy.Billing.system.Billing.System.repository;

import inventroy.Billing.system.Billing.System.entity.EmailTemplates;
import inventroy.Billing.system.Billing.System.entity.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepo extends JpaRepository<Suppliers,Long> {
    Suppliers findByEmail(String name);
}
