public class Screen {

   public void displayMessage(String message) {
      System.out.print(message); 
   } 

   public void displayMessageLine(String message) {
      System.out.println(message);   
   } 

   // displays a rupeer amount
   public void displayRupeeAmount(double amount) {
      System.out.printf("Rs.%,.2f", amount);   
   }
} 