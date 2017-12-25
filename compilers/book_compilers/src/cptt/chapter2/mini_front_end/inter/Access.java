package cptt.chapter2.mini_front_end.inter;

import cptt.chapter2.mini_front_end.lexer.Tag;
import cptt.chapter2.mini_front_end.lexer.Word;
import cptt.chapter2.mini_front_end.symbols.Type;

public class Access extends Op{
	public Id array;
	public Expr index;
	public Access(Id a, Expr ex, Type t) {
		super(new Word("[]",Tag.INDEX), t);
		array = a;
		index = ex;
	}
	
	public Expr gen() {
		return new Access(array, index.reduce(), getType());
	}
	
	public void jumping(int t, int f) {
		emitJumps(this.reduce().toString(), t, f);
	}
	
	public String toString() {
		return array.toString() + " [ " + index.toString() + " ] ";
	}
	
}
