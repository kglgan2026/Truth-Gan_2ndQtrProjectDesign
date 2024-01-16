public class AlreadyUnlockedException extends Exception {
  public AlreadyUnlockedException() {
    System.out.println("Wait this isn't locked, we don't need to unlock it");
  }
}
