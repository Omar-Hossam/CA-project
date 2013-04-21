import java.util.ArrayList;

public class DM {
	ArrayList<String> memory = new ArrayList<String>();
	ArrayList<String> index = new ArrayList<String>(); 
	public String readData(String address){
		if (index.contains(address)) {
			int i = index.indexOf(address);
			return memory.get(i);
		} else {
			return "null";
		}
	}
	
	public void writeData(String address, String data){
		index.add(address);
		memory.add(data);
	}
}
