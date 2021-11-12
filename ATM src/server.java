import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server {

    public static void main(String[] args) throws IOException {
        ServerSocket ss;
        ss = new ServerSocket(1234);
        Socket s = ss.accept();
        DataOutputStream dopt = new DataOutputStream(s.getOutputStream());
        DataInputStream dint = new DataInputStream(s.getInputStream());
        String writ = "", read = "";
        Scanner sc = new Scanner(System.in);
        while (!read.equals("over")) {
            writ = sc.nextLine();
            dopt.writeUTF(writ);
            dopt.flush();
            read = dint.readUTF();
            System.out.println("Client:- " + read);
        }
        dint.close();
        s.close();
        ss.close();
        sc.close();
    }
}

