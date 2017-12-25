package cptt.chapter2.mini_front_end.lexer;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import cptt.chapter2.mini_front_end.symbols.Type;

public class Lexer {
	
	public static int line = 1;
	
	private char peek = ' ';
	
	private InputStream in;
	
	
	private HashMap<String, Word> words = new HashMap<String, Word>();
	
	private void reserve(Word w) {
		words.put(w.getLexeme(),w);
	}
	
	public Lexer() {
		this(System.in);
	}
	
	public Lexer(InputStream in) {
		this.in = in;
		reserve(new Word("if", Tag.IF));
		reserve(new Word("else",Tag.ELSE));
		reserve(new Word("while",Tag.WHILE));
		reserve(new Word("do", Tag.DO));
		reserve(new Word("break",Tag.BREAK));
		reserve(Word.TRUE);
		reserve(Word.FALSE);
		reserve(Type.Int);
		reserve(Type.Char);
		reserve(Type.Bool);
		reserve(Type.Float);
	}
	
	public void readCh() throws IOException {
		peek = (char) in.read();
	}
	
	public boolean readCh(char c) throws IOException{
		readCh();
		if(peek != c) {
			return false;
		};
		peek = ' ';
		return true;
	}
	
	private boolean isSpaceOrTab(int c) {
		return c == ' ' || c == '\t';
	}
	
	private boolean isNewLine(int c) {
		return c == '\n';
	}
	
	private Word ternary(boolean condition, Word a, Word b) {
		return condition ? a :b ;
	}
	
	public Token scan() throws IOException {
		for(;;readCh()) {
			if(isSpaceOrTab(peek)) {
				continue;
			}else if(isNewLine(peek)) {
				line++;
			}else {
				break;
			};
		}
		
		switch( peek ) {
		case '&':
			return ternary(readCh('&'), Word.AND, Word.BITWISEAND);
		case '|':
			return ternary(readCh('|'), Word.OR, Word.BITWISEOR);
		case '=':	
			return ternary(readCh('='),Word.EQ, Word.ASSIGN);
		case '!':
			return ternary(readCh('='), Word.NE, Word.EXCLAMATION);
		case '<':
			return ternary(readCh('='), Word.LE, Word.LT);
		case '>':
			return ternary(readCh('='), Word.GE, Word.GT);
		}
		
		if(Character.isDigit(peek)) {
			StringBuilder b = new StringBuilder();
			do {
				b.append(peek);
				readCh();
			}while(Character.isDigit(peek));
			
			if(peek != '.') {
				int v = Integer.parseInt(b.toString());
				return new Num(v);
			}
			b.append(".");
			readCh();
			while(Character.isDigit(peek)) {
				b.append(peek);
			}
			
			float f = Float.parseFloat(b.toString());
			return new Real(f);
		}
		
		
		if(Character.isLetter(peek)) {
			StringBuffer b = new StringBuffer();
			do {
				b.append(peek);
			}while(Character.isLetterOrDigit(peek));
			
			String s = b.toString();
			Word w = words.get(s);
			if(w != null) {
				return w;
			}
			w = new Word(s, Tag.ID);
			words.put(s, w);
			return w;
		}
		
		Token tok = new RawChar(peek);
		peek = ' ';
		return tok;
	}
	
}
