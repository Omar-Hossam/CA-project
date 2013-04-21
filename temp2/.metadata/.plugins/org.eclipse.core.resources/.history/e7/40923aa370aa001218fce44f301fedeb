import java.util.ArrayList;

public class DM {
	ArrayList<String> memory = new ArrayList<String>();
	public String readData(String address){
		int x = Integer.parseInt(address);
		return memory.get(x/4);
	}
	
	public void writeData(String address, String data){
		int x = Integer.parseInt(address);
		memory.set(x/4, data);
	}
}
