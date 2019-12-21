package by.htp.ts.command;

import java.util.HashMap;
import java.util.Map;
import by.htp.ts.command.impl.*;

public final class CommandProvider {
	private Map<CommandName, Command> commands=new HashMap<CommandName,Command>();
	
	public CommandProvider() {
		commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.LOGOUT, new LogOutCommand());
		
		commands.put(CommandName.GO_TO_AUTHORIZATION_PAGE, new GoToAuthorizationPage());
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
		commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
		
		commands.put(CommandName.CHANGE_LANGUAGE, new ChangeLanguage());
	}
	
	public Command getCommand(String commandStr) {
		CommandName commandName=CommandName.valueOf(commandStr.toUpperCase());
		return commands.get(commandName);
	}

}
