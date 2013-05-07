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

	public void performInstruction(String ins) {
		// String Instruction = im.getInstruction(pc);
		pc = adder.add(pc, 4);
		String[] z = ins.split(" ");
		String in = z[0];
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
		String signExtendedShiftedByTwo = Long.toBinaryString(Long.parseLong(signExtend, 2) << 2);
		long resultAdder2 = adder.add(pc, Long.parseLong(signExtendedShiftedByTwo, 2));
		String aluRes = "";
		if (in.equals("000000")) {
			aluRes = alu.performOperation(registerFile.rregister1.data,
					resultMux2, Integer.parseInt(z[5]));
			if(Long.parseLong(aluRes,2) == 0) {
				zero = true;
				System.out.println(1);
			}
		}
		if (in.equals("000100")) {
			aluRes = alu.performOperation(registerFile.rregister1.data,
					resultMux2, 100010);
			if(Long.parseLong(aluRes,2) == 0) {
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
				String part0 = aluRes; 
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
				dataRead = "000000000000000000000000" + dm.readData(aluRes);
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
					dataRead = "000000000000000000000000" + dm.readData(aluRes);
				} else {
					dataRead = "111111111111111111111111" + dm.readData(aluRes);
				}
			}
		}
		if (control.MemWrite == 1) {
			//sw
			if(in.equals("101011")) {
				aluRes = alu.performOperation(registerFile.rregister1.data,
						resultMux2, 100000);
				if(Long.parseLong(aluRes,2) == 0) {
					zero = true;
				}
				dm.writeData(Long.toBinaryString(Long.parseLong(aluRes,2)), registerFile.rregister1.data.substring(0, 8));
				dm.writeData(Long.toBinaryString(Long.parseLong(aluRes,2)+1), registerFile.rregister1.data.substring(8, 16));
				dm.writeData(Long.toBinaryString(Long.parseLong(aluRes,2)+2), registerFile.rregister1.data.substring(16, 25));
				dm.writeData(Long.toBinaryString(Long.parseLong(aluRes,2)+3), registerFile.rregister1.data.substring(25, 32));
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
				dm.writeData (aluRes, registerFile.rregister1.data.substring(0, 8));
			}
		}
		boolean branch = false;
		if(control.branch == 0) {
			branch = true;
		} else {
			branch = false;
		}
		int controlAdderTwo = 0;
		if(branch && zero) {
			controlAdderTwo = 1;
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

	public reg findRegByOpcode(String s) {
		if (s.equals("10001")) {
			return this.$s1;
		} else if (s.equals("10010")) {
			return this.$s2;
		} else {
			return this.$s3;
		}
	}

	public String translate(String x) {
		String result = "";
		String[] a;
		a = x.split(" ");
		if (a[0].equals("ADD")) {
			result += "0000000";
		}
		result += getRegOpcode(a[2]);
		result += getRegOpcode(a[3]);
		result += getRegOpcode(a[1]);
		return result;
	}

	public String getRegOpcode(String a) {
		if (a.equals("$s1")) {
			return "10001";
		} else if (a.equals("$s2")) {
			return "10010";
		} else {
			/* $s3 */return "10011";
		}
	}

	public static void main(String[] args) {
		Datapath p = new Datapath();
		p.$s1.set("00001000000000000000000000000110");
		p.$s2.set("11010000000000000000000000000011");
		p.$s3.set("10");
		p.performInstruction("000000 10001 10010 10011 00000 100111");
		p.performInstruction("101011 10010 10010 000000000000000001");
		p.performInstruction("100011 10010 10010 000000000000000001");
	}
}
