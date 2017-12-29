package cptt.chapter2.mini_front_end.inter;

import cptt.chapter2.mini_front_end.lexer.Num;
import cptt.chapter2.mini_front_end.lexer.Real;
import cptt.chapter2.mini_front_end.lexer.Token;
import cptt.chapter2.mini_front_end.lexer.Word;
import cptt.chapter2.mini_front_end.symbols.Type;

public class Constant extends Expr {
	
	public Constant(int i) {
		super(new Num(i), Type.Int);
	}
	
	public Constant(float f) {
		super(new Real(f), Type.Float);
	}
	
	private static final Constant True = new Constant(Word.TRUE,Type.Bool);
	private static final Constant False = new Constant(Word.FALSE, Type.Bool);
	
	public Constant(Token tok, Type p) {
		super(tok, p);
	}
	
	public void jumping(int t, int f) {
		if(this == True && t != 0) {
			emit("goto L" + t);
		}else if (this == False && f != 0) {
			emit("goto L" + f);
		}
	}
	
	public static Constant getContantTrue() {
		return True;
	}
	
	public static Constant getConstatnFlase() {
		return False;
	}
	
}
