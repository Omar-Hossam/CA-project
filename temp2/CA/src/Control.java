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
	}
}
