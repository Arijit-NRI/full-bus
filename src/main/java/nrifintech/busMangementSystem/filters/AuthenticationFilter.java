package nrifintech.busMangementSystem.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import nrifintech.busMangementSystem.Service.interfaces.UserService;
import nrifintech.busMangementSystem.exception.ResouceNotFound;
import nrifintech.busMangementSystem.exception.UnauthorizedAction;
import nrifintech.busMangementSystem.repositories.UserRepo;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Get the custom header from the request
		String userName = request.getHeader("email");
		String password = request.getHeader("password");

		String url = request.getRequestURI();
		if (url.equals(new String("/api/v1/user/create"))) {
			filterChain.doFilter(request, response);
			return;
		}
		int userType = 0;

		System.out.println(userType);
		if (url.contains("/admin"))
			userType = 1;

		/*if ((userType == 0 && !userService.checkUser(userName, password)))
			throw new UnauthorizedAction("Missing or invalid Custom-Header header", "undefiened user");
		if ((userType == 1 && !userService.checkAdmin(userName, password)))
			throw new UnauthorizedAction("Missing or invalid Custom-Header header", "undefiened user");*/

		filterChain.doFilter(request, response);
	}
}
