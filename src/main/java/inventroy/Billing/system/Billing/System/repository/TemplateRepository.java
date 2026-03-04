package inventroy.Billing.system.Billing.System.repository;

import inventroy.Billing.system.Billing.System.entity.EmailTemplates;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<EmailTemplates,Long>{

    EmailTemplates findByTemplateName(String templateName);
}
