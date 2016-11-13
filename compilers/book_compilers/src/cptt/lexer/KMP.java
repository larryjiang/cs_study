package cptt.lexer;

import java.util.ArrayList;
import java.util.List;



public class KMP{
	public static int[] next(String pattern){
		int length = pattern.length();
		
		if (length == 0) {
			throw new IllegalArgumentException("Pattern should not be an empty string.");
		}
		
		int next[] = new int[length];
		
		next[0] = -1;
		
		for(int i = 1; i < pattern.length(); i++){
			int pre = next[i - 1];
			
			while((pre >= 0) && (pattern.charAt(pre + 1) != pattern.charAt(i))){
				pre = next[pre];
			}
			
			if(pre == -1){
				if(pattern.charAt(0) == pattern.charAt(i)){
					next[i] = 0;
				}else{
					next[i] = -1;
				}
			}else{
				next[i] = pre + 1;
			}
		}
		return next;
	}
	
	public static int[] look(String pattern){
		int length = pattern.length();
		
		if (length == 0) {
			throw new IllegalArgumentException("Pattern should not be an empty string.");
		}
		int[] next = new int[pattern.length()];
		
		int t = -1;
		next[0] = -1;
		for(int i = 0; i < pattern.length() - 1; i++){
			while( (t > -1) && pattern.charAt(i + 1) != pattern.charAt(t + 1)){
				t = next[t];
			}
			if(pattern.charAt(i+1) == pattern.charAt(t + 1)){
				t = t + 1;
				next[i + 1] = t;
			}else{
				next[i + 1] = -1;
			}
		}
		return next;
	}
	
	public static List<Integer> match(String toMatch, String pattern){
		if(pattern.length() > toMatch.length()){
			throw new IllegalArgumentException();
		}
		
		int[] next = look(pattern);
		ArrayList<Integer> r = new ArrayList<>();
		int mIndex = 0;
		//int pIndex = 0;
		int pIndex = -1;
		int end = toMatch.length() - pattern.length() + 1;
		while(mIndex < toMatch.length()){
			while((pIndex > -1) && (toMatch.charAt(mIndex) != pattern.charAt(pIndex + 1))){
				pIndex = next[pIndex];
			}
			if(toMatch.charAt(mIndex++) == pattern.charAt(pIndex + 1)){
				pIndex = pIndex + 1;
			}
			if(pIndex  == pattern.length() - 1){
				r.add(mIndex - pattern.length());
			}
		}
		return r;
	}
	
	public static void main(String args[]){
		String pattern = "ababaa";
		if(args.length == 1){
			pattern = args[0];
		}
		String toMatch = "ababaa";
		if(args.length == 2){
			toMatch = args[0];
			pattern = args[1];
		}
		
		int[] output = look(pattern);
			
		for(int i = 0; i < output.length; i++){
			System.out.print(i + "\t");
		}
		System.out.println();
		for(int i : output){
			System.out.print(i + "\t");
		}
		
		List<Integer> result = match(toMatch, pattern);
		System.out.println();
		for(int i : result){
			System.out.print(i + "\t");
		}
		
		System.out.println();
	}
	
	
	
}
