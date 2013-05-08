import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Datapath {

	long pc = 0;
	Adder adder = new Adder();
	Control control = new Control();
	IM im = new IM();
	Registers registerFile = new Registers();
	ALU alu = new ALU();
	DM dm = new DM();
	Mux mux = new Mux();
	reg $zero = new reg();
	reg $at = new reg();
	reg $v0 = new reg();
	reg $v1 = new reg();
	reg $a0 = new reg();
	reg $a1 = new reg();
	reg $a2 = new reg();
	reg $a3 = new reg();
	reg $t0 = new reg();
	reg $t1 = new reg();
	reg $t2 = new reg();
	reg $t3 = new reg();
	reg $t4 = new reg();
	reg $t5 = new reg();
	reg $t6 = new reg();
	reg $t7 = new reg();
	reg $s0 = new reg();
	reg $s1 = new reg();
	reg $s2 = new reg();
	reg $s3 = new reg();
	reg $s4 = new reg();
	reg $s5 = new reg();
	reg $s6 = new reg();
	reg $s7 = new reg();
	reg $t8 = new reg();
	reg $t9 = new reg();
	reg $k0 = new reg();
	reg $k1 = new reg();
	reg $gp = new reg();
	reg $sp = new reg();
	reg $fp = new reg();
	reg $ra = new reg();
	boolean zero = false;
	ArrayList<String> instructions = new ArrayList<String>();
	public void performInstruction(String ins) {
		// String Instruction = im.getInstruction(pc);
		pc = adder.add(pc, 4);

		String[] z = ins.split(" ");
		String in = z[0];

		if (z.length == 2) {
			control.set_controler("000010");
			if (z[0].equals("000011")) {
				long y = pc + 4;
				String x = Long.toBinaryString(y);
				this.$ra.data = x;
			}
			else {
				if (z[0].equals("001000")){
					reg x = findRegByOpcode(z[1]);
					z[1]=x.data;
				}
			}
			String nPC = Long.toBinaryString(pc);
			nPC = nPC.charAt(0) + nPC.charAt(1) + nPC.charAt(2) + nPC.charAt(3)
			+ z[1] + "00";
			String w=Long.toBinaryString(pc);
			String q = mux.select(w, nPC, control.jump);
			long PC=Long.parseLong(q, 2);
			pc=PC;

		} else {
			System.out.println("1");
			control.set_controler(in);

			registerFile.setRregister1(this.findRegByOpcode(z[1]));
			registerFile.setRregister2(this.findRegByOpcode(z[2]));
			String resultMux = mux.select(z[2], z[3], control.regDst);
			registerFile.setWregister(this.findRegByOpcode(resultMux));
			String r2 = registerFile.rregister2.data;

			String signExtend = "";
			if (z[3].charAt(0) == '1') {
				
				signExtend = "1111111111111111" + z[3];
			} else {
				
				signExtend = "0000000000000000" + z[3];
			}

			String resultMux2 = mux.select(r2, signExtend, control.ALUSrc);
			System.out.println(resultMux2.length() + "s");

			String signExtendedShiftedByTwo = Long.toBinaryString(Long
					.parseLong(signExtend, 2) << 2);
			long resultAdder2 = adder.add(pc,
					Long.parseLong(signExtendedShiftedByTwo, 2));

			String aluRes = "";

			if (in.equals("000000")) {
				System.out.println("2");
				if(z[5].equals("000000")||z[5].equals("000010")){
					System.out.println("3");
					System.out.println(Integer.parseInt(z[5]));
					aluRes = alu.performOperation(registerFile.rregister2.data, z[4], Integer.parseInt(z[5]));
					if (Long.parseLong(aluRes, 2) == 0) {
						zero = true;
					}
				}
			
			else{
				System.out.println("3");
				aluRes = alu.performOperation(registerFile.rregister1.data,
						resultMux2, Integer.parseInt(z[5]));
				if (Long.parseLong(aluRes, 2) == 0) {
					zero = true;
				}
			}
			}

			//beq
			if (in.equals("000100")) {
				aluRes = alu.performOperation(registerFile.rregister1.data,
						resultMux2, 100010);
				if (Long.parseLong(aluRes, 2) == 0) {
					zero = true;
				}
			}

			//bne
			if (in.equals("000101")) {
				aluRes = alu.performOperation(registerFile.rregister1.data,
						resultMux2, 100010);
				if (Long.parseLong(aluRes, 2) == 0) {
					zero = true;
				}
			}
			
			//ori
			if (in.equals("001101")) {
				aluRes = alu.performOperation(registerFile.rregister1.data,
						resultMux2, 100101);
				if (Long.parseLong(aluRes, 2) == 0) {
					zero = true;
				}
			}

			//andi
			if (in.equals("001100")) {
				aluRes = alu.performOperation(registerFile.rregister1.data,
						resultMux2, 100100);
				if (Long.parseLong(aluRes, 2) == 0) {
					zero = true;
				}
			}
			//addi
			if (in.equals("001000")) {

				System.out.println("4");
				aluRes = alu.performOperation(registerFile.rregister1.data,
						resultMux2, 100000);
				if (Long.parseLong(aluRes, 2) == 0) {
					zero = true;
				}
			}
			String dataRead = "";

			if (control.MemRead == 1) {

				//lw
				if (in.equals("100011")) {
					aluRes = alu.performOperation(registerFile.rregister1.data,
							resultMux2, 100000);
					if(Long.parseLong(aluRes,2) == 0) {
						zero = true;
					}
					String part0 = Long.toBinaryString(Long.parseLong(aluRes,2));
					String part1 = Long.toBinaryString(Long.parseLong(aluRes,2)+1);
					String part2 = Long.toBinaryString(Long.parseLong(aluRes,2)+2);
					String part3 = Long.toBinaryString(Long.parseLong(aluRes,2)+3);
					dataRead = dm.readData(part0) + dm.readData(part1) + dm.readData(part2) + dm.readData(part3);
				}

				//lbu
				if(in.equals("100100")) {
					aluRes = alu.performOperation(registerFile.rregister1.data,
							resultMux2, 100000);
					if(Long.parseLong(aluRes,2) == 0) {
						zero = true;
					}
					dataRead = "000000000000000000000000" + dm.readData(Long.toBinaryString(Long.parseLong(aluRes,2)));
				}

				//lb
				if(in.equals("100000")) {
					aluRes = alu.performOperation(registerFile.rregister1.data,
							resultMux2, 100000);
					if(Long.parseLong(aluRes,2) == 0) {
						zero = true;
					}
					String partial = dm.readData(aluRes);
					if (partial.charAt(0) == '0') {
						dataRead = "000000000000000000000000" + dm.readData(Long.toBinaryString(Long.parseLong(aluRes,2)));
					} else {
						dataRead = "111111111111111111111111" + dm.readData(Long.toBinaryString(Long.parseLong(aluRes,2)));
					}
				}

				//lui
				if(in.equals("001111")) {
					dataRead = "0000000000000000" + z[3];
				}

				//lh
				if (in.equals("100001")) {
					aluRes = alu.performOperation(registerFile.rregister1.data,
							resultMux2, 100000);
					if (Long.parseLong(aluRes, 2) == 0) {
						zero = true;
					}
					String part0 = Long.toBinaryString(Long.parseLong(aluRes,2));
					String part1 = Long.toBinaryString(Long.parseLong(aluRes, 2) + 1);
					String partial = dm.readData(part0) + dm.readData(part1);
					if (partial.charAt(0) == '0') {
						dataRead = "0000000000000000" + partial;
					} else {
						dataRead = "1111111111111111" + partial;
					}
				}

				//lhu
				if (in.equals("100101")) {
					aluRes = alu.performOperation(registerFile.rregister1.data,
							resultMux2, 100000);
					if (Long.parseLong(aluRes, 2) == 0) {
						zero = true;
					}
					String part0 = Long.toBinaryString(Long.parseLong(aluRes,2));
					String part1 = Long.toBinaryString(Long
							.parseLong(aluRes, 2) + 1);
					dataRead = "0000000000000000" + dm.readData(part0) + dm.readData(part1);
				}
			}

			if (control.MemWrite == 1) {
				//sw
				if(in.equals("101011")) {
					System.out.println("storing");
					aluRes = alu.performOperation(registerFile.rregister1.data,
							resultMux2, 100000);
					if(Long.parseLong(aluRes,2) == 0) {
						zero = true;
					}
					dm.writeData(Long.toBinaryString(Long.parseLong(aluRes,2)), registerFile.rregister1.data.substring(0, 8));
					dm.writeData(Long.toBinaryString(Long.parseLong(aluRes,2)+1), registerFile.rregister1.data.substring(8, 16));
					dm.writeData(Long.toBinaryString(Long.parseLong(aluRes,2)+2), registerFile.rregister1.data.substring(16, 24));
					dm.writeData(Long.toBinaryString(Long.parseLong(aluRes,2)+3), registerFile.rregister1.data.substring(24, 32));
				}

				//sh
				if(in.equals("101001")) {
					aluRes = alu.performOperation(registerFile.rregister1.data,
							resultMux2, 100000);
					if(Long.parseLong(aluRes,2) == 0) {
						zero = true;
					}
					dm.writeData(Long.toBinaryString(Long.parseLong(aluRes,2)), registerFile.rregister1.data.substring(8, 16));
					dm.writeData(Long.toBinaryString(Long.parseLong(aluRes,2)+1), registerFile.rregister1.data.substring(0, 8));
				}

				//sb
				if(in.equals("101000")) {
					aluRes = alu.performOperation(registerFile.rregister1.data,
							resultMux2, 100000);
					if(Long.parseLong(aluRes,2) == 0) {
						zero = true;
					}
					dm.writeData (Long.toBinaryString(Long.parseLong(aluRes,2)), registerFile.rregister1.data.substring(0, 8));
				}
			}

			boolean branch = false;

			if (control.branch == 1) {
				branch = true;
			}

			int controlAdderTwo = 0;

			if (branch) {
				if((in.equals("000100") && zero) || (in.equals("000101") && !zero)){
					controlAdderTwo = 1;
				}
			}

			pc = mux.select(pc, resultAdder2, controlAdderTwo);

			String resultMux3 = mux.select(aluRes, dataRead, control.MemToReg);

			if (control.RegWrite == 1) {
				registerFile.setWdata(resultMux3);
				this.findRegByOpcode(resultMux).data = registerFile.getWdata();
				System.out.println("Result of operation: "
						+ registerFile.wregister.data);
			}
		}
	}

	public reg findRegByOpcode(String s){
		if(s.equals("01001")){
			return this.$t1;
		} 
		else if(s.equals("01010")){
				return this.$t2;
			}
		else if(s.equals("01011")){
				return this.$t3 ;
		}
		else if(s.equals("00000")){
			return this.$zero ;
		}
		else if(s.equals("00010")){
			return this.$v0;
		}
		else if(s.equals("00011")){
			return this.$v1;
			
		}
		else if(s.equals("00100")){
			return this.$a0;

		}
		else if(s.equals("00101")){
			return this.$a1;

		}
		else if(s.equals("00110")){
			return this.$a2;
		
		}
		else if(s.equals("00111")){
			return this.$a3;
			
		}
		else if(s.equals("01000")){
			return this.$t0;
		}
		else if(s.equals("01100")){
			return this.$t4;
		}
		else if(s.equals("01101")){
			return this.$t5;
		}
		else if(s.equals("01110")){
			return this.$t6;
		}
		else if(s.equals("01111")){
			return this.$t7;
			
		}
		else if(s.equals("10000")){
			return this.$s0;
		}
		else if(s.equals("10001")){
			return this.$s1;
		}
		else if(s.equals("10010")){
			return this.$s2;
		}
		else if(s.equals("10011")){
			return this.$s3;
		}
		else if(s.equals("10100")){
			return this.$s4;
		}
		else if(s.equals("10101")){
			return this.$s5;
		}
		else if(s.equals("10110")){
			return this.$s6;
		}
		else if(s.equals("10111")){
			return this.$s7;
		}
		else if(s.equals("11000")){
			return this.$t8;
		}
		else if(s.equals("11001")){
			return this.$t9;
		}
		else if(s.equals("11100")){
			return this.$gp;
		}
		else if(s.equals("11101")){
			return this.$sp;
		}
		else if(s.equals("11111")){
			return this.$ra;
		}
		return null;
	}
	
	public Label setLabels(String line, CA piky, int counter) {
		String temp = piky.checkLabel(line);
		if (temp.equals("label exists")) {
			String s [] = line.split(" ");
			String tempo = "";
			for(int i = 1; i<s.length;i++) {
				if(i == s.length-1) {
					tempo = tempo+s[i];
				} else {
					tempo = tempo+s[i]+" ";
				}
			}
			instructions.add(tempo);
			return new Label(s[0].substring(0, s[0].length()-1), counter);
		}
		instructions.add(line);
		return null;
	}
	
	public static void main(String[] args) {
		Datapath p = new Datapath();
		p.$s1.set("0000000000000000000000000000011");
		p.$s2.set("10000000000000000000000000000010");
		//p.performInstruction("000000 10010 10001 10011 00000 101010");
		CA piky = new CA();
		String sCurrentLine; 
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("C:\\testing.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int counterFile = 0;
		ArrayList<Label> labels = new ArrayList<Label>();
		try {
			while ((sCurrentLine = br.readLine()) != null) {
				Label tempLabel = p.setLabels(sCurrentLine, piky, counterFile);
				if (tempLabel != null) {
					labels.add(tempLabel);
				}
				counterFile++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int timo = 0; timo<p.instructions.size(); timo++) {
			String tempIsntruction = piky.translate(p.instructions.get(timo), labels);
			p.im.addIns(tempIsntruction);
		}
		
		//p.performInstruction("101011 10001 10010 000000000000000001");
		//p.performInstruction("001111 10001 10011 100000000000000001");
		//p.performInstruction("000000 xxxxx 10010 10011 00011 000010");
		//p.performInstruction("001000 10001 10010 0000000000000001");
		//p.performInstruction("101011 10010 10010 000000000000000001");
		//p.performInstruction("100011 10010 10010 000000000000000001");
		//p.performInstruction("001000 10001 10010 000000000001100100");
	}
}
