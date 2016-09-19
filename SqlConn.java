
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JTable;
public class SqlConn extends JFrame
{
    private String jdbcDriver = "com.mysql.jdbc.Driver";
    //private String dbAddress = "jdbc:mysql://localhost:3306/";
    //private String dbName = "TIGER";
    //private String userName = "root";
    //private String password = "faststart";

    private Statement st;
   
    private Connection con;
    String sql; 
     String key; 

    public SqlConn() throws NoSuchAlgorithmException, FileNotFoundException, IOException,StringIndexOutOfBoundsException {
        try {
            
         
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection("jdbc:mysql://localhost/DEDUP?user=root&password=faststart");
            st = con.createStatement();
             //String sql = "CREATE DATABASE DEDUP";
             //st.executeUpdate(sql);
            
             //sql = ("CREATE TABLE HASH(FileName VARCHAR(30),FilePath VARCHAR(30),HashValue VARCHAR(50),Pointer varchar(10))");

           //st.executeUpdate(sql);
           String filepath = "E:\\DATA1.docx";
           File f=new File(filepath);
 
       
        FileInputStream fileInput = new FileInputStream(f);
        
        MessageDigest md = MessageDigest.getInstance("MD5");

  
  /* Read stream to EOF as normal... */
     // byte[] digest = md.digest();
  
  byte[] buffer = new byte[262144];
            int read = 0;
            try {
                    while( (read = fileInput.read(buffer)) > 0) {
                            md.update(buffer, 0, read);
                    }
                    byte[] md5sum = md.digest();
                    String result = "";

		for (int i = 0; i < md5sum.length; i++)
		{
			result += Integer.toString((md5sum[i] & 0xff) + 0x100, 16).substring(1);
		}

              //System.out.println(result);     
                //String file = filepath.substring(filepath.lastIndexOf('\');
                String extension = filepath.substring(filepath.lastIndexOf('.')+1);
                    //BigInteger bigInt = new BigInteger(1, md5sum);
         PreparedStatement pstmt = con.prepareStatement("insert into HASH(Name,Extension,HashValue,FilePath,UserID) values(?,?,?,?,?)");
                
               sql = "select count(*) from HASH where HashValue = '"+result+"'";
               ResultSet rs = st.executeQuery(sql);
                rs.next();
                    if ( rs.getInt(1) == 0) {
                        pstmt.setString(1, f.getName());
                        //System.out.println(f.getName());
                        pstmt.setString(2,extension);
                        pstmt.setString(3,result);
                         pstmt.setString(4, f.getPath());
                         pstmt.setInt(5,1);
                          //System.out.println("result");
                          pstmt.executeUpdate();
                          
                         //System.out.println(" not exist");
                    } else {
                         
                         System.out.println(" exist");
                         sql="select FilePath from HASH where HashValue='"+result+"'";
                         rs=st.executeQuery(sql);
                          while(rs.next()) { // process results one row at a time
                             key = rs.getString(1);
                            // System.out.print(key);
                          }
                          pstmt.setString(1, f.getName());
                        //System.out.println(f.getName());
                        pstmt.setString(2,extension);
                        pstmt.setString(3,result);
                         pstmt.setString(4,key);
                         pstmt.setInt(5,2);
                          pstmt.executeUpdate();
                    }
                    
                sql="select Name from HASH where UserID='"+2+"'";
                rs=st.executeQuery(sql);
                
                while(rs.next())
                {
                    System.out.println(rs.getString(1));
                }
               
               
        }  
            catch(IOException e) {
        }
        }
        catch (ClassNotFoundException | SQLException e) {
        }
    }
    public static void main(String args[]) throws IOException, NoSuchAlgorithmException
    {
        SqlConn s=new SqlConn();
    }
}



