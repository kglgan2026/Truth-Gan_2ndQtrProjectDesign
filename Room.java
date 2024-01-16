import java.util.*;

public class Room {
  private ArrayList<Object> containedObjects;
  private static ArrayList<Room> allRooms = new ArrayList();
  private Object roomExit;
  private static int roomCount;

  public Room(Object e){
    this.containedObjects = new ArrayList();
    this.roomExit = e;
    allRooms.add(this);
    roomCount++;
  }

  public ArrayList<Object> getContainedObjects() {
    return this.containedObjects;
  }
  public static ArrayList<Room> getAllRooms() {
    return allRooms;
  }
  public Object getExit() {
    return this.roomExit;
  }
  public static int getRoomCount() {
    return roomCount;
  }

 public void addObject(Object o){
    this.containedObjects.add(o); 
  }
  public Object selectObject(String n) throws ObjectNotFoundException{
    for(Object o : containedObjects){
        if(o.getName().toUpperCase().equals(n)){
            return o;
        }
    }
    throw new ObjectNotFoundException();
  }

  public Object selectObjectToUnlock(String n) throws ObjectNotFoundException, AlreadyUnlockedException{
    for(Object o : containedObjects){
        if(o.getName().toUpperCase().equals(n)){
          if(o.getLockedClue() == null) {
            throw new AlreadyUnlockedException();
          }
          else if(!(o.getLockLock() == null)) {
            if(o.getLockLock().getUnlockStatus() == "unlocked") {
              throw new AlreadyUnlockedException();
            }
            else {
              return o;
            }
          }
          else if(!(o.getNumLock() == null)) {
            if(o.getNumLock().getUnlockStatus() == "unlocked") {
              throw new AlreadyUnlockedException();
            }
            else {
              return o;
            }
          }
          else if(!(o.getLetLock() == null)) {
            if(o.getLetLock().getUnlockStatus() == "unlocked") {
              throw new AlreadyUnlockedException();
            }
            else {
              return o;
            }
          }
        }
    }
    throw new ObjectNotFoundException();
  }
}
