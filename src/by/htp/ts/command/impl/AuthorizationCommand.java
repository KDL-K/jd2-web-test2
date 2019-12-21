package by.htp.ts.command.impl;

import java.io.IOException;
import by.htp.ts.service.ClientService;
import by.htp.ts.service.ServiceException;
import by.htp.ts.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.ts.bean.User;
import by.htp.ts.command.Command;

public class AuthorizationCommand implements Command {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String ERROR_LOG_PASS="Incorrect login or password";
		final String ERROR_AUTH="Error of authorization";
		
		String login=request.getParameter(CommandImplParameter.LOGIN);
		String password=request.getParameter(CommandImplParameter.PASSWORD);
		
		HttpSession session=request.getSession(true);
		
		String goToPage;
		try {
			ServiceFactory service=ServiceFactory.getInstance();
			ClientService clientS=service.getClientS();
			User user=clientS.signIn(login, password);
			if(user!=null) {
				goToPage="Controller?command=go_to_main_page";
				session.setAttribute("user", user);
				session.setAttribute("goto_request", goToPage);
				
			}else {
				session.setAttribute(CommandImplParameter.ERROR_MESSAGE, ERROR_LOG_PASS);
				goToPage="Controller?command=go_to_error_page";
			}
		}catch(ServiceException e) {
			//log
			session.setAttribute(CommandImplParameter.ERROR_MESSAGE, ERROR_AUTH);
			goToPage="Controller?command=go_to_error_page";
		}
		
		response.sendRedirect(goToPage);
		
	}

}
