public class NicknameTooLongException extends Exception {
  public NicknameTooLongException(){
    System.out.println("Okay that's a little too long, I'd like to call you something shorter. Do you think you could pick something else?");
  }
}
