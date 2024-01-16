import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    System.out.println("*insert story dialogue*");
    
    Player player1 = new Player();

    Item doorKey = new Item("Door Key", "\n\n It's a small key that looks like it could probably fit in a door. There's only one door in the room, think we should try our luck?");
    Lock doorLock = new Lock("Door Lock", doorKey);
    Object door = new Object("Door", "\n\n It's a door, think it'll lead to the exit? \n Okay it's locked, but it's the only door in the room so no way it isn't the way out. There's gotta be a key around here somewhere...", "It's a door, which is open now !!",doorLock); //fsr i can't inspect this
      
    Item drawerKey = new Item("Drawer Key", "\n\n It's a small key...no clue what it's for");
    Lock drawerLock = new Lock("Drawer Lock", drawerKey);
    Object drawer = new Object("Drawer", "\n\n It's a drawer, pretty cool. It's locked, though-- looks like it needs some sort of key. Not too much else to say about it", "It's the drawer that we opened very skillfully",drawerLock);
    drawer.addItem(doorKey);

    Object table = new Object("Table", "\n\n It's just a table, there's nothing special abo-... Look under it? Sure, why not.\n Oh wow there's a code under here, 5678");

    NumberCode chestLock = new NumberCode("Chest Lock", 5678, 4);
    Object chest = new Object("Chest", "\n\n It's a chest. Unfortunately, it's locked, but it seems like if we can find some sort of code, we'd be able to open it. It needs 4 digits as far as I can tell", "It's just the chest we opened earlier", chestLock);
    chest.addItem(drawerKey);

    Room room1 = new Room(door);
    room1.addObject(drawer);
    room1.addObject(table);
    room1.addObject(chest);
    room1.addObject(door);

    Room room2 = new Room(door);
    room2.addObject(drawer);
    room2.addObject(door);

    player1.assignRoom();

    int response = 0;
    int enteredNumCode = 0;
    String enteredLetCode = "";
    Item chosenKey;
    Item inspectedItem;
    
    while(player1.getHope() > 0 && player1.getCurrentRoom().getExit().getIsLocked()) { //why is it not ending-
      
      System.out.println("\nWhat do you think we should do now?\n"); //maybe make protagonist cannonically mute
      System.out.println("1 - Let's inspect something ⠕⠝⠑");
      System.out.println("2 - I think we can try to open something ⠞⠺⠕");
      System.out.println("3 - Let's take a look at what we have in our inventory ⠞⠓⠗⠑⠑");
      System.out.println("4 - Could you remind me of what clues we've found so far? ⠋⠕⠥⠗");//maybe i'll make a class just for this guys dialogue
      System.out.println("Anything Else - *ignore him*");
      Object chosenObject = null; 

    try {
      response = sc.nextInt();
      
      if(!(response == 0)) {
        if(response == 1) {
          System.out.println("Inspect something? What did you want me to look at? There's...uh...a:");
          for(Object o : player1.getCurrentRoom().getContainedObjects()) {
            System.out.println(o.getName()); 
          }
          sc.nextLine();
          String selected = sc.nextLine().toUpperCase();
          try {
            chosenObject = player1.getCurrentRoom().selectObject(selected);
            player1.inspect(chosenObject);
          }
          catch(ObjectNotFoundException e) {
          }
        }
  
        else if(response == 2) {
          System.out.println("You think we can unlock something? That's awesome, what do you think we can unlock?");
          sc.nextLine();
          String selected = sc.nextLine().toUpperCase();
          try {
            chosenObject = player1.getCurrentRoom().selectObjectToUnlock(selected);
            System.out.println("Alright, sounds good, give me a sec");
  
            try { 
              if(chosenObject.getLockType() == "numberCode") {
                System.out.println("This one's looking for a number code with " + chosenObject.getNumLock().getCodeLength() + " digits, what should I put in?");
                try {
                  //sc.nextInt();
                  String enteredNumCodeString = sc.nextLine();
                  enteredNumCode = Integer.parseInt(enteredNumCodeString);
                  player1.unlock(chosenObject, enteredNumCode, enteredNumCodeString);
                }
                catch(InputMismatchException e) {
                  System.out.println("Slight problem, I don't think that's a number");
                }
                catch(IncorrectCodeLengthException e) {
                }
              }
              else if(chosenObject.getLockType() == "letterCode") {
                System.out.println("This one's looking for a letter code with " + chosenObject.getLetLock().getCodeLength() + " letters, what should I put in?");
                sc.nextLine();
                enteredLetCode = sc.nextLine().toUpperCase(); //enter exception thing later (wrong input type), all caps it
                try {
                  player1.unlock(chosenObject, enteredLetCode);
                }
                catch(InputMismatchException e) {
                    System.out.println("Slight problem, I don't think those are letters");
                } 
                catch(IncorrectCodeLengthException e) {
                }
              }
              else if(chosenObject.getLockType() == "lock") {
                System.out.println("What am I supposed to use to open this again?");
                //sc.nextLine();
                String keyResponse = sc.nextLine().toUpperCase(); //all caps it
                try {
                  chosenKey = player1.selectItem(keyResponse);
                  player1.unlock(chosenObject, chosenKey);
                }
                catch(ItemNotFoundException e) {
                }
              }
            }
            catch(AlreadyUnlockedException e) {
            }
          }
          catch(AlreadyUnlockedException e) {
            }
          catch(ObjectNotFoundException e) {
          }
        }
  
        else if(response == 3) {
          
          try {
            player1.openInventory();
            
            String selected = "";
            while(!(selected.toUpperCase().equals("no"))) {
              System.out.println("\nIs there anything (else) in particular you wanted me to focus on? Just say the name of the item, or you could say no if you don't want to");
              sc.nextLine(); //what is happening over here
              selected = sc.nextLine().toUpperCase();
              if(!(selected.equals("no"))) {
                inspectedItem = player1.selectItem(selected);
                System.out.println("\nHmm...what did I say when we got this again? I think I said: ");
                player1.inspect(inspectedItem);
              }
            }   
            System.out.println("Alright, let me put this stuff away then");
          }
          catch(ItemNotFoundException e) {
          }
          catch(InventoryEmptyException e) {
          }
        }
  
        else if(response == 4) {
          try {
            player1.recallClues(player1.getCurrentRoom());
          }
          catch(KnowNothingException e) {
          }
        }

        else {
          System.out.println("Are you ignoring me? D:\n");
        }
      } 
    }
    catch(InputMismatchException e) {
      System.out.println("Hey, don't ignore me D:"); //why is it not doing this-
      sc.nextLine(); 
    }
    
    //below is a test game I ran with my friend !!

    /*
    player1.inspect(chest);
      player1.unlock(chest, "4473");
    
    player1.inspect(drawer);
    player1.inspect(table);
    player1.inspect(door);
      player1.unlock(chest, "7236");
    
    player1.inspect(chest);
      player1.unlock(door, drawerKey);
      player1.unlock(drawer, drawerKey);
    
    player1.inspect(drawer);
  
      player1.unlock(door, doorKey);
    */

    
  }

  System.out.println("OH MY GOD WE DID IT, WE'RE OUT"); //if hope > 0

}
  public static void respond(int response){
    
  }
}
