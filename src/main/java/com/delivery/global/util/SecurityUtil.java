package com.delivery.global.util;

import com.delivery.global.config.security.PrincipalDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

    public Long getCurrentMemberId() {
        PrincipalDetails principal =
                (PrincipalDetails)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Long.parseLong(principal.getUsername());
    }
}
