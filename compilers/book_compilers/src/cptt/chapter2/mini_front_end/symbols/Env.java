package cptt.chapter2.mini_front_end.symbols;
import cptt.chapter2.mini_front_end.lexer.*;
import cptt.chapter2.mini_front_end.inter.*;
import java.util.HashMap;

public class Env {
	private HashMap<Token, Id> symbolTable;
	
	protected Env prev;
	public Env(Env env) {
		symbolTable =  new HashMap<Token, Id>();
		prev = env;
	}
	
	public void put(Token w, Id id) {
		symbolTable.put(w, id);
	}
	
	public Id get(Token t) {
		for(Env e = this; e != null; e = e.prev) {
			Id found = (Id) e.getSymbolTable().get(t);
			if(found != null) {
				return found;
			}
		}
		return null;
	}
	
	public HashMap<Token, Id> getSymbolTable(){
		return symbolTable;
	}
}
