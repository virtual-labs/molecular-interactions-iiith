package iiit.vlabdemos.bouncingball;


import javax.swing.*;

public class ManyOptions {
	
  public static void main(String[] args) {
	  String [] str = new String[2];
	  str[0] = "hardeep";
	  str[1] = "rajpal";
    JOptionPane.showInputDialog(null, "Please choose a name", "Example 1",
	JOptionPane.QUESTION_MESSAGE, null, new Object[] {str,
	    "Amanda", "Colin", "Don", "Fred", "Gordon", "Janet", "Jay", "Joe",
	    "Judie", "Kerstin", "Lotus", "Maciek", "Mark", "Mike", "Mulhern",
	    "Oliver", "Peter", "Quaxo", "Rita", "Sandro", "Tim", "Will"},
				"Joe");
  }
}