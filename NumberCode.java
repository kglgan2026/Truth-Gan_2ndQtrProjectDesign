public class NumberCode extends Puzzle {
  private int unlockCode, codeLength; //it needs to convert the code to a string for the codelength to work-

  public NumberCode(String n, int u, int c){
    this.name = n;
    this.unlockCode = u;
    this.codeLength = c;
    this.unlockStatus = "locked";
  }

  public int getUnlockCode(){
    return this.unlockCode;
  }
  public int getCodeLength() {
    return this.codeLength;
  }
  
}
