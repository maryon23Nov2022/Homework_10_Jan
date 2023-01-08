import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileInputStreamDemo{
    public static void main(String[] args) throws IOException{
        InputStream inputStream = null;
        try{
            inputStream = new FileInputStream("C:\\Users\\zhuqi\\Desktop\\2022-2023-1\\Project\\Homework\\stream-of-bytes\\src\\main\\java\\data.txt");

            int b = inputStream.read();
            System.out.printf("%c\n", b);

            byte[] buffer = inputStream.readAllBytes();
            System.out.printf("%s\n", new String(buffer));
        } finally{
            if(inputStream != null) inputStream.close();
        }
    }
}
