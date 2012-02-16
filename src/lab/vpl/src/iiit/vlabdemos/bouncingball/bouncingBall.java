
package iiit.vlabdemos.bouncingball;

/*
 * $RCSfile: FreeVibration.java,v $
 *
 * Copyright (c) 2009 cITe, Inc. All rights reserved. *
 * $Revision: 1.3 $
 * $Date: 2009/07/21 $
 * $State: Exp $
 */





import iiit.vlabdemos.common.*;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.*;
import javax.swing.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Bounds;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.media.j3d.Group;
import javax.media.j3d.Node;
import javax.media.j3d.Switch;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;


/**
 * Simple Java 3D program that can be run as an application or as an applet.
 */
public class bouncingBall extends javax.swing.JPanel  implements MouseListener, MouseMotionListener,MouseWheelListener{
	//  Variables declaration - do not modify//GEN-BEGIN:variables
	//////////////////////////GUI componenet ///////////////////////////
	private javax.swing.JPanel topPanel;
	private javax.swing.JPanel simulationPanel;
	private javax.swing.JPanel bottomPanel;
	private javax.swing.JPanel rightPanel;

	private javax.swing.JButton startButton=null;
	private javax.swing.JButton replayButton=null;
	private javax.swing.JButton nextButton=null;
	private javax.swing.JButton ExitBtn=null;
	private javax.swing.JButton Cool=null;
	private javax.swing.JButton ManualButton=null;




	//private GraphPlotter         graphPlotter;
	////////////////////////////Java3D componenet ///////////////////////////

	private SimpleUniverse      univ = null;                  // Simple Universe Java3D
	private BranchGroup         scene = null;                 // BranchGroup Scene graph
	private Switch 				switchGroup=null;			 //
//
//	private BallMotion          leftBall = null;              //object of class Ballmotion.
//	private BallMotion         rightBall = null;
	
	private double hardeep = 1;


	private HorizNGraphs	forceGraph =null;
	private HorizNGraphs		energyGraph =null;

	private FullViewGraph		fullViewGraph= new FullViewGraph();

	private HashMap 			hm = new HashMap();
	
	private J3DShape 			m_j3d	= new J3DShape();
	private double x=0.4;


	private JLabel outlbl_val[];
	private JLabel outputlbl[] ;
	private JLabel outputlbl2[];
	private JLabel iLabel[];
	private int trial[];
	private JLabel m_Objective= new JLabel("Objective:");
	private javax.swing.JPanel first;			// Input panel 1
	private javax.swing.JPanel second;	
	private javax.swing.JPanel third;	
	private javax.swing.JPanel fourth;
	private javax.swing.JPanel fifth;
	private javax.swing.JPanel radio;	
	private javax.swing.JPanel button1;	
	private javax.swing.JPanel button2;	
	private javax.swing.JPanel button3;	
	

	DecimalFormat Formatter;
	private Timer timer=null;
	private double fields[];
	private int previousMaterial = 0;
	private int presentMaterial = 0;
	private int previousMass = 5;
	private double previousHeight = 10;
	private double scale=0.4;
	private int check[];
	private int audio_file_count=0;
	private int activeAtom = 1;
	private int activeK = 1;
	private int kArray[] = new int[10];
	
	private int moveBeedX;
	private boolean pressedIn=false;
	private int timerStart = 0;



	// Timer for simulation    



	private int stage = 0;
	private double zval=2.41;


	private boolean startStop = false;
	private boolean valChange = true;
	
	private int current_audio_count=1;
	private static final double g = 9.8;

	URL url = Resources.getResource("resources/video_audio/Bouncing Ball/Instr1.wav");
    AudioClip  clip =  Applet.newAudioClip(url);;
    
    atom atomarray[] = new atom[10];
    private int numAtoms = 2;
    private int numK = 1;
    
    javax.swing.JButton atomButton[] = new javax.swing.JButton[4];;
    javax.swing.JButton kButton[] = new javax.swing.JButton[10];
    JSlider sliderSize = new JSlider(JSlider.HORIZONTAL,  1, 10 , 5);
    JSlider sliderMass = new JSlider(JSlider.HORIZONTAL, 1,10,5);
    JSlider sliderKvalue= new JSlider(JSlider.HORIZONTAL, 1,10,5);
    JSlider sliderDist= new JSlider(JSlider.HORIZONTAL, 1,150,1);
    public int lastValDist = 1;
   
    



	public BranchGroup createSceneGraph() 
	{
		// Create the root of the branch graph
		BranchGroup objRoot = new BranchGroup();
		objRoot.setCapability(Group.ALLOW_CHILDREN_EXTEND );
		objRoot.setCapability(Group.ALLOW_CHILDREN_READ);
		objRoot.setCapability(Group.ALLOW_CHILDREN_WRITE);
		objRoot.setCapability( BranchGroup.ALLOW_DETACH );



		objRoot.addChild(createLabSetup());
		//objRoot.addChild(createBuilding());

		//objRoot.addChild(createAllText());
		//objRoot.addChild(createIonsSwitchGroup());

		objRoot.addChild(m_j3d.createBox(new Vector3d(0,-0.32, -.1),new Vector3d(1,.03,0.5),new Vector3d(0,0,0),new Color3f(1f, 1f,1f),"resources/images/table.jpg"));
		objRoot.addChild(m_j3d.createBox(new Vector3d(0,0.4, -.6),new Vector3d(3,.9,.1),new Vector3d(0f, 0f,0f), new Color3f(0.6f,0.6f,0.6f)));

		 																														
		return objRoot;
	}

	private Canvas3D createUniverse(Container container) 
	{
		GraphicsDevice graphicsDevice;
		if (container.getGraphicsConfiguration() != null) {
			graphicsDevice = container.getGraphicsConfiguration().getDevice();
		} else {
			graphicsDevice =
				GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		}
		GraphicsConfigTemplate3D template = new GraphicsConfigTemplate3D();
		GraphicsConfiguration config = graphicsDevice.getBestConfiguration(template);

		Canvas3D c = new Canvas3D(config);

		univ = new SimpleUniverse(c);

		// This will move the ViewPlatform back a bit so the
		// objects in the scene can be viewed.

		ViewingPlatform viewingPlatform = univ.getViewingPlatform();
		//setLight();

		univ.getViewingPlatform().setNominalViewingTransform();


		ViewingPlatform vp = univ.getViewingPlatform();
		TransformGroup steerTG = vp.getViewPlatformTransform();
		Transform3D t3d = new Transform3D();
		steerTG.getTransform(t3d);
		Vector3d s = new Vector3d();
		Vector3f currPos=new Vector3f();
		t3d.get(currPos); 

		// System.out.println("current Pos:" + currPos);


		t3d.lookAt( new Point3d(0, 0, 2.41 ), new Point3d(0,0,0), new Vector3d(0,1,0));
		t3d.invert();  

		//t3d.setTranslation(new Vector3d(0,0,8));
		steerTG.setTransform(t3d); 


		// Ensure at least 5 msec per frame (i.e., < 200Hz)
		univ.getViewer().getView().setMinimumFrameCycleTime(5);

		return c;
	}

	private void setLight() {
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
		PlatformGeometry pg = new PlatformGeometry();


		Color3f ambientColor = new Color3f(0.1f, 0.1f, 0.1f);
		AmbientLight ambientLightNode = new AmbientLight(ambientColor);
		ambientLightNode.setInfluencingBounds(bounds);
		pg.addChild(ambientLightNode);


		Color3f light1Color = new Color3f(1.0f, 1.0f, 0.9f);
		Vector3f light1Direction  = new Vector3f(1.0f, 1.0f, 1.0f);
		Color3f light2Color = new Color3f(1.0f, 1.0f, 1.0f);
		Vector3f light2Direction  = new Vector3f(-1.0f, -1.0f, -1.0f);

		DirectionalLight light1
		= new DirectionalLight(light1Color, light1Direction);
		light1.setInfluencingBounds(bounds);
		pg.addChild(light1);

		DirectionalLight light2
		= new DirectionalLight(light2Color, light2Direction);
		light2.setInfluencingBounds(bounds);
		pg.addChild(light2);

		ViewingPlatform viewingPlatform = univ.getViewingPlatform();
		viewingPlatform.setPlatformGeometry( pg );


	}


	void destroy() {
		univ.cleanup();
	}

	public static TransformGroup createSphereAt(float x , float y, float z,float radius,Color3f col){
        Sphere atom1 = new Sphere(radius);
        
        Appearance appearance1 = new Appearance();
        appearance1.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
        appearance1.setCapability(Appearance.ALLOW_TRANSPARENCY_ATTRIBUTES_READ);
        appearance1.setCapability(Appearance.ALLOW_TRANSPARENCY_ATTRIBUTES_WRITE);
        appearance1.setCapability(Appearance.ALLOW_MATERIAL_READ);
        appearance1.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
        
        
        appearance1.setColoringAttributes(new ColoringAttributes(col,0));
//        TransparencyAttributes ta = new TransparencyAttributes(TransparencyAttributes.NICEST,.01f);
//        ta.setCapability(Transparency.TRANSLUCENT);
//        appearance1.setTransparencyAttributes(ta);
        appearance1.setTransparencyAttributes(new TransparencyAttributes(TransparencyAttributes.NICEST,.1f));
//        		TransparencyAttributes.BLENDED,0.1f,
//        	    TransparencyAttributes.BLEND_SRC_ALPHA,
//        	    TransparencyAttributes.BLEND_ONE));//TransparencyAttributes.FASTEST,.1f));
        
          atom1.setAppearance(appearance1);

//        Transform3D tr2 = new Transform3D();
//        tr2.setTranslation(new Vector3d(0f,0f,0f));
        
        TransformGroup tg = new TransformGroup();//tr2);
        Transform3D transform = new Transform3D();
        Vector3f vector = new Vector3f(x, y, z);
        Color3f light1Color = new Color3f(255f, 0.1f, 0.1f);
        BoundingSphere bounds = 
           new BoundingSphere(new Point3d(0.0,0.0,0.0), .2f);
      
        Vector3f light1Direction  = new Vector3f(0, 0,0);
        DirectionalLight light1
          = new DirectionalLight(col, light1Direction);
        
        
        light1.setInfluencingBounds(bounds);
        light1.setEnable(true);
        
        //light1.setCapability(Transparency.TRANSLUCENT);
        tg.addChild(light1);
        transform.setTranslation(vector);
        tg.setTransform(transform);
        tg.addChild(atom1);
        
//        if(atom1.getBounds().intersect(new Point3d(+.05f,.05f,.05f))){
//        	System.out.println("yes i have done it" + atom1.getBounds());
//        }
//        else{
//        	System.out.println("No still not."+ atom1.getBounds());
//        }
        return tg;
    }
	public TransformGroup createSpringAt(float x1, float y1, float z1, float x2, float y2, float z2,int loops){
		Transform3D tr2 = new Transform3D();
        tr2.setTranslation(new Vector3d(x1,y1,z1));
		
		
		TransformGroup tg = new TransformGroup(tr2);
		float part = (x2-x1)/loops;
//		float tmp_x1=x1,tmp_y1=y1,tmp_z1=z1;
		float tmp_x1 = 0,tmp_y1 = 0,tmp_z1 =0;
//		Point3d p1 = new Point3d(tmp_x1,tmp_y1,tmp_z1);
//		Point3d p2 = new Point3d(tmp_x1+part,tmp_y1+0.02,tmp_z1);
//		Vector3d pos = new Vector3d(0, 0, 0);
//		Vector3d scale = new Vector3d(1,1,1);
//		Color3f red = new Color3f(255f,0f,0f);
//		TransformGroup line1 = tg.addChild((TransformGroup)m_j3d.createLine(p1,p2,pos,scale,red));
//		p1 = new Point3d()
		Vector3d pos = new Vector3d(0, 0, 0);
		for(int i = 0 ; i < loops ; i++){
//			Point3d p1 = new Point3d(0f,0f,0f);
//			Point3d p2 = new Point3d(part,.02,0);
			Point3d p1 = new Point3d(tmp_x1,tmp_y1,tmp_z1);
			Point3d p2 = new Point3d(tmp_x1+part,tmp_y1+0.02,tmp_z1);
//			Vector3d pos = new Vector3d(tmp_x1, tmp_y1, tmp_z1);
			Vector3d scale = new Vector3d(1,1,1);
			Color3f red = new Color3f(255f,0f,0f);
			tg.addChild((TransformGroup)m_j3d.createLine(p1,p2,pos,scale,red));
			tmp_x1=tmp_x1+part;			
			//pos = new Vector3d(tmp_x1, tmp_y1, tmp_z1);
			//tmp_z1=tmp_z1;
//			p2 = new Point3d(-part,.02,0);
			p1 = new Point3d(tmp_x1+part,tmp_y1,tmp_z1);
			tg.addChild((TransformGroup)m_j3d.createLine(p1, p2, pos, scale, red));			
		}
		tg.setCapability(Node.ALLOW_AUTO_COMPUTE_BOUNDS_READ);
		tg.setCapability(Node.ALLOW_AUTO_COMPUTE_BOUNDS_WRITE);
		return tg;//(TransformGroup)m_j3d.createLine(p1,p2,pos,scale,red);
		
	}
	public class atom{
		public float x,y,z,rad,mass;
		String hash;
		
