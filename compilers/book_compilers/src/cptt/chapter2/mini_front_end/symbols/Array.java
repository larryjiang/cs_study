package cptt.chapter2.mini_front_end.symbols;

import cptt.chapter2.mini_front_end.lexer.Tag;

public class Array extends Type {
	private Type of;
	private int size = 1;
	
	public Array(int sz, Type p) {
		super("[]", Tag.INDEX, sz * p.getWidth());
		size = sz;
		of = p;
	}
	
	public String toString() {
		return "[" + size + "] " + of.toString();
	}
	
	public Type getType() {
		return of;
	}
	
	public boolean equals(Object a) {
		if(!super.equals(a)) {
			return false;
		}
		Array other = (Array)a;
		return of.equals(other.getType());
		
	}
}
