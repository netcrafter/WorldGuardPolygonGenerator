package net.goodnightkimba.wgpg.command;

import net.goodnightkimba.wgpg.Config;
import net.goodnightkimba.wgpg.command.StringProcessor;

public class UserPermissionException extends Exception {

	private static final long serialVersionUID = 3761616429918364774L;
	
	private String errorMessage;

	public UserPermissionException() {
		this.errorMessage = Config.getString("permission-denided-cmd");
		
		StringProcessor sp = new StringProcessor(this.errorMessage);
		sp.processColor();
		this.errorMessage = sp.getString();
	}

	public String getMessage() {
		return this.errorMessage;
	}
}