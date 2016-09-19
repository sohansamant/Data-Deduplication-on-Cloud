import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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
import static javax.swing.JFrame.EXIT_ON_CLOSE;


public class DEDUP extends JFrame implements ActionListener
{
    private String jdbcDriver = "com.mysql.jdbc.Driver";
     private Statement st;
     FileInputStream fileinput;
     MessageDigest md;
         File[] file;
   String sf = new String();
   String path1="root@172.16.30.228:/BackupDirectory/user1/";
   String path2 = "/BackupDirectory/user1/";
   int userID=1;
   String s2="";
   private Connection con;
   String sql;
   String key;
   JFileChooser fc=new JFileChooser();
   ResultSet rs;
   //uploading components
   JPanel panel=new JPanel();
   JLabel label1=new JLabel("File Path");
   JButton dff=new JButton("DFF");
   JButton cancelUpload=new JButton("Cancel");
   JButton cancelRetrieve=new JButton("Cancel");
   JButton cancelDelete=new JButton("Cancel");
   JButton browse=new JButton("browse");
   JButton upload=new JButton("upload");
   JButton Mupload = new JButton("UPLOAD");
   JButton retrieve = new JButton("RETRIEVE");
   JButton delete= new JButton("DELETE");
   JButton delt= new JButton("Delete");
   JTextField text1=new JTextField(20);
   JPanel panel1=new JPanel();
   
   //Retrival components
   JPanel jp=new JPanel();
    JPanel jp1=new JPanel();
    JPanel jp2=new JPanel();
    JLabel l=new JLabel("UserID");
    JTextField tf=new JTextField(20);
    JButton ok=new JButton("OK");
    JButton returns=new JButton("Retrieve");
    JComboBox jComboBox1=new JComboBox();
    String path="";
    JList list;
    JScrollPane scrollPane;
    
   public void Dedup()
   {
       
      /*if(panel1.isDisplayable())
       {
           panel1.setVisible(false);
       }*/
      if(jp1.isDisplayable())
       {
           jp1.setVisible(false);
       }
      if(jp2.isDisplayable())
       {
           jp2.setVisible(false);
       }
      
       setTitle("DATA DEDUPLICATION ON CLOUD");
       setSize(500, 450);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
        
       
       //JTable jt=new JTable();
      // String[] columnNames = {"First Name"};
       //String[] data;
       JLabel jl=new JLabel("<html>Data Deduplication On Cloud enables you to upload, retrieve and delete files. It also enables you deduplicate data on the local machine using the DFF button. </html>");
      
       //Mupload = new JButton("UPLOAD");
       //retrive = new JButton("RETRIEVE");
       panel.setLayout(null);
       jl.setBounds(10,20,470,50);
       
       Mupload.setBounds(200,100, 100, 50);
       retrieve.setBounds(200,180, 100, 50);
       dff.setBounds(200,330,100,50);
       //String text = "Dedu prog";      
       
       //upload.setAlignmentX(2000);
       //upload.setAlignmentY(2000);
       panel.add(jl);
       delete.setBounds(200,250,100,50);
       panel.add(delete);
       
       panel.add(Mupload);
       panel.add(retrieve);
       panel.add(dff);
       getContentPane().add(panel);
       panel.setVisible(true);
       dff.addActionListener(this);
       Mupload.addActionListener(this);
       retrieve.addActionListener(this);
       delete.addActionListener(this);
       //f.add(upload, BorderLayout.NORTH);
       //f.setVisible(true);
   }
   
   public void upload() throws ClassNotFoundException, SQLException
   {
       panel.setVisible(false);
       setTitle("Select files to Upload to Cloud");
       setSize(530,190);
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
       upload.setBounds(130,100,100,30);
       
       cancelUpload.setBounds(240,100,100,30);
       panel1.add(cancelUpload);
              panel1.setVisible(true);

      getContentPane().add(panel1);
       
       cancelUpload.addActionListener(this);
       browse.addActionListener(this);
       upload.addActionListener(this);
     
   }
    public void Retrieve()throws ClassNotFoundException, SQLException
    {
        
       panel.setVisible(false); 
       setTitle("Select files to Retrieve from Cloud");
       setSize(500,500);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       
      
       jp.setLayout(null);
       
       jp.add(l);
       
       l.setBounds(20, 50, 100, 30);
       
       jp.add(tf);
       
       tf.setBounds(90, 55, 300, 25);
       
       
       jp.add(ok);
       ok.setBounds(400, 52, 100, 30);
       
       
       
       getContentPane().add(jp);
       cancelRetrieve.addActionListener(this);
       
    }
    
