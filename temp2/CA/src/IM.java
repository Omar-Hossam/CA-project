import java.util.ArrayList;

public class IM {
	ArrayList<String> instructions = new ArrayList<String>();
	public String getInstruction(int pc){
		pc = pc / 4;
		return instructions.get(pc);
	}
	public void set(ArrayList<String> a){
		this.instructions = a; 
	}
}
