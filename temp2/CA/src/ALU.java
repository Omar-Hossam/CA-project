public class ALU {
	public String performOperation(String data1, String data2, int fifi){
		long temp = 0;
		long x;
		long y;
		String binaryValue;
		switch (fifi){
			case 100000: 
				temp = Long.parseLong(data1,2)+Long.parseLong(data2,2);
				binaryValue = Long.toBinaryString(temp);
				return binaryValue;
			case 000000:
			    x= convert_to_long(data1);
			    y= convert_to_long(data2);
				temp = x << y;
				binaryValue = Long.toBinaryString(temp);
				return binaryValue;
			case 000010:
				x= convert_to_long(data1);
				y= convert_to_long(data2);
				temp =x >>> y;
				binaryValue = Long.toBinaryString(temp);
				return binaryValue;
			case 100111:
				temp = (Long.parseLong(data1,2)|Long.parseLong(data2,2));
				String temp2 = Long.toBinaryString(temp);
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
				temp = Long.parseLong(data1,2) | Long.parseLong(data2,2);
				String orValue = Long.toBinaryString(temp);
				return orValue;
			case 100010:
				temp = Long.parseLong(data1,2) - Long.parseLong(data2,2);
				binaryValue = Long.toBinaryString(temp);
				return binaryValue; 
		}
		return "";
	}
	public static long convert_to_long(String x){
		long sum=0;
		String z="";
		long y=0;
		for(int i=0;i<x.length();i++){
			z=z+x.charAt(i);
			y=Long.parseLong(z, 2);
			sum=sum+((long) (Math.pow(2, x.length()-i-1))*y);
			z="";
		}
			return sum;
	}
	
}
