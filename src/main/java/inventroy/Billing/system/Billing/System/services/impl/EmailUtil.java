package inventroy.Billing.system.Billing.System.services.impl;

import inventroy.Billing.system.Billing.System.dto.request.Email.PrepareEmailContent;
import inventroy.Billing.system.Billing.System.entity.EmailTemplates;
import inventroy.Billing.system.Billing.System.repository.TemplateRepository;
import inventroy.Billing.system.Billing.System.util.Email.EmailContaint;
import freemarker.template.Template;
import org.springframework.stereotype.Component;
import freemarker.template.Configuration;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.HashMap;
import java.util.Map;

@Component
public class EmailUtil {
    private final TemplateRepository templateRepository;
    private final Configuration freemarker;


    public EmailUtil(TemplateRepository templateRepository, Configuration freemarker) {
        this.templateRepository = templateRepository;
        this.freemarker = freemarker;
    }

    public String generateEmail(PrepareEmailContent prepareEmailContent) {

        EmailTemplates templates = templateRepository.findByTemplateName("cashier_template");
        Map<String, Object> map = new HashMap<>();
        map.put(EmailContaint.FIRSTNAME, prepareEmailContent.getCashierfirstname());
        map.put(EmailContaint.LASTNAME, prepareEmailContent.getCashierlastname());
        map.put(EmailContaint.VERIFICATION_LINK, prepareEmailContent.getVerificationLink());

        String emailContent;
        try {
            Template customer = new Template("user", templates.getTemplate(), freemarker);
            emailContent = FreeMarkerTemplateUtils.processTemplateIntoString(customer, map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return emailContent;
    }
}


