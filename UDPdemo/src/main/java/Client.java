import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client{
    public static void main(String[] args) throws Exception{

        //A datagram socket is the sending or receiving point for a packet delivery service.
        DatagramSocket datagramSocket = new DatagramSocket();


        byte[] buffer = "UDP".getBytes();

        //Datagram packets are used to implement a connectionless packet delivery service.
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), 3000);

        datagramSocket.send(datagramPacket);

        datagramSocket.close();
    }
}
