public class Control {
	int regDst;
	int ALUSrc;
	int MemToReg;
	int RegWrite;
	int MemRead;
	int MemWrite;
	int branch;
	int ALUOp1;
	int ALUOp0;
	public void set_controler(String instruction){
		if (instruction.equals("000000")){
			regDst = 1;
			ALUSrc = 0;
			MemToReg = 0;
			RegWrite = 1;
			MemRead = 0;
			MemWrite = 0;
			branch = 0;
			ALUOp1 = 1;
			ALUOp0 = 0;
		}
		if (instruction.equals("100011")) {
			regDst = 0;
			ALUSrc = 1;
			MemToReg = 1;
			RegWrite = 1;
			MemRead = 1;
			MemWrite = 0;
			branch = 0;
			ALUOp1 = 0;
			ALUOp0 = 0;
		}
		if (instruction.equals("101011")) {
			regDst = 0;
			ALUSrc = 1;
			MemToReg = 1;
			RegWrite = 0;
			MemRead = 0;
			MemWrite = 1;
			branch = 0;
			ALUOp1 = 0;
			ALUOp0 = 0;
		}
	}
}
