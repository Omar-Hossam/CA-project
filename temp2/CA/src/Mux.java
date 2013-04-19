public class Mux {
	public String select(String a, String b, int s){
		if(s == 0){
			return a;
		}
		else{
			return b;
		}
	}
}
