package cptt.chapter2.mini_front_end.inter;

import cptt.chapter2.mini_front_end.lexer.Lexer;

public class Node {
	private int lexline = 0;
	public static int labels = 0;
	
	public Node() {
		lexline = Lexer.line;
	}
		
	public void error(String s) {
		throw new Error("near line " + lexline + ": " + s);
	}
	
	public int newLabel() {
		return ++labels;
	}
	
	public void emitLabel(int i) {
		System.out.print("L" + i + ":");
	}
	
	public void emit(String s) {
		System.out.println("\t" + s);
	}
	
}
