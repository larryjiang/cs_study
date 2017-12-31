package cptt.chapter2.mini_front_end.inter;

import cptt.chapter2.mini_front_end.lexer.Token;
import cptt.chapter2.mini_front_end.symbols.Type;

public class Expr extends Node{
	protected Token op;
	protected Type type;
	
	Expr(Token tok, Type p){
		this.op = tok;
		this.type = p;
	}
	
	public Expr gen() {
		return this;
	}
	
	public Expr reduce() {
		return this;
	}
	
	public void jumping(int t, int f) {
		emitJumps(this.toString(),t,f);
	}
	
	public void emitJumps(String test, int t, int f) {
		if(t != 0 && f != 0) {
			emit("if " + test + " goto L" + t);
			emit("goto L" + f);
		}else if(t != 0) {
			emit("if " + test + " goto L" + t);
		}else if(f != 0) {
			emit("iffalse " + test + " goto L" + f);
		}else {
			;
		}
	}
	
	public String toString() {
		return op.toString();
	}
	
	public Token getOp() {
		return op;
	}
	
	public Type getType() {
		return type;
	}
	
}
