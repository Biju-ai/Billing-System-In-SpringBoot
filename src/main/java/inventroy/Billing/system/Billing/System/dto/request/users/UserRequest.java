package inventroy.Billing.system.Billing.System.dto.request.users;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserRequest {

    private String firstname;

    private String lastname;

    private String email;

    private String phonenumber;

    private String password;

    private LocalDate dateofbirth;

    private String gender;

    private String status;

    private Long role;


}
