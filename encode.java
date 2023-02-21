import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;



public class testsubstring {
	
	public static void main (String [] args) throws IOException
	{
	
        	 String text = new String(Files.readAllBytes(Paths.get("test.vql")), StandardCharsets.UTF_8);
	String encodedString = Base64.getEncoder().encodeToString(text.getBytes());
	System.out.println(encodedString);
		
		 }  
	}
