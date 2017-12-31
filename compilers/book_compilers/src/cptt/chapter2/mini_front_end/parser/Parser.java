package cptt.chapter2.mini_front_end.parser;

import java.io.IOException;

import cptt.chapter2.mini_front_end.inter.Access;
import cptt.chapter2.mini_front_end.inter.And;
import cptt.chapter2.mini_front_end.inter.Arith;
import cptt.chapter2.mini_front_end.inter.Break;
import cptt.chapter2.mini_front_end.inter.Constant;
import cptt.chapter2.mini_front_end.inter.Do;
import cptt.chapter2.mini_front_end.inter.Else;
import cptt.chapter2.mini_front_end.inter.Expr;
import cptt.chapter2.mini_front_end.inter.Unary;
import cptt.chapter2.mini_front_end.inter.Id;
import cptt.chapter2.mini_front_end.inter.If;
import cptt.chapter2.mini_front_end.inter.Not;
import cptt.chapter2.mini_front_end.inter.Or;
import cptt.chapter2.mini_front_end.inter.Rel;
import cptt.chapter2.mini_front_end.inter.Seq;
import cptt.chapter2.mini_front_end.inter.Stmt;
import cptt.chapter2.mini_front_end.inter.Set;
import cptt.chapter2.mini_front_end.inter.SetElem;
import cptt.chapter2.mini_front_end.inter.While;
import cptt.chapter2.mini_front_end.lexer.Lexer;
import cptt.chapter2.mini_front_end.lexer.Tag;
import cptt.chapter2.mini_front_end.lexer.Token;
import cptt.chapter2.mini_front_end.lexer.Word;
import cptt.chapter2.mini_front_end.lexer.Num;
import cptt.chapter2.mini_front_end.lexer.RawChar;
import cptt.chapter2.mini_front_end.symbols.Env;
import cptt.chapter2.mini_front_end.symbols.Type;
import cptt.chapter2.mini_front_end.symbols.Array;

import static cptt.chapter2.mini_front_end.lexer.RawChar.getRawChar;

public class Parser {
	private Lexer lexer;
	private Token lookahead;
	private Env top = null;

	private int used = 0;

	public Parser(Lexer l) throws IOException {
		this.lexer = l;
		move();
	}

	private void move() throws IOException {
		lookahead = lexer.scan();
	}

	public void error(String s) {
		throw new Error("new line " + Lexer.line + ": " + s);
	}

	public void match(Token t) throws IOException {
		if (lookahead.equals(t)) {
			move();
		} else {
			error("syntax error");
		}
	}

	public void match(Tag tag) throws IOException {
		if (lookahead.getTag() == tag) {
			move();
		} else {
			error("syntax error");
		}
	}

	public void program() throws IOException {
		Stmt s = block();
		int begin = s.newLabel();
		int after = s.newLabel();
		s.emitLabel(begin);
		s.gen(begin, after);
		s.emitLabel(after);

	}

	private Stmt block() throws IOException {
		match(getRawChar('{'));
		Env saved = top;
		top = new Env(top);
		decls();
		Stmt s = stmts();
		match(getRawChar('}'));
		top = saved;
		return s;
	}

	private void decls() throws IOException {
		while (lookahead.getTag() == Tag.BASIC) {
			Type p = determineType();
			Token tok = lookahead;
			match(Tag.ID);
			match(getRawChar(';'));
			Id id = new Id(tok, p, used);
			top.put(tok, id);
			used = used + p.getWidth();
		}
	}

	private Type determineType() throws IOException {
		Type p = (Type) lookahead;
		move();
		if (!lookahead.equals(getRawChar('['))) {
			return p;
		} else {
			return dims(p);
		}
	}

	private Type dims(Type p) throws IOException {
		match(getRawChar('['));
		Token tok = lookahead;
		match(Tag.NUM);
		match(getRawChar(']'));
		if (lookahead.equals(getRawChar('['))) {
			p = dims(p);
		}
		return new Array(((Num) tok).value(), p);
	}

	private Stmt stmts() throws IOException {
		if (lookahead.equals(getRawChar('}'))) {
			return Stmt.NULLSTMT;
		} else {
			return new Seq(stmt(), stmts());
		}
	}

