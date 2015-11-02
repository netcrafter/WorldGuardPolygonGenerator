package net.goodnightkimba.wgpg.commands;

public class StringProcesser {
	
	private String string = "";
	
	public StringProcesser(String string) {
		this.string = string;
	}
	
	public String getString() {
		return this.string;
	}
	
	public void processColor() {
		this.string = this.string.replaceAll("(?i)&([a-fklmno0-9])", "\u00A7$1");
	}
	
	public void processVar(String var, String replacement) {
		this.string = this.string.replace(var, replacement);
	}
}
