import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Server{
    private static final ExecutorService pool = new ThreadPoolExecutor(2, 3, 8,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws IOException{
        try(ServerSocket serverSocket = new ServerSocket(3000)){
            while(true){
                Socket socket = serverSocket.accept();
                Runnable target = new RunnableServer(socket);
//                new SubServer(socket).start();
                pool.execute(target);
            }
        }
    }
}
