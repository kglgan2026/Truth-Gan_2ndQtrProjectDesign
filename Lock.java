public class Lock extends Puzzle {
  private Item key;

  public Lock(String n, Item k){
    this.name = n;
    this.key = k;
    this.unlockStatus = "locked";
  }

  public Item getKey(){
    return this.key;
  }
}
