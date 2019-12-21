package by.htp.ts.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.ts.bean.User;
import by.htp.ts.bean.UserPlusLogPass;
import by.htp.ts.command.Command;
import by.htp.ts.service.ClientService;
import by.htp.ts.service.ServiceException;
import by.htp.ts.service.ServiceFactory;

public class RegistrationCommand implements Command {
	public void execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		System.out.println("RegistrationCommand execute");
		
		String login = request.getParameter(CommandImplParameter.LOGIN);
		String password = request.getParameter(CommandImplParameter.PASSWORD);
		
		User user = new User();
		user.setEmail(request.getParameter(CommandImplParameter.EMAIL));
		user.setName(request.getParameter(CommandImplParameter.NAME));
		user.setSurname(request.getParameter(CommandImplParameter.SURNAME));
		user.setRole(request.getParameter(CommandImplParameter.ROLE));
		user.setSex(request.getParameter(CommandImplParameter.SEX));
		user.setAge(Integer.parseInt(request.getParameter(CommandImplParameter.AGE)));
		
		UserPlusLogPass userPLP=new UserPlusLogPass(user, login, password);
		
		HttpSession session=request.getSession(true);
		
		String goToPage;
		boolean isRegistered;
		try {
			ServiceFactory service=ServiceFactory.getInstance();
			ClientService clientS=service.getClientS();
			isRegistered=clientS.register(userPLP);
			if(isRegistered) {
				goToPage="Controller?command=go_to_authorization_page&message=You have been registered. Please log in";
			}else {
				goToPage="Controller?command=go_to_registration_page&message=Entered login is exist. Please try again.";
			}
		}catch(ServiceException e) {
			//log
			goToPage="Controller?command=go_to_registration_page&message=Error of registration.";
		}
		
		session.setAttribute("goto_request", goToPage);
		response.sendRedirect(goToPage);
		
	}

}
