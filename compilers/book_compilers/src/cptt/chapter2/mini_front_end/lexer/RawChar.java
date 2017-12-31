package cptt.chapter2.mini_front_end.lexer;

import java.util.HashMap;
import java.util.Map;

public class RawChar extends Token {
	
	public static final Map<Integer, RawChar> GLOBALCHARMAP = new HashMap<>(); ;
	
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
	
	public boolean equals(Object rc) {
		if(!super.equals(rc)) {
			return false;
		}
		
		RawChar other = (RawChar) rc;
		
		return this.getChar() == other.getChar();
	}
	
	public static RawChar getRawChar(char c) {
		RawChar rc = GLOBALCHARMAP.get((int)c);
		if(rc == null) {
			rc = new RawChar(c);
			GLOBALCHARMAP.put((int)c, rc);
		}
		return rc;
	}
	
}
