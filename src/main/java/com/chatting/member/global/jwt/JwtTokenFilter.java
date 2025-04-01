package com.chatting.member.global.jwt;// package com.yanoos.global.jwt;
//
// import com.yanoos.global.jwt.service.JwtTokenService;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;
//
// import java.io.IOException;
// import java.util.Collections;
//
// @RequiredArgsConstructor
// @Component
// @Slf4j
// //OncePerRequestFilter가 스프링 빈인가? 그래서 생성자 주입이 되는건가? 그럼 RequiredArgsConstructor도 가능?
// public class JwtTokenFilter extends OncePerRequestFilter {
//     private final JwtTokenService jwtTokenService;
//     @Override//TODO 필요한 곳에서만 필터 타도록 수정
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//         String jwt = jwtTokenService.getJwtFromRequest(request);
//         log.info("jwt = {}",jwt);
//         if(jwt != null && jwtTokenService.validateToken(jwt)){//jwt 유효성 검증
//             log.info("in");
//             Long memberId = jwtTokenService.getUserIdFromJwt(jwt);//jwt에서 사용자 id 추출
//             CustomPrincipal principal = new CustomPrincipal(); //사용자의 주요 정보와 권한
//             principal.setMemberId(memberId);
//             UsernamePasswordAuthenticationToken authentication  =
//                     new UsernamePasswordAuthenticationToken(principal, null,
//                             Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));//TODO 역할 구현
//             authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//
//             // 인증 세부 정보 설정
//             SecurityContextHolder.getContext().setAuthentication(authentication);
//             // SecurityContext에 인증 정보 저장
//         }
//
//         filterChain.doFilter(request, response); // 다음 필터로 요청과 응답 전달
//     }
//
//
// }
