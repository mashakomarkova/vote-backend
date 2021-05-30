package ua.nure.diploma.vote.config.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ua.nure.diploma.vote.dto.RoleDto;
import ua.nure.diploma.vote.dto.UserDto;
import java.util.Arrays;
import java.util.List;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(UserDto user) {
        return new JwtUser(user.getId(),
                user.getEmail(),
                user.getPassword(),
                true,
                mapToGrantedAuthorities(user.getRole()));
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(RoleDto role) {
        return Arrays.asList(new SimpleGrantedAuthority(role.getName()));
    }
}
