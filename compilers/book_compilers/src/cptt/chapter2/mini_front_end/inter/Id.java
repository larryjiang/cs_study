package cptt.chapter2.mini_front_end.inter;

import cptt.chapter2.mini_front_end.lexer.Token;
import cptt.chapter2.mini_front_end.symbols.Type;

public class Id extends Expr{
	private int offset;
	
	Id(Token tok, Type p, int b) {
		super(tok, p);
		this.offset = b;
	}
	
	public int getOffset() {
		return offset;
	}
}
