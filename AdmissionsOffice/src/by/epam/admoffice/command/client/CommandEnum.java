package by.epam.admoffice.command.client;

import by.epam.admoffice.command.ActionCommand;
import by.epam.admoffice.command.DeleteStudentsCommand;
import by.epam.admoffice.command.EmptyCommand;
import by.epam.admoffice.command.LanguageCommand;
import by.epam.admoffice.command.LoginCommand;
import by.epam.admoffice.command.LogoutCommand;
import by.epam.admoffice.command.MatriculationCommand;
import by.epam.admoffice.command.RegisterCommand;
import by.epam.admoffice.command.RegisterFormCommand;
import by.epam.admoffice.command.SearchCommand;
import by.epam.admoffice.command.SearchFormCommand;
import by.epam.admoffice.command.UnregisterCommand;
import by.epam.admoffice.command.UnregisterFormCommand;

public enum CommandEnum {

	LOGIN {
		{
			this.command = new LoginCommand();
		}
	},

	LOGOUT {
		{
			this.command = new LogoutCommand();
		}
	},
	REGISTER {
		{
			this.command = new RegisterCommand();
		}
	},
	REGISTERFORM {
		{
			this.command = new RegisterFormCommand();
		}
	},
	EMPTYCOMMAND {
		{
			this.command = new EmptyCommand();
		}
	},
	MATRICULATION {
		{
			this.command = new MatriculationCommand();
		}
	},
	SEARCHFORM {
		{
			this.command = new SearchFormCommand();
		}
	},
	SEARCH {
		{
			this.command = new SearchCommand();
		}
	},
	UNREGISTERFORM {
		{
			this.command = new UnregisterFormCommand();
		}
	},
	UNREGISTER {
		{
			this.command = new UnregisterCommand();
		}
	},
	DELETESTUDENTS {
		{
			this.command = new DeleteStudentsCommand();
		}
	},
	LANGUAGE {
		{
			this.command = new LanguageCommand();
		}
	};
	ActionCommand command;

	public ActionCommand getCurrentCommand() {
		return command;
	}
}
