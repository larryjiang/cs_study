package cptt.chapter2.mini_front_end.lexer;

public class Token {
	
	private final Tag tag;
	
	public Token(Tag tag) {
		this.tag = tag;
	}
	
	public String toString() {
		return tag.name();
	}
	
	public Tag getTag() {
		return tag;
	}
	
}
