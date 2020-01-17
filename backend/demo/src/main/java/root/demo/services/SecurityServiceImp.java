package root.demo.services;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImp implements SecurityService {

    @Override
    public Boolean hasProtectedAccess(String authority) {

        SimpleGrantedAuthority sga = new SimpleGrantedAuthority(authority);

        return (SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .contains(sga));
    }

}
