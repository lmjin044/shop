package com.shop.Config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //임포트 할 때는 시큐리티코어로 지정되어야 한다.
        String userId="";
        if(authentication !=null){
            userId = authentication.getName();
        }
        return Optional.of(userId);
    }
}
