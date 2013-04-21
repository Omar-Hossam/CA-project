public class ALU {
	public String performOperation(String data1, String data2, int fifi){
		int temp = 0;
		String binaryValue;
		switch (fifi){
			case 100000: 
				temp = Integer.parseInt(data1,2)+Integer.parseInt(data2,2);
				binaryValue = Integer.toBinaryString(temp);
				return binaryValue;
			case 100111:
				temp = (Integer.parseInt(data1)|Integer.parseInt(data2,2));
				String temp2 = temp + "";
				binaryValue = "";
				for(int i = 0; i < temp2.length(); i++ ) {
					if(temp2.charAt(i) == '1') {
						binaryValue += "0";
					} else {
						binaryValue += "1";
					}
				}
				return binaryValue;
			case 100101:
				temp = Integer.parseInt(data1,2) | Integer.parseInt(data2,2);
				String orValue = Integer.toBinaryString(temp);
				return orValue;
		}
		return "";
	}
}
