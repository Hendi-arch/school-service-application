package com.hendi.schoolservice.infrastructure.config.web.security.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hendi.schoolservice.infrastructure.config.web.security.service.MyUserDetailService;
import com.hendi.schoolservice.infrastructure.config.web.security.util.JwtUtils;

@Component
public class SecurityMethodFilter extends OncePerRequestFilter {

	private final MyUserDetailService myUserDetailService;
	private final JwtUtils jwtUtils;

	// Inject dependencies
	public SecurityMethodFilter(
			MyUserDetailService myUserDetailService,
			JwtUtils jwtUtils) {
		this.myUserDetailService = myUserDetailService;
		this.jwtUtils = jwtUtils;
	}

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain)
			throws ServletException, IOException {
		String jwtToken = jwtUtils.parseJwt(request);

		if (StringUtils.hasText(jwtToken) && jwtUtils.validateJwtToken(jwtToken)) {
			String username = jwtUtils.getUserNameFromJwtToken(jwtToken);
			setAuthenticationIfNotExists(request, username);
		}

		filterChain.doFilter(request, response);
	}

	private void setAuthenticationIfNotExists(HttpServletRequest request, String username) {
		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = myUserDetailService.loadUserByUsername(username);

			if (userDetails != null) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
	}

}
