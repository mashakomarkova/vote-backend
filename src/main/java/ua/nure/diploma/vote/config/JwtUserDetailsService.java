package ua.nure.diploma.vote.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.nure.diploma.vote.config.jwt.JwtUserFactory;
import ua.nure.diploma.vote.dto.UserDto;
import ua.nure.diploma.vote.service.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto user = userService.getUser(email);

        if (user == null) {
            throw new UsernameNotFoundException("User with email: "+ email + " Not found");
        }

        return JwtUserFactory.create(user);
    }
}
