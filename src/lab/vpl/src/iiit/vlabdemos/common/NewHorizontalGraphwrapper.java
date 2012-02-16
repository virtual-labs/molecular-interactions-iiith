package iiit.vlabdemos.common;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/** Draw on a JPanel rather than on JApplet's Panel. **/
//public class GraphPlotter extends JPanel 
public class NewHorizontalGraphwrapper extends JPanel 
{
	private HorizontalGraph m_graph;
	
	
	
	public NewHorizontalGraphwrapper (HorizontalGraph graph,Color bkg) {
		   //setBackground(Color.black);
		  //setPreferredSize(new Dimension(w,h));
		  this.setLayout(new java.awt.BorderLayout());
		  this.setBackground(bkg);
		  this.setBorder(BorderFactory.createLineBorder(new Color(235,233,215),8));

		  m_graph = graph;
		  //m_graph.setBackgroundColor(bkg);
		  
	       add(m_graph);
	       
		   
	  } 
	
	
   		     
	
}