		public Color3f col;
		
		public atom(){			
		}
		public atom(float x,float y,float z,float rad,Color3f col,String hash){
			this.x=x;
			this.y=y;
			this.z=z;
			this.rad=rad;
			this.col=col;
			this.hash = hash;
			this.mass = 0.05f;
			
		}
		public TransformGroup create(){
			return createSphereAt(x,y,z,rad,col);
		}
		public int getSize(){
			float tmp = this.rad*100;
			return (int)tmp;
		}
		public int getMass(){
			float tmp = this.mass*100;
			return (int)tmp;
		}
		public void setMass(float mass){
			System.out.println("mass : "+ mass);
			this.mass = mass;			
		}
		public void setSize(float rad){
			System.out.println("in atom setsize : " + rad);
			float scaleby;
//			if(this.rad > rad){
//				scaleby = 1 + 10*(this.rad - rad); 
//			}
//			else{
//				
//			}
			scaleby = 10*(this.rad + rad);
			///this.rad=rad;
			TransformGroup scalesize = (TransformGroup)hm.get(this.hash);
			Transform3D t3d = new Transform3D();
			scalesize.getTransform(t3d);
			t3d.setScale(new Vector3d(scaleby,scaleby,scaleby));
			scalesize.setTransform(t3d);
		}
	}

	private Group createLabSetup() {
		hm.put("hydrogen", 0);
		hm.put("carbon", 1);
		hm.put("nitrogen", 2);
		hm.put("oxygen", 3);
		Color3f green = new Color3f(.1f,1.4f,.1f);
        Color3f yellow = new Color3f(255f,230f,0f);
        Color3f trycol = new Color3f(1.0f*170/255,1.0f*170/255,1.0f*170/255);
		
		
		//to make slider changelistener work for all atoms
		atomarray[0] = new atom(.0f,.0f,.0f,.05f,green,"atom0");
		switchGroup = new Switch( Switch.CHILD_MASK );
        switchGroup.setCapability( Switch.ALLOW_SWITCH_WRITE );
		
		Transform3D t3d = new Transform3D();
		t3d.setTranslation(new Vector3d(-.15f,0f,0f));
		
		TransformGroup group = new TransformGroup(t3d);
		TransformGroup spring = new TransformGroup();
		
        
        TransformGroup sp1Group = new TransformGroup();
        TransformGroup sp2Group = new TransformGroup();
        TransformGroup atomTg1 = new TransformGroup();
        TransformGroup atomTg2 = new TransformGroup();
        //TransformGroup laserAtbeed2 = new TransformGroup();
        
        atomarray[1]= new atom(.0f,.0f,.0f,.05f,green,"atom1");
        atomTg1 = atomarray[1].create();
        hm.put("atom1", atomTg1);
        
        
        sp1Group.addChild(atomTg1);
        sp1Group.addChild(createSphereAt(-.125f, .0f, .0f, .025f, yellow));
        //sp1Group.addChild(m_j3d.createSphere(new Vector3d(0f,0f,0f), .05f, green, green,green, green, .75f));
        //createSphereAt(-.275f, .0f, .0f, .025f, yellow).getBounds().intersect(new Point3d());
        sp1Group.addChild(m_j3d.createLine(new Point3d(-.10f,0f,0f),new Point3d(.0f,0f,0f),new Vector3d(0f,0f,0f),new Vector3d(1,1,1),new Color3f(0f,0f,255f)));
        //the box to stop the beed to move
        sp1Group.addChild(m_j3d.createBox(new Vector3d(-.16f,.0f,.0f), new Vector3d(.01f,.1f,.1f), new Vector3d(0f,0f,0f), new Color3f(1.0f*43/255f,1.0f*43/255f,1.0f*43/255f)));
        //laser number 1
        sp1Group.addChild(m_j3d.createConewithTransparency(new Vector3d(-.125f,-.125f,.0f), new Vector3d(1f,2.5f,1f), new Vector3d(0f,0f,0f), new Color3f(1.0f*205/255,1.0f*85/255,1.0f*85/255)));
        sp1Group.addChild(m_j3d.createConewithTransparency(new Vector3d(-.125f,.125f,.0f), new Vector3d(1f,2.5f,1f), new Vector3d(180f,0f,0f), new Color3f(1.0f*205/255,1.0f*85/255,1.0f*85/255)));
        atomarray[2] = new atom(.30f, .0f, .0f, .05f, green,"atom2");
        atomTg2 = atomarray[2].create();
        hm.put("atom2", atomTg2);
        
		sp2Group.addChild(atomTg2);//createSphereAt(.15f, .0f, .0f, .05f, green));		
		sp2Group.addChild(createSphereAt(.425f, .0f, .0f, .025f, yellow));
		sp2Group.addChild(m_j3d.createLine(new Point3d(.40f,0f,0f),new Point3d(.30f,0f,0f),new Vector3d(0f,0f,0f),new Vector3d(1,1,1),new Color3f(0f,0f,255f)));
		sp2Group.addChild(m_j3d.createConewithTransparency(new Vector3d(.425f,-.125f,.0f), new Vector3d(1f,2.5f,1f), new Vector3d(0f,0f,0f), new Color3f(1.0f*205/255,1.0f*85/255,1.0f*85/255)));
        sp2Group.addChild(m_j3d.createConewithTransparency(new Vector3d(.425f,.125f,.0f), new Vector3d(1f,2.5f,1f), new Vector3d(180f,0f,0f), new Color3f(1.0f*205/255,1.0f*85/255,1.0f*85/255)));
		group.addChild(sp1Group);
		group.addChild(sp2Group);
		
		spring.addChild(switchGroup);
		
		switchGroup.addChild(createSpringAt(0f,0f,0f,+.30f,.0f,.0f,20));
		switchGroup.addChild(createSpringAt(0f,0f,0f,+.30f,.0f,.0f,15));
		switchGroup.addChild(createSpringAt(0f,0f,0f,+.30f,.0f,.0f,10));
		switchGroup.addChild(createSpringAt(0f,0f,0f,+.30f,.0f,.0f,5));
		java.util.BitSet visibleNodes = new java.util.BitSet( switchGroup.numChildren() );
        visibleNodes.set( 0 );
        switchGroup.setChildMask( visibleNodes );
		
        
        //spring = createSpringAt(0f, .0f, .0f, +.30f, .0f, .0f);
		group.addChild(spring);

		group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		group.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		spring.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		spring.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		sp1Group.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		sp1Group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		sp2Group.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		sp2Group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		atomTg1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		atomTg1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		atomTg2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		atomTg2.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		hm.put("spring", spring);
		hm.put("molecule1",sp1Group);
		hm.put("molecule2",sp2Group);
		
//		my try
		
		MouseRotate mr = new MouseRotate();
	    mr.setTransformGroup(group);
	    mr.setSchedulingBounds(new BoundingSphere(new Point3d(), 100.0));
	    mr.setFactor(0.007);
	    group.addChild(mr);
	    //my try
        return group;
		
		/*
		switchGroup = new Switch( Switch.CHILD_MASK );
        switchGroup.setCapability( Switch.ALLOW_SWITCH_WRITE );
   

		Transform3D ts = new Transform3D();
		ts.setTranslation(new Vector3d(0,0,0));

		
		TransformGroup objtrans = new TransformGroup(ts);
		objtrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objtrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		
		 creating first Ball 
		ts = new Transform3D();
		ts.setTranslation(new Vector3d(-0.3,0.35,0));
		TransformGroup leftBallMotion = new TransformGroup(ts);

		leftBallMotion.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		leftBallMotion.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		Color3f r1= new Color3f(.5f,.5f,0.7f);
		Color3f b1 = new Color3f(1f,1f,1f);  
		Vector3d pos1 = new Vector3d(0,0.35,0);
		Vector3d pos2 = new Vector3d(0,0.35,0);
		leftBallMotion.addChild(m_j3d.createTexturedSphere(new Vector3d(0,0,0),0.05f , b1, b1, b1, b1, 20f,"resources/images/football.bmp"));
		hm.put("ball1",leftBallMotion);
		
         creating the second ball
		ts = new Transform3D();
		ts.setTranslation(new Vector3d(0.3,0.35,0));
		TransformGroup rightBallMotion = new TransformGroup(ts);
		rightBallMotion.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		rightBallMotion.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		rightBallMotion.addChild(switchGroup);
		switchGroup.addChild(m_j3d.createTexturedSphere(new Vector3d(0,0,0),0.05f , r1, b1, r1, b1, 10f,"resources/images/football.bmp"));
		switchGroup.addChild(m_j3d.createTexturedSphere(new Vector3d(0,0,0),0.05f , r1, b1, r1, b1, 10f,"resources/images/wood.jpg"));
		switchGroup.addChild(m_j3d.createTexturedSphere(new Vector3d(0,0,0),0.05f , r1, b1, r1, b1, 10f,"resources/images/steel.bmp"));
		hm.put("ball2",rightBallMotion);
		java.util.BitSet visibleNodes = new java.util.BitSet( switchGroup.numChildren() );
        visibleNodes.set( 1);
        switchGroup.setChildMask( visibleNodes );
		objtrans.addChild(leftBallMotion);
		objtrans.addChild(rightBallMotion);
        
		 calling the constructor 
		leftBall=  new BallMotion("ball1",pos1,5,10,0.9,9.8);
		rightBall=  new BallMotion("ball2",pos2,5,10,0.4,9.8);
		outputlbl[4].setText("  : " +  Formatter.format("0")  + " J"); 
		outputlbl[5].setText("  : " +  Formatter.format("0")  + " J"); 
		outputlbl[10].setText("  : " +  Formatter.format("0")  + " J"); 
		outputlbl[11].setText("  : " +  Formatter.format("0")  + " J");
		
		
		return objtrans;*/
		
	}






