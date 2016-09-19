import java.io.File;
 
public class Create_Directory
{
    public static void main(String[] args)
    {	
	File file = new File("C:\\Directory1");
	if (!file.exists()) {
		if (file.mkdir()) {
			System.out.println("Directory is created!");
                        
		} 
       
	}
         else
                {
                    System.out.println("Directory All ready exi");
                }
 
	
	}
 
    }

