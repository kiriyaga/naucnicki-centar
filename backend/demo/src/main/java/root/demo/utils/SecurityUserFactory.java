package root.demo.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import root.demo.model.SecurityUser;
import root.demo.model.User;

import java.util.Collection;

public class SecurityUserFactory {

    //private static final Logger logger = LoggerFactory.getLogger(SecurityUserFactory.class);

    public static SecurityUser create(User user) {

        Collection<? extends GrantedAuthority> authorities;
        try {
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoleEnum().toString());
        } catch (Exception e) {
            authorities = null;
        }
        return new SecurityUser(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(),
                null, authorities);
    }

}
