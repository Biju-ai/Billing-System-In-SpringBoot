package inventroy.Billing.system.Billing.System.dto.request.users;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadPassword {

    @NotBlank(message = "unique token")
    private String token;

    @NotBlank(message = "New password is required ")
    private String newPassword;

    @NotBlank(message = "The password must match the NewPassword")
    private String confirmPassword;
}
