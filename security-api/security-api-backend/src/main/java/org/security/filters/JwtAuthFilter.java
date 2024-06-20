package org.security.filters;

import lombok.RequiredArgsConstructor;
import org.security.dao.UserDao;
import org.security.utils.JwtUtils;
import org.springframework.http.HttpHeaders;
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

/**
 *
 * @author tibinatomy
 */
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserDao userDao;
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String userEmail;
        String jwtToken;

        if (null == authHeader || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        jwtToken = authHeader.substring(7);
        userEmail = jwtUtils.extractUsername(jwtToken);
        if (null != userEmail && null == SecurityContextHolder.getContext().getAuthentication()) {
            UserDetails userDetails = userDao.findUserByEmail(userEmail);
            if (jwtUtils.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