	private Group createIonsSwitchGroup()
	{
		switchGroup = new Switch( Switch.CHILD_MASK );
		switchGroup.setCapability( Switch.ALLOW_SWITCH_WRITE );


		///////////////  Lines ///////////////////////


		////////////////////////////// Text ///////////////////////
		// switchGroup.addChild(createFont3D("L (m)",new Vector3d(-0.2f,0.2f,0f),new Vector3d(.05,.05,.05), new Color3f(0.0f, 0.0f, 1.0f),"sl"));
		//switchGroup.addChild(createFont3D(">",new Vector3d(0.48f,0.52f,.1f),new Vector3d(.1,.07,.05), new Color3f(0.0f, 0.0f, 0.0f),"a"));
		//switchGroup.addChild(createFont3D("<",new Vector3d(-0.53f,0.52f,.1f),new Vector3d(.1,.07,.05), new Color3f(0.0f, 0.0f, 0.0f),"a"));


		java.util.BitSet visibleNodes = new java.util.BitSet( switchGroup.numChildren() );
		for(int i=0; i<4; i++) visibleNodes.set( i ); 

		// 	 	assign the visibility mask to the Switch
		switchGroup.setChildMask( visibleNodes );


		return switchGroup;
	}


	/**
	 * Creates new form FreeVibration
	 */
	public bouncingBall(Container container) {
		// Initialize the GUI components
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		initComponents();

		centerPanel(container);
		//        scene.addChild(bgleg);
	}


	// ----------------------------------------------------------------

	// Applet framework

	public static class MyApplet extends JApplet {
		bouncingBall mainPanel;

		public void init() {
			setLayout(new BorderLayout());
			mainPanel = new bouncingBall(this);
			add(mainPanel, BorderLayout.CENTER);

		}

		public void destroy() {
			mainPanel.destroy();
		}
	}

	// Application framework

	private static class MyFrame extends JFrame {
		MyFrame() {
			setLayout(new BorderLayout());
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			setTitle("");
			getContentPane().add(new bouncingBall(this), BorderLayout.CENTER);
			pack();
		}
	}

	// Create a form with the specified labels, tooltips, and sizes.
	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MyFrame().setVisible(true);
			}
		});
	}
	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc=" Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {


		//      new GridLayout(2, 1)
		setLayout(new java.awt.BorderLayout());

		bottomPanel = new javax.swing.JPanel(); 	// input from user at bottom
		simulationPanel = new javax.swing.JPanel(); // 3D rendering at center
		topPanel= new javax.swing.JPanel();    		// Pause, resume, Next
		rightPanel = new javax.swing.JPanel();    	// Graph and Input and Output Parameter


		topPanel();

		simulationPanel.setPreferredSize(new java.awt.Dimension(1000, 500));
		simulationPanel.setLayout(new java.awt.BorderLayout());

		bottomPanel();

		rightPanel();

		//      Set Alignment
		//add(guiPanel, java.awt.BorderLayout.NORTH);
		add(topPanel, java.awt.BorderLayout.NORTH);
		add(simulationPanel, java.awt.BorderLayout.CENTER);
		add(bottomPanel, java.awt.BorderLayout.SOUTH);
		add(rightPanel, java.awt.BorderLayout.EAST); 

		startStop = false;
		valChange = false;
		valChange = true;
		stage =0;

		timer = new Timer(50,new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//...Perform a task...
				timerActionPerformed(evt);
			}
		});




	}// </editor-fold>//GEN-END:initComponents
	
	private void resetSliders(){
		
		for(int i = 1 ; i <= numAtoms; i++){
			activeAtom = 0;
			sliderSize.setValue(10);			
			sliderMass.setValue(10);
			System.out.println("active atom in reset = " + i);
			activeAtom = i;			
			sliderSize.setValue(5);			
			sliderMass.setValue(5);		
			//sliderKvalue.setValue(5);
		}
		System.out.println("i := "  );
		for(int j = 1 ; j <= numK; j++){
			System.out.println("in active k :");
			activeK = j;
			sliderKvalue.setValue(5);
		}
		sliderDist.setValue(1);		
	}
	
	private void reset()
	{
		//Initialising the value of Left Ball
		//leftBall.setHeight(0.35, 10);
		// leftBall.setTime(0.0);

		// Initialising the value of Right Ball
		hardeep = 1;
		
		
		pressedIn = false;
		timerStart = 0;
		
		lastValDist = 1;
		
		timerStart = 0;
		
		resetSliders();
		activeAtom = 1;
		activeK = 1;
		
		//JSlider slider =(JSlider)hm.get("slider2");
	     
		
		//rightBall.setHeight( (((1/25.0f)*(slider.getValue()+4)) - 0.21) , slider.getValue());
		
		//rightBall.setTime(0.0);
		
	}

	private void topPanel() {

		java.awt.GridBagConstraints gridBagConstraints;

		javax.swing.JPanel guiPanel = new javax.swing.JPanel(); // Pause, resume at top
		guiPanel.setLayout(new java.awt.GridBagLayout());
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);  

		//        javax.swing.JButton pauseButton = new javax.swing.JButton();  
		//        javax.swing.JButton startButton = new javax.swing.JButton(); 
		
		replayButton = new javax.swing.JButton("Reset");
		ImageIcon icon = m_j3d.createImageIcon("resources/icons/restart.png"); 
		replayButton.setIcon(icon);
		ManualButton = new javax.swing.JButton("Manual");
		icon = m_j3d.createImageIcon("resources/icons/hand_24.png");
		ManualButton.setIcon(icon);
		startButton = new javax.swing.JButton("Start");
		icon = m_j3d.createImageIcon("resources/icons/start.png"); 
		startButton.setIcon(icon);
		//nextButton = new javax.swing.JButton("Next");
	   // icon = m_j3d.createImageIcon("resources/icons/next.png");        
		//nextButton.setIcon(icon);
		//        ImageIcon icon = m_j3d.createImageIcon("resources/images/show_graph.png");        
		//        startButton.setIcon(icon);
		//startButton.setPreferredSize(new Dimension(100,30));
		ExitBtn =new javax.swing.JButton("Exit");
		icon = m_j3d.createImageIcon("resources/icons/exit.png");        
		ExitBtn.setIcon(icon);
		 ExitBtn.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	// Toggle
	            	
	            	System.exit(0);
	            	
	            }
	          });
		




		trial = new int[4];
		check = new int[4];
		check[0] = 0;
		check[1] = 0;
		check[2] = 0;
		check[3] = 0;
		//reStartButton.setText("Re-Start");  
		replayButton.setEnabled(true);
		//nextButton.setEnabled(false);



		guiPanel.setBackground(new Color(67,143,205));//Color.BLACK
		topPanel.setLayout(new java.awt.BorderLayout());
		topPanel.add(guiPanel, java.awt.BorderLayout.NORTH);


		startButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// Toggle
				startStop = !startStop;

				if(startStop)  startSimulation(evt); 
				else pauseSimulation();
				univ.getCanvas().repaint();
			}
		});
		ManualButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JOptionPane.showMessageDialog(simulationPanel,"You can manually change the distance between the atoms. Use the slider given below.");
				enableStage(5);				
			}
		});



		replayButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				reset();
				replayButton.setEnabled(false);
				//startButton.setEnabled(true);
				startButton.setText("Start");
				//startStop = !startStop;
				startStop = false;

//				forceGraph.clearGraphValue();
//				energyGraph.clearGraphValue();

				valChange = true;
				resetSimulation(evt);
				univ.getCanvas().repaint();
				//timer.start();

				//            	reStartButton.setEnabled(false);
				//                //startButton.setEnabled(true);
				//                startButton.setText("Start");
				//                startStop = false;
				//                timer.stop();
