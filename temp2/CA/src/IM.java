import java.util.ArrayList;

public class IM {
	ArrayList<String> instructions = new ArrayList<String>();
	public String getInstruction(long pc){
		pc = pc / 4;
		return instructions.get((int) pc);
	}
	public void addIns(String temp){
		this.instructions.add(temp); 
	}
}
