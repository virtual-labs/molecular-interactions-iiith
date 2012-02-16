package iiit.vlabdemos.bouncingball;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import javax.swing.*;


public class AddressChooser extends JFrame 
{
	private String fileName = "stratego.dat";  //ip file name
	private String opponentIPAddress;
  
	private ArrayList<Object> aL;
	private Object[] arr;
	
	
	public AddressChooser(String localIPAddress) 
	{
		//pane = new JOptionPane("PANE");
		aL = new ArrayList<Object>();
		
		
		try
		{
			FileInputStream fis = new FileInputStream(
					fileName);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader input = new BufferedReader(isr);
			String line;
			
			while((line = input.readLine()) != null)
			{
				aL.add(line);
			}
		}
		catch(FileNotFoundException FNF)
		{
			System.err.println("File not found: " + FNF);
		}
		catch(IOException E)
		{
		  System.err.println("IOException: " + E);  
		}
		
		System.out.println(aL);
		arr = aL.toArray();
		JComboBox ipList = new JComboBox(arr);
		ipList.setEditable(true);
		
 
		
		do
		{
	
		  // opponentIPAddress= JOptionPane.showMessageDialog(null,"Your",
				//   "TITLE", ipList, JOptionPane.PLAIN_MESSAGE);
				   
			if(opponentIPAddress == null)
				System.exit(0);
		}while(opponentIPAddress.equals(""));
  
		System.out.println(System.getProperty("user.home"));
		 

	}
	
	public static void main(String[] args)
	{
	   
				AddressChooser c1 = new AddressChooser("172.189.131.19");
			 
		
	}

}

