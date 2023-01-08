import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SubServer extends Thread{
    private final Socket socket;
    SubServer(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run(){
        System.out.printf("Thread has been executed\n");

        try{
            InputStream inputStream = socket.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            System.out.printf("Connection come from: %s\n", socket.getInetAddress().getHostAddress());
            System.out.printf("Remote port number is %d\n", socket.getPort());
            byte[] msg = new byte[1024 * 8];
            while(true){
                int len = bufferedInputStream.read(msg, 0, 1024);
                if(len > 0){
                    String string = new String(msg, 0, len);
                    System.out.printf("port: %d, %s\n", socket.getPort(), string);
                }
            }
        } catch (IOException e){
            System.out.printf("port: %d, Disconnected.\n", socket.getPort());
        }

    }
}