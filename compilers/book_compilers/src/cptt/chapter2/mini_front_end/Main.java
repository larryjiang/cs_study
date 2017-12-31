package cptt.chapter2.mini_front_end;


import java.io.IOException;
import java.io.InputStream;

import cptt.chapter2.mini_front_end.lexer.Lexer;
import cptt.chapter2.mini_front_end.parser.Parser;

public class Main {
	public static void main(String args[]) throws IOException {
		InputStream fis = Main.class.getResourceAsStream("test");
		Lexer lexer = new Lexer(fis);
		Parser parser = new Parser(lexer);
		parser.program();
		System.out.println();
	}

}
