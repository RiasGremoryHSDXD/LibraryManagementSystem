import java.io.File;
import java.util.*;
public class FilePathTesting {
    public static void main(String[] args)
    {
        File file = new File("src/ImageDirectory/person_logo.png");
        System.out.println("File Exist: " + file.exists());
    }
}
