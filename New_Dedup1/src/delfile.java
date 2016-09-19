
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;



public class delfile {
     public static long folderSize(File directory) {
    long length = 0;
    for (File file : directory.listFiles()) {
        if (file.isFile())
            length += file.length();
        else
            length += folderSize(file);
    }
    return length;
     }
     public static void main(String args[]) throws NullPointerException
     {
         delfile d = new delfile();
         File f = new File("\\172.16.30.228/BackupDirectory/user1");
         long l = d.folderSize(f);
         System.out.print(l);
         
     }
}
