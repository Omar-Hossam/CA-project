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
  int jump;

  public void set_controler(String instruction){
    
    //R-type
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
      jump = 0;
    }
    
    //lw
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
      jump = 0;
    }
    
    //lh
    if (instruction.equals("100001")) {
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
    
    //sw
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
      jump = 0;
    }
    
    //sh
    if (instruction.equals("101001")) {
      regDst = 0;
      ALUSrc = 1;
      MemToReg = 1;
      RegWrite = 0;
      MemRead = 0;
      MemWrite = 1;
      branch = 0;
      ALUOp1 = 0;
      ALUOp0 = 0;
      jump = 0;
    }
    
    //sb
    if (instruction.equals("101000")) {
      regDst = 0;
      ALUSrc = 1;
      MemToReg = 1;
      RegWrite = 0;
      MemRead = 0;
      MemWrite = 1;
      branch = 0;
      ALUOp1 = 0;
      ALUOp0 = 0;
      jump = 0;
    }
    
    //addi
    if (instruction.equals("001000")){
      regDst = 0;
      ALUSrc = 1;
      MemToReg = 0;
      RegWrite = 1;
      MemRead = 0;
      MemWrite = 0;
      branch = 0;
      ALUOp1 = 1;
      ALUOp0 = 0;
    }
    
    //ori
    if (instruction.equals("001101")){
      regDst = 0;
      ALUSrc = 1;
      MemToReg = 0;
      RegWrite = 1;
      MemRead = 0;
      MemWrite = 0;
      branch = 0;
      ALUOp1 = 1;
      ALUOp0 = 0;
    }
    
    //andi
    if (instruction.equals("001100")){
      regDst = 0;
      ALUSrc = 1;
      MemToReg = 0;
      RegWrite = 1;
      MemRead = 0;
      MemWrite = 0;
      branch = 0;
      ALUOp1 = 1;
      ALUOp0 = 0;
    }
    
    //beq
    if (instruction.equals("000100")) {
      regDst = 0;
      ALUSrc = 0;
      MemToReg = 0;
      RegWrite = 0;
      MemRead = 0;
      MemWrite = 0;
      branch = 1;
      ALUOp1 = 0;
      ALUOp0 = 1;
      jump = 0;
    }
    //j
    if (instruction.equals("000010")) {
      regDst = 0;
      ALUSrc = 0;
      MemToReg = 0;
      RegWrite = 0;
      MemRead = 0;
      MemWrite = 0;
      branch = 0;
      ALUOp1 = 0;
      ALUOp0 = 0;
      jump = 1;
    }
  }
}
