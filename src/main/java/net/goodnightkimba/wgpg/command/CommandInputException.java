package net.goodnightkimba.wgpg.command;

import net.goodnightkimba.wgpg.Config;

public class CommandInputException extends Exception {

	private String errorMessage = "Invalid command. /help for help";

	public CommandInputException(String error, String value, String satsifier, String field) {
		String e = Config.getColorString(error);
		e = e.replace("{field}", field);
		e = e.replace("{value}", value);
		e = e.replace("{satsifier}", satsifier);
		this.errorMessage = e;
	}

	public String getMessage() {
		return this.errorMessage;
	}
}