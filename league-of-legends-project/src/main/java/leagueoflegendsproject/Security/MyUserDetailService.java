package leagueoflegendsproject.Security;

import leagueoflegendsproject.Models.Database.ApplicationUser;
import leagueoflegendsproject.Repositories.ApplicationUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;

    public MyUserDetailService(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserRepository.findById(username)
                .orElseThrow(() -> new IllegalArgumentException("Unappropriated username"));
    }


}