//				                forceGraph.clearGraphValue();
//				                energyGraph.clearGraphValue();
				//                
				//                valChange = true;

			}
		});

		/* nextButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {

	            	if(stage > 0 && check[stage - 1] == 0 || stage == 0)
	            	{
	            		stage++;
	            	}
	            	JSlider slider = (JSlider)hm.get("slider1");
	            	slider.setValue(5);
	            	outputlbl[5].setText(" : " +  "5"  + " kg"); 
					iLabel[4].setText(":: " + "5"  + " kg");
	            	slider = (JSlider)hm.get("slider2");
	            	slider.setValue(10);
	            	outputlbl[6].setText(" : " +  "10"  + " m"); 
					iLabel[6].setText(":: " + "10"  + " m");
					JRadioButton wood = (JRadioButton)hm.get("wood");
					wood.setSelected(true);
					rightBall.setHeight(0.35, 10);
					rightBall.setMass(5);
					rightBall.setCoeffecient("Wood");
					leftBall.setHeight(0.35, 10);
					
	            	
	            	nextButton.setEnabled(false);            	
	            	onNextStage();
	            	univ.getCanvas().repaint();
	            }
	          });*/
		 
		 
		




		guiPanel.add(replayButton, gridBagConstraints);
		guiPanel.add(startButton, gridBagConstraints);
		guiPanel.add(ManualButton, gridBagConstraints);
		 //guiPanel.add(nextButton, gridBagConstraints);
		  guiPanel.add(ExitBtn, gridBagConstraints);






	}


	private void rightPanel() {

		rightPanel.setLayout(new java.awt.GridLayout(2,1,0,1));
		rightPanel.setBorder(BorderFactory.createLineBorder(new Color(235,233,215),4));
		int w=300;
		int h=300;
		//javax.swing.JPanel guiPanel = new javax.swing.JPanel(); // Pause, resume at top
		//guiPanel.setLayout(new java.awt.GridBagLayout());
		//gridBagConstraints = new java.awt.GridBagConstraints();
		//gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 0);  
		JPanel  p[]= new JPanel[1];
		outlbl_val = new JLabel[12];
		//rightPanel.add(p);
		 String[]  Names={"Force"};
	     String[] Units={"N"};
		forceGraph = new HorizNGraphs(w,h,1,"r","F(r)"); 
		forceGraph.setHeading("Force vs Extension");
		forceGraph.setNUnits(Units);
		forceGraph.setNCaptions(Names);
		forceGraph.setAxisUnit("m","N");
		forceGraph.setYAxisColor(Color.BLACK);
		forceGraph.setYScale(750);
		forceGraph.setYoffset(270);
	
		forceGraph.fitToYwindow(true);
		Color colors[]=new Color[1];
        colors[0]=Color.BLUE;
        
      
        
        
        forceGraph.setNColors(colors);
       
		p[0]=forceGraph ;
		 p[0].setBorder(BorderFactory.createLineBorder(new Color(235,233,215),4));
		p[0].setVisible(true);


		/*p[1] = new JPanel(new java.awt.GridLayout(4,2,0,0));
		// p[1].setBackground(new Color(130,169,193));
		p[1].setBackground(new Color(219,226,238));

	/*	int Position=JLabel.RIGHT;
		JLabel lbl = new JLabel("Height", Position);  p[1].add(lbl);

		outlbl_val[0] = new JLabel(" : 10 m", JLabel.LEFT); p[1].add(outlbl_val[0]);
		lbl = new JLabel("Mass", Position);  p[1].add(lbl);
		outlbl_val[1] = new JLabel(" : 5 kg", JLabel.LEFT); p[1].add(outlbl_val[1]);
		lbl = new JLabel("Material Of Ball", Position);  p[1].add(lbl);
		outlbl_val[3] = new JLabel(" : Rubber ", JLabel.LEFT); p[1].add(outlbl_val[3]);

		lbl = new JLabel(" gravity", Position);  p[1].add(lbl);

		outlbl_val[2] = new JLabel(" : 9.8 m/s^2", JLabel.LEFT); p[1].add(outlbl_val[2]);*/
		
//		PanelWindowWrapper win = new PanelWindowWrapper(p,new Color(0.96f,0.96f,0.96f));
//		rightPanel.add(win);




		//p[1].setBackground(new Color(219,226,238));



		// forceGraph.setBorder(BorderFactory.createLineBorder(new Color(235,233,215),8));

		 //NewHorizontalGraphwrapper wrapper = new  NewHorizontalGraphwrapper(forceGraph ,Color.GRAY);
		 rightPanel.add(forceGraph);
		p = new JPanel[1];

        String[] Name = {"1 vs 2"};
        String[] Unit = {"J"};
		energyGraph = new HorizNGraphs(w,h,1,"r","U(r)");
		energyGraph.setHeading("Potential energy vs extension");
		 
	    energyGraph.setNUnits(Unit);
	        
	    energyGraph.setNCaptions(Name);
		energyGraph.setAxisUnit("m","J");
		energyGraph.setYScale(750);
		energyGraph.setYAxisColor(new Color(0.0f,0.54f,0.27f));
		energyGraph.setYoffset(270);
	//	energyGraph.fitToYwindow(true);
		Color colors1[]=new Color[1];
        colors1[0]=Color.BLUE;
       
       
        
        
        energyGraph.setNColors(colors1);
		//energyGraph.setBorder(BorderFactory.createLineBorder(new Color(235,233,215),8));
		p[0]=energyGraph;
		p[0].setBorder(BorderFactory.createLineBorder(new Color(235,233,215),4));
		//wrapper = new NewHorizontalGraphwrapper(energyGraph,Color.GRAY);
	/*	p[1] = new JPanel(new java.awt.GridLayout(4,2,0,0));
		p[1].setBackground(new Color(219,226,238));


		lbl = new JLabel("Height", Position);  p[1].add(lbl);

		outlbl_val[4] = new JLabel(" : 10 m", JLabel.LEFT); p[1].add(outlbl_val[4]);
		lbl = new JLabel("Mass", Position);  p[1].add(lbl);
		outlbl_val[5] = new JLabel(" : 5 kg", JLabel.LEFT); p[1].add(outlbl_val[5]);
		
		lbl = new JLabel("Material of Ball", Position);  p[1].add(lbl);
		outlbl_val[7] = new JLabel(" : Wood ", JLabel.LEFT); p[1].add(outlbl_val[7]);

		lbl = new JLabel(" gravity", Position);  p[1].add(lbl);

		outlbl_val[6] = new JLabel(" : 9.8 m/s^2", JLabel.LEFT); p[1].add(outlbl_val[6]);*/
//		
//		win = new PanelWindowWrapper(p,new Color(0.96f,0.96f,0.96f));
//		rightPanel.add(win);





		//        gwrapper = new HorizontalGraphWrapper(energyGraph,1,2);
		//        rightPanel.add(gwrapper);
		// Can't call draw graph here as Graphics object is not initialize

		//guiPanel.add(forceGraph);

		//rightPanel.setLayout(new java.awt.BorderLayout());
		//GridLayout(int rows, int cols, int hgap, int vgap) 


		rightPanel.add(energyGraph);
		rightPanel.setSize(300,500);
		//rightPanel.setMaximumSize(new Dimension(300,500));

		//rightPanel.setVisible(false);        

		//rightPanel.setBackground(new Color(0,0,0));

		rightPanel.setVisible(true);
	}


	private void centerPanel(Container container)
	{
		simulationPanel.setPreferredSize(new java.awt.Dimension(1024, 660));
		simulationPanel.setLayout(new java.awt.BorderLayout());

		javax.swing.JPanel guiPanel = new javax.swing.JPanel();
		guiPanel.setBackground(new Color(100,100,100));
		JLabel lbl = new JLabel("Molecular Interaction", JLabel.CENTER);
		lbl.setFont(new Font("Book Antiqua", Font.BOLD, 18));

		lbl.setForeground(Color.orange);
		//lbl.setBackground(Color.BLACK);
		guiPanel.add(lbl);
		simulationPanel.add(guiPanel, BorderLayout.NORTH);

		Canvas3D c = createUniverse(container);
		simulationPanel.add(c, BorderLayout.CENTER);
		c.addMouseMotionListener(this);
		c.addMouseWheelListener(this);
		c.addMouseListener(this);

		JPanel btmPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
		simulationPanel.add(btmPanel, BorderLayout.SOUTH);

		guiPanel = new javax.swing.JPanel();
		guiPanel.setBackground(new Color(100,100,100));         
		simulationPanel.add(guiPanel, BorderLayout.EAST);

		guiPanel = new javax.swing.JPanel();
		guiPanel.setBackground(new Color(100,100,100));         
		simulationPanel.add(guiPanel, BorderLayout.WEST);

		// Create the content branch and add it to the universe
		scene = createSceneGraph();
		univ.addBranchGraph(scene);


		m_Objective = new JLabel(" ", JLabel.LEFT);
		m_Objective.setFont(new Font("Arial", Font.BOLD, 13));
		m_Objective.setForeground(Color.WHITE);
		guiPanel = new javax.swing.JPanel();
		guiPanel.setBackground(new Color(100,100,100));        
		guiPanel.add(m_Objective);
		btmPanel.add(guiPanel,BorderLayout.NORTH);

		guiPanel = new javax.swing.JPanel(); //          
		guiPanel.setBackground(new Color(235,233,215));
		guiPanel.setLayout(new java.awt.GridBagLayout());
		java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);  







		btmPanel.add(guiPanel,BorderLayout.CENTER);

		guiPanel = new javax.swing.JPanel(); // 
		guiPanel.setBackground(new Color(130,169,193));
		guiPanel.setBorder(BorderFactory.createLineBorder(new Color(235,233,215),4));
		guiPanel.add(createInputOutputPanel());
		
		btmPanel.add(guiPanel,BorderLayout.SOUTH);
	}
	
	class ComboPanel extends JPanel {
		 ComboPanel(String title, JComponent c) {
		   setLayout(new FlowLayout());
		   setBorder(new TitledBorder(title));
		   setBackground(new Color(130,169,193));
		   add(c);
		 }
		}

	private JPanel createInputOutputPanel(){


		JPanel ioparm = new JPanel(new java.awt.GridLayout(1,2,30,0));
		ioparm.setBackground(new Color(130,169,193));
		JPanel parm = new JPanel(new java.awt.GridLayout(3,3,0,0)); 
		parm.setBackground(new Color(130,169,193));
		outputlbl= new JLabel[17];// new  =
		outputlbl2 = new JLabel[17];
		
		
		

		int i=0;
		
		
		JLabel lbl = new JLabel("atom# ", JLabel.LEFT);  	parm.add(lbl); lbl.setForeground(Color.yellow);
		lbl = new JLabel("size ", JLabel.LEFT);			parm.add(lbl); lbl.setForeground(Color.yellow);
		lbl = new JLabel("mass ",JLabel.LEFT);				parm.add(lbl); lbl.setForeground(Color.yellow);
		
		// lbl = new JLabel("1", JLabel.LEFT); parm.add(lbl);
		atomButton[1] =  new javax.swing.JButton("1");
		
		
		atomButton[1].addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JOptionPane.showMessageDialog(simulationPanel,"The values will be updated for atom #1. Use the sliders given below");
				// Toggle
				activeAtom = 1;
				sliderSize.setValue(atomarray[1].getSize());	
				sliderMass.setValue(atomarray[1].getMass());
				iLabel[4].setText(":: " + Formatter.format(atomarray[1].getSize()) );
				enableStage(4);
			}
		});
		  
		parm.add(atomButton[1]);
		
		outputlbl[i] = new JLabel(" : .05 ", JLabel.LEFT);   		outputlbl[i].setForeground(Color.white); parm.add(outputlbl[i]);
		outputlbl2[i] = new JLabel(" : .05 ", JLabel.LEFT);   		outputlbl2[i].setForeground(Color.white); parm.add(outputlbl2[i++]);
		
		//lbl = new JLabel("2", JLabel.LEFT);	    	parm.add(lbl);
		atomButton[2] = new javax.swing.JButton("2");
		
		atomButton[2].addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// Toggle
				JOptionPane.showMessageDialog(simulationPanel,"The values will be updated for atom #2. Use the sliders given below");
				activeAtom = 2;
				sliderSize.setValue(atomarray[2].getSize());
				sliderMass.setValue(atomarray[2].getMass());
				//sliderSize.fireStateChanged();
				iLabel[4].setText(":: " + Formatter.format(atomarray[2].getSize()) );
				enableStage(4);
			}
		});
		parm.add(atomButton[2]);
		outputlbl[i] = new JLabel(" : .05", JLabel.LEFT);   		outputlbl[i].setForeground(Color.white); parm.add(outputlbl[i]);
		outputlbl2[i] = new JLabel(" : .05 ", JLabel.LEFT);   		outputlbl2[i].setForeground(Color.white); parm.add(outputlbl2[i++]);
		
//		lbl = new JLabel("3", JLabel.LEFT);	    	parm.add(lbl);
//		outputlbl[i] = new JLabel(" : .04", JLabel.LEFT);   		outputlbl[i].setForeground(Color.white); parm.add(outputlbl[i]);
//		outputlbl2[i] = new JLabel(" : .01 ", JLabel.LEFT);   		outputlbl2[i].setForeground(Color.white); parm.add(outputlbl2[i++]);
//		lbl = new JLabel("Material ", JLabel.CENTER);	    	parm.add(lbl);
//		outputlbl[i] = new JLabel(" : Rubber", JLabel.LEFT);   		outputlbl[i].setForeground(Color.white); parm.add(outputlbl[i++]);
///*		lbl = new JLabel("Gravity", JLabel.CENTER);	    	parm.add(lbl);
//		outputlbl[i] = new JLabel(" : 9.8 m/s^2 ", JLabel.LEFT);   		outputlbl[i].setForeground(Color.white); parm.add(outputlbl[i++]);*/
//		lbl = new JLabel(" Kinetic Energy ", JLabel.CENTER);	    	parm.add(lbl);
//		outputlbl[i] = new JLabel(" : 0 J ", JLabel.LEFT);   		outputlbl[i].setForeground(Color.white); parm.add(outputlbl[i++]);
//
//		lbl = new JLabel(" Potenial Energy ", JLabel.CENTER);	    	parm.add(lbl);
//		outputlbl[i] = new JLabel(" : 0 J ", JLabel.LEFT);   		outputlbl[i].setForeground(Color.white); parm.add(outputlbl[i++]);




		ioparm.add(parm);

		parm = new JPanel(new java.awt.GridLayout(2,3,0,0)); 
		//parm.setBackground(new Color(144,186,229));
		parm.setBackground(new Color(130,169,193));
		
		//parm.add(dlt);
		
		lbl = new JLabel("atom#1   ", JLabel.LEFT);  	parm.add(lbl); lbl.setForeground(Color.yellow);
		lbl = new JLabel("atom#2   ", JLabel.LEFT);			parm.add(lbl); lbl.setForeground(Color.yellow);
		lbl = new JLabel("k ",JLabel.LEFT);				parm.add(lbl); lbl.setForeground(Color.yellow);
		
		lbl = new JLabel("1  ", JLabel.LEFT);	    		parm.add(lbl); 
		outputlbl[i] = new JLabel("2  ", JLabel.LEFT);   		 parm.add(outputlbl[i++]);
		kButton[1] = new javax.swing.JButton("k12: 0.05");		kArray[1] = 5;
		kButton[1].addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// Toggle
				JOptionPane.showMessageDialog(simulationPanel,"The K value will be updated for atom #1 and atom #2. Use the slider given below");
				activeK = 1;
				sliderKvalue.setValue(kArray[1]);
				//sliderMass.setValue(atomarray[2].getMass());
				//sliderSize.fireStateChanged();
				//iLabel[4].setText(":: " + Formatter.format(atomarray[2].getSize()) );
				enableStage(3);
			}
		});
		parm.add(kButton[1]);
		//outputlbl2[i] = new JLabel("k12 ", JLabel.LEFT);   		outputlbl2[i].setForeground(Color.white); parm.add(outputlbl2[i++]);
		
		//just now commented
