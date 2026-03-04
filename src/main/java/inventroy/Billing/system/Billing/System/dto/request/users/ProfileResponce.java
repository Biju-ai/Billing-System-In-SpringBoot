package inventroy.Billing.system.Billing.System.dto.request.users;

import com.fasterxml.jackson.annotation.JsonFormat;
import inventroy.Billing.system.Billing.System.entity.products.Roles;
import inventroy.Billing.system.Billing.System.util.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProfileResponce {
    private String firstname;

    private String lastname;

    private String email;

    private String phonenumber;

    @JsonFormat(pattern = "yyyy-MM-dd")  // 👈 This is crucial
    private LocalDate dateofbirth;

    private String gender;

    private Status status;

    private Roles role;

}
