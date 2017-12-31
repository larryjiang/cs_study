package cptt.lexer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Trie{
	private Set<String> stringSet;
	private TrieNode root = new TrieNode(null,0);
	private int maxState = 0;
	private Map<Integer, TrieNode> stateNodeMap = new HashMap<Integer,TrieNode>();
	
	public Trie(Set<String> ss){
		stringSet = ss;
	}
	
	public void initialize(){
		root.setParent(null);
		stateNodeMap.put(root.getState(), root);
		this.constructTrieFromStringSet();
	}
	
	private void constructTrieFromStringSet(){
		for(String s:stringSet){
			TrieNode currentNode = root;
			for(Character c : s.toCharArray()){
				if(currentNode.getCharacterMap() == null){
					TrieNode node = new TrieNode(c, ++maxState);
					currentNode.createNextCharacterMap();
					currentNode.addNextCharacterNode(node);
					stateNodeMap.put(node.getState(), node);
					node.setParent(currentNode);
				}else{
					if(currentNode.getCharacterMap().get(c) == null){
						TrieNode node = new TrieNode(c, ++maxState);
						currentNode.addNextCharacterNode(node);
						stateNodeMap.put(node.getState(), node);
						node.setParent(currentNode);
					}
				}
				currentNode = currentNode.getCharacterMap().get(c);
			}
			currentNode.setAccept(true);
		}
	}
	
	public int[] constructFailureFunction(){
		int[] result = new int[maxState + 1];
		if(root.getCharacterMap() == null){
			return null;
		}
		
		for(Character c : root.getCharacterMap().keySet()){
			result[root.getCharacterMap().get(c).getState()] = 0;
			System.out.println(root.getCharacterMap().get(c).getState());
		}
		TrieNode preNode;
		for(int i = 1; i <= maxState; i++){
			TrieNode parent = stateNodeMap.get(i).getParent();
			if(parent == root){
				continue;
			}else{
				preNode = stateNodeMap.get(result[parent.getState()]);
				tag1:while(preNode != null){
					for(Character c : preNode.getCharacterMap().keySet()){
						if(c.equals(stateNodeMap.get(i).getCharOfNode()) ){
							result[i] = preNode.getNextCharacterNode(c).getState();
							break tag1;
						}
					}
					if(preNode == root){
						result[i] = 0;
						break tag1;
					}
					preNode = stateNodeMap.get(result[preNode.getState()]);
				}
			}
		}
		return result;
	}	
	
	public static void main(String[] args){
		String s1 = "he";
		String s2 = "she";
		String s3 = "his";
		String s4 = "hers";
		Set<String> set = new HashSet<String>();
		set.add(s1);
		set.add(s2);
		set.add(s3);
		set.add(s4);
 		Trie tire = new Trie(set);
		tire.initialize();
		for(int i = 0; i <= tire.maxState; i++){
			TrieNode node = tire.stateNodeMap.get(i);
			System.out.println(i +  "------->" + node.getCharOfNode() + ",with parent ------>," + (node.getParent() == null ? null :node.getParent().getState()) + "is accept node ? " + (node.isAccept() ? true : false));
		}
		
		int[] result = tire.constructFailureFunction();
		for(int i = 1; i <= tire.maxState; i++){
			System.out.println(i + "---------->" + result[i]);
		}
		
	}
	
	
	
}

class TrieNode{
	private int state;
	private Character charOfNode;
	boolean accept = false;
	private Map<Character, TrieNode> nextCharacterMap; 
	private TrieNode parent;
	
	
	
	public TrieNode(Character c, int state){
		this.state = state;
		this.charOfNode = c;
	}
	
	public int getState(){
		return state;
	}
	
	public void setState(int state){
		this.state = state;
	}
	
	public boolean isAccept(){
		return accept;
	}
	
	public void setAccept(boolean accept){
		this.accept = accept;
	}
	public Map<Character, TrieNode> getCharacterMap(){
		return nextCharacterMap;
	}
	
	public void createNextCharacterMap(){
		nextCharacterMap = new HashMap<Character,TrieNode>();
	}
	
	public TrieNode getNextCharacterNode(Character c){
		return nextCharacterMap.get(c);
	}
	
	public void addNextCharacter(Character c, int state){
		TrieNode node = new TrieNode(c, state).setParent(this);
		nextCharacterMap.put(c, node);
	}
	
	public void addNextCharacterNode(TrieNode node){
		nextCharacterMap.put(node.getCharOfNode(), node);
	}
	
	public TrieNode getParent(){
		return parent;
	}
	
	public TrieNode setParent(TrieNode p){
		this.parent = p;
		return this;
	}
	
	public Character getCharOfNode(){
		return charOfNode;
	}
	
	public TrieNode setCharOfNode(Character c){
		this.charOfNode = c;
		return this;
	}
}