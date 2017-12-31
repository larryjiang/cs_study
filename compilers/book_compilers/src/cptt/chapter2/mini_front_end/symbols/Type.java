package cptt.chapter2.mini_front_end.symbols;

import cptt.chapter2.mini_front_end.lexer.Tag;
import cptt.chapter2.mini_front_end.lexer.Word;

public class Type extends Word{
	private int width = 0;

	public Type(String s, Tag tag, int w) {
		super(s, tag);
		this.width = w;
	}
	
	public int getWidth() {
		return width;
	}
	
	public String toString() {
		return super.toString() + width;
	}
	
	public static final Type  Int = new Type("int", Tag.BASIC, 4);
	public static final Type Float = new Type("float", Tag.BASIC, 8);
	public static final Type Char = new Type("char", Tag.BASIC, 1);
	public static final Type Bool = new Type("bool", Tag.BASIC, 1);
	
	public static boolean numeric(Type p) {
		if(p == Type.Char || p == Type.Int || p == Type.Float) {
			return true;
		}else {
			return false;
		}
	}
	
	public static Type max(Type p1, Type p2) {
		if(!numeric(p1) || !numeric(p2)) {
			return null;
		}else if( p1 == Type.Float || p2 == Type.Float) {
			return Type.Float;
		}else if(p1 == Type.Int || p2 == Type.Int) {
			return Type.Int;
		}else {
			return Type.Char;
		}
	}
	
	public boolean equals(Object t) {
		if(!super.equals(t)) {
			return false;
		}
		Type other = (Type)t;
		return this.getWidth() == other.getWidth();
		
	}
	
}
