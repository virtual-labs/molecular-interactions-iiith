package iiit.vlabdemos.common;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import iiit.vlabdemos.common.Resources;
import iiit.vlabdemos.common.J3DShape;




public class MyButton extends JButton implements MouseListener {

	private J3DShape 			m_j3d	= new J3DShape();
	
	private Color MouseOver =null;
	private Color MouseExit=null;
	private Color BorderColor=null;
	private Color Background=null;
	private ImageIcon Icon =null;
	private  String Tooltiptext=null;
	
	URL url = Resources.getResource("resources/audio/magic_bells.wav");
    AudioClip  clip =  Applet.newAudioClip(url);;
	
	public MyButton(Color Mover,Color Mexit,Color Border,Color bgcolor,String string,String Iconfile,String ToolTipText )
	{
		super("");
		MouseOver= Mover;
		MouseExit=Mexit;
		BorderColor=Border;
		Background=bgcolor;
		
	   string="Big Mass";
		
		
		if(Iconfile!=null)
		{ 
			Icon=m_j3d.createImageIcon(Iconfile); 
			this.setIcon(Icon);
		
		}
		if(ToolTipText!=null)
		{
			this.setToolTipText(ToolTipText);
			
		}
		
		this.addMouseListener(this);
		
		 MouseExit=this.getBackground();
		/* this.setText( string);
		 this.setVerticalTextPosition(AbstractButton.BOTTOM);
	        this.setHorizontalTextPosition(AbstractButton.CENTER); */

	
		
		 
	}
	
	
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	    
		this.setBackground(MouseOver);
		//this.setBorder(BorderFactory.createLineBorder(BorderColor,2));
		// repaint();
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto
		this.setBackground(MouseExit);
		
		//this.setBorder(BorderFactory.c
		//this.setBorder(BorderFactory.createLineBorder(BorderColor,0));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		clip.play();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	} 

}
