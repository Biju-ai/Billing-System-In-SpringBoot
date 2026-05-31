package inventroy.Billing.system.Billing.System.services.impl;

import inventroy.Billing.system.Billing.System.entity.Users;
import inventroy.Billing.system.Billing.System.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    public  final UserRepo userRepo;

    public MyUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users=userRepo.findByEmail(username);
        if(users == null){
            System.out.println("in the Load user by username.");
            throw new UsernameNotFoundException("User not found");
        }
        return  new org.springframework.security.core.userdetails.User(
                users.getEmail(),
                users.getPassword(),
                users.getAuthorities()
        );
    }
}
