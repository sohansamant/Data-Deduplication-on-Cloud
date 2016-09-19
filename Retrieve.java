
import java.awt.event.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class Retrieve extends JFrame implements ActionListener
{
    JPanel jp=new JPanel();
    JPanel jp1=new JPanel();
    JLabel l=new JLabel("UserID");
    JTextField tf=new JTextField(20);
    JButton ok=new JButton("OK");
    JButton returns=new JButton("Retrieve");
    JList list;
     private String jdbcDriver = "com.mysql.jdbc.Driver";
     private Statement st;
     private Connection con;
    String sql;
    String path="";
    Retrieve()
    {
        
        
       setTitle("Retrieve From CLOUD");
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
       ok.addActionListener(this);
       
    }
    
     public void ret()
    {
        try{
             Class.forName(jdbcDriver);
         
            con = DriverManager.getConnection("jdbc:mysql://localhost/dedup?user=root&password=faststart");
            st = con.createStatement();
            
            
            int a=Integer.parseInt(tf.getText());
             //System.out.println(a);
            sql="select FilePath from hash where UserID = '"+a+"'";
            ResultSet rs=st.executeQuery(sql);
            if(rs.next())
            {
            //if(rs.getString(1))
            //{
                 path=rs.getString(1);
            }  
            else
            {
                JOptionPane.showMessageDialog(null,"Invalid user ID");
            }  //System.out.println(path);
            //}
            //String path="C:\\Users\\dell1\\Desktop";
            jp.setVisible(false);
            
            setTitle("Retrieve From CLOUD");
            setSize(500,500);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            sql="select Name from hash";
            rs=st.executeQuery(sql);
            //String data[];
            //String col[]={"Name"};
            /*while(rs.next())
            {
             data[]={rs.getString("Name")};
            }*/
            //JTable jt= new JTable(data,col);
            File folder=new File(path);
            File[]listOfFiles=folder.listFiles();
            DefaultTableModel model = new DefaultTableModel();
            //JTable table = new JTable(model);
            list=new JList(listOfFiles);
            
            returns.setBounds(10, 10, 100, 100);
            jp1.add(returns);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            //list.setLayoutOrientation(JList.LEFT_ALIGNMENT);
            list.setBounds(20,100,100,100);
            jp1.add(list);
            getContentPane().add(jp1);
            returns.addActionListener(this);
            //jp1.setVisible(true);
            //pnl.revalidate();
         } catch (ClassNotFoundException ex) {
            Logger.getLogger(Retrieve.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Retrieve.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==ok)
        {
           ret();
        }
        if(e.getSource()==returns)
            
        {
            if(list.getSelectedIndex()==-1)
            {
               JOptionPane.showMessageDialog(null,"Plz select a file which u want to retrieve"); 
            }
            else{
           JOptionPane.showMessageDialog(null,"You Have Successfully Retrieve your file");
            }
        }
    }
   
    
     public static void main(String[] args) {
       Retrieve r = new Retrieve();
       r.setVisible(true);
    }
    
}
