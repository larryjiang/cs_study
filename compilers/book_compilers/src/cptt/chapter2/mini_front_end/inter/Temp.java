package cptt.chapter2.mini_front_end.inter;

import cptt.chapter2.mini_front_end.lexer.Word;
import cptt.chapter2.mini_front_end.symbols.Type;

public class Temp extends Expr{
	public static int count = 0;
	int number = 0;
	public Temp(Type p) {
		super(Word.TEMP, p);
		number = ++count;
	}
	
	public String toString() {
		return "t" + number;
	}
}
