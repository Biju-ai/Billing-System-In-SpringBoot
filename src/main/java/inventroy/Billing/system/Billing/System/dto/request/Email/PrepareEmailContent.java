package inventroy.Billing.system.Billing.System.dto.request.Email;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrepareEmailContent {
    private String verificationLink;
    private String cashierfirstname;
    private String cashierlastname;
}