    public void delete()          
    {
        //JOptionPane.showMessageDialog(null," Retrieve file failed"); 
        try{
            if(panel.isDisplayable())
            {
                panel.setVisible(false);
            }
            Class.forName(jdbcDriver);
         
            con = DriverManager.getConnection("jdbc:mysql://172.16.30.228/DEDU?user=sap&password=faststart");
            st = con.createStatement();
            
            
            //int a=Integer.parseInt(tf.getText());
             //System.out.println(a);
            sql="select Name from hash where UserID = '"+userID+"'";
            rs=st.executeQuery(sql);
           // System.out.println();
            DefaultListModel model=new DefaultListModel();
            while(rs.next())
            {
            //if(rs.getString(1))
            //{
                 //path=rs.getString(1);
                model.addElement(rs.getString("Name"));
            } 
           
            /*else
            {
                JOptionPane.showMessageDialog(null,"Invalid user ID");
            }*/  //System.out.println(path);
            //}
           
            //panel.setVisible(false);
            jp2.setLayout(null);
            setTitle("Select files to Delete from Cloud");
            setSize(500,490);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
                 
         
            
            delt.setBounds(280, 420, 100, 30);
            cancelDelete.setBounds(390, 420, 100, 30);
            jp2.add(delt);
            jp2.add(cancelDelete);
            list=new JList(model);
            scrollPane = new JScrollPane(list);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            //list.setLayoutOrientation(JList.LEFT_ALIGNMENT);
            list.setBounds(10,10,480,400);
            
            jp2.add(list);
            jp2.add(scrollPane);
            
            
           //jp1.add(jComboBox1);
            getContentPane().add(jp2);
            jp2.setVisible(true);
            cancelDelete.addActionListener(this);
            delt.addActionListener(this);
            
         } catch (ClassNotFoundException ex) {
            Logger.getLogger(Retrieve.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Retrieve.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
 
     public void ret()throws ClassNotFoundException, SQLException
    {
        
        try{
            if(panel.isDisplayable())
            {
                panel.setVisible(false);
            }
            
            Class.forName(jdbcDriver);
         
            con = DriverManager.getConnection("jdbc:mysql://172.16.30.228/DEDU?user=sap&password=faststart");
            st = con.createStatement();
            
            
            //int a=Integer.parseInt(tf.getText());
             //System.out.println(a);
            sql="select Name from hash where UserID = '"+userID+"'";
            rs=st.executeQuery(sql);
            DefaultListModel model=new DefaultListModel();
            while(rs.next())
            {
            //if(rs.getString(1))
            //{
                 //path=rs.getString(1);
                model.addElement(rs.getString("Name"));
            }  
            /*else
            {
                JOptionPane.showMessageDialog(null,"Invalid user ID");
            }*/  //System.out.println(path);
            //}
            //String path="C:\\Users\\dell1\\Desktop";
            
            jp1.setLayout(null);
            setTitle("SELECT FILES TO RETRIEVE");
            setSize(500,490);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
                 
            //File folder=new File(path);
            //File[]listOfFiles=folder.listFiles();
            
            //list=new JList(listOfFiles);
            
           
            list=new JList(model);
            scrollPane = new JScrollPane(list);
           
            
           
            
            
            
            
            
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            //list.setLayoutOrientation(JList.LEFT_ALIGNMENT);
            list.setBounds(10,10,480,400);
            jp1.add(list);
           //jp1.add(jComboBox1);
             returns.setBounds(280, 420, 100, 30);
            cancelRetrieve.setBounds(390,420,100,30);
            jp1.add(cancelRetrieve);
            jp1.add(returns);
            jp1.add(scrollPane);
            getContentPane().add(jp1);
            jp1.setVisible(true);
            returns.addActionListener(this);
            cancelRetrieve.addActionListener(this);
            //con.close();
            //jp1.setVisible(true);
            //pnl.revalidate();
         } catch (ClassNotFoundException ex) {
            Logger.getLogger(Retrieve.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Retrieve.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   

   
   // @Override
   
    @Override
    public void actionPerformed(ActionEvent e)throws NullPointerException
    
    {
        if(e.getSource()==delt)
        {
            if(list.getSelectedIndex()==-1)
            {
               JOptionPane.showMessageDialog(null,"Please select a file."); 
            }
            else
            {
                try{
            Class.forName(jdbcDriver);
         
            con = DriverManager.getConnection("jdbc:mysql://172.16.30.228/DEDU?user=sap&password=faststart");
            st = con.createStatement();
            String name=(String)list.getSelectedValue();
           // System.out.println(name);
            sql = "select COUNT(*) from hash where Name= '"+name+"'";
            rs=st.executeQuery(sql);
            rs.next();
            int a=rs.getInt(1);
            if(a==1)
            {
                //remove File
                 Process proc1 = Runtime.getRuntime().exec("ssh root@172.16.30.228 rm -f "+path2+name);
               BufferedReader read = new BufferedReader(new InputStreamReader(proc1.getInputStream()));
           try {
                proc1.waitFor();
                } 
                catch (InterruptedException e1)
            
                {
                System.out.println(e1.getMessage());
               }
                while (read.ready()) 
                 {
               System.out.println(read.readLine());
                } 
                sql="DELETE from hash where Name='"+name+"' AND UserID='"+userID+"'";
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,"'"+name+"'"+" deleted successfully from Cloud."); 
            }
            else
            {
                sql="DELETE from hash where Name='"+name+"' AND UserID='"+userID+"'";
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,"'"+name+"'"+" deleted successfully from Cloud."); 
            }
           // System.out.println(a);
            
            
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }                catch (ClassNotFoundException ex) {
            Logger.getLogger(Retrieve.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Retrieve.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            }
        }
        
        if(e.getSource()==delete)
        {
            delete();
        }
        if(e.getSource()==cancelUpload)
        {
         
            /*try {
                
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }*/
            panel1.setVisible(false);
            Dedup();
            
           
           
        }
        if(e.getSource()==cancelRetrieve)
        {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            Dedup();
            
           
           
        }
        if(e.getSource()==cancelDelete)
        {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            Dedup();
            
           
           
        }
        
        if(e.getSource()==dff)
        {
            new DFF().setVisible(true);
        }
       if(e.getSource()==Mupload)
       {
           try { 
              
               
               upload();
           } catch (    SQLException | ClassNotFoundException ex) {
               Logger.getLogger(DEDUP.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       if(e.getSource()==retrieve)
       {
            try {
                ret();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
           
       }
        if(e.getSource()==ok)
        {   
            try {
                ret();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            
        }
        
        if(e.getSource()==returns)
            
        {
            if(list.getSelectedIndex()==-1)
            {
               JOptionPane.showMessageDialog(null,"Please select a file."); 
            }
            else{
                try {
                    Class.forName(jdbcDriver);
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                try {
                    con = DriverManager.getConnection("jdbc:mysql://172.16.30.228/DEDU?user=sap&password=faststart");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                try {
                    st = con.createStatement();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                String n=(String)list.getSelectedValue();
               // System.out.println(n);
                sql="select FilePath from hash where Name='"+n+"'";
                try {
                    rs=st.executeQuery(sql);
                    rs.next();
                    String p=rs.getString(1);
                   //System.out.println(p);
                    Process proc = null; 
                    try {
                        proc = Runtime.getRuntime().exec("rsync "+p+n+" /home/fedora/Desktop/Retrieved_Files"); //Whatever you want to execute
                    } catch (IOException ex) {
                    }
            BufferedReader read = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            try {
                proc.waitFor();
            } catch (InterruptedException e1)
            {
                System.out.println(e1.getMessage());
            }
                } catch (SQLException ex) {
                    Logger.getLogger(DEDUP.class.getName()).log(Level.SEVERE, null, ex);
                }
                String rpath="/home/fedora/Desktop/Retrieved_Files/";
                File f1=new File(rpath+n);
                if (f1.exists()) {
           JOptionPane.showMessageDialog(null,"You have successfully retrieved the file at "+"'"+rpath+"'");
                }
                else 
                {
                   JOptionPane.showMessageDialog(null," File retrieval failed."); 
                }
                
            }
        }
        if(e.getSource()==browse)
        {
            
           //fc.setVisible(true);
           fc.setMultiSelectionEnabled(true);
           int returnVal = fc.showOpenDialog(this);
            
             if (returnVal == JFileChooser.APPROVE_OPTION)
             {
                  file = fc.getSelectedFiles();
                 // text1.setText(file.getPath());
                  
                  for(int i=0;i<file.length;i++)
                  {
                     sf+=file[i].getAbsolutePath()+" ";
                  }
                  text1.setText(sf);
                  
                
            }
             else
             {
                 fc.setVisible(false);
                 panel1.setVisible(true);
                 
             }
        }
         
          if(e.getSource()==upload)
          {
                
               
            String s1 = new String();
            
            s1=text1.getText();
            //System.out.println(s1);
            file = fc.getSelectedFiles();
            
        
            
              try {
            
         
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection("jdbc:mysql://172.16.30.228/DEDU?user=sap&password=faststart");
            st = con.createStatement();
            //System.out.println("Connetd");
             //String sql = "CREATE DATABASE DEDUP";
             //st.executeUpdate(sql);
            
             //sql = ("CREATE TABLE HASH(FileName VARCHAR(30),FilePath VARCHAR(30),HashValue VARCHAR(50),Pointer varchar(10))");

           //st.executeUpdate(sql);
         /*  String filepath =text1.getText();
           File f=new File(filepath);
 
       
        FileInputStream fileinput1 = new FileInputStream(f); */
            for(int j=0;j<file.length;j++)
            {
                    String filepath =file[j].getAbsolutePath();
             
                        File f=new File(filepath);
                             try {
                                    fileinput = new FileInputStream(f);
                                 } catch (FileNotFoundException ex) {
                                 Logger.getLogger(DEDUP.class.getName()).log(Level.SEVERE, null, ex);
                                    }
        
        
                                    MessageDigest md1 = MessageDigest.getInstance("MD5");
                                    byte[] buffer = new byte[262144];
                                    int read = 0;
                                     try {
                                            while( (read = fileinput.read(buffer)) > 0) {
                                             md1.update(buffer, 0, read);
                                         }
                                        byte[] md5sum = md1.digest();
                                        String result = "";

                                        for (int i = 0; i < md5sum.length; i++)
                                        {
                                            result += Integer.toString((md5sum[i] & 0xff) + 0x100, 16).substring(1);
                                        }

                                        //System.out.println(result);     
                //String file = filepath.substring(filepath.lastIndexOf('\');
                                        String extension = filepath.substring(filepath.lastIndexOf('.')+1);
                
                    //BigInteger bigInt = new BigInteger(1, md5sum);
           PreparedStatement pstmt = con.prepareStatement("insert into hash(Name,Extension,HashValue,FilePath,UserID,size) values(?,?,?,?,?,?)");
                
               sql = "select count(*) from hash where HashValue = '"+result+"'";
               ResultSet rs1 = st.executeQuery(sql);
                rs1.next();
                    if ( rs1.getInt(1)== 0)
                    {
                        sql="select count(*) from hash where Name = '"+f.getName()+"'"+"AND UserID= '"+userID+"'";
                        rs1=st.executeQuery(sql);
                         
                        rs1.next();
                        if(rs1.getInt(1)==1)
                        {
                            JOptionPane.showMessageDialog(null,"Please rename your file '"+f.getName()+"'");
                        }
                        else{
                        pstmt.setString(1, f.getName());
                        //System.out.println(f.getName());
                        pstmt.setString(2,extension);
                        pstmt.setString(3,result);
                         pstmt.setString(4,path1);
                         pstmt.setInt(5,userID);
                         pstmt.setLong(6,f.length());
                          //System.out.println("result");
                          pstmt.executeUpdate();
                          
                          //rsync command
                         
                          
                                  s2=s2+" "+file[j].getAbsolutePath();
                                  
                          
                        } 
                         
                    } 
                    else 
                    {
                         
                        // System.out.println("exist");
                         sql="select count(*) from hash where UserID= '"+userID+"'"+"AND HashValue= '"+result+"'";
                         rs1=st.executeQuery(sql);
                         rs1.next();
                         //System.out.println(rs.getString(1));
                         if(rs1.getInt(1)>0)
                         {
                          
                             
                             
                             //logic for dumping file to other user before rsync for this user
                         JOptionPane.showMessageDialog(null,"File'"+f.getName()+"'"+" already exists.");
                         
                         }
                         else
                         {
                             JOptionPane.showMessageDialog(null,"File'"+f.getName()+"'"+"has been uploaded successfully.");
                             sql="select FilePath from hash where HashValue='"+result+"'";
                         rs1=st.executeQuery(sql);
                          while(rs1.next()) { // process results one row at a time
                             key = rs1.getString(1);
                            // System.out.print(key);
                          }
                          pstmt.setString(1, f.getName());
                        //System.out.println(f.getName());
                         pstmt.setString(2,extension);
                         pstmt.setString(3,result);
                         pstmt.setString(4,key);
                         pstmt.setInt(5,userID);
                         pstmt.setLong(6,f.length());
                         pstmt.executeUpdate();
                             
                         }
                         
                    }
                         
               
        } catch(Exception a) {
        }
       }
            //System.out.println(s2);
            if(s2.isEmpty()==false)
            {
            Process proc = Runtime.getRuntime().exec("rsync "+s2+" "+path1);
           BufferedReader read1 = new BufferedReader(new InputStreamReader(proc.getInputStream()));
           try {
                proc.waitFor();
                } 
                catch (InterruptedException e1)
            
                {
                System.out.println(e1.getMessage());
               }
                while (read1.ready()) 
                 {
               System.out.println(read1.readLine());
                }
                JOptionPane.showMessageDialog(null,"File'"+s2+"'"+"uploaded"); 
            }
              
              }
              catch(Exception a) {}
              
        }
          
   }
         
          
        
    
     
   
    public static void main(String[] args) throws ClassNotFoundException, SQLException
    {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        DEDUP my=new DEDUP();
        my.Dedup();
        my.setVisible(true);
    }
    
}

