package iiit.vlabdemos.common;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.HashMap;

import javax.media.j3d.TransformGroup;
import javax.swing.JPanel;
public class Microscope extends JPanel {
	private Image mImage;
	int state=0;
	int width, height,viewHeight,MinRange=0,MaxRange=100;
	int x_center,centerHeight;
	int y_center;
	int radius;
	float scale=1;
	boolean header_flag=false;
	boolean  hide = false;
	private HashMap<String, Color> Color_hash = new HashMap<String, Color>();
	private HashMap<String, Integer> StartAngle_hash=new HashMap<String, Integer>();
	private HashMap<String, Integer> ArcAngle_hash=new HashMap<String, Integer>();
	String heading="";
	ArrayList<String> Arc_name = new ArrayList<String>();
	Color m_bkg_Color = Color.GRAY;
	Color CircleCol= Color.BLACK;
	Color WallCol=Color.black;
	String reading="0.000";
	
	public Microscope (int w,int h,int r,float s) 
	{
		   //setm_bkg_Color(Color.black);
		   setPreferredSize(new Dimension(w,h));
		   
		   width = w;
		   height = h;
		   state =0;
		   x_center=(int)(w*0.5);
		   y_center=(int)(h*0.5);;
		   centerHeight=y_center;
		   radius=r;
		   scale=s;
		   viewHeight=radius;//20+height/2;
		   
	 }
	
	public void drawMicroscope()
	{
		checkOffscreenImage();
		
		if(hide) return;
		Graphics g1 = getGraphics();
		draw (mImage.getGraphics());
		g1.drawImage(mImage, 0, 0, null);
		// drawArcs(g);
	}
	public void draw(Graphics g) 
	{
	 Graphics2D g1 = (Graphics2D) g;
	 g1.setColor(m_bkg_Color);
	 g1.fillRect(0, 0, width-1, height);
		
		
		int r=(int)scale*radius;
		int TopCornerX=x_center-r;
		int TopCornerY=y_center-r;
		int rect_width=2*r;
		int rect_height=2*r;
		
	
		
		
		
		//walls of beaker
		g.setColor(WallCol);
		//g.drawLine((int)(x_center-r*.5),0,(int)( x_center-r*.5),height);
	//	g.drawLine((int)(x_center+r*.5),0,(int)( x_center+r*.5),height);
		g.fillRoundRect((int)(x_center-.5*r),TopCornerY, r,2*r,50,20);
		//g.drawArc((int)(x_center-1.25*r),(int)( viewHeight-1.5*r), (int)(2.5*r),(int)( 2.5*r),245,50);
		g.setColor(Color.GRAY);
		g.fillRoundRect((int)(x_center-r/2),TopCornerY, (int)(r),viewHeight,50,20);
		
		////center crossed lines and circle
		g.setColor(CircleCol);
		g.drawLine((int)(x_center-r*.7),y_center,(int)( x_center+r*.7),y_center);
		g.drawLine(x_center,(int)(y_center-r*.3),x_center,(int)( y_center+r*.3));
		g.drawArc(TopCornerX,TopCornerY,rect_width,rect_height,0,360);	
		//digital meter
		g.setColor(Color.WHITE);
		g.fillRect(x_center+2*r, y_center, 3*r/2, 3*r/4);
		drawAllText(g);
		showScale(g);
		
	}
	public void drawAllText(Graphics g)
	{
		g.setColor(Color.BLACK);
		Font cur_Font = new Font("Book Antiqua", Font.BOLD, 18);
		g.setFont(cur_Font);
		if(header_flag)
			g.drawString(heading, (int)(width*0.25),15);
		
		g.drawString(reading,x_center +2*radius+10,y_center+30);
		
	}	
	private void showScale(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		Font cur_Font = new Font("Arial", Font.BOLD, 10); //Book Antiqua
		g.setFont(cur_Font);
	    int y_step=20;
	    float[] dash1 = { 2f, 0f, 2f };
	    
	    Stroke strk= g2d.getStroke();
	     g2d.setStroke(new BasicStroke(1, 
	            BasicStroke.CAP_BUTT, 
	            BasicStroke.JOIN_ROUND, 
	            1.0f, 
	            dash1,2f)
	    );
	    int steps=height/(MaxRange-MinRange);
	    for(int i=height,j=0;i>=0;i-=15,j++)
	    {
	    	g.drawLine((int)(x_center+radius),i,(int)(x_center+radius+10),i);
	    	g.drawString((MinRange+steps*j)+"", (int)(x_center+radius+15),i);
	    }
	    
	    
	    g2d.setStroke(strk);
	}
	private void checkOffscreenImage() {
		    Dimension d = getSize();
		    if (mImage == null || mImage.getWidth(null) != width || mImage.getHeight(null) != height) 
			    mImage = createImage(width, height);
	} 
	public void paint( Graphics g1 )
	{
		checkOffscreenImage();
		draw(mImage.getGraphics());
		if(hide) return;
		g1.drawImage(mImage, 0, 0, null);
	}
	 public void setHeading (String h) 
	 {
		 header_flag=true;
		  heading = h;
	 } //
	 public void setHideFlag(boolean f){
		  hide =f;
	  }
	 public void setState(int s){
		  state =s;
	  }
	public void setScale(float s)
	{
		scale=s;
	}
	public void setArcColor(String name,Color c)
	{
		Color_hash.put(name, c);
	}
	public void setArcIntialAngle(String name,int theta)
	{
		StartAngle_hash.put(name, theta);
	}
	public void setArcAngle(String name,int theta)
	{
		ArcAngle_hash.put(name, theta);
	}
	public void SetViewHeight(int t)
	{
		
		viewHeight+=(int)(radius*t*.1);	
		//centerHeight+=viewHeight;
	}
	public void SetAbsoluteViewHeight(int t)
	{
		viewHeight=radius+t;
	}
	public void setRange(int l,int h)
	{
		MinRange=l;
		MaxRange=h;
	}
	public void SetReading(String s)
	{
		float temp=Float.parseFloat(s);
		temp=temp*1000;
		temp=Math.round(temp);
		temp/=1000;
	//	System.out.println("string    "+temp);
		//if(s.length()>5)
		//	s=s.substring(0,4);
		reading=temp+"";
	}
    public void setm_bkg_Color(Color c)
    {
    	m_bkg_Color=c;
    }
    public void CircleColour(Color c)
    {
    	CircleCol=c;
    }
    public void WallColour(Color c)
    {
    	WallCol=c;
    }
}