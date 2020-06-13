package rbs.wg.WorkoutGenerator.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rbs.wg.WorkoutGenerator.model.Admin;
import rbs.wg.WorkoutGenerator.model.AppUser;
import rbs.wg.WorkoutGenerator.repository.AdminRepository;
import rbs.wg.WorkoutGenerator.repository.AppUserRepository;

import java.util.Optional;

@Service
public class UserAndAdminDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<AppUser> user = userRepository.findByEmail(email);

        if(user.isPresent()) {
            return user.get();
        }

        Optional<Admin> admin = adminRepository.findByEmail(email);

        if(admin.isPresent()) {
            return admin.get();
        }

        throw new UsernameNotFoundException("User with email: " + email + " not found");
    }
}
