import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client{
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket(InetAddress.getLocalHost(), 3000);

        OutputStream outputStream = socket.getOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

        Scanner in = new Scanner(System.in);

        while(true){
            String string = in.nextLine();
            if("exit".equals(string)) break;
            System.out.printf("%s\n", string);
            bufferedOutputStream.write(string.getBytes());
            bufferedOutputStream.flush();
        }
    }
}