//		lbl = new JLabel("2  ", JLabel.LEFT);	    	parm.add(lbl); 
//		outputlbl[i] = new JLabel("1  ", JLabel.LEFT);   		 parm.add(outputlbl[i++]);
//		kButton[2] = new javax.swing.JButton("k21: 0.05");		kArray[2] = 5;
//		kButton[2].addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				// Toggle
//				JOptionPane.showMessageDialog(simulationPanel,"The K value will be updated for atom #2 and atom #1. Use the slider given below");
//				activeK = 2;
//				sliderKvalue.setValue(kArray[2]);
//				//sliderMass.setValue(atomarray[2].getMass());
//				//sliderSize.fireStateChanged();
//				//iLabel[4].setText(":: " + Formatter.format(atomarray[2].getSize()) );
//				enableStage(3);
//			}
//		});
//		parm.add(kButton[2]);
		//just now commented
		//outputlbl2[i] = new JLabel("k21", JLabel.LEFT);   		outputlbl2[i].setForeground(Color.white); parm.add(outputlbl2[i++]);

//		lbl = new JLabel("Experimental Ball  ", JLabel.RIGHT);   
//		parm.add(lbl); lbl.setForeground(Color.yellow);
//		lbl = new JLabel("Parameters", JLabel.LEFT);			parm.add(lbl); lbl.setForeground(Color.yellow);
//		lbl = new JLabel("Mass", JLabel.CENTER);	    		parm.add(lbl);
//		outputlbl[i] = new JLabel(" : 5 kg", JLabel.LEFT);   		outputlbl[i].setForeground(Color.white); parm.add(outputlbl[i++]);
//		lbl = new JLabel("Height", JLabel.CENTER);	    	parm.add(lbl);
//		outputlbl[i] = new JLabel(" : 10 m", JLabel.LEFT);   		outputlbl[i].setForeground(Color.white); parm.add(outputlbl[i++]);
//		lbl = new JLabel("Material  ", JLabel.CENTER);	    	parm.add(lbl);
//		outputlbl[i] = new JLabel(" : Wood", JLabel.LEFT);   		outputlbl[i].setForeground(Color.white); parm.add(outputlbl[i++]);
//	/*	lbl = new JLabel("Gravity", JLabel.CENTER);	    	parm.add(lbl);
//		outputlbl[i] = new JLabel(" : 9.8 m/s^2 ", JLabel.LEFT);   		outputlbl[i].setForeground(Color.white); parm.add(outputlbl[i++]);*/
//
//
//		lbl = new JLabel(" Kinetic Energy ", JLabel.CENTER);	    	parm.add(lbl);
//		outputlbl[i] = new JLabel(" : 0 J ", JLabel.LEFT);   		outputlbl[i].setForeground(Color.white); parm.add(outputlbl[i++]);
//
//		lbl = new JLabel(" Potenial Energy ", JLabel.CENTER);	    	parm.add(lbl);
//		outputlbl[i] = new JLabel( " : 0 J ", JLabel.LEFT);   		outputlbl[i].setForeground(Color.white); parm.add(outputlbl[i++]);



		ioparm.add(parm);
		
		// drop down list
		
		parm = new JPanel();//new java.awt.GridLayout(4,3,0,0)); 
		//parm.setBackground(new Color(144,186,229));
		parm.setBackground(new Color(130,169,193));
		
		String[] itemStr = {"hydrogen","carbon","nitrogen","oxygen"};
		
		JComboBox combo = new JComboBox();
		for(int rec = 0 ; rec < itemStr.length ; rec++){
			combo.addItem(itemStr[rec]);
		}
//		 combo.addItem(itemStr[1]);
//		 combo.addItem(itemStr[2]);
//		 combo.addItem(itemStr[4]);
//		 combo.addItem(itemStr[5]);
//		 combo.addItem(itemStr[8]);
//		 combo.addItem(itemStr[9]);
//		 combo.addItem(itemStr[10]);
		combo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// Toggle
				String childName= (String)((JComboBox)evt.getSource()).getSelectedItem();
				System.out.println("cn: " +childName);
				int childNum = (Integer)hm.get(childName);
				System.out.println("N: "+ childNum);
				java.util.BitSet visibleNodes = new java.util.BitSet( switchGroup.numChildren() );
		        visibleNodes.set( childNum );
		        switchGroup.setChildMask( visibleNodes );
				//switchGroup.setWhichChild((Integer) hm.get(childName));								
			}
		});		
		
		parm.add(new ComboPanel("choose atom",combo));
		ioparm.add(parm);
		hm.put("inputOutputPanel",ioparm);
		//ioparm.setVisible(true);
		return  ioparm;

	}


	
	private void bottomPanel()
	{
		
		initInputControlsField();

		java.awt.GridBagConstraints gridBagConstraints;
		Color bk = new Color(219,226,238);
		Formatter = new DecimalFormat("#####.##");



		bottomPanel  = new JPanel(new java.awt.GridLayout(3,1,0,0));

		bottomPanel.setBackground(bk);
		//bottomPanel.setBorder(BorderFactory.createLineBorder(new Color(235,233,215),8));

		//first=new JPanel(new java.awt.GridLayout(4,3,0,0));
		//first.setBackground(bk);


		/*JLabel lbl = new JLabel("Left Ball Mass ", JLabel.RIGHT);


		//JSlider slider = new JSlider(JSlider.HORIZONTAL,  1, 10, 5);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				valChange = true;

				int val =((JSlider) e.getSource()).getValue();
				fields[0]=((double)val);

				leftBall.setMass(fields[0]);

				outputlbl[0].setText(" : " +  Formatter.format(fields[0])  + " kg"); 
				iLabel[0].setText(":: " + Formatter.format(fields[0])  + " kg");
				outlbl_val[1].setText(" : " +  Formatter.format(fields[0])  + " kg");

			}
		});
		// slider.setPreferredSize(new Dimension(150,20));
		slider.setBackground(bk);
		iLabel[0].setBackground(Color.YELLOW);
		first.add(lbl);
		first.add(slider);
		first.add(iLabel[0]);





		lbl = new JLabel("Left Ball Coeff of Restitution  ", JLabel.RIGHT);


		slider = new JSlider(JSlider.HORIZONTAL,  0, 10 , 5);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				valChange = true;
				int val =((JSlider) e.getSource()).getValue();
				fields[1]= ((float)val)/10.0;

				leftBall.setCoeffecient(fields[1]);

				outputlbl[2].setText(" : " + ((float)val)/10.0 ); 
				outputlbl[8].setText(" : " + "9.8" + " m/s^2"); 
				outputlbl[3].setText(" : " + "9.8" + " m/s^2"); 
				iLabel[1].setText(":: " + (float)val/10.0 ); 
				outlbl_val[3].setText(" : " + (float)val/10.0 );

			}
		});

		slider.setBackground(bk);
		iLabel[1].setBackground(Color.YELLOW);
		first.add(lbl);
		first.add(slider);
		first.add(iLabel[1]);




		lbl = new JLabel("Left Ball Height ", JLabel.RIGHT);

		slider = new JSlider(JSlider.HORIZONTAL, 1,10,10);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				valChange = true;
				int val =((JSlider) e.getSource()).getValue();
				fields[2]= (1/25.0f)*(val+4);

				leftBall.setHeight(fields[2] - 0.21,val);
				leftBall.update();

				outputlbl[1].setText(" : " + Formatter.format(val) + " m"); 
				iLabel[2].setText(":: " +Formatter.format(val) + " m"); 
				outlbl_val[0].setText(":: " +Formatter.format(val) + " m" );





			}
		});
		// slider.setPreferredSize(new Dimension(150,20));
		slider.setBackground(bk);
		iLabel[2].setBackground(Color.YELLOW);
		first.add(lbl);
		first.add(slider);
		first.add(iLabel[2]);*/


		

		//bottomPanel.add(first);

	JPanel blank=new JPanel(new java.awt.GridLayout(1,1,0,0));
		blank.setBackground(bk);
		JLabel lbl = new JLabel("", JLabel.RIGHT);
		bottomPanel.add(blank);
		
		JPanel  blank1=new JPanel(new java.awt.GridLayout(1,1,0,0));
		blank1.setBackground(bk);

		second=new JPanel(new java.awt.GridLayout(1,3,0,0));
		second.setBackground(bk);


		 lbl = new JLabel("Size ", JLabel.RIGHT);


		
		sliderSize.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(activeAtom != 0){
				valChange = true;

				int val =((JSlider) e.getSource()).getValue();
				fields[4]=((double)val);
               	atomarray[activeAtom].setSize((float)fields[4]/100);
				

				outputlbl[activeAtom - 1 ].setText(" : " +  Formatter.format(fields[4]/100)); 
				iLabel[4].setText(":: " + Formatter.format(fields[4]) );
		//		outlbl_val[5].setText(" : " +  Formatter.format(fields[4])  + " kg");
				}
				else {
					System.out.println("atom  0 called in size");
				}
			}
		});
		
		// slider.setPreferredSize(new Dimension(150,20));
		sliderSize.setBackground(bk);
		iLabel[0].setBackground(Color.YELLOW);
		second.add(lbl);
		second.add(sliderSize);
		second.add(iLabel[4]);
		
	  	hm.put("label1", lbl);
		hm.put("slider1",sliderSize);
		hm.put("ilabel1",iLabel[4]);
	
		
		 third = new JPanel(new java.awt.GridLayout(1,3,0,0));
		third.setBackground(bk);
		lbl = new JLabel("Mass ", JLabel.RIGHT);

		
		sliderMass.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (activeAtom != 0) {
					valChange = true;
					int val = ((JSlider) e.getSource()).getValue();
					fields[6] = (double) val;

					// atomarray[1].setSize(val);
					atomarray[activeAtom].setMass((float) fields[6] / 100);
					// rightBall.update();

					outputlbl2[activeAtom - 1].setText(" : "
							+ Formatter.format(fields[6] / 100));
					iLabel[6].setText(":: "
							+ Formatter.format((float) fields[6]) + " amu");
					// outlbl_val[4].setText(":: " +Formatter.format(val) + " m"
					// );
				}else {
					System.out.println("atom  0 called in mass");
				}
			}
		});
		// slider.setPreferredSize(new Dimension(150,20));
		sliderMass.setBackground(bk);
		iLabel[2].setBackground(Color.YELLOW);
		third.add(lbl);
		third.add(sliderMass);
		third.add(iLabel[6]);
		
	  	hm.put("label2", lbl);
		hm.put("slider2",sliderMass);
		hm.put("ilabel2",iLabel[6]);
		
		// k Slider starts here
		fourth = new JPanel(new java.awt.GridLayout(1,3,0,0));
		fourth.setBackground(bk);
		lbl = new JLabel("KValue ", JLabel.RIGHT);

		
		sliderKvalue.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				System.out.println("in slider k value ");
				valChange = true;
				int val =(int)((JSlider) e.getSource()).getValue();
