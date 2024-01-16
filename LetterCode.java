public class LetterCode extends Puzzle {
  private int codeLength;
  private String unlockCode;

  public LetterCode(String n, String u, int c){
    this.name = n;
    this.unlockCode = u;
    this.codeLength = c;
    this.unlockStatus = "locked";
  }

  public String getUnlockCode(){
    return this.unlockCode;
  }
  public int getCodeLength() {
    return this.codeLength;
  }

}
