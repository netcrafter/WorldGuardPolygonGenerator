package net.goodnightkimba.wgpg.command;

public class StringProcessor {
	
	private String string = "";
	
	public StringProcessor(String string) {
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
