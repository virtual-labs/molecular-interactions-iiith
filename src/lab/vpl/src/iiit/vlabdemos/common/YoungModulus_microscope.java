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
public class YoungModulus_microscope extends JPanel {
	private Image mImage;
	int state=0;
	int width, height,viewHeight,MinRange=0,MaxRange=100,min=10,max=20;
	int x_center;
	int y_center;
	int radius;
	float scale=1;
	int width_rectangle =0;
	float scale_reading=0;
	boolean show_scale=false;
	boolean header_flag=false;
	boolean  hide = false;
	
	private HashMap<String, Color> Color_hash = new HashMap<String, Color>();
	private HashMap<String, Integer> StartAngle_hash=new HashMap<String, Integer>();
	private HashMap<String, Integer> ArcAngle_hash=new HashMap<String, Integer>();
	String heading="";
	ArrayList<String> Arc_name = new ArrayList<String>();
	Color m_bkg_Color = Color.GRAY;
	Color CircleCol= Color.WHITE;
	Color WallCol=Color.black;
	Color cirecle_border= Color.BLUE;
	Color rect_color= Color.magenta;
	Color triangle_color=Color.MAGENTA;
	
	public YoungModulus_microscope(int w,int h,int r,float s) 
	{
		   //setm_bkg_Color(Color.black);
		   setPreferredSize(new Dimension(w,h));
		   
		   width = w;
		   height = h;
		   state =0;
		   x_center=(int)(w*0.5);
		   y_center=(int)(h*0.5);;
		   radius=r;
		   scale=s;
		   viewHeight=-1;
		   width_rectangle=(int)(0.3*r);
		   
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
		//g.setColor(WallCol);
		//g.drawLine((int)(x_center-r*.5),0,(int)( x_center-r*.5),height);
	//	g.drawLine((int)(x_center+r*.5),0,(int)( x_center+r*.5),height);
		//g.fillRect((int)(x_center-.5*r),0, r, height);
		//g.drawArc((int)(x_center-1.25*r),(int)( viewHeight-1.5*r), (int)(2.5*r),(int)( 2.5*r),245,50);
		//g.setColor(Color.GRAY);
		//g.fillRoundRect((int)(x_center-r/2),(int)(-20), (int)(r),(int)(viewHeight),50,20);
		////center crossed lines
		g.setColor(CircleCol);
		g.fillArc(TopCornerX,TopCornerY,rect_width,rect_height,0,360);
		
		
		
		
		
		g.setColor(rect_color);
		g.fillRect((int)(x_center-width_rectangle),y_center+viewHeight, 2*width_rectangle, r-viewHeight);
		
		g.setColor(triangle_color);
		
		int[] xPoints = {(int)(x_center-width_rectangle),x_center,(int)(x_center+width_rectangle)};
		int[] yPoints = {y_center+viewHeight,y_center+viewHeight-width_rectangle,y_center+viewHeight};
		g.fillPolygon(xPoints, yPoints, 3);
		
		g.setColor(cirecle_border);
		g.drawLine((int)(x_center-r*.7),y_center,(int)( x_center+r*.7),y_center);
		g.drawLine(x_center,(int)(y_center-r*.7),x_center,(int)( y_center+r*.7));
		g.drawArc(TopCornerX,TopCornerY,rect_width,rect_height,0,360);
		
		
		// to hide  triangle when it goes beyond circle
		g.setColor(m_bkg_Color);
		g.fillRect((int)(x_center-r),y_center+r+1, 2*r, r);
		g.fillRect((int)(x_center-r),y_center-2*r-1, 2*r, r);
		
		
		
		
		
		
		
		
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
	}	
	private void showScale(Graphics g)
	{ 
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(cirecle_border);
        int r=(int)scale*radius;
        //Drawing arrow 
        g2d.drawLine((int)(x_center+r*.85),y_center,(int)( x_center+r*1.3),y_center);
        
        g2d.setColor(cirecle_border);
        
		int[] xPoints = {(int)(x_center+r*1.3-5),(int)(x_center+r*1.3),(int)(x_center+r*1.3-5)};
		int[] yPoints = {y_center-5,y_center,y_center+5};
		g.fillPolygon(xPoints, yPoints, 3);
		
		//drawing rectangle scale 
		
		g2d.setColor(Color.white);
		g2d.fillRect((int)(x_center+r*1.4+5),(int)(y_center-r), (int)(0.12*r), (int)(2*r));
		
		//adding small marks  to rectangular scales
		g2d.setColor( Color.BLACK);
		int steps = (int)((r)/(max-min));
		boolean long_line = true;
		int count=0;
		for(int i=0;i<2*r ; i=i+steps)
		{
			if(long_line)
			{  
				g2d.setColor( Color.BLACK);
				g2d.drawLine((int)(x_center+r*1.4+5),y_center-r+i,(int)( x_center+r*1.4+5+0.7*0.12*r),y_center-r+i);
				long_line=false;
				g2d.setColor(cirecle_border );
				Font cur_Font = new Font("Book Antiqua", Font.BOLD, 7);
				g2d.setFont(cur_Font);
				g2d.drawString(String.valueOf(max-count),(int)(x_center+r*1.4-7),y_center-r+i+3);
				count++;
				
				
			}
			else
			{    
				g2d.setColor( Color.BLACK);
				g2d.drawLine((int)(x_center+r*1.4+5),y_center-r+i,(int)( x_center+r*1.4+5+0.3*0.12*r),y_center-r+i);
				long_line=true;
			}
			
		}
		
		
        
        
        

/*		g2d.setColor(Color.BLACK);
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
	    	g.drawLine((int)(x_center+.5*radius),i,(int)(x_center+.5*radius+10),i);
	    	g.drawString((MinRange+steps*j)+"", (int)(x_center+.5*radius+15),i);
	    }
	    
	    
	    g2d.setStroke(strk);*/



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
	 public void setState(int s)
	 {
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
	public void IncreaseViewHeight(int t)
	{
		
		viewHeight=viewHeight+t;	
	}
	public void setRange(int l,int h)
	{
		MinRange=l;
		MaxRange=h;
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
    public void SetRect_color(Color c)
    {
    	rect_color=c;
    }
    public void SetTriangle_color(Color c)
    {
    	triangle_color=c;
    }
    public void SetCirecle_border(Color c)
    {
    	cirecle_border=c;
    }
    public void setMax(int val)
    {
    	max=val;
    	min=max-10;
    }
    public void IncreaseMAx(int val)
    {
    	max=max+val;
    	min=max-10;
    }
    public int reading ()
    {
    	return (max-5) ;
    }
}
