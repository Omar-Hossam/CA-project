import java.util.ArrayList;

public class CA {
	int PC;
	int n;
	String $t1;
	String $t2;
	String $t3;
	static int counter = 0;

	/*
	 * void advance_pc (int offset) { PC = n*PC; int p = n*PC; p += offset; }
	 */
	public String translate(String line, ArrayList labels) {
		String s[] = line.split(" ");
		helper(s);
		String opcode = "";
		String x;
		String rs;
		String rd;
		String rt;
		String shamt = "";

		if (s[0].equals("add")) {
			x = "100000";
			shamt = "00000";
			rd = s[1];
			rs = s[2];
			rt = s[3];
			opcode = "000000" + " " + rs + " " + rt + " " + rd + " " + shamt
					+ " " + x;

		} else if (s[0].equals("addi")) {
			x = "001000";
			rt = s[1];
			rs = s[2];
			String p = Integer.toBinaryString(Integer.parseInt(s[3]));
			for (int i = p.length(); i < 16; i++) {
				p = "0" + p;
			}
			opcode = x + " " + rs + " " + rt + " " + p;

		} else if (s[0].equals("sub")) {
			x = "100010";
			shamt = "00000";
			rd = s[1];
			rs = s[2];
			rt = s[3];
			opcode = "000000" + " " + rs + " " + rt + " " + rd + " " + shamt
					+ " " + x;

		} else if (s[0].equals("lw")) {
			x = "100011";
			rt = s[1];
			String[] piky = s[2].split("\\(");
			String p = Integer.toBinaryString(Integer.parseInt(piky[0]));
			for (int i = p.length(); i < 16; i++) {
				p = "0" + p;
			}
			String[] w = piky[1].split("\\)");
			helper(w);
			rs = w[0];
			opcode = x + " " + rs + " " + rt + " " + p;

		} else if (s[0].equals("lh")) {
			x = "100001";
			rt = s[1];
			String[] piky = s[2].split("\\(");
			String p = Integer.toBinaryString(Integer.parseInt(piky[0]));
			for (int i = p.length(); i < 16; i++) {
				p = "0" + p;
			}
			String[] w = piky[1].split("\\)");
			helper(w);
			rs = w[0];
			opcode = x + " " + rs + " " + rt + " " + p;
		} else if (s[0].equals("lhu")) {
			x = "100101";
			rt = s[1];
			String[] piky = s[2].split("\\(");
			String p = Integer.toBinaryString(Integer.parseInt(piky[0]));
			for (int i = p.length(); i < 16; i++) {
				p = "0" + p;
			}
			String[] w = piky[1].split("\\)");
			helper(w);
			rs = w[0];
			opcode = x + " " + rs + " " + rt + " " + p;
		} else if (s[0].equals("lb")) {
			x = "100000";
			rt = s[1];
			String[] piky = s[2].split("\\(");
			String p = Integer.toBinaryString(Integer.parseInt(piky[0]));
			for (int i = p.length(); i < 16; i++) {
				p = "0" + p;
			}
			String[] w = piky[1].split("\\)");
			helper(w);
			rs = w[0];
			opcode = x + " " + rs + " " + rt + " " + p;
		} else if (s[0].equals("lbu")) {
			x = "100100";
			rt = s[1];
			String[] piky = s[2].split("\\(");
			String p = Integer.toBinaryString(Integer.parseInt(piky[0]));
			for (int i = p.length(); i < 16; i++) {
				p = "0" + p;
			}
			String[] w = piky[1].split("\\)");
			helper(w);
			rs = w[0];
			opcode = x + " " + rs + " " + rt + " " + p;
		} else if (s[0].equals("sw")) {
			x = "101011";
			rt = s[1];
			String[] piky = s[2].split("\\(");
			String p = Integer.toBinaryString(Integer.parseInt(piky[0]));
			for (int i = p.length(); i < 16; i++) {
				p = "0" + p;
			}
			String[] w = piky[1].split("\\)");
			helper(w);
			rs = w[0];
			opcode = x + " " + rs + " " + rt + " " + p;
		} else if (s[0].equals("sh")) {
			x = "101001";
			rt = s[1];
			String[] piky = s[2].split("\\(");
			String p = Integer.toBinaryString(Integer.parseInt(piky[0]));
			for (int i = p.length(); i < 16; i++) {
				p = "0" + p;
			}
			String[] w = piky[1].split("\\)");
			helper(w);
			rs = w[0];
			opcode = x + " " + rs + " " + rt + " " + p;
		} else if (s[0].equals("sb")) {
			x = "101000";
			rt = s[1];
			String[] piky = s[2].split("\\(");
			String p = Integer.toBinaryString(Integer.parseInt(piky[0]));
			for (int i = p.length(); i < 16; i++) {
				p = "0" + p;
			}
			String[] w = piky[1].split("\\)");
			helper(w);
			rs = w[0];
			opcode = x + " " + rs + " " + rt + " " + p;
		} else if (s[0].equals("lui")) {
			x = "001111";
			rt = s[1];
			String p = Integer.toBinaryString(Integer.parseInt(s[2]));
			for (int i = p.length(); i < 16; i++) {
				p = "0" + p;
			}
			opcode = x + " " + "00000" + " " + rt + " " + p;

		} else if (s[0].equals("sll")) {
			x = "000000";
			rs = s[1];
			rt = s[2];
			String p = Integer.toBinaryString(Integer.parseInt(s[3]));
			for (int i = p.length(); i < 5; i++) {
				p = "0" + p;
			}
			opcode = x + " " + "00000" + " " + rt + " " + rs + " " + p + " "
					+ x;

		} else if (s[0].equals("srl")) {
			x = "000010";
			rs = s[1];
			rt = s[2];
			String p = Integer.toBinaryString(Integer.parseInt(s[3]));
			for (int i = p.length(); i < 5; i++) {
				p = "0" + p;
			}
			opcode = "000000" + " " + "00000" + " " + rt + " " + rs + " " + p
					+ " " + x;
		} else if (s[0].equals("and")) {
			x = "100100";
			shamt = "00000";
			rd = s[1];
			rs = s[2];
			rt = s[3];
			opcode = "000000" + " " + rs + " " + rt + " " + rd + " " + shamt
					+ " " + x;
		} else if (s[0].equals("andi")) {
			x = "001100";
			rt = s[1];
			rs = s[2];
			String p = Integer.toBinaryString(Integer.parseInt(s[3]));
			for (int i = p.length(); i < 16; i++) {
				p = "0" + p;
			}
			opcode = x + " " + rs + " " + rt + " " + p;

		} else if (s[0].equals("or")) {
			x = "100101";
			shamt = "00000";
			rd = s[1];
			rs = s[2];
			rt = s[3];
			opcode = "000000" + " " + rs + " " + rt + " " + rd + " " + shamt
					+ " " + x;
		} else if (s[0].equals("ori")) {
			x = "001101";
			rt = s[1];
			rs = s[2];
			String p = Integer.toBinaryString(Integer.parseInt(s[3]));
			for (int i = p.length(); i < 16; i++) {
				p = "0" + p;
			}
			opcode = x + " " + rs + " " + rt + " " + p;
		} else if (s[0].equals("nor")) {
			x = "100111";
			shamt = "00000";
			rd = s[1];
			rs = s[2];
			rt = s[3];
			opcode = "000000" + " " + rs + " " + rt + " " + rd + " " + shamt
					+ " " + x;
		} else if (s[0].equals("beq")) {
			x = "000100";
			rs = s[1];
			rt = s[2];
			int j = 0;
			for (int i = 0; i < (labels).size(); i++) {
				Label l = (Label) labels.get(i);
				if (l.name.equals(s[3])) {
					j = l.place;
					break;
				}
			}
			int offset = (j - (this.counter+1));
			String tempString = Integer.toBinaryString(offset); 
			if (tempString.length() > 16) {
    			int counter2 = tempString.length() - 16;
    			tempString = tempString.substring(counter2, tempString.length());
    		}
    		counter = 16 - tempString.length();
    		for ( int i = 0; i< counter; i++) {
    			tempString = '0' + tempString;
    		}
			opcode = x + " " + rs + " " + rt + " " + tempString;

		} else if (s[0].equals("bne")) {
			x = "000101";
			rs = s[1];
			rt = s[2];
			int j = 0;
			for (int i = 0; i < (labels).size(); i++) {
				Label l = (Label) labels.get(i);
				if (l.name.equals(s[3])) {
					j = l.place;
					break;
				}
			}
			int offset = (j - (this.counter+1));
			String tempString = Integer.toBinaryString(offset);
			if (tempString.length() > 16) {
    			int counter2 = tempString.length() - 16;
    			tempString = tempString.substring(counter2, tempString.length());
    		}
    		counter = 16 - tempString.length();
    		for ( int i = 0; i< counter; i++) {
    			tempString = '0' + tempString;
    		}
			opcode = x + " " + rs + " " + rt + " " + tempString;
			
		} else if (s[0].equals("j")) {
			x = "000010 ";
			int j = 0;
			for (int i = 0; i < (labels).size(); i++) {
				Label l = (Label) labels.get(i);
				if (l.name.equals(s[1])) {
					j = l.place;
					break;
				}

			}
			String y = Integer.toBinaryString(j);
			int count0 = 26 - y.length();
    		for ( int i = 0; i< count0; i++) {
    			y = '0' + y;
    		}
			opcode = x + y;
		} else if (s[0].equals("jal")) {
			x = "000011";
			int j = 0;
			for (int i = 0; i < (labels).size(); i++) {
				Label l = (Label) labels.get(i);
				if (l.name.equals(s[1])) {
					j = l.place;
					break;
				}

			}
			String y = Integer.toBinaryString(j);
			int count0 = 26 - y.length();
    		for ( int i = 0; i< count0; i++) {
    			y = '0' + y;
    		}
			opcode = x + y;
		} else if (s[0].equals("jr")) {
			x = "001000 ";
			opcode = x + s[1];
		} else if (s[0].equals("slt")) {
			x = "101010";
			shamt = "00000";
			rd = s[1];
			rs = s[2];
			rt = s[3];
			opcode = "000000" + " " + " " + rs + " " + rt + " " + rd + " "
					+ shamt + " " + x;
		}

		this.counter++;
		return opcode;

	}

