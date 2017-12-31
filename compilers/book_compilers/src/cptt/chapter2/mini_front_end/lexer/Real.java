package cptt.chapter2.mini_front_end.lexer;

public class Real extends Token {
	private final float value;
	
	public Real(float v) {
		super(Tag.REAL);
		this.value = v;
	}
	
	public String toString() {
		return String.valueOf(value);
	}
	
	public float value() {
		return value;
	}
	
	public boolean equals(Real r) {
		if(!super.equals(r)) {
			return false;
		}
		Real other = (Real)r;
		
		return Float.compare(this.value(), other.value()) == 0;
	}
	

}
