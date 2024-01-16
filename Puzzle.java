public abstract class Puzzle {
  protected String name, info, unlockStatus;

  public String getInfo(){
    return this.info;
  }
  public String getUnlockStatus(){
    return this.unlockStatus;
  }
  public String getName(){
    return this.name;
  }

  public void setUnlock(){
    this.unlockStatus = "unlocked";
  }
}
