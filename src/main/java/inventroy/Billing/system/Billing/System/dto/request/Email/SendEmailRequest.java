package inventroy.Billing.system.Billing.System.dto.request.Email;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailRequest {
    @NotBlank(message="invalid email formatted.")
    private String reciverEmail;
    @NotBlank(message = "message shoud be needed")
    private String message;
    @NotBlank(message="messsage should be needed")
    private String subject;
}
