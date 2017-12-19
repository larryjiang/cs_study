package cptt.chapter2.mini_front_end.lexer;

public class Num extends Token {
	private final int value;
	
	public Num(int v) {
		super(Tag.NUM);
		this.value = v;
	}
	
	public String toString() {
		return String.valueOf(value);
	}
	
	public int value() {
		return value;
	} 
	
}
