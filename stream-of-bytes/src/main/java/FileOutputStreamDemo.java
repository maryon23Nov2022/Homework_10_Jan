import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileOutputStreamDemo {
    public static void main(String[] args) throws IOException{
        OutputStream outputStream = null;
        try{
            //if "append" attribute is false(which is the default value), the original contents in the file will be cleared first.
            outputStream = new FileOutputStream("C:\\Users\\zhuqi\\Desktop\\2022-2023-1\\Project\\Homework\\stream-of-bytes\\src\\main\\java\\data.txt", true);
            outputStream.write('\n');
            outputStream.write("great".getBytes());

            //Flushes this output stream and forces any buffered output bytes to be written out.
            outputStream.flush();
        } finally{
            if(outputStream != null) outputStream.close();   //will also flush corresponding output stream.
        }
    }
}