//				fields[6]= (double)val;

//				atomarray[1].setSize(val);
				kArray[activeK] = val;
				//rightBall.update();

				kButton[activeK].setText(" : " + Formatter.format((float)1.0*val/100)); 
				iLabel[8].setText(":: " +Formatter.format(val) + " amu"); 
		//		outlbl_val[4].setText(":: " +Formatter.format(val) + " m" );
			}
		});
		// slider.setPreferredSize(new Dimension(150,20));
		sliderKvalue.setBackground(bk);
		iLabel[8].setBackground(Color.YELLOW);
		fourth.add(lbl);
		fourth.add(sliderKvalue);
		fourth.add(iLabel[8]);
		
	  	hm.put("label3", lbl);
		hm.put("slider3",sliderKvalue);
		hm.put("ilabel3",iLabel[8]);
		// k slider ends here
		
		//k slide starts here	
		fifth = new JPanel(new java.awt.GridLayout(1,3,0,0));
		fifth.setBackground(bk);
		lbl = new JLabel("dist ", JLabel.RIGHT);

		
		sliderDist.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int val = (int) ((JSlider) e.getSource()).getValue();
				if (timerStart == 0) {
					valChange = true;
					
//					if (lastValDist > val) {
//						// hardeep = 1;
//						hardeep += 0.02;
//
//					} else {
//						// hardeep = 1;
//						hardeep -= 0.02;
//					}
//					lastValDist = val;
					hardeep = 1 + 1.0f * val * .02f;
					System.out.println("in slider : hardeep : " + hardeep);

					TransformGroup getTransGp = (TransformGroup) hm
							.get("spring");
					TransformGroup getTransGpSp1 = (TransformGroup) hm
							.get("molecule1");
					TransformGroup getTransGpSp2 = (TransformGroup) hm
							.get("molecule2");

					Transform3D gettrans3d = new Transform3D();
					Transform3D gettrans3dsp1 = new Transform3D();
					Transform3D gettrans3dsp2 = new Transform3D();

					getTransGp.getTransform(gettrans3d);
					// gettrans3d.setTranslation(new
					// Vector3d(hardeep/100,0f,0f));
					gettrans3d.setScale(new Vector3d(hardeep, 1f, 1f));
					// gettrans3d.setTranslation(new Vector3d(-.15f
					// +hardeep/10,.0f,.0f));
					if (hardeep > 3) {
						gettrans3d.setScale(new Vector3d(0f, 0f, 0f));
					}
					// //getTransGp.setCollisionBounds(new Bounds(new
					// Point3d(-.0275f,.0f,.0f)));
					// getTransGp.setCapability(Node.ALLOW_AUTO_COMPUTE_BOUNDS_READ);
					// getTransGp.setCapability(Node.ALLOW_AUTO_COMPUTE_BOUNDS_WRITE);
					getTransGp.setTransform(gettrans3d);

					// getTransGpSp1.getTransform(gettrans3dsp1);
					// gettrans3dsp1.setTranslation(new
					// Vector3d(-hardeep/10,0f,0f));
					// getTransGpSp1.setTransform(gettrans3dsp1);

					getTransGpSp2.getTransform(gettrans3dsp2);
					gettrans3dsp2.setTranslation(new Vector3d(-.30 + hardeep
							* .30, 0f, 0f));
					getTransGpSp2.setTransform(gettrans3dsp2);
					if (hardeep < 3.8) {
						forceGraph.setCurrentValue((float) (hardeep -1),
								(float) (1.0*kArray[activeK]/100 * (hardeep -1) ));
						forceGraph
								.addGraphValue((float) (1.0*kArray[activeK]/100 * (hardeep -1 )));// -freeBody.getDisplacment()*500));
						forceGraph.drawGraph();
						
						energyGraph.setCurrentValue((float) (hardeep -1),
								(float) (1.0 / 2 * 1.0*kArray[activeK]/100 * (hardeep -1) * (hardeep -1)));
						energyGraph
								.addGraphValue((float) (1.0 / 2 * 1.0*kArray[activeK]/100 * (hardeep-1) * (hardeep -1)));// -freeBody.getDisplacment()*500));
						energyGraph.drawGraph();
						System.out.println("hardeep: " + hardeep);
					}

					// fields[6]= (double)val;

					// atomarray[1].setSize(val);
					// kArray[activeK] = val;
					// rightBall.update();

					// kButton[activeK].setText(" : " +
					// Formatter.format((float)1.0*val/100));
					
					// outlbl_val[4].setText(":: " +Formatter.format(val) + " m"
					// );
				}
				iLabel[10].setText(":: " + Formatter.format(val) + " m");
			}
		});
		// slider.setPreferredSize(new Dimension(150,20));
		sliderDist.setBackground(bk);
		iLabel[10].setBackground(Color.YELLOW);
		fifth.add(lbl);
		fifth.add(sliderDist);
		fifth.add(iLabel[10]);
		
	  	hm.put("label4", lbl);
		hm.put("slider4",sliderDist);
		hm.put("ilabel4",iLabel[10]);
		// k slider ends here
		
		blank1.add(second);
		blank1.add(third);
		blank1.add(fourth);
		blank1.add(fifth);
		bottomPanel.add(blank1);
		





		/*lbl = new JLabel("Right Ball Coeff. of Restitution", JLabel.RIGHT);


		slider = new JSlider(JSlider.HORIZONTAL,  0, 10 , 5);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				valChange = true;
				int val =((JSlider) e.getSource()).getValue();
				fields[5]= ((float)val)/10.0;

				rightBall.setCoeffecient(fields[5]);

				outputlbl[8].setText(" : " + ((float)val)/10.0 ); 
				iLabel[5].setText(":: " +((float)val)/10.0 ); 
				outlbl_val[7].setText(" : " + (float)val/10.0 );






			}
		});

		slider.setBackground(bk);
		iLabel[1].setBackground(Color.YELLOW);
		second.add(lbl);
		second.add(slider);
		second.add(iLabel[5]);*/
		 
		first=new JPanel(new java.awt.GridLayout(1,3,0,0));
	        first.setBackground(bk);
	       
	        // Added by Me till commenting comes
	        
	 //       JLabel lab1 = new JLabel("", JLabel.CENTER);
	   //     first.add(lab1);
	        
	        
	      
	        JLabel lab = new JLabel("Material", JLabel.RIGHT);
	       first.add(lab);
	        
	               
	         radio = new JPanel(new java.awt.GridLayout(1,6,0,0));
	        radio.setBackground(bk);
	        
	        button1 = new JPanel(new java.awt.GridLayout(1,2,0,0));
	        button1.setBackground(bk);
	        JRadioButton Rubber, Wood, Steel;
	        ButtonGroup buttonGroup = new ButtonGroup();
	        Rubber = new JRadioButton("Rubber");
	        buttonGroup.add(Rubber);
	        Rubber.setBackground(bk);
	        button1.add(Rubber);
	        radio.add(button1);
	      
	       
	        
	        /*JPanel hello = new JPanel(new java.awt.GridLayout(1,1,0,0));
	      hello.setBackground(bk);
	       ImageIcon fbd=new ImageIcon();
	        fbd=m_j3d.createImageIcon("resources/images/football.jpg");
	        // ((Object) fbd).setHeight(25);
	       Image wow = fbd.getImage();
	     Image newwow = wow.getScaledInstance(20,20, 1);
	        fbd.setImage(newwow);
	       JLabel lbl1=new JLabel();
	        lbl1.setIcon(fbd);
	        hello.add(lbl1,JLabel.CENTER);
	        hello.setVisible(true);
	        radio.add(hello);*/
	   
	        button2 = new JPanel(new java.awt.GridLayout(1,2,0,0));
	        button2.setBackground(bk);
	        Wood = new JRadioButton("Wood"); 
	        Wood.setBackground(bk);
	        buttonGroup.add(Wood);
	     button2.add(Wood);
	        radio.add(button2);
	        
	        hm.put("wood", Wood);   
	        /*hello = new JPanel(new java.awt.GridLayout(1,1,0,0));
		      hello.setBackground(bk);
		     fbd=new ImageIcon();
		        fbd=m_j3d.createImageIcon("resources/images/wood.jpg");
		        // ((Object) fbd).setHeight(25);
		       wow = fbd.getImage();
		        newwow = wow.getScaledInstance(20,20, 1);
		        fbd.setImage(newwow);
		        lbl1=new JLabel();
		        lbl1.setIcon(fbd);
		        hello.add(lbl1,JLabel.CENTER);
		        hello.setVisible(true);
		        radio.add(hello);*/
	        
		     button3 = new JPanel(new java.awt.GridLayout(1,2,0,0));
		    button3.setBackground(bk);
	        Steel = new JRadioButton("Steel");
	        Steel.setBackground(bk);
	        buttonGroup.add(Steel);
	        button3.add(Steel);
	        Wood.setSelected(true);
	        radio.add(button3);
	       
	      
	       /*---- image of stell ---*/ 
	        /*hello = new JPanel(new java.awt.GridLayout(1,1,0,0));
		      hello.setBackground(bk);
		     fbd=new ImageIcon();
		        fbd=m_j3d.createImageIcon("resources/images/steel.jpg");
		        // ((Object) fbd).setHeight(25);
		         wow = fbd.getImage();
		         newwow = wow.getScaledInstance(20,20, 1);
		        fbd.setImage(newwow);
		        lbl1=new JLabel();
		        lbl1.setIcon(fbd);
		        hello.add(lbl1,JLabel.CENTER);
		        hello.setVisible(true);
		        radio.add(hello);*/
		        first.add(radio);
		       
	        Rubber.addActionListener(new java.awt.event.ActionListener() {
	               public void actionPerformed(ActionEvent e) {
	                   JRadioButton s = (JRadioButton)e.getSource();
	                   System.out.println(s.getText());
	                   
                			                		
	               //    rightBall.setCoeffecient("Rubber");
	               	//	outlbl_val[7].setText(" : " +  "Rubber"  + "");
	               		outputlbl[7].setText(" : " + "Rubber" );
	               		
	               		
	                         repaint();
	               }
	             });
	       
	    
	        Wood.addActionListener(new java.awt.event.ActionListener() {
	              public void actionPerformed(ActionEvent e) {
	                  JRadioButton s = (JRadioButton)e.getSource();
	                 System.out.println(s.getText());
	                 		
	                 	//	rightBall.setCoeffecient("Wood");
	                 	//	outlbl_val[7].setText(" : " +  "Wood"  + "");
	                 		outputlbl[7].setText(" : " + "Wood" ); 
	                 		
	                 repaint();
	              }
	             });
	        Steel.addActionListener(new java.awt.event.ActionListener() {
	              public void actionPerformed(ActionEvent e) {
	                  JRadioButton s = (JRadioButton)e.getSource();
	                 System.out.println(s.getText());
	                
	                 //   rightBall.setCoeffecient("Steel");
	                //	outlbl_val[7].setText(" : " +  "Steel"  + "");
	                	outputlbl[7].setText(" : " + "Steel" );
	                	
	                 repaint();
	              }
	             });
	        //blank1.add(first);
	        bottomPanel.add(blank1);
	    	JPanel blank3=new JPanel(new java.awt.GridLayout(1,1,0,0));
			blank3.setBackground(bk);
			lbl = new JLabel("", JLabel.RIGHT);
			bottomPanel.add(blank3);
	        
	        
	        
	        
	        bottomPanel.setVisible(true);
		
		
		hm.put("BottomPanel",bottomPanel);
		enableStage(0);
		trial[0]++;
		
		// clip.play();
		 current_audio_count++;
		 
		 

	}

	private void initInputControlsField() {


		iLabel = new JLabel[15];
		int i=0;
		iLabel[i] = new JLabel("5 kg", JLabel.LEFT); iLabel[i++].setForeground(Color.blue);
		iLabel[i] = new JLabel("0.9 ", JLabel.LEFT); iLabel[i++].setForeground(Color.blue);
		iLabel[i] = new JLabel("10 m", JLabel.LEFT); iLabel[i++].setForeground(Color.blue);
		iLabel[i] = new JLabel("45(deg)", JLabel.LEFT); iLabel[i++].setForeground(Color.blue);
		iLabel[i] = new JLabel("5", JLabel.LEFT); iLabel[i++].setForeground(Color.blue);
		iLabel[i] = new JLabel("0.5", JLabel.LEFT); iLabel[i++].setForeground(Color.blue);
		iLabel[i] = new JLabel("5", JLabel.LEFT); iLabel[i++].setForeground(Color.blue);
		iLabel[i] = new JLabel("45(deg)", JLabel.LEFT); iLabel[i++].setForeground(Color.blue);
		iLabel[i] = new JLabel("5",JLabel.LEFT); iLabel[i++].setForeground(Color.blue);
		iLabel[i] = new JLabel("5",JLabel.LEFT); iLabel[i++].setForeground(Color.blue);
		iLabel[i] = new JLabel("1",JLabel.LEFT); iLabel[i++].setForeground(Color.blue);

		fields =new double[9];
		fields[0]= 5;
		fields[1]= 0.9;
		fields[2]= 10;
		fields[3]=45;



		fields[4]= 5;
		fields[5]= 0.5;
		fields[6]= 10;
		fields[7]=45;

}

	private  void enable(Container root, boolean enable) {
		Component children[] = root.getComponents();
		for(int i = 0; i < children.length; i++) 
			children[i].setEnabled(enable);
	}



	private void onNextStage()
	{

		valChange = true; // Clear the graph. or Graph will restart on Play 
		if( stage < 4)
		{
        trial[stage] ++;
		}
		forceGraph.clearGraphValue();
		energyGraph.clearGraphValue();
		
	//	resetOutputParameters(); // Clear the Output Parameters
		if(stage > 2)
		{
			
			enableStage(-2);
		}
		else
		{
			enableStage(stage);
		}
		replayButton.setEnabled(false);
		bottomPanel.setVisible(true);
		
	/*	JPanel  ioparm=(JPanel)hm.get("inputOutputPanel");
		ioparm.setVisible(true);*/

	//	JPanel  ioparm1=(JPanel)hm.get("ButtonPanel");
		//ioparm1.setVisible(false);

		//setInstructionText();

	}

	private void enableStage(int s){
		switch(s){
		case -2: enable(first ,true);  enable(second ,true); enable(third,true);enable(fourth,false); enable(fifth,false);enable(ManualButton,true);enable(radio,true); enable(button1,true); enable(button2,true); enable(button3,true); break;	
		case -1:   enable(first ,false);  enable(second ,false); enable(third,false);enable(fourth,false); enable(fifth,false);enable(ManualButton,false);enable(radio ,false); enable(button1,false); enable(button2,false); enable(button3,false); break;			
		case -3:   		enable(first ,true);  enable(second ,true);enable(third ,true);enable(fourth,false);enable(fifth,false);enable(ManualButton,true);  break;
		case 0:    	enable(first,false);	enable(second,true);enable(third,true) ;enable(fourth,false);enable(fifth,false);enable(ManualButton,false); enable(radio,false); enable(button1,false); enable(button2,false); enable(button3,false); break;


		case 1:
			enable(first,false);	enable(second,false);enable(third,false) ;enable(fourth,true);enable(fifth,false);enable(ManualButton,true); enable(radio,false); enable(button1,false); enable(button2,false); enable(button3,false); break;
		case 2: 	enable(first,true);	enable(second,false);enable(third,false) ; enable(fourth,false);enable(fifth,false);enable(ManualButton,true);enable(radio,true); enable(button1,true); enable(button2,true); enable(button3,true); break;
		case 3: 	enable(second,false);enable(third,false) ; enable(fourth,true); enable(fifth,false);break;
		case 4: 	enable(second,true);enable(third,true) ; enable(fourth,false);enable(fifth,false); break;
		case 5: 	enable(second,false);enable(third,false) ; enable(fourth,false);enable(fifth,true); break;
		
		
		}

	}

	public void setInstructionText(){
		valChange = true;

		switch(stage){
		case 1: // Home //textGeom.setString("What is Periodic Motion? ");
			m_Objective.setText(">: Does change in mass effect the mime period?");
			m_Objective.setForeground(Color.GREEN);
			break;
		case 2:
			m_Objective.setText(">: Can you change time period by changing initial displacment?");
			m_Objective.setForeground(Color.WHITE);
			break;
		case 3:    		
			m_Objective.setText(">: How length of pendulum and time period are related?");
			m_Objective.setForeground(Color.GREEN);
			break;
		case 4:
			m_Objective.setText(">: Change different parameter and observe the effect in time period");
			m_Objective.setForeground(Color.WHITE);

			break;
		case 5: // Home 

			stage =0;
			break;
		}

	}

	private void resetOutputParameters()
	{
		int i=2;
		outlbl_val[i++].setText("t sec");
		outlbl_val[i++].setText("@ deg");
		outlbl_val[i++].setText("ke J");
		outlbl_val[i++].setText( " pe J");
	}
	private void resetSimulation(java.awt.event.ActionEvent evt)
	{
		timer.stop();
		timerStart = 0;
	
//		ImageIcon icon = m_j3d.createImageIcon("resources/icons/stop.png"); 
//		startButton.setIcon(icon);
//		startButton.setText("Stop");
		enableStage(-2);    	
		replayButton.setEnabled(true);
		ManualButton.setEnabled(true);
		forceGraph.clearGraphValue();
		energyGraph.clearGraphValue();
		forceGraph.setState(0);
		energyGraph.setState(0);
		



		if(valChange){



			forceGraph.clearGraphValue();
			energyGraph.clearGraphValue();
		}

		//timer.start();
		System.out.println("reset func");


	}

	private void startSimulation(java.awt.event.ActionEvent evt)
	{
	
		ImageIcon icon = m_j3d.createImageIcon("resources/icons/stop.png"); 
		startButton.setIcon(icon);
		startButton.setText("Stop");
		enableStage(-1);    	
		replayButton.setEnabled(false);
		ManualButton.setEnabled(false);
		forceGraph.setState(0);
		energyGraph.setState(0);



		/*if(valChange){



			forceGraph.clearGraphValue();
			energyGraph.clearGraphValue();
		}*/

		timer.start();
		timerStart = 1;
		System.out.println("Timer started");


	}



	private void timerActionPerformed(java.awt.event.ActionEvent evt)
	{
		
		hardeep +=0.02;
		
		TransformGroup getTransGp=(TransformGroup)hm.get("spring"); 
		TransformGroup getTransGpSp1 = (TransformGroup)hm.get("molecule1");
		TransformGroup getTransGpSp2 = (TransformGroup)hm.get("molecule2");

		Transform3D gettrans3d = new Transform3D();
		Transform3D gettrans3dsp1 = new Transform3D();
		Transform3D gettrans3dsp2 = new Transform3D();

		getTransGp.getTransform(gettrans3d);	
		//gettrans3d.setTranslation(new Vector3d(hardeep/100,0f,0f));
		gettrans3d.setScale(new Vector3d(hardeep,1f,1f));
//		gettrans3d.setTranslation(new Vector3d(-.15f +hardeep/10,.0f,.0f));
		if(hardeep > 3){
			gettrans3d.setScale(new Vector3d(0f,0f,0f));			
		}
//		//getTransGp.setCollisionBounds(new Bounds(new Point3d(-.0275f,.0f,.0f)));
//		getTransGp.setCapability(Node.ALLOW_AUTO_COMPUTE_BOUNDS_READ);
//		getTransGp.setCapability(Node.ALLOW_AUTO_COMPUTE_BOUNDS_WRITE);
		getTransGp.setTransform(gettrans3d);
		
//		getTransGpSp1.getTransform(gettrans3dsp1);
//		gettrans3dsp1.setTranslation(new Vector3d(-hardeep/10,0f,0f));
//		getTransGpSp1.setTransform(gettrans3dsp1);

		getTransGpSp2.getTransform(gettrans3dsp2);
		gettrans3dsp2
				.setTranslation(new Vector3d(-.30 + hardeep * .30, 0f, 0f));
		// gettrans3dsp2.setTranslation(new Vector3d( -.15 +
		// hardeep*.28,0f,0f));
		getTransGpSp2.setTransform(gettrans3dsp2);
		// energyGraph.setState(1);
		if (hardeep < 3.8) {
			forceGraph.setCurrentValue((float) (hardeep ),
					(float) (1.0*kArray[activeK]/100 * (hardeep ) ));
			forceGraph
					.addGraphValue((float) (1.0*kArray[activeK]/100 * (hardeep -1 )));// -freeBody.getDisplacment()*500));
			forceGraph.drawGraph();

			
			energyGraph.setCurrentValue((float) (hardeep ),
					(float) (1.0 / 2 * 1.0*kArray[activeK]/100 * (hardeep ) * (hardeep )));
			energyGraph
					.addGraphValue((float) (1.0 / 2 * 1.0*kArray[activeK]/100 * (hardeep -1 ) * (hardeep -1 )));// -freeBody.getDisplacment()*500));
			energyGraph.drawGraph();
			System.out.println("hardeep: " + hardeep + "kvalue : " + kArray[activeK]);
		}
		// here
		float v = (float) ((hardeep - 1) / .02f);
		iLabel[10].setText(":: " +Formatter.format(v) + " m"); 
		JSlider js = (JSlider)hm.get("slider4");
		js.setValue((int)v);
		//
		
		return;            
	}

	private void pauseSimulation() {
		// int check ;
		timer.stop();
		timerStart = 0;

		// clip.stop();

		if (current_audio_count <= 9) {
			String AudioPath = "resources/video_audio/Bouncing Ball/Instr"
					+ current_audio_count + ".wav";
			URL url = Resources.getResource(AudioPath);
			clip = Applet.newAudioClip(url);
			// clip.play();
			current_audio_count++;
		}

		// check = stage;
		if (stage > 2) {
			if (stage == 3 && trial[stage - 1] == 3) {

				// JSlider slider = (JSlider)hm.get("slider1");
				// slider.setValue(5);
				// outputlbl[5].setText(" : " + "5" + " kg");
				// iLabel[4].setText(":: " + "5" + " kg");
				// slider = (JSlider)hm.get("slider2");
				// slider.setValue(10);
				// outputlbl[6].setText(" : " + "10" + " m");
				// iLabel[6].setText(":: " + "10" + " m");
				// JRadioButton wood = (JRadioButton)hm.get("wood");
				// wood.setSelected(true);
				// rightBall.setHeight(0.35, 10);
				// rightBall.setMass(5);
				// rightBall.setCoeffecient("Wood");
				// leftBall.setHeight(0.35, 10);

				valChange = false;

				//forceGraph.clearGraphValue();
				//energyGraph.clearGraphValue();

				// JOptionPane.showMessageDialog(simulationPanel,"Now its time to try them all at a time");
				trial[stage - 1] = 4;
			}
			enableStage(-2);
			stage = 3;
			trial[stage]++;
			// nextButton.setEnabled(false);
		} else {
			if (stage > 0 && trial[stage - 1] == 3) {
				if (stage == 1) {
					JSlider slider = (JSlider) hm.get("slider1");
					slider.setValue(5);
					// rightBall.setMass(5);
					// outputlbl[5].setText(" : " + "5" + " kg");
					// iLabel[4].setText(":: " + "5" + " kg");
					// leftBall.setHeight(0.35, 10);
					// rightBall.setHeight(0.35, 10);

				}

				if (stage == 2) {
					JSlider slider = (JSlider) hm.get("slider2");
					slider.setValue(10);
					// rightBall.setHeight(0.35, 10);
					// outputlbl[6].setText(" : " + "10" + " m");
					// iLabel[6].setText(":: " + "10" + " m");
					// leftBall.setHeight(0.35, 10);
				}

				// JOptionPane.showMessageDialog(simulationPanel,"Try next Parameter");
				valChange = false;

				//forceGraph.clearGraphValue();
				//energyGraph.clearGraphValue();

				trial[stage - 1] = 4;
			}
			enableStage(4);
			// nextButton.setEnabled(true);
		}
		ManualButton.setEnabled(true);
		replayButton.setEnabled(true);
		ImageIcon icon = m_j3d.createImageIcon("resources/icons/start.png");
		startButton.setIcon(icon);
		startButton.setText("Start");
		if (stage < 3) {
			trial[stage]++;
		}
		if (stage == 3 && trial[stage] > 1) {
			replayButton.setEnabled(true);
		}
		if (stage < 3 && trial[stage] >= 3) {
			check[stage] = 1;
			stage++;

		}

		rightPanel.setVisible(true);
		forceGraph.setState(0);
		energyGraph.setState(0);

		univ.getCanvas().repaint();

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		System.out.println("mouse pressed at : " + arg0.getLocationOnScreen().x+" : " + arg0.getPoint().getX());
		TransformGroup movebeed = (TransformGroup)hm.get("molecule2");
		//if(movebeed.getBounds().intersect(arg0))
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

		ViewingPlatform vp = univ.getViewingPlatform();
		TransformGroup steerTG = vp.getViewPlatformTransform();
		Transform3D t3d = new Transform3D();
		steerTG.getTransform(t3d);
		//Vector3d s = new Vector3d();
		Vector3f currPos=new Vector3f();
		t3d.get(currPos); 

		int notches = e.getWheelRotation();
		double movement =0.005;
		if (notches < 0)
		{
			movement=movement*-1;


		}
		zval=zval+movement;


		t3d.lookAt( new Point3d(0,0,zval), new Point3d(0,0,0), new Vector3d(0,1,0));
		t3d.invert();

		steerTG.setTransform(t3d); 


	}

	