	public String checkLabel(String line) {
		String s[] = line.split(" ");
		helper(s);
		String opcode = "";
		if (s[0].equals("add")) {
			opcode = "no";
		} else if (s[0].equals("addi")) {
			opcode = "no";
		} else if (s[0].equals("sub")) {
			opcode = "no";
		} else if (s[0].equals("lw")) {
			opcode = "no";
		} else if (s[0].equals("lh")) {
			opcode = "no";
		} else if (s[0].equals("lhu")) {
			opcode = "no";
		} else if (s[0].equals("lb")) {
			opcode = "no";
		} else if (s[0].equals("lbu")) {
			opcode = "no";
		} else if (s[0].equals("sw")) {
			opcode = "no";
		} else if (s[0].equals("sh")) {
			opcode = "no";
		} else if (s[0].equals("sb")) {
			opcode = "no";
		} else if (s[0].equals("lui")) {
			opcode = "no";
		} else if (s[0].equals("sll")) {
			opcode = "no";
		} else if (s[0].equals("srl")) {
			opcode = "no";
		} else if (s[0].equals("and")) {
			opcode = "no";
		} else if (s[0].equals("andi")) {
			opcode = "no";
		} else if (s[0].equals("or")) {
			opcode = "no";
		} else if (s[0].equals("ori")) {
			opcode = "no";
		} else if (s[0].equals("nor")) {
			opcode = "no";
		} else if (s[0].equals("beq")) {
			opcode = "no";
		} else if (s[0].equals("bne")) {
			opcode = "no";
		} else if (s[0].equals("j")) {
			opcode = "no";
		} else if (s[0].equals("jal")) {
			opcode = "no";
		} else if (s[0].equals("jr")) {
			opcode = "no";
		} else if (s[0].equals("slt")) {
			opcode = "no";
		} else {
			return "label exists";
		}
		return opcode;
	}

