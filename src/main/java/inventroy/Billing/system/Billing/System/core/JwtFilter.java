package inventroy.Billing.system.Billing.System.core;

import inventroy.Billing.system.Billing.System.services.impl.JwtService;
import inventroy.Billing.system.Billing.System.services.impl.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public  class JwtFilter extends OncePerRequestFilter {

    private final MyUserDetailsService myUserDetailsService;
    private final JwtService jwtService;

    public JwtFilter(MyUserDetailsService myUserDetailsService, JwtService jwtService) {
        this.myUserDetailsService = myUserDetailsService;
        this.jwtService = jwtService;
    }
    @Override
    protected  void doFilterInternal(@NotNull HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Bearer token
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        log.info("Request URI: {}", authHeader);

        String uri = request.getRequestURI();
        log.info("Request URI: {}", uri);



        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
            log.info("username:{}",username + "Inside the auth filter");
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetail = myUserDetailsService.loadUserByUsername(username);

            if (jwtService.validateToken(token, userDetail)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }

        log.info("token:{}",token);

        filterChain.doFilter(request, response);
    }

}
