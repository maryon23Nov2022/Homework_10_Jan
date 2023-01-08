import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server{
    public static void main(String[] args) throws Exception{
        DatagramSocket datagramSocket = new DatagramSocket(3000);

        byte[] buffer = new byte[1024 * 64];
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);

        while(true){
            datagramSocket.receive(datagramPacket);     //This method blocks until a datagram is received.

            String string = new String(buffer, 0, datagramPacket.getLength());
            System.out.println("Got message: " + string);

            //Print the IP address of client machine
            System.out.println(datagramPacket.getAddress().getHostAddress());
            System.out.printf("%d\n\n", datagramPacket.getPort());
        }

//        datagramSocket.close();
    }
}