package cptt.chapter2.easyexpressiontranslator; 
import java.io.*;
public class Postfix{
    public static void main(String args[]) throws IOException{
    	Parser parser = new Parser();
    	parser.expr();
    	System.out.println();
    }
}

class Parser{
    private int lookahead;
    private InputStreamReader inputStreamReader;
    
    public Parser() throws IOException{
    	inputStreamReader =  new InputStreamReader(System.in);
        lookahead = inputStreamReader.read();
    }

    void expr() throws IOException{
    	term();
    	while (true) {
    		if('+' == lookahead){
    			match('+');term();System.out.print('+');
    		}else if ('-' == lookahead) {
				match('-');term();System.out.print('-');
			}else{
				return;
			}
		}
    }
        
    void term() throws IOException{
    	if(Character.isDigit((char) lookahead)){
    		System.out.print((char) lookahead);
    		match(lookahead);
    	}else {
			throw new Error("Syntax Error"); 
		}
    }

    void match(int t) throws IOException{
    	if(lookahead == t){
    		lookahead = inputStreamReader.read();
    	}else {
			throw new Error("Syntax Error");
		}
    }

}
