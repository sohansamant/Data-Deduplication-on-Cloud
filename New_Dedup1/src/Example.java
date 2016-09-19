
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class Example extends JFrame {

    public Example() {
       // JFrame f = new JFrame();
       setTitle("DEDUPLICATION");
       setSize(500, 350);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       JPanel panel=new JPanel();
       JTable jt=new JTable();
       String[] columnNames = {"First Name"};
       String[] data;
       JLabel jl=new JLabel("Data Deduplication On Cloud enables you to upload and retrieve files as required.");
       JButton Mupload = new JButton("UPLOAD");
       JButton retrive = new JButton("RETRIEVE");
       panel.setLayout(null);
       jl.setBounds(10,50,1000,30);
       Mupload.setBounds(200,100, 100, 50);
       retrive.setBounds(200,180, 100, 50);
       String text = "Dedu prog";      
       
       //upload.setAlignmentX(2000);
       //upload.setAlignmentY(2000);
       panel.add(jl);
       panel.add(Mupload);
       panel.add(retrive);
       getContentPane().add(panel);
       //f.add(upload, BorderLayout.NORTH);
       //f.setVisible(true);
    }

    public static void main(String[] args) {
       Example ex = new Example();
       ex.setVisible(true);
    }
}