	private Stmt stmt() throws IOException {
		Expr x;
		Stmt s, s1, s2;
		Stmt savedStmt;

		switch (lookahead.getTag()) {
		case IF:
			match(Tag.IF);
			match(getRawChar('('));
			x = bool();
			match(getRawChar(')'));
			s1 = stmt();
			if (lookahead.getTag() != Tag.ELSE) {
				return new If(x, s1);
			}
			match(Tag.ELSE);
			s2 = stmts();
			return new Else(x, s1, s2);
		case WHILE:
			While whileNode = new While();
			savedStmt = Stmt.ENCLOSING;
			Stmt.ENCLOSING = whileNode;
			match(Tag.WHILE);
			match(getRawChar('('));
			x = bool();
			match(getRawChar(')'));
			s1 = stmt();
			whileNode.init(x, s1);
			Stmt.ENCLOSING = savedStmt;
			return whileNode;
		case DO:
			Do doNode = new Do();
			savedStmt = Stmt.ENCLOSING;
			Stmt.ENCLOSING = doNode;
			match(Tag.DO);
			s1 = stmt();
			match(Tag.WHILE);
			match(getRawChar('('));
			x = bool();
			match(getRawChar(')'));
			match(getRawChar(';'));
			doNode.init(x, s1);
			Stmt.ENCLOSING = savedStmt;
			return doNode;
		case BREAK:
			match(Tag.BREAK);
			match(getRawChar(';'));
			return new Break();
		case RAWCHAR:
			RawChar rc = (RawChar) lookahead;
			if (rc.getChar() == ';') {
				move();
				return Stmt.NULLSTMT;
			}

			if (rc.getChar() == '{') {
				return block();
			}
		default:
			return assign();
		}
	}

	private Stmt assign() throws IOException {
		Stmt stmt;
		Token t = lookahead;
		match(Tag.ID);
		Id id = top.get(t);
		if (id == null) {
			error(t.toString() + " undeclared");
		}
		if (lookahead.getTag() == Tag.ASSIGN) {
			move();
			stmt = new Set(id, bool());
		} else {
			Access x = offset(id);
			match(Word.ASSIGN);
			stmt = new SetElem(x, bool());
		}
		match(getRawChar(';'));
		return stmt;
	}

	private Expr bool() throws IOException {
		Expr x = join();
		while (lookahead.getTag() == Tag.OR) {
			Token tok = lookahead;
			move();
			x = new Or(tok, x, join());
		}
		return x;
	}

	private Expr join() throws IOException {
		Expr x = equality();
		while (lookahead.getTag() == Tag.AND) {
			Token tok = lookahead;
			move();
			x = new And(tok, x, equality());
		}
		return x;
	}

	private Expr equality() throws IOException {
		Expr x = rel();
		while (lookahead.getTag() == Tag.EQ || lookahead.getTag() == Tag.NE) {
			Token tok = lookahead;
			move();
			x = new Rel(tok, x, rel());
		}
		return x;
	}

	private Expr rel() throws IOException {
		Expr x = expr();
		switch (lookahead.getTag()) {
		case LT:
		case LE:
		case GE:
		case GT:
			Token tok = lookahead;
			move();
			return new Rel(tok, x, expr());
		default:
			return x;
		}
	}
	
	private Expr expr() throws IOException {
		Expr x = term();
		while(lookahead.equals(getRawChar('+')) || lookahead.equals(getRawChar('-'))) {
			Token tok = lookahead;
			move();
			x = new Arith(tok,x,term());
		}
		return x;
	}
	
	private Expr term() throws IOException {
		Expr x = unary();
		while(lookahead.equals(getRawChar('*')) || lookahead.equals(getRawChar('/'))) {
			Token tok = lookahead;
			move();
			x = new Arith(tok,x,unary());
		}
		return x;
	}
	
	private Expr unary() throws IOException {
		if(lookahead.equals(getRawChar('-'))) {
			move();
			return new Unary(Word.MINUS, unary());
		}else if(lookahead.equals(getRawChar('!'))) {
			Token t = lookahead;
			move();
			return new Not(t,unary());
		}else {
			return factor();
		}
	}
	
	private Expr factor() throws IOException {
		Expr x = null;
		switch(lookahead.getTag()) {
			case NUM:
				x = new Constant(lookahead, Type.Int);
				move();
				return x;
			case REAL:
				x = new Constant(lookahead, Type.Float);
				move();
				return x;
			case TRUE:
				x = Constant.getContantTrue();
				move();
				return x;
			case FALSE:
				x = Constant.getConstatnFlase();
				move();
				return x;
			case ID:
				String s = ((Word)lookahead).getLexeme();
				Id id = top.get(lookahead);
				if(id == null) {
					error(s + " undeclared");
				}
				move();
				if(!lookahead.equals(getRawChar('['))) {
					return id;
				}else {
					return offset(id);
				}
			case RAWCHAR:
				RawChar rc = (RawChar) lookahead;
				if(rc.getChar() == '(') {
					move();
					x = bool();
					match(getRawChar(')'));
					return x;
				}
			default:
				error("syntax error");
				return x;
			
		}
		
	}
	
	Access offset(Id a) throws IOException {
		Expr i;
		Expr w;
		Expr t1, t2;
		Expr loc;
		Type type = a.getType();
		match(getRawChar('['));
		i = bool();
		match(getRawChar(']'));
		type  = ((Array)type).getType();
		w = new Constant(type.getWidth());
		t1 = new Arith(getRawChar('*'), i, w);
		loc = t1;
		while(lookahead.equals(getRawChar('['))) {
			match(getRawChar('['));
			i = bool();
			match(getRawChar(']'));
			w = new Constant(type.getWidth());
			t1 = new Arith(getRawChar('*'), i, w);
			t2 = new Arith(getRawChar('+'), loc, t1);
			loc = t2;
		}
		return new Access(a, loc, type);
	}
	
	

}
