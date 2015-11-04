package net.goodnightkimba.wgpg.command;

import net.goodnightkimba.wgpg.Config;

public class CommandInputException extends Exception {

	private static final long serialVersionUID = 7182058260209361641L;
	
	protected String error = "invalidInput";
	protected String errorMessage = "Invalid command. /help for help";
	
	public CommandInputException(String error) {
		this.error = error;
		
		this.errorMessage = Config.getString(error);
		
		StringProcessor sp = new StringProcessor(this.errorMessage);
		sp.processColor();
		this.errorMessage = sp.getString();
	}

	public CommandInputException(String error, String value, String satsifier, String field) {
		this.error = error;
		
		this.errorMessage = Config.getString(error);
		
		StringProcessor sp = new StringProcessor(this.errorMessage);
		sp.processColor();
		sp.processVar("{field}", field);
		sp.processVar("{value}", value);
		sp.processVar("{satsifier}", satsifier);
		this.errorMessage = sp.getString();
	}

	public String getMessage() {
		return this.errorMessage;
	}
}