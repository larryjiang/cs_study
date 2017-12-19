package cptt.chapter2.mini_front_end.lexer;

import java.util.HashMap;
import java.util.Map;

public class Word extends Token{
	private final String lexeme;
	
	public Word(String s, Tag tag) {
		super(tag);
		this.lexeme = s;
	}
	
	public String toString() {
		return lexeme;
	}
	
	public static final Word AND = new Word("&&",Tag.AND);
	public static final Word EQ = new Word("==", Tag.EQ);
	public static final Word NE = new Word("!=", Tag.NE);
	public static final Word LE = new Word("<=", Tag.LE);
	public static final Word GE = new Word(">=", Tag.GE);
	public static final Word MINUS = new Word("minus", Tag.MINUS);
	public static final Word TRUE = new Word("true",Tag.TRUE);
	public static final Word FALSE = new Word("false",Tag.FALSE);
	public static final Word TEMP = new Word("temp",Tag.TEMP);
	
	public static final Map<Tag,Word> PREDEFINEDWORDS = new HashMap<>(); 
	static {
		Word[] t = new Word[] {AND, EQ, NE, LE, GE, MINUS, TRUE, FALSE, TEMP};
		for(Word w : t) {
			PREDEFINEDWORDS.put(w.getTag(), w);
		}
	}
	
	public Word getPredefinedWordByTag(Tag tag) {
		return PREDEFINEDWORDS.get(tag);
	}
	
	
}
