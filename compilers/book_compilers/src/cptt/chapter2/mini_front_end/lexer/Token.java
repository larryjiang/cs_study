package cptt.chapter2.mini_front_end.lexer;


public class Token {
	
	protected final Tag tag;
	
	public Token(Tag tag) {
		this.tag = tag;
	}
	
	public String toString() {
		return tag.name();
	}
	
	public Tag getTag() {
		return tag;
	}
	
	public boolean equals(Object t) {
		if(this == t) {
			return true;
		}
		
		if(t == null) {
			return false;
		}
		
		if(this.getClass() != t.getClass()) {
			return false;
		}
		
		Token other = (Token)t;
		
		return this.tag == other.tag;
	}
	
	public int hashCode() {
		String id = this.getClass().getName() + super.hashCode() + tag.hashCode() + tag.getOrdinal() + this.toString();
		return id.hashCode();
	}
	
	// for testing
	public String getIdentityString() {
		String id = this.getClass().getName() + super.hashCode() + tag.hashCode() + tag.getOrdinal() + this.toString();
		return id;
	}
	
}
