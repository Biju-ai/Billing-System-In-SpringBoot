package inventroy.Billing.system.Billing.System.dto.Responce.products;

import inventroy.Billing.system.Billing.System.dto.request.Email.PrepareEmailContent;
import inventroy.Billing.system.Billing.System.entity.UserEmailLog;
import inventroy.Billing.system.Billing.System.entity.Users;
import inventroy.Billing.system.Billing.System.repository.UserEmailLoginRepository;
import inventroy.Billing.system.Billing.System.services.impl.EmailUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerEmailLogo {
    private final EmailUtil emailUtil;
    private final UserEmailLoginRepository userEmailLoginRepository;

    public CustomerEmailLogo(EmailUtil emailUtil, UserEmailLoginRepository userEmailLoginRepository) {
        this.emailUtil = emailUtil;
        this.userEmailLoginRepository = userEmailLoginRepository;
    }


    public UserEmailLog mapToEntity(Users users){
        PrepareEmailContent prepareEmailContent =new PrepareEmailContent();
        prepareEmailContent.setCashierfirstname(users.getFirstname());
        prepareEmailContent.setCashierlastname(users.getLastname());
        prepareEmailContent.setVerificationLink("http://192.168.1.86:3000/updatePassword?token=" + users.getVerificationToken());


        String emailContent = emailUtil.generateEmail(prepareEmailContent);

        UserEmailLog userEmailLog =new UserEmailLog();
        userEmailLog.setUser(users);
        userEmailLog.setEmail(users.getEmail());
        userEmailLog.setMessage(emailContent);
        userEmailLog.setIssend(true);
        userEmailLog.setCreatedAt(LocalDateTime.now());
        userEmailLoginRepository.save(userEmailLog);
        return userEmailLog;
       }

}
