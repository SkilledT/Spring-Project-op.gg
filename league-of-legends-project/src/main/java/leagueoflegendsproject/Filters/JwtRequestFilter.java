package leagueoflegendsproject.Filters;

import io.jsonwebtoken.MalformedJwtException;
import leagueoflegendsproject.Security.JwtUtil;
import leagueoflegendsproject.Security.MyUserDetailService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private MyUserDetailService myUserDetailService;
    private JwtUtil jwtUtil;

    public JwtRequestFilter(MyUserDetailService myUserDetailService, JwtUtil jwtUtil) {
        this.myUserDetailService = myUserDetailService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            jwt = authorizationHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(jwt);
            } catch (MalformedJwtException ex){
                throw new IOException("Invalid token");
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.myUserDetailService.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwt, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
