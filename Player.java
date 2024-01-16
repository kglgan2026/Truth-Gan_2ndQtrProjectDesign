import java.util.*;
import java.util.ArrayList;

public class Player {
  
  private String nickname;
  private static ArrayList<Item> inventory = new ArrayList();
  private int hope;
  private static ArrayList<Object> inspectedObjects = new ArrayList();
  private Room currentRoom;

  public Player() { 
    this.hope = 100;
  }
  
  public String getNickname() {
    return this.nickname;
  }
  public ArrayList<Item> getInventory() {
    return inventory;
  }
  public int getHope() {
    return this.hope;
  }
  public Room getCurrentRoom() {
    return this.currentRoom;
  }
  /*
  public void setCurrentRoom(Room r) {
    this.currentRoom = r;
  }
  */
  public void assignRoom() {
    int selected = (int) Math.floor(Math.random()*Room.getRoomCount());
    this.currentRoom = Room.getAllRooms().get(selected);
  }

  public void setNickname(String n) throws NicknameTooLongException {
    if (n.length() > 12) {
      throw new NicknameTooLongException();
    }
    else {
      this.nickname = n; 
    }
    //characters only exception, tho i think that's just an inputmismatch thing in the main method
  }

  public void openInventory() throws InventoryEmptyException {
    if(inventory.isEmpty()) {
      throw new InventoryEmptyException();
    }
    else {
      System.out.println("Sounds like a great plan. I'd just show you our stuff, but uh, you can't actually see, so I'll just tell you what we have and you can probably imagine it\n");
      for (Item i : inventory) {
        System.out.println(i.getName() + "\n");
      }
    }
  }
  public Item selectItem(String n) throws ItemNotFoundException {
    for(Item i : inventory){
      if(i.getName().toUpperCase().equals(n)){
          return i;
      }
    }
    throw new ItemNotFoundException();
  }

  public void recallClues(Room r) throws KnowNothingException { //something isn't working-
    if(inspectedObjects.isEmpty()) {
      throw new KnowNothingException();
    }
    else {
      System.out.println("Okay so I have no idea what could be important, so I'll just tell you everything I remember saying !! \n");
      
      for (Object o : r.getContainedObjects()) { //doesn't work after inspecting newly unlocked obj D:
        if(o.getInspected()) {
          if(o.getLockedClue() == null) { 
            System.out.println(o.getName() + ": " + o.getUnlockedClue() + "\n");
          }
          else {
            if(!(o.getLockLock() == null)) {
              if(o.getLockLock().getUnlockStatus() == "locked") {
                System.out.println(o.getName() + ": " + o.getLockedClue() + "\n");
              }
              else {
                System.out.println(o.getName() + " (when it was locked): " + o.getLockedClue() + "\n\n" + o.getName() + "(when we unlocked it): " + o.getUnlockedClue() + "\n");
              }
            }
            else if(!(o.getNumLock() == null)) {
              if(o.getNumLock().getUnlockStatus() == "locked") {
                System.out.println(o.getName() + ": " + o.getLockedClue() + "\n");
              }
              else {
                System.out.println(o.getName() + " (when it was locked): " + o.getLockedClue() + "\n\n" + o.getName() + "(when we unlocked it): " + o.getUnlockedClue() + "\n");
              }
            }
            else if(!(o.getLetLock() == null)) {
              if(o.getLetLock().getUnlockStatus() == "locked") {
                System.out.println(o.getName() + ": " + o.getLockedClue() + "\n");
              }
              else {
                System.out.println(o.getName() + " (when it was locked): " + o.getLockedClue() + "\n\n" + o.getName() + "(when we unlocked it): " + o.getUnlockedClue() + "\n");
              }
            }
            /*
            else {
              System.out.println(o.getName() + " (when it was locked): " + o.getLockedClue() + "\n" + o.getName() + "(when we unlocked it): " + o.getUnlockedClue() + "\n");
              } */
          }
        }
      }
    }
  }

  public void inspect(Item i) {
    System.out.println("'" + i.getInfo() + "'");
  }
  public void inspect(Object o) { //implement null exception, locked exception ?
    o.setInspected();
    inspectedObjects.add(o);
    
    if(!(o.getLockLock() == null) && o.getLockLock().getUnlockStatus() == "locked") {
      System.out.println(o.getLockedClue());
    }
    else if(!(o.getNumLock() == null) && o.getNumLock().getUnlockStatus() == "locked") {
      System.out.println(o.getLockedClue());
    }
    else if(!(o.getLetLock() == null) && o.getLetLock().getUnlockStatus() == "locked") {
      System.out.println(o.getLockedClue());
    }
    else { 
      System.out.println(o.getUnlockedClue()); //not the best thing, will say an unlocked thing is locked
      if(!(o.getContainedItems() == null)) {
        for (Item i : o.getContainedItems()) {
          inventory.add(i); 
          //o.removeItem(i); 
          System.out.println("We got a/an " + i.getName() + "! " + i.getInfo()); //don't do this if already inspected
        }
      }
    }    
  }

  public void unlock(Object o, Item i) throws AlreadyUnlockedException { //not the right key
    if(o.getLockLock().getUnlockStatus() == "unlocked") {
      throw new AlreadyUnlockedException();
    }
    else{
      if(o.getLockLock().getKey().equals(i)){
        o.getLockLock().setUnlock();
        o.setUnlocked();
        System.out.println("You used the " + i.getName() + " to open the " + o.getName() + ". It worked! It's open now");
      }
      else {
        System.out.println("incorrect key");
        this.hope -= 10;
      }
    }
  }
    
  public void unlock(Object o, String s) throws AlreadyUnlockedException, IncorrectCodeLengthException { //not the right key
    int length = o.getLetLock().getCodeLength();
    if(o.getLetLock().getUnlockStatus() == "unlocked") {
      throw new AlreadyUnlockedException();
    }
    else if(o.getLetLock().getCodeLength() != length) {
      throw new IncorrectCodeLengthException();
    }
    else{
      if(o.getLetLock().getUnlockCode().toUpperCase().equals(s)){
        o.getLetLock().setUnlock();
        o.setUnlocked();
        System.out.println("It worked! It's open now");
      }
      else{
        System.out.println("incorrect key");
        this.hope -= 10;
      }
    }
  }

  public void unlock(Object o, int i, String s) throws AlreadyUnlockedException, IncorrectCodeLengthException { //not the right key
    int length = s.length();
    if(o.getNumLock().getUnlockStatus() == "unlocked") {
      throw new AlreadyUnlockedException();
    }
    else if(!(o.getNumLock().getCodeLength() == length)) {
      throw new IncorrectCodeLengthException();
    }
    else{
      if(o.getNumLock().getUnlockCode() == i){
        o.getNumLock().setUnlock();
        o.setUnlocked();
        System.out.println("It worked! It's open now");
      }
      else{
        System.out.println("incorrect key");
        this.hope -= 10;
      }
    }
  }
  
}