//	class BallMotion {
//
//		private String hashName;
//		private TransformGroup transGroup;
//		private Vector3d position;
//		private double mass;
//		private double height;
//		private double coeffecientRestitution;
//		private double gravity = 9.8;
//		private double elapsedTime =0;
//		private double kineticEnergy = 0;
//		private double potentialEnergy = 0;
//		private double obtainableHeight =0 ;
//		private double constant;
//		private double realConstant ;
//		private int state=0;
//		int counter =0;
//		private double velocity;
//		int val =0 ;
//		double actualVelocity ;
//		double initVelocity =0;
//		int sliderHeight = 0;
//		
//		//constructor
//		public BallMotion(String name,Vector3d pos,double  ballMass, double ballHeight , double ballCoeffecient,double ballGravity)
//		{
//			velocity = 0;
//			gravity = ballGravity;
//			height = ballHeight;
//			position = pos;
//			hashName = name;
//			constant = position.getY() + 0.23   ;
//			realConstant = height;
//			coeffecientRestitution = ballCoeffecient;
//			mass = ballMass;
//			previousMass = 5;
//			previousHeight = 10;
//			sliderHeight = 10;
//			state = 0;
//		}
//		
//		/*------- setting the Height -----*/
//		public void setHeight(double x,double actual)
//		{
//			state = 0;
//			velocity = 0;
//			kinetic();
//			height = actual;
//			potential();
//			realConstant = actual;
//			constant = x + 0.23;
//			position.setY(x);
//			sliderHeight = (int)actual;
//		}
//		
//		/*----- setting the coeffecient of restitution of the ball ---- */
//		public void setCoeffecient(String materialBall)
//		{
//			  java.util.BitSet visibleNodes = new java.util.BitSet( switchGroup.numChildren() );
//              int i=0;
//              if(materialBall.equals("Rubber"))
//              {
//                      i=0;
//                      coeffecientRestitution = 0.9;
//                      previousMaterial  = presentMaterial;
//	               		presentMaterial = 1;
//              }
//              if(materialBall.equals("Wood"))
//              {        
//                      i=1;
//                      coeffecientRestitution = 0.4;
//                      previousMaterial  = presentMaterial;
//	               		presentMaterial = 0;
//              }
//              if(materialBall.equals("Steel"))
//              {
//                      i=2;
//                      coeffecientRestitution = 0.2;
//                      previousMaterial  = presentMaterial;
//                      presentMaterial = 2;
//                      
//              }
//
//              visibleNodes.set( i);
//              switchGroup.setChildMask( visibleNodes );
//	
//		}
//		
//		/*------  setting the mass as chosen by the user ----*/
//		public void setMass(double x)
//		{
//			mass = x;
//			x = x + 5;
//			
//			TransformGroup getTransGp=(TransformGroup)hm.get(hashName); 
//			Transform3D gettrans3d = new Transform3D();
//			getTransGp.getTransform(gettrans3d);
//			gettrans3d.setScale(new Vector3d(x/10 ,x/10, 0));
//			getTransGp.setTransform(gettrans3d);
//		}
//		/*----- function returning the mass of the ball ---*/
//		
//		public double getMass()
//		{
//			return mass;
//		}
//		
//		/*----- function returning the Height attained ny the Ball ----*/
//		public double getHeight()
//		{
//			return height;
//		}
//		/*----- function returning the sliderHeight attained ny the Ball ----*/
//		public int getSliderHeight()
//		{
//			return sliderHeight;
//		}
//		
//		/* ----- function returning the time ------*/
//		public double getTime()
//		{
//			return elapsedTime;
//		}
//		
//		/*----   function returning the coeffecient of restitution ------*/
//		public double getCoeffecient()
//		{
//			return coeffecientRestitution;
//		}
//		
//		/* -------   function returning the velocity -----*/
//		public double getVelocity()
//		{
//			return velocity;
//		}
//		
//		/*--------- function returning the kinetic energy of the Ball ---------*/
//		public double getKinetic()
//		{
//              if(state == 2)
//              {
//            	  kineticEnergy =0;
//              }
//				return kineticEnergy;
//		}
//		/* --- function returning the potential energy of the ball ----*/
//		public double getPotential()
//		{
//			 if(state == 2)
//             {
//           	  kineticEnergy =0;
//             }
//			return potentialEnergy;
//		}
//		
//		/*----  To rebound the ball on hitting the floor ( prevent going further down) -------*/
//		public int CheckCollision(){
//			int value = 1;
//			if( position.getY()  <= -0.2)
//			{
//				value = -1;
//
//			}
//			return value;
//
//		}
//		
//		/*----- To check Whether Ball has attained the desirable height at the time of its upward motion ------*/
//
//		public int CheckMaximum()
//		{
//			int value = 0;
//			if(obtainableHeight <= position.getY() )
//			{
//				value = -1;
//
//			}
//			return value;
//
//		}
//		
//		
//		/* ------   as name says  ( here position and height(wrt slider) both are updated ) ------ */
//		public void updatePosition()
//		{
//			if( state == 0)
//			{
//				int check = CheckCollision();
//
//				if ( check == -1)
//				{
//				
//					val++;
//					position.setY(-0.21);
//					height = 0;
//					velocity = Math.sqrt(2 * gravity * constant);
//					velocity = velocity * coeffecientRestitution ;
//					obtainableHeight = (velocity * velocity) / ( 2* gravity);
//					constant = obtainableHeight ;
//					obtainableHeight = obtainableHeight - 0.21;
//					actualVelocity = Math.sqrt(2 * gravity * realConstant);
//					actualVelocity = actualVelocity * coeffecientRestitution;
//					realConstant = (actualVelocity * actualVelocity) / ( 2 * gravity);
//					velocity = actualVelocity;
//					
//					
//					state = 1;
//					System.out.println(hashName + " " + obtainableHeight);
//					if( obtainableHeight <= -0.209)
//						state =2;
//					
//					return;
//				}
//				position.setY(position.getY() - 0.005);
//				height = height - 0.083;
//				initVelocity = velocity;
//				updateVelocity(0);
//				updateTime(initVelocity,velocity);
//			}
//			else if(state == 1)
//			{
//				int  check = CheckMaximum();
//				if( check == -1)
//				{
//					position.setY(obtainableHeight);
//					velocity = 0;
//					state = 0;
//					return;
//				}
//				
//				position.setY(position.getY() + 0.005 );
//				height = height + 0.083;
//				initVelocity = velocity;
//				updateVelocity(1);
//				updateTime(initVelocity,velocity);
//				//System.out.println(velocity);
//			}
//
//
//		}
//		
//        /*-------  To update the time  -----*/
//		public void updateTime(double u,double v)
//		{
//			elapsedTime = elapsedTime + Math.abs((u - v)/gravity);
//
//		}
//		/*------   Setting the time elapsed ------*/
//		public void setTime(double x)
//		{
//			elapsedTime = x;
//		}
//		
//		/*-------  To update the velocity with progress of time  ------*/
//		public void updateVelocity(int x)
//		{
//			if( x == 1)
//			{
//				velocity = Math.sqrt(Math.abs((velocity * velocity) -(0.083 * gravity * 2)));
//			}
//			
//			if(x == 0)
//			{
//				velocity = Math.sqrt(Math.abs((velocity * velocity) +(0.083* gravity * 2)));
//			}
//
//
//		}
//		
//		/* ------- Finally update the Ball position with the updated height , updated time ------- */
//
//		public void update()
//		{
//			counter++;
//			TransformGroup getTransGp=(TransformGroup)hm.get(hashName); 
//
//			Transform3D gettrans3d = new Transform3D();
//
//			getTransGp.getTransform(gettrans3d);
//			if( hashName == "ball1")
//			{
//				position.setX(-0.3);
//
//			}
//			if( hashName == "ball2")
//			{
//				position.setX(0.3);
//			}
//
//			position.setZ(0);
//		
//			if( counter% 2 == 0)
//			{
//				kinetic();
//				potential();
//			}
//			if(state == 2)
//			{
//				position.setY(- 0.21);
//			}
//			gettrans3d.setTranslation(position);
//			getTransGp.setTransform(gettrans3d);
//		}
//		
//		/* ----- Calculate the Kinetic Energy -------  */
//		public void kinetic()
//		{
//			kineticEnergy = 0.5 * velocity * mass * velocity;
//		}
//		
//		/*----- Calculate the Potential Energy at height h ----*/
//		public void potential()
//		{
//			if(height < 0)
//				height = 0;
//			
//			potentialEnergy = mass * gravity * height;
//		}
//	}







}///////////////// Defination COmplete






