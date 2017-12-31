package cptt.chapter2.mini_front_end.lexer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestToken {

	@Test
	public void testTokenEquals() {
		Token t1 = new Token(Tag.AND);
		Token t2 = new Token(Tag.AND);
		assertTrue(t1.equals(t2));
	}
	
	@Test
	public void testNumEquals() {
		Num n1 = new Num(1);
		Num n2 = new Num(1);
		Num n3 = new Num(2);
		assertTrue(n1.equals(n2));
		assertFalse(n1.equals(n3));
		Token t1 = new Token(Tag.AND);
		assertFalse(n1.equals(t1));

	}
	
	@Test
	public void testRealEquals() {
		Real r1 = new Real(3.2f);
		Real r2 = new Real(3.2f);
		Real r3 = new Real(3.3f);
		
		assertTrue(r1.equals(r2));
		assertFalse(r2.equals(r3));
		Token t1 = new Token(Tag.AND);
		assertFalse(r1.equals(t1));
	}
	
	@Test
	public void testRawChar() {
		RawChar rc1 = new RawChar('a');
		RawChar rc2 = new RawChar('a');
		RawChar rc3 = new RawChar('c');
		
		assertTrue(rc1.equals(rc2));
		assertFalse(rc2.equals(rc3));
		Token t1 = new Token(Tag.AND);
		assertFalse(rc1.equals(t1));
	}
	
	@Test
	public void testWord() {
		Word w1 = new Word("if", Tag.IF);
		Word w2 = new Word("if", Tag.IF);
		Word w3 = new Word("else", Tag.ELSE);

		assertTrue(w1.equals(w2));
		assertFalse(w2.equals(w3));
		Token t1 = new Token(Tag.AND);
		assertFalse(w1.equals(t1));

	}
	
	@Test
	public void testIdentityString() {
		Token t1 = new Token(Tag.AND);
		Num n1 = new Num(1);
		Real r1 = new Real(3.2f);
		Word w1 = new Word("if", Tag.IF);
		
		System.out.println(t1.getIdentityString());
		assertTrue(t1.getIdentityString().equals(t1.getIdentityString()));
		System.out.println(n1.getIdentityString());
		System.out.println(r1.getIdentityString());
		System.out.println(w1.getIdentityString());

	}
	
	
}