	public static void main(String[] args) {
		CA c = new CA();
		String line = "add $t1 $t8 $t3";
		String line1 = "sll $t0 $v0 3";
		String line2 = "addi $t0 $v0 3";
		String line3 = "lw $t0 16($t1)";
		System.out.println(c.translate(line3, null));
		System.out.println(c.translate(line2, null));
		System.out.println(c.translate(line, null));
		System.out.println(c.translate(line1, null));

	}

	public void helper(String[] s) {
		for (int j = 0; j < s.length; j++) {

			if (s[j].equals("$t1")) {
				s[j] = "01001";
			} else if (s[j].equals("$t2")) {
				s[j] = "01010";
			} else if (s[j].equals("$t3")) {
				s[j] = "01011";
			} else if (s[j].equals("$zero")) {
				s[j] = "00000";
			} else if (s[j].equals("$v0")) {
				s[j] = "00010";
			} else if (s[j].equals("$v1")) {
				s[j] = "00011";
			} else if (s[j].equals("$a0")) {
				s[j] = "00100";
			} else if (s[j].equals("$a1")) {
				s[j] = "00101";
			} else if (s[j].equals("$a2")) {
				s[j] = "00110";
			} else if (s[j].equals("$a3")) {
				s[j] = "00111";
			} else if (s[j].equals("$t0")) {
				s[j] = "01000";
			} else if (s[j].equals("$t4")) {
				s[j] = "01100";
			} else if (s[j].equals("$t5")) {
				s[j] = "01101";
			} else if (s[j].equals("$t6")) {
				s[j] = "01110";
			} else if (s[j].equals("$t7")) {
				s[j] = "01111";
			} else if (s[j].equals("$s0")) {
				s[j] = "10000";
			} else if (s[j].equals("$s1")) {
				s[j] = "10001";
			} else if (s[j].equals("$s2")) {
				s[j] = "10010";
			} else if (s[j].equals("$s3")) {
				s[j] = "10011";
			} else if (s[j].equals("$s4")) {
				s[j] = "10100";
			} else if (s[j].equals("$s5")) {
				s[j] = "10101";
			} else if (s[j].equals("$s6")) {
				s[j] = "10110";
			} else if (s[j].equals("$s7")) {
				s[j] = "10111";
			} else if (s[j].equals("$t8")) {
				s[j] = "11000";
			} else if (s[j].equals("$t9")) {
				s[j] = "11001";
			} else if (s[j].equals("$gp")) {
				s[j] = "11100";
			} else if (s[j].equals("$sp")) {
				s[j] = "11101";
			} else if (s[j].equals("$ra")) {
				s[j] = "11111";
			}
		}
	}
}
