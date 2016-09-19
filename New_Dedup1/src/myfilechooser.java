import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;


public class myfilechooser extends JFrame implements ActionListener
{
    private String jdbcDriver = "com.mysql.jdbc.Driver";
     private Statement st;
     FileInputStream fileInput;
     MessageDigest md;
   
    private Connection con;
    String sql; 
     String key; 
   JFileChooser fc;
   JLabel label1=new JLabel("File Path");
   
   JButton browse=new JButton("browse");
   JButton upload=new JButton("upload");
   
   JTextField text1=new JTextField(20);
   
   JPanel panel1=new JPanel();
  
   
   public myfilechooser() throws ClassNotFoundException, SQLException
   {
       
       setTitle("UPLOAD TO CLOUD");
       setSize(530,200);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
      
       panel1.setLayout(null);
       panel1.add(label1);
       
       label1.setBounds(20, 50, 100, 30);
       panel1.add(text1);
       
       text1.setBounds(90, 55, 300, 25);
       panel1.add(browse);
       browse.setBounds(400, 52, 100, 30);
       
       panel1.add(upload);
       upload.setBounds(200,100,100,30);
       
       
       getContentPane().add(panel1);
       
       browse.addActionListener(this);
       upload.addActionListener(this);
     
   }
 
   

   
   // @Override
    public void actionPerformed(ActionEvent e)throws NullPointerException
    
    {
       
        if(e.getSource()==browse)
        {
            
           fc=new JFileChooser();
           int returnVal = fc.showOpenDialog(myfilechooser.this);
            
             if (returnVal == JFileChooser.APPROVE_OPTION)
             {
                  File file = fc.getSelectedFile();
                  text1.setText(file.getPath());
                
            }
        }
         
          if(e.getSource()==upload)
          {
                try {
            
         
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection("jdbc:mysql://localhost/DEDUP?user=root&password=faststart");
            st = con.createStatement();
            //System.out.println("Connetd");
             //String sql = "CREATE DATABASE DEDUP";
             //st.executeUpdate(sql);
            
             //sql = ("CREATE TABLE HASH(FileName VARCHAR(30),FilePath VARCHAR(30),HashValue VARCHAR(50),Pointer varchar(10))");

           //st.executeUpdate(sql);
           String filepath =text1.getText();
           File f=new File(filepath);
 
       
        FileInputStream fileInput = new FileInputStream(f);
        
        MessageDigest md = MessageDigest.getInstance("MD5");

  
  
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
                    
                /*sql="select Name from HASH where UserID='"+2+"'";
                rs=st.executeQuery(sql);
                
                while(rs.next())
                {
                    System.out.println(rs.getString(1));
                }*/
               
               
        }  
            catch(IOException a) {
        }
        }
        catch (ClassNotFoundException | SQLException b) {
        }      catch (FileNotFoundException ex) {
                   Logger.getLogger(myfilechooser.class.getName()).log(Level.SEVERE, null, ex);
               } catch (NoSuchAlgorithmException ex) {
                   Logger.getLogger(myfilechooser.class.getName()).log(Level.SEVERE, null, ex);
               }
              
          }
         
          
         
        
        
        
        
    }
    
     
   
    public static void main(String[] args) throws ClassNotFoundException, SQLException
    {
        
        myfilechooser my=new myfilechooser();
        my.setVisible(true);
    }
    
}

