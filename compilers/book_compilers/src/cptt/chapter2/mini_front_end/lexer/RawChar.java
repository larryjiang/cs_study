package cptt.chapter2.mini_front_end.lexer;

public class RawChar extends Token {
	
	private char c;
	
	public RawChar(char c) {
		super(Tag.RAWCHAR);
		this.c = c;
	}
	
	public String toString() {
		return String.valueOf(c);
	}
	
	public char getChar() {
		return c;
	}
	
}
