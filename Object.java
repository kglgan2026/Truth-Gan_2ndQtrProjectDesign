import java.util.*;

public class Object {
  protected String name, lockedClue, unlockedClue, lockType;
  protected ArrayList<Item> containedItems;
  protected LetterCode letCodeLock;
  protected NumberCode numCodeLock;
  protected Lock lockLock;
  protected boolean inspected;
  protected boolean isLocked;

  public Object(String n, String c, String u, Lock l){ //locked obj
    this.name = n;
    this.lockedClue = c;
    this.unlockedClue = u;
    this.lockLock = l;
    this.lockType = "lock";
    this.inspected = false;
    this.isLocked = true;
    this.containedItems = new ArrayList(); //?? 
  }
  public Object(String n, String c, String u, NumberCode l){ //locked obj
    this.name = n;
    this.lockedClue = c;
    this.unlockedClue = u;
    this.numCodeLock = l;
    this.lockType = "numberCode";
    this.inspected = false;
    this.isLocked = true;
    this.containedItems = new ArrayList(); //?? 
  }
  public Object(String n, String c, String u, LetterCode l){ //locked obj
    this.name = n;
    this.lockedClue = c;
    this.unlockedClue = u;
    this.letCodeLock = l;
    this.lockType = "letterCode";
    this.inspected = false;
    this.isLocked = true;
    this.containedItems = new ArrayList(); //?? 
  }
  public Object(String n, String u){ //unlocked obj
    this.name = n;
    this.unlockedClue = u;
    this.isLocked = false;
  }

  public void setUnlocked() {
    this.isLocked = false;
  }
  public boolean getIsLocked() {
    return this.isLocked;
  }
  public boolean getInspected() {
    return this.inspected;
  }
  public String getLockType() {
    return this.lockType;
  }
  public String getName(){
    return this.name;
  }
  public String getLockedClue(){
    return this.lockedClue;
  }
  public String getUnlockedClue(){
    return this.unlockedClue;
  }
  public NumberCode getNumLock(){
    return this.numCodeLock;
  }
  public LetterCode getLetLock(){
    return this.letCodeLock;
  }
  public Lock getLockLock(){
    return this.lockLock;
  }
  /*public Puzzle getLock(){
    return this.lock;
  }*/
  public ArrayList<Item> getContainedItems(){
    return this.containedItems;
  }

//method to add items to obj?
  public void addItem(Item i){
    this.containedItems.add(i);
  }
  public void setInspected() {
    this.inspected = true;
  }
  public void removeItem(Item i) {
    this.containedItems.remove(i);
  }

}
