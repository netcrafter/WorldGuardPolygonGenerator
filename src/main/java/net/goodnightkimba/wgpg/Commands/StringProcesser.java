package net.goodnightkimba.WorldGuardPolygonGenerator.Commands;

public class StringProcesser {
	
	private String string;
	
	public StringProcesser(String string) {
		this.string = string;
	}
	
	public String getString() {
		return this.string;
	}
	
	public void processColor() {
		String[] codes = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "r", "l", "n", "o", "k", "m"};
		for (int i = 0; i < codes.length; i++) {
			this.string = string.replaceAll("&[" + codes[i] +"]", "\u00A7" + codes[i]);
		}
	}
	
	public void processVar(String var, String replacement) {
		this.string = this.string.replace(var, replacement);
	}
}
