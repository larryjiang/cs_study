package cptt.chapter2.mini_front_end.inter;

public class Seq extends Stmt {
	private Stmt first;
	private Stmt second;
	
	public Seq(Stmt s1, Stmt s2) {
		this.first = s1;
		this.second = s2;
	}

	public Stmt getFirst() {
		return first;
	}

	public Stmt getSecond() {
		return second;
	}
	
	public void gen(int begin, int after) {
		if(first == Stmt.NULLSTMT) {
			second.gen(begin, after);;
		}else if(second == Stmt.NULLSTMT) {
			first.gen(begin, after);
		}else {
			int label = newLabel();
			first.gen(begin, label);
			emitLabel(label);
			second.gen(label, after);
		}
	}
	
}
