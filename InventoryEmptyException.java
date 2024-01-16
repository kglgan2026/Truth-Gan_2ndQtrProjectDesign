public class InventoryEmptyException extends Exception {
  public InventoryEmptyException() {
    System.out.println("I don't think we've actually picked anything up yet, we probably should look around more");
  }
}
