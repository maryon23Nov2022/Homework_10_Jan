import java.io.*;

public class CopyFileWithBuffer{
    public static void main(String[] args) throws Exception{
        //The try-with-resources statement ensures that each resource is closed at the end of the statement.
        try(InputStream inputStream = new FileInputStream("C:\\Users\\zhuqi\\Desktop\\2022-2023-1\\Project\\Homework\\stream-of-bytes\\src\\main\\java\\data.txt");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            OutputStream outputStream = new FileOutputStream("C:\\Users\\zhuqi\\Desktop\\2022-2023-1\\Project\\Homework\\stream-of-bytes\\src\\main\\java\\copy.txt", false);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            Resource resource = new Resource();
            ){
            byte[] buffer = new byte[1024];
            while(true){
                int len = bufferedInputStream.read(buffer);
                if(len == -1) break;
                String string = new String(buffer, 0, len);
                System.out.printf("%s\n", string);
                bufferedOutputStream.write(buffer, 0, len);
            }
        }
    }
}

class Resource implements AutoCloseable{
    Resource(){}

    @Override
    public void close() throws Exception {
        System.out.printf("closed\n");
    }
}
