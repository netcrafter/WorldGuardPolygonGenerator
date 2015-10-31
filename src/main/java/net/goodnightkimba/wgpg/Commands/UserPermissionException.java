package net.goodnightkimba.WorldGuardPolygonGenerator.Commands;

import net.goodnightkimba.WorldGuardPolygonGenerator.Config;

public class UserPermissionException extends Exception {

	private static final long serialVersionUID = 3761616429918364774L;
	
	private String errorMessage;

	public UserPermissionException() {
		this.errorMessage = Config.getString("permission-denided-cmd");
		
		StringProcesser sp = new StringProcesser(this.errorMessage);
		sp.processColor();
		this.errorMessage = sp.getString();
	}


	public String getMessage() {
		return this.errorMessage;
	}
}
