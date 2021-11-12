import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class client {

    public void chat() throws IOException {
        Socket s;
        s = new Socket("localhost", 1234);
        DataOutputStream dopt = new DataOutputStream(s.getOutputStream());
        DataInputStream dint = new DataInputStream(s.getInputStream());
        String writ = "", read = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome To the ATM Help Counter");
        while (!writ.equals("over")) {
            read = dint.readUTF();
            System.out.println("Server:- " + read);
            writ = sc.nextLine();
            dopt.writeUTF(writ);
            dopt.flush();
        }
        dopt.close();
        s.close();
        sc.close();
    }
}
