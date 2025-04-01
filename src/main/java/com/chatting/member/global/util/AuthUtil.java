package com.chatting.member.global.util;

import com.chatting.member.global.exception.BusinessException;
import com.chatting.member.global.exception.code.CommonErrorCode;
import com.chatting.member.global.jwt.CustomPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {
    public Long getMemberId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            CustomPrincipal principal = (CustomPrincipal) authentication.getPrincipal();
            // principal에서 userId, groupId, personaId, authName 등에 접근 가능
            return principal.getMemberId();
        }
        throw new BusinessException(CommonErrorCode.MEMBER_NOT_FOUND);
    }
}
