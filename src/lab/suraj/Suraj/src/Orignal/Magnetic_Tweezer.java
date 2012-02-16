 package Orignal;

/*
 * $RCSfile: FreeVibration.java,v $
 *
 * Copyright (c) 2009 cITe, Inc. All rights reserved. *
 * $Revision: 1.3 $
 * $Date: 2009/07/21 $
 * $State: Exp $
 */





import iiit.vlabdemos.common.FullViewGraph;
import iiit.vlabdemos.common.HorizontalGraph;
import iiit.vlabdemos.common.J3DShape;
import iiit.vlabdemos.common.MyButton;
import iiit.vlabdemos.common.PanelWindowWrapper;
import iiit.vlabdemos.common.Resources;
import iiit.vlabdemos.common.StopWatch;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.Cylinder;

import javax.vecmath.AxisAngle4d;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.media.j3d.Group;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Switch;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;


/**
 * Simple Java 3D program that can be run as an application or as an applet.
 */
public class Magnetic_Tweezer extends javax.swing.JPanel  implements MouseListener, MouseMotionListener,MouseWheelListener{
	//  Variables declaration - do not modify//GEN-BEGIN:variables
	//////////////////////////GUI componenet ///////////////////////////
	private javax.swing.JPanel topPanel;
	private javax.swing.JPanel simulationPanel;
	private javax.swing.JPanel bottomPanel;
	private javax.swing.JPanel rightPanel;
	
	private javax.swing.JButton startButton=null;
	private javax.swing.JButton reStartButton=null;
	private javax.swing.JButton nextButton=null;
	private javax.swing.JButton InstructionBtn=null;
	//private javax.swing.JButton Cool=null;

   
	

	//private GraphPlotter         graphPlotter;
	////////////////////////////Java3D componenet ///////////////////////////

	private SimpleUniverse      univ = null;                  // Simple Universe Java3D
	private BranchGroup         scene = null;                 // BranchGroup Scene graph
	private Switch 				switchGroup=null;			 //
	

	// all buttons
	JButton diatomic;
	JButton triatomic;
	JButton quadatomic;
	JButton Start_experiment;
	
	//all sliders
	JSlider slider_x;
	JSlider slider_y;
	JSlider Viscosity;
	JSlider atom_first;
	JSlider atom_second;
	JSlider atom_third;
	JSlider atom_fourth;
	JSlider value_12;
	JSlider value_23;
	JSlider value_34;
	JSlider distance_12;
	JSlider distance_23;
	JSlider distance_34;
//	JSlider center1;
	JSlider center2;
	JSlider center3;
	JSlider center4;
	
	

	int Total_cylinder=0;
	//other
	double net_translation=0;
	double topmost_atom_coordinates[]=new double[3];
	double lowest_atom_coordinates[]=new double[3];
	int topmost_atom=0;
	int lowest_atom=0;
	double radius[]=new double[5];
	double value[]=new double[5];
	double distance[]=new double[5];
	double bond_length[]= new double[5];
	double bond_center_x[]=new double[5];
	double bond_center_y[]=new double[5];
	double bond_rotation[]=new double[5]; 
	double center_x[]=new double[5];
	double center_y[]=new double[5];
	double magneticbead_x=0;
	double magneticbead_y=0;
	double counter=1;
	int number_of_atoms=2;
	double k[]=new double[5];
	double r0[]=new double[5];
	int current_viscosity=0;

	double Scaling=0.6;
	double poly_centers[][]=new double[1000][5];
	String poly_type[]=new String[1000];
	int bond_between[][]=new int [1000][2];
	int bond_count=0;
	int poly_count=0;
	
//	private PendulumBody        LeftPendulamBody =null;
//	private PendulumBody        RightPendulamBody =null;  // Shape3D
	
	private HorizontalGraph		Energy =null;
	private HorizontalGraph		Force =null;
	
//	private FullViewGraph		fullViewGraph= new FullViewGraph();

	private HashMap 			hm = new HashMap();
	private J3DShape 			m_j3d	= new J3DShape();

	
	private double x=0;
	private double y=1;

	//private JLabel outlbl_val[];
	//private JLabel outputlbl[];
	//private JLabel iLabel[];
    private JLabel m_Objective= new JLabel("Objective:");
	
    private javax.swing.JPanel first;			// Input panel 1
	private javax.swing.JPanel second;	
	private javax.swing.JPanel third;
	private javax.swing.JPanel fourth;
	private javax.swing.JPanel fifth;
	
	
	

	private Timer timer=null;
	private float fields[];
	DecimalFormat Formatter;
	
	private double flag=0;
	private double scale=0;
	private int direction=0;
	//private float atoms_index[5]={0};
	private double [] atoms_center= new double[10];
	private int atoms_count=0;
	private double magnet_x=0.0;
	private double magnet_y=0.0;
	private double magnet_x_translate=0.0;
	private double magnet_y_translate=0.0;
//	private double bond_length=1;
	private double Energy_x=0;
	private double Energy_y=0;
	// Timer for simulation    
	
	

	private int stage = 0;
	private double zval=2.41;
	private double Pendulam_length=0.18;
	private float sphere_radius =0.015f;
	private String WallImage="resources/images/wall3.jpg";
	
	private boolean startStop = false;
	private boolean valChange = true;
	private Vector3d leftcenter;
	private Vector3d rightcenter;
	
	
	 private static final double g = 9.8;

	
	
	
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
		
	//	objRoot.addChild(m_j3d.createBox(new Vector3d(0,-0.32, -.1),new Vector3d(3,.03,2),new Vector3d(0,0,0),new Color3f(0f, 1f, 0f),"resources/images/table.jpg"));
	//	objRoot.addChild(m_j3d.createBox(new Vector3d(0,0.4, -.6),new Vector3d(3,.9,.1),new Vector3d(0f, 0f,0f), new Color3f(0.5f,0.6f,0.72f)));
		
	    
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
        setLight();
        
        univ.getViewingPlatform().setNominalViewingTransform();
        

       ViewingPlatform vp = univ.getViewingPlatform();
   	    TransformGroup steerTG = vp.getViewPlatformTransform();
   	    Transform3D t3d = new Transform3D();
   	    steerTG.getTransform(t3d);
   	    Vector3d s = new Vector3d();
   	    Vector3f currPos=new Vector3f();
   	    t3d.get(currPos); 
   	    
   	   // System.out.println("current Pos:" + currPos);
   	  
   	    
   	    t3d.lookAt( new Point3d(0, 0, 3 ), new Point3d(0,0,0), new Vector3d(0,1,0));
   	 //t3d.lookAt( new Point3d(0, 0, 2.7 ), new Point3d(0,0,0), new Vector3d(0,1,0));
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

    
    private void destroy() {
        univ.cleanup();
    }

 
        
    private Group createLabSetup() {
    	
    	
    	for(int i=0;i<5;i++)
    	{
    		radius[i]=0.02;
    		value[i]=0;
    		distance[i]=0;
    	}
    	
    	Transform3D tr = new Transform3D();
    	TransformGroup objMain = new TransformGroup(tr);
    	objMain.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	objMain.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	//objMain.addChild(m_j3d.createLine(new Point3d(0.02,-0.22,0), new Point3d(0.02,-0.18,0), new Vector3d(0,0,0), new Vector3d(1,1,1), new Color3f(1,1,1)));
   	 	
    	//tr.setTranslation(new Vector3d(0,0.1,0));
    	
    	TransformGroup molecule = new TransformGroup();
    	molecule.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	molecule.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	
    	TransformGroup Start_Experiment = new TransformGroup();
    	Start_Experiment.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	Start_Experiment.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	
    	
    	TransformGroup magnet = new TransformGroup();
    	magnet.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	magnet.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	
    	TransformGroup northpole = new TransformGroup();
    	northpole.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	northpole.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

    	TransformGroup southpole = new TransformGroup();
    	southpole.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	southpole.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

    	TransformGroup fieldlines = new TransformGroup();
    	fieldlines.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	fieldlines.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

    //	fieldlines.addChild(m_j3d.createSphere(pos, radius, ambient, emissive, diffuse, specular, shine));
        	
    	TransformGroup plane = new TransformGroup();
    	plane.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	plane.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	
    	TransformGroup plane1 = new TransformGroup();
    	plane1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	plane1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	
    	TransformGroup structure = new TransformGroup();
    	structure.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	structure.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	
    	TransformGroup background = new TransformGroup();
    	background.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	background.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	

//		tr=new Transform3D();
    	TransformGroup PolyAtomic = new TransformGroup();//tr);
    	PolyAtomic.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	PolyAtomic.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	
    	TransformGroup another_medium = new TransformGroup();//tr);
		another_medium.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		another_medium.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	
    	
		if(1==1)
    	{
    		
    		//double poly_centers[][]=new double[1000][5];
    		//char poly_type[]=new char[1000];
    		//int poly_count=0;

    		double temp_centers[][]=new double[1000][5];
    		String temp_type[]=new String[1000];
    		int temp_count=0;
    //		String []temp;
    		double min_first=10000.0;
    		double min_second=10000.0;
    		double min_third=10000.0;
    		double max_first=-10000.0;
    		double max_second=-10000.0;
    		double max_third=-10000.0;
    		/*
    		try{
    			// Open the file that is the first 
    			// command line parameter
    			FileInputStream fstream = new FileInputStream("/home/abhishek/workspace/VPL/bin/iiit/vlabdemos/common/cholestrol.txt");
    			// Get the object of DataInputStream
    			DataInputStream in = new DataInputStream(fstream);
    			BufferedReader br = new BufferedReader(new InputStreamReader(in));
    			String strLine;
    			//Read File Line By Line
    			int i=0;
    			while ((strLine = br.readLine()) != null)   
    			{
    				//adding spheres
    				
    		        
    				temp=strLine.split(" +");
    				
    		*/
    		//System.out.println("HELLOW");
    		String data_file1 = "cholestrol.txt";
    		int i=0;
            //StringBuffer strBuff;
            URL url = Resources.getResource(data_file1);

            try
                  {
            			InputStream in = url.openStream();
                          BufferedReader bf = new BufferedReader(new InputStreamReader(in));
                          String strLine;
                        
                  while ((strLine = bf.readLine()) != null)
                      {
                          String delimit =" +";
                          String[] tokens = strLine.split(delimit);    		
    			
                          poly_type[i]=tokens[2];//temp[2];


                    String C="C";
    				String H="H";
    				String O="O";


    				if(poly_type[i].equals(C))
    				{
    					tr=new Transform3D();
    					TransformGroup temp1 = new TransformGroup(tr);
    					temp1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    					temp1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    					temp1.addChild(m_j3d.createSphere(new Vector3d(0,0,0), 0.015f, new Color3f(0f,0,0), new Color3f(0,0,0), new Color3f(0,0,0), new Color3f(0,0,0), 0f));
    			    	hm.put("molecule"+i, temp1);
    			    	PolyAtomic.addChild(temp1);
    				}
    				if(poly_type[i].equals(H))
    				{
    					tr=new Transform3D();
    					TransformGroup temp1 = new TransformGroup(tr);
    					temp1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    					temp1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    					temp1.addChild(m_j3d.createSphere(new Vector3d(0,0,0), 0.01f, new Color3f(0f,0,0), new Color3f(0,0,0), new Color3f(0,0,0), new Color3f(0,0,1), 0f));
    					hm.put("molecule"+i, temp1);
    					PolyAtomic.addChild(temp1);
    				
    				}
    				if(poly_type[i].equals(O))
    				{
    					tr=new Transform3D();
    					TransformGroup temp1 = new TransformGroup(tr);
    					temp1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    					temp1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    					temp1.addChild(m_j3d.createSphere(new Vector3d(0,0,0), 0.02f, new Color3f(0.2f,0,0), new Color3f(1,0,0), new Color3f(1,0,0), new Color3f(1,0,0), 1f));
    					hm.put("molecule"+i, temp1);
    					PolyAtomic.addChild(temp1);
    					
    				}
    				
    				temp_centers[i][0]=Double.parseDouble(tokens[4].trim());
    				temp_centers[i][1]=Double.parseDouble(tokens[5].trim());
    				temp_centers[i][2]=Double.parseDouble(tokens[6].trim());
    				//first
    				if(temp_centers[i][0] > max_first)
    				{
    					max_first=temp_centers[i][0];
    				}
    				if(temp_centers[i][0] < min_first)
    				{
    					min_first=temp_centers[i][0];
    				}
    				
    				//second
    				if(temp_centers[i][1] > max_second)
    				{
    					max_second=temp_centers[i][1];
    				}
    				if(temp_centers[i][1] < min_second)
    				{
    					min_second=temp_centers[i][1];
    				}
    				
    				//third
    				if(temp_centers[i][2] > max_third)
    				{
    					max_third=temp_centers[i][2];
    				}
    				if(temp_centers[i][2] < min_third)
    				{
    					min_third=temp_centers[i][2];
    				}
   					i++;
    			}
    			temp_count=i;
    			poly_count=i;
    			//Close the input stream
    			in.close();
    		}catch (Exception e){//Catch exception if any
    			System.err.println("Error: " + e.getMessage());
    	    	}
    		
    		double distance,d0,d1,d2;
    		for(int k=0;k<poly_count;k++)
    		{
    			for(int l=k+1;l<poly_count;l++)
    			{
    				d0=(temp_centers[k][0]-temp_centers[l][0])*(temp_centers[k][0]-temp_centers[l][0]);
    				d1=(temp_centers[k][1]-temp_centers[l][1])*(temp_centers[k][1]-temp_centers[l][1]);
    				d2=(temp_centers[k][2]-temp_centers[l][2])*(temp_centers[k][2]-temp_centers[l][2]);
    				distance=Math.sqrt(d0+d1+d2);
    				if(poly_type[k].equalsIgnoreCase("C") && poly_type[l].equalsIgnoreCase("C"))
    				{
    				//	System.out.println("between "+poly_type[k] + " and " +poly_type[l] +" is "+ distance);
    					//bond_between
    					if(distance > 1.36 && distance < 1.56)
    					{
    						bond_between[bond_count][0]=k;
    						bond_between[bond_count][1]=l;
    						tr=new Transform3D();
        					TransformGroup temp1 = new TransformGroup(tr);
        					temp1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        					temp1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        					temp1.addChild(m_j3d.createCylinder(0.003f,1, new Vector3d(0,0,0),new Vector3d(1,1,1) ,new Vector3d(0,0,0), new Color3f(0,1,1), "hello",hm));
        			//		temp1.addChild(m_j3d.createSphere(new Vector3d(0,0,0), 0.01f, new Color3f(0f,0,0), new Color3f(0,0,0), new Color3f(0,0,0), new Color3f(0,0,1), 0f));
        					hm.put("bond_between"+bond_count, temp1);
        					PolyAtomic.addChild(temp1);
        					bond_count++;
    					}
        			}
    				else if((poly_type[k].equalsIgnoreCase("C") && poly_type[l].equalsIgnoreCase("O")) || (poly_type[k].equalsIgnoreCase("O") && poly_type[l].equalsIgnoreCase("C"))) 
    				{
        			//		System.out.println("between "+poly_type[k] + " and " +poly_type[l] +" is "+ distance);
        					//bond_between
        					if(distance > 1.41 && distance < 1.45)
        					{
        						bond_between[bond_count][0]=k;
        						bond_between[bond_count][1]=l;
        						tr=new Transform3D();
            					TransformGroup temp1 = new TransformGroup(tr);
            					temp1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            					temp1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
            					temp1.addChild(m_j3d.createCylinder(0.004f,1, new Vector3d(0,0,0),new Vector3d(1,1,1) ,new Vector3d(0,0,0), new Color3f(1,1,0), "hello",hm));
            					hm.put("bond_between"+bond_count, temp1);
            					PolyAtomic.addChild(temp1);
            					bond_count++;
        					}
            		}
    				else if((poly_type[k].equalsIgnoreCase("O") && poly_type[l].equalsIgnoreCase("H")) || (poly_type[k].equalsIgnoreCase("H") && poly_type[l].equalsIgnoreCase("O"))) 
    				{
        				//	System.out.println("between "+poly_type[k] + " and " +poly_type[l] +" is "+ distance);
        					//bond_between
        					if(distance > 1.06 && distance < 1.1)
        					{
        						bond_between[bond_count][0]=k;
        						bond_between[bond_count][1]=l;
        						tr=new Transform3D();
            					TransformGroup temp1 = new TransformGroup(tr);
            					temp1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            					temp1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
            					temp1.addChild(m_j3d.createCylinder(0.0025f,1, new Vector3d(0,0,0),new Vector3d(1,1,1) ,new Vector3d(0,0,0), new Color3f(0.5f,0.5f,0.5f), "hello",hm));
            					hm.put("bond_between"+bond_count, temp1);
            					PolyAtomic.addChild(temp1);
            					bond_count++;
        					}
            		}
    				else if((poly_type[k].equalsIgnoreCase("C") && poly_type[l].equalsIgnoreCase("H")) || (poly_type[k].equalsIgnoreCase("H") && poly_type[l].equalsIgnoreCase("C"))) 
    				{
        		//			System.out.println("between "+poly_type[k] + " and " +poly_type[l] +" is "+ distance);
        					//bond_between
        					if(distance > 1.06 && distance < 1.12)
        					{
        						bond_between[bond_count][0]=k;
        						bond_between[bond_count][1]=l;
        						tr=new Transform3D();
            					TransformGroup temp1 = new TransformGroup(tr);
            					temp1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            					temp1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
            					temp1.addChild(m_j3d.createCylinder(0.0025f,1, new Vector3d(0,0,0),new Vector3d(1,1,1) ,new Vector3d(0,0,0), new Color3f(1,0,1), "hello",hm));
            					hm.put("bond_between"+bond_count, temp1);
            					PolyAtomic.addChild(temp1);
            					bond_count++;
        					}
            		}
    			}
    		}
    		
    		for(int l=0;l<bond_count;l++)
    		{
    			System.out.println("Bond between " +bond_between[l][0] +" and "+ bond_between[l][1]);
    		}
    		System.out.println("bond_Count "+ bond_count);
    		double diff_first=max_first-min_first;
    		double diff_second=max_second-min_second;
    		double diff_third=max_third-min_third;
    		System.out.println(diff_first + " " + diff_second +" "+diff_third);
    		if(diff_first > diff_second)
    		{
    			if(diff_first > diff_third)
    			{
    				//first= y axis
    				for(int j=0;j<temp_count;j++)
    				{
    					poly_centers[j][1]=temp_centers[j][0]/diff_first;
    				}
    				if(diff_second > diff_third)
    				{
    					for(int j=0;j<temp_count;j++)
    					{
    						poly_centers[j][0]=temp_centers[j][1]/diff_first;
    						poly_centers[j][2]=temp_centers[j][2]/diff_first;
    					}
    				}
    				else
    				{
    					for(int j=0;j<temp_count;j++)
    					{
    						poly_centers[j][2]=temp_centers[j][1]/diff_first;
    						poly_centers[j][0]=temp_centers[j][2]/diff_first;
    					}
    				}
    			}
    		}
    		else
    		{
    			if(diff_second > diff_third)
    			{
    				//second =y axis
    				for(int j=0;j<temp_count;j++)
    				{
    					poly_centers[j][1]=temp_centers[j][1]/diff_second;
    				}
    				if(diff_first > diff_third)
    				{
    					for(int j=0;j<temp_count;j++)
    					{
    						poly_centers[j][0]=temp_centers[j][0]/diff_second;
    						poly_centers[j][2]=temp_centers[j][2]/diff_second;
    					}
    				}
    				else
    				{
    					for(int j=0;j<temp_count;j++)
    					{
    						poly_centers[j][2]=temp_centers[j][0]/diff_second;
    						poly_centers[j][0]=temp_centers[j][2]/diff_second;
    					}
    				}
    			}
    		}
    		System.out.println("x "+poly_centers[temp_count-1][0]);
    		System.out.println("y "+poly_centers[temp_count-1][1]);
    		System.out.println("z " +poly_centers[temp_count-1][2]);
    		System.out.println("temp count is " + temp_count);
    	}

//    	Color3f r1= new Color3f(.5f,.5f,0.5f);
//  	Color3f b1 = new Color3f(0.3f,.5f,0.6f);
    	
   
    // support 1

    	tr=new Transform3D();
    	TransformGroup support1 = new TransformGroup(tr);
    	support1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	support1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	support1.addChild(m_j3d.createCylinder(0.01f,0.08f, new Vector3d(0,0,0),new Vector3d(1,1,1) ,new Vector3d(0,0,0), new Color3f(0,0,1), "hello",hm));
    	//support1.addChild(m_j3d.createCylinder(new Vector3d(0,0,0), new Vector3d(0.4,0.4,0.4), new Vector3d(0,0,90), new Color3f(0.2f,0.5f,0.5f)));
    	tr.setTranslation(new Vector3d(0,-0.3,0));/////
    	support1.setTransform(tr);

    	
    //	First Atom 	
    	tr=new Transform3D();
    	TransformGroup atom1 = new TransformGroup(tr);
    	atom1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	atom1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	atom1.addChild(m_j3d.createSphere(new Vector3d(0,0,0), 0.02f, new Color3f(1,0,0), new Color3f(1,0,0), new Color3f(1,0,0), new Color3f(1,0,1), 3.0f));
    	
    //  Second Atom
    	tr=new Transform3D();
    	TransformGroup atom2 = new TransformGroup(tr);
    	atom2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	atom2.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	atom2.addChild(m_j3d.createSphere(new Vector3d(0,0,0), 0.02f, new Color3f(1,0,0), new Color3f(1,0,0), new Color3f(1,0,0), new Color3f(1,0,1), 3.0f));
    
    //  Third atom 	
    	tr=new Transform3D();
    	TransformGroup atom3 = new TransformGroup(tr);
    	atom3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	atom3.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	atom3.addChild(m_j3d.createSphere(new Vector3d(0,0,0), 0.02f, new Color3f(1,0,0), new Color3f(1,0,0), new Color3f(1,0,0), new Color3f(1,0,1), 3.0f));
    	
    //  Fourth atom 	
    	tr=new Transform3D();
    	TransformGroup atom4 = new TransformGroup(tr);
    	atom4.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	atom4.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	atom4.addChild(m_j3d.createSphere(new Vector3d(0,0,0), 0.02f, new Color3f(1,0,0), new Color3f(1,0,0), new Color3f(1,0,0), new Color3f(1,0,1), 3.0f));
    	
    	
    //first bond	
        tr=new Transform3D();
    	TransformGroup bond1 = new TransformGroup(tr);
    	bond1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	bond1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	bond1.addChild(m_j3d.createCylinder(0.005f,1, new Vector3d(0,0,0), new Vector3d(1,1,1),new Vector3d(0,0,0), new Color3f(0,1,0), "hello",hm));
    	
    	//second bond	
        tr=new Transform3D();
    	TransformGroup bond2 = new TransformGroup(tr);
    	bond2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	bond2.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	bond2.addChild(m_j3d.createCylinder(0.005f,1, new Vector3d(0,0,0), new Vector3d(1,1,1),new Vector3d(0,0,0), new Color3f(0,1,0), "hello",hm));
    	
    	//third bond	
        tr=new Transform3D();
    	TransformGroup bond3 = new TransformGroup(tr);
    	bond3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	bond3.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	bond3.addChild(m_j3d.createCylinder(0.005f,1, new Vector3d(0,0,0), new Vector3d(1,1,1),new Vector3d(0,0,0), new Color3f(0,1,0), "hello",hm));
    

    	tr=new Transform3D();
    	TransformGroup support2 = new TransformGroup(tr);
    	support2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	support2.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	support2.addChild(m_j3d.createCylinder(0.01f,(float)0.08, new Vector3d(0,0,0), new Vector3d(1,1,1),new Vector3d(0,0,0), new Color3f(0,0,1), "hello",hm));
//    	support2.addChild(m_j3d.createCylinder(new Vector3d(0,-0.3,0), new Vector3d(0.4,0.4,0.4), new Vector3d(0,0,90), new Color3f(0.2f,0.5f,0.5f)));
    	
   
    	
  
    	// magnetic bead
    	tr=new Transform3D();
    	TransformGroup magneticbead = new TransformGroup(tr);
    	magneticbead.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	magneticbead.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		magneticbead.addChild(m_j3d.createSphere(new Vector3d(0,0,0), 0.03f, new Color3f(0.5f,0.5f,0), new Color3f(0.5f,0.5f,0), new Color3f(1,0,0), new Color3f(0,0,0), 3.0f));
      //	magneticbead.addChild(m_j3d.createCylinder(0.01f,(float)(center_y[2]+0.08) , new Vector3d(0,0,0),new Vector3d(0,0,0) ,new Vector3d(0,0,0), new Color3f(1,0,0), "hello",hm));
		
    	
    	
    	
    //	plane.addChild(m_j3d.createBox(new Vector3d(0,-0.34,-0.8),new Vector3d(0.3,.01,1),new Vector3d(0,0,0),new Color3f(0f, 1f, 0f),"resources/images/grey4.jpg"));
    	plane.addChild(m_j3d.createBox(new Vector3d(0,-0.34,-0.4),new Vector3d(0.3,.01,1),new Vector3d(0,0,0),new Color3f(0f, 1f, 0f),"grey4.jpg"));
    	plane1.addChild(m_j3d.createBox(new Vector3d(0,-0.2,-0.4),new Vector3d(0.3,0.001,1),new Vector3d(0,0,0),new Color3f(0f, 1f, 0f),"grey4.jpg"));
    	background.addChild(m_j3d.createBox(new Vector3d(0,0,-2),new Vector3d(2,2,1),new Vector3d(0,0,0),new Color3f(0f, 1f, 0f),"bg.jpg"));
    	northpole.addChild(m_j3d.createBox(new Vector3d(-0.12,0.4,-0.06),new Vector3d(0.04,.03,0.02),new Vector3d(0,0,0),new Color3f(0f, 1f, 0f),"bar_magnetN.gif"));
    	southpole.addChild(m_j3d.createBox(new Vector3d(0.12,0.4,-0.06),new Vector3d(0.04,.03,0.02),new Vector3d(0,0,0),new Color3f(0f, 1f, 0f),"bar_magnetS.gif"));
    
    	/*
    	for(float i=0;i<360;i++)
    	{
    	
    		if(i%20 > 10.0)
    		{
    			//250-235-215
    			fieldlines.addChild(m_j3d.createSphere(new Vector3d(0.1*Math.cos(i*Math.PI/180),0.4+0.1*Math.sin(i*Math.PI/180),0), 0.002f, new Color3f(0f,0f,0f), new Color3f(0f,0f,0f), new Color3f(0,0,0), new Color3f(1,0,0), 0f));
    		}
    	}
    	for(float i=0;i<360;i++)
    	{
    	
    		if(i%20 > 10.0)
    		{
    			fieldlines.addChild(m_j3d.createSphere(new Vector3d(0.12*Math.cos(i*Math.PI/180),0.4+0.12*Math.sin(i*Math.PI/180),0), 0.002f, new Color3f(0,0,0), new Color3f(0,0,0), new Color3f(0,0,0), new Color3f(1,0,0), 0f));
    		}
    	}
    	for(float i=0;i<360;i++)
    	{
    	
    		if(i%20 > 10.0)
    		{
    			fieldlines.addChild(m_j3d.createSphere(new Vector3d(0.14*Math.cos(i*Math.PI/180),0.4+0.14*Math.sin(i*Math.PI/180),0), 0.002f, new Color3f(0,0,0), new Color3f(0,0,0), new Color3f(0,0,0), new Color3f(1,0,0), 0f));
    		}
    	}
    	
    	*/
    	
    	for(float i=0;i<360;i++)
    	{
    	
    		if(i%20 > 10.0)
    		{
    			fieldlines.addChild(m_j3d.createSphere(new Vector3d(0.14*Math.cos(i*Math.PI/180),0.4+0.14*Math.sin(i*Math.PI/180),-0.06), 0.002f, new Color3f(0,0,0), new Color3f(0,0,0), new Color3f(0,0,0), new Color3f(1,0,0), 0f));
    			fieldlines.addChild(m_j3d.createSphere(new Vector3d(0.12*Math.cos(i*Math.PI/180),0.4+0.12*Math.sin(i*Math.PI/180),-0.06), 0.002f, new Color3f(0,0,0), new Color3f(0,0,0), new Color3f(0,0,0), new Color3f(1,0,0), 0f));
    			fieldlines.addChild(m_j3d.createSphere(new Vector3d(0.1*Math.cos(i*Math.PI/180),0.4+0.1*Math.sin(i*Math.PI/180),-0.06), 0.002f, new Color3f(0,0,0), new Color3f(0,0,0), new Color3f(0,0,0), new Color3f(1,0,0), 0f));
    		}
    	}
  
    	for(float i=0;i<360;i++)
    	{
    		if(i%20 > 10.0)
    		{
    			magneticbead.addChild(m_j3d.createSphere(new Vector3d(0.1*Math.cos(i*Math.PI/180),0.1*Math.sin(i*Math.PI/180),0), 0.002f, new Color3f(0f,0f,0f), new Color3f(0,0,0), new Color3f(0,0,0), new Color3f(0.59f,0.39f,0f), 1f));
    		}
    	}
    	//createlinegeometry(new Point3d(0,0,0), new Point3d(0.1f,0.1f,01.f), new Color3f(1,0,0)
    /*	for(double i=0;i<180;i+=10)
    	{
    		for(double j=0;j<360;j+=10)
    		{
    			System.out.println("i is " + i + " j is "+j );
    			float x= ((float) (0.1* Math.cos(j)* Math.sin(i)));
    			float y = (float) ( 0.1 * Math.sin(j) * Math.sin(i));
    			float z= (float) (0.1 * Math.cos(i));
    			magneticbead.addChild(m_j3d.createLine(new Point3d(0,0,0), new Point3d(x,y,z), new Vector3d(0,0,0), new Vector3d(1,1,1), new Color3f(1,0,0)));
    		}
    	}*/
    	//bond.addChild(m_j3d.createCylinder(new Vector3d(0,0,0), new Vector3d(1,1,1), new Vector3d(0,0,90), new Color3f(0.2f,0.2f,0.5f)));
    	magnet_x=0.0;
    	magnet_y=0.4;
    //	System.out.println(magnet_y*100);
    
    	
    	hm.put("plane",plane);
    	hm.put("plane1",plane1);
    	hm.put("molecule", molecule);
    	hm.put("magnet", magnet);
    	hm.put("structure",structure);
    	hm.put("support1", support1);
    	hm.put("support2", support2);
        hm.put("atom1", atom1);
        hm.put("atom2", atom2);
        hm.put("atom3", atom3);
        hm.put("atom4", atom4);
        hm.put("magneticbead", magneticbead);
        hm.put("bond1", bond1);
        hm.put("bond2", bond2);
        hm.put("bond3", bond3);
        hm.put("poly_atomic",PolyAtomic);
        hm.put("Medium",another_medium);        
        hm.put("Start_Experiment", magneticbead);
        //System.out.println("POLY " +poly_count);
        TransformGroup objtr;
        
        if(number_of_atoms==4)
        {
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("medium_cylinder"+0);
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(1,1,1));
        	tr.setTranslation(new Vector3d(0,-0.10,0));
        	//tr.setTranslation(new Vector3d(0,-0.1,0));
        	objtr.setTransform(tr);
        }
        else
        {
        /*	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("medium_cylinder"+0);
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(1,0.8,1));
        	tr.setTranslation(new Vector3d(0,-0.10,0));
        	//tr.setTranslation(new Vector3d(0,-0.1,0));
        	objtr.setTransform(tr);*/
        }
        
        int temp4=0;
		for( int k=1;k<Total_cylinder;k++)
		{
		/*	tr = new Transform3D();
            objtr=(TransformGroup)hm.get("medium_cylinder"+k);
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
        	//tr.setTranslation(new Vector3d(0,-0.1,0));
        	objtr.setTransform(tr);
//			hm.put("medium_cylinder"+temp4,aimer);
	//		another_medium.addChild(temp2);
			temp4++;*/
		}
		
		//for cylinder lid
	/*	tr = new Transform3D();
        objtr=(TransformGroup)hm.get("cylinder_lid");
    	objtr.getTransform(tr);
    	tr.setScale(new Vector3d(0,0,0));
    	tr.setTranslation(new Vector3d(0,0.2,0));
    	objtr.setTransform(tr);
	
		System.out.println("TOTAL CYLINDERS"+temp4);
*/
        for(int k=0;k<poly_count;k++)
        {
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("molecule"+k);
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
        //	tr.setTranslation(new Vector3d(poly_centers[k][0],poly_centers[k][1],poly_centers[k][2]));
        	objtr.setTransform(tr);
        }

        for(int k=0;k<poly_count;k++)
        {
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("molecule"+k);
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
        //	tr.setTranslation(new Vector3d(poly_centers[k][0],poly_centers[k][1],poly_centers[k][2]));
        	objtr.setTransform(tr);
        }

        for(int k=0;k<bond_count;k++)
        {
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("bond_between"+k);
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
        //	tr.setTranslation(new Vector3d(poly_centers[k][0],poly_centers[k][1],poly_centers[k][2]));
        	objtr.setTransform(tr);
        }
        
        
        tr = new Transform3D();
    	objtr=(TransformGroup)hm.get("atom1");
    	objtr.getTransform(tr);
    	radius[1]=0.02;
    	center_y[1]=-0.3 + (0.08/2);
    	center_x[1]=0;
    	tr.setTranslation(new Vector3d(center_x[1],center_y[1],0));
    	objtr.setTransform(tr);
    	
        
    	tr = new Transform3D();
    	objtr=(TransformGroup)hm.get("atom2");
    	objtr.getTransform(tr);
    	center_x[2]=0;
    	center_y[2]=-0.16;
    	radius[2]=0.02;
    	tr.setTranslation(new Vector3d(center_x[2],center_y[2],0));
    	objtr.setTransform(tr);
    
    	
    	tr = new Transform3D();
    	objtr=(TransformGroup)hm.get("atom3");
    	objtr.getTransform(tr);
    	center_x[3]=0;
    	center_y[3]=-0.06;
    	radius[3]=0.02;
    	tr.setScale(new Vector3d(0,0,0));
    	tr.setTranslation(new Vector3d(center_x[3],center_y[3],0));
    	objtr.setTransform(tr);
    
    	tr = new Transform3D();
    	objtr=(TransformGroup)hm.get("atom4");
    	objtr.getTransform(tr);
    	center_x[4]=0;
    	center_y[4]=-0.06+.1;
    	radius[4]=0.02;
    	tr.setScale(new Vector3d(0,0,0));
    	tr.setTranslation(new Vector3d(center_x[4],center_y[4],0));
    	objtr.setTransform(tr);
    
    	tr = new Transform3D();
    	objtr=(TransformGroup)hm.get("plane1");
    	objtr.getTransform(tr);
    	tr.setScale(new Vector3d(0,0,0));
    	objtr.setTransform(tr);
    
    	
    	//  First Bond	
    	double temp=(center_x[1]-center_x[2])*(center_x[1]-center_x[2]) +(center_y[1]-center_y[2])*(center_y[1]-center_y[2]);
    	bond_center_x[1]=(center_x[1]+center_x[2])/2;
    	bond_center_y[1]=(center_y[1]+center_y[2])/2;
    	bond_length[1]=Math.sqrt(temp);
    	bond_rotation[1]=Math.atan((center_y[2]-center_y[1])/(center_x[2]-center_x[1]));
    	tr = new Transform3D();
    	objtr=(TransformGroup)hm.get("bond1");
    	objtr.getTransform(tr);
   // 	System.out.println(bond_length[1]);
    	tr.setScale(new Vector3d(1,bond_length[1],1));
    	tr.setRotation(new AxisAngle4d(0,0,1,(Math.PI/2)+bond_rotation[1]));
    	tr.setTranslation(new Vector3d(bond_center_x[1], bond_center_y[1] , 0));
    	objtr.setTransform(tr);
        
    	
    //  Second Bond	
    	temp=(center_x[2]-center_x[3])*(center_x[2]-center_x[3]) +(center_y[2]-center_y[3])*(center_y[2]-center_y[3]);
    	bond_center_x[2]=(center_x[2]+center_x[3])/2;
    	bond_center_y[2]=(center_y[2]+center_y[3])/2;
    	bond_length[2]=Math.sqrt(temp);
    	bond_rotation[2]=Math.atan((center_y[3]-center_y[2])/(center_x[3]-center_x[2]));
    	tr = new Transform3D();
    	objtr=(TransformGroup)hm.get("bond2");
    	objtr.getTransform(tr);
   // 	System.out.println(bond_length[1]);
    	tr.setScale(new Vector3d(0,0,0));
    	//tr.setScale(new Vector3d(1,bond_length[2],1));
    	tr.setRotation(new AxisAngle4d(0,0,1,(Math.PI/2)+bond_rotation[2]));
    	tr.setTranslation(new Vector3d(bond_center_x[2], bond_center_y[2] , 0));
    	objtr.setTransform(tr);
        
    	  //  Third Bond	
    	temp=(center_x[3]-center_x[4])*(center_x[3]-center_x[4]) +(center_y[3]-center_y[4])*(center_y[3]-center_y[4]);
    	bond_center_x[3]=(center_x[3]+center_x[4])/2;
    	bond_center_y[3]=(center_y[3]+center_y[4])/2;
    	bond_length[3]=Math.sqrt(temp);
    	bond_rotation[3]=Math.atan((center_y[4]-center_y[3])/(center_x[4]-center_x[3]));
    	tr = new Transform3D();
    	objtr=(TransformGroup)hm.get("bond3");
    	objtr.getTransform(tr);
   // 	System.out.println(bond_length[1]);
    	tr.setScale(new Vector3d(0,0,0));
    //	tr.setScale(new Vector3d(1,bond_length[3],1));
    	tr.setRotation(new AxisAngle4d(0,0,1,(Math.PI/2)+bond_rotation[3]));
    	tr.setTranslation(new Vector3d(bond_center_x[3], bond_center_y[3] , 0));
    	objtr.setTransform(tr);
  
    	
     	tr = new Transform3D();
    	objtr=(TransformGroup)hm.get("support2");
    	objtr.getTransform(tr);
     	tr.setTranslation(new Vector3d(center_x[2],center_y[2]+0.08/2,0));
    	support2.setTransform(tr);
    	
    	
        
    	
     	tr = new Transform3D();
    	objtr=(TransformGroup)hm.get("magneticbead");
    	objtr.getTransform(tr);
    	tr.setTranslation(new Vector3d(center_x[2],center_y[2]+0.08,0));
    	magneticbead.setTransform(tr);
    	magneticbead_x=center_x[2];
    	magneticbead_y=center_y[2]+0.08;

    	
    	
        // mouse rotation
        MouseRotate mr = new MouseRotate();
        mr.setTransformGroup(structure);
      //  mr.setTransformGroup(PolyAtomic);
        mr.setSchedulingBounds(new BoundingSphere(new Point3d(), 100.0));
        mr.setFactor(0.007);
        structure.addChild(mr);
        
//        mr = new MouseRotate();
//        //mr.setTransformGroup(structure);
//        mr.setTransformGroup(PolyAtomic);
//        mr.setSchedulingBounds(new BoundingSphere(new Point3d(), 100.0));
//        mr.setFactor(0.007);
//        structure.addChild(mr);
//        
        mr = new MouseRotate();
        //mr.setTransformGroup(structure);
        mr.setTransformGroup(Start_Experiment);
        mr.setSchedulingBounds(new BoundingSphere(new Point3d(), 100.0));
        mr.setFactor(0.007);
        structure.addChild(mr);
        
    
      	objMain.addChild(magnet);
      	objMain.addChild(another_medium);
      	objMain.addChild(Start_Experiment);
      	Start_Experiment.addChild(PolyAtomic);
      	Start_Experiment.addChild(support1);
      	Start_Experiment.addChild(support2);
      	//objMain.addChild(PolyAtomic);
    	objMain.addChild(background);
    	magnet.addChild(northpole);
    	magnet.addChild(southpole);
    	magnet.addChild(fieldlines);
    	objMain.addChild(plane);
    	objMain.addChild(plane1);
    	objMain.addChild(structure);
    	structure.addChild(molecule);
    //	structure.addChild(PolyAtomic);//
    	molecule.addChild(atom1);
    	molecule.addChild(atom2);
    	
    	molecule.addChild(atom3);
    	molecule.addChild(atom4);
    	molecule.addChild(bond1);
    	molecule.addChild(bond2);
    	molecule.addChild(bond3);
    	structure.addChild(magneticbead);
   // 	structure.addChild(support1);
    //	structure.addChild(support2);
    	

    	// all buttons
    	diatomic.setEnabled(true);
    	triatomic.setEnabled(true);
    	quadatomic.setEnabled(true);
    	
    	//all sliders
    	Start_experiment.setEnabled(false);
    	slider_x.setEnabled(false);
    	slider_y.setEnabled(false);
    	atom_first.setEnabled(false);
    	atom_second.setEnabled(false);
    	atom_third.setEnabled(false);
    	atom_fourth.setEnabled(false);
    	value_12.setEnabled(false);
    	value_23.setEnabled(false);
    	value_34.setEnabled(false);
    	distance_12.setEnabled(false);
    	distance_23.setEnabled(false);
    	distance_34.setEnabled(false);
    	center2.setEnabled(false);
    	center3.setEnabled(false);
    	center4.setEnabled(false);
		Viscosity.setEnabled(false);
    	
    	return objMain;
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
    public Magnetic_Tweezer(Container container) {
        // Initialize the GUI components
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);
        initComponents();

        centerPanel(container);
//        scene.addChild(bgleg);
    }

    
    // ----------------------------------------------------------------
    
    // Applet framework

    public static class MyApplet extends JApplet {
    	Magnetic_Tweezer mainPanel;

        public void init() {
            setLayout(new BorderLayout());
            mainPanel = new Magnetic_Tweezer(this);
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
            setTitle("My first applet");
            getContentPane().add(new Magnetic_Tweezer(this), BorderLayout.CENTER);
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

    private void topPanel() {
    //	topPanel  = new JPanel(new java.awt.GridLayout(1,3,40,0));    
    	java.awt.GridBagConstraints gridBagConstraints;
        
        javax.swing.JPanel guiPanel = new javax.swing.JPanel(); // Pause, resume at top
        guiPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);  
                
        startButton = new javax.swing.JButton("Start");
        ImageIcon icon = m_j3d.createImageIcon("start.png"); 
        startButton.setIcon(icon);
       
        guiPanel.setBackground(new Color(67,143,205));//Color.BLACK
        topPanel.setLayout(new java.awt.BorderLayout());
        topPanel.add(guiPanel, java.awt.BorderLayout.NORTH);
        
        
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	// Toggle
            	startStop = !startStop;
            	enableStage(10);
            	if(startStop)  startSimulation(evt);
            	else pauseSimulation();
            	univ.getCanvas().repaint();
            }
          });
        
       
      guiPanel.add(startButton, gridBagConstraints);
}
    
    
    private void rightPanel() {
        
    	int w=300;
        int h=200;
        
    	rightPanel.setLayout(new java.awt.GridLayout(2,1,0,1));
    	javax.swing.JPanel guiPanel = new javax.swing.JPanel(); // Pause, resume at top
   
    	JPanel p = new JPanel(new java.awt.GridLayout(1,1,0,0));
        Energy = new HorizontalGraph(w,h,"dis","Energy");
        Energy.setHeading("Energy Vs Displacement");
        Energy.setAxisUnit("J","A");
        Energy.setYAxisColor(new Color(0.0f,0.54f,0.27f));        
        Energy.fitToYwindow(true);
        //Energy.setXOffset(1);
        Energy.setYOffset(120);
        Energy.setYScale(1);
        p.add(Energy);
        rightPanel.add(p);        

      	p = new JPanel(new java.awt.GridLayout(1,1,0,0));
        Force= new HorizontalGraph(w,h,"dis","Force");
        Force.setHeading("Force Vs Displacement");
        Force.setAxisUnit("N","A");
        Force.setYAxisColor(new Color(0.0f,0.54f,0.27f));        
        Force.fitToYwindow(true);
        //Force.setXOffset(1);
        Force.setYOffset(120);
        p.add(Force);
        rightPanel.add(p);
    
    
       

        rightPanel.setVisible(true);
    }
    
    
    private void centerPanel(Container container){
    	
   	 	simulationPanel.setPreferredSize(new java.awt.Dimension(1024, 660));
        simulationPanel.setLayout(new java.awt.BorderLayout());
       
        javax.swing.JPanel guiPanel = new javax.swing.JPanel();
        guiPanel.setBackground(new Color(100,100,100));
        JLabel lbl = new JLabel("Magnetic Effect on Molecule", JLabel.CENTER);
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
        
    
        m_Objective = new JLabel(">: Start the experiment and observe the time period on maximum response.", JLabel.LEFT);
        m_Objective.setFont(new Font("Arial", Font.BOLD, 13));
        m_Objective.setForeground(Color.WHITE);
        guiPanel = new javax.swing.JPanel();
        guiPanel.setBackground(new Color(100,100,100));        
      //  guiPanel.add(m_Objective);
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
      // guiPanel.add(createInputOutputPanel());
      // guiPanel.add(DifferentButtons());
       btmPanel.add(guiPanel,BorderLayout.SOUTH);
   }
  
    private void bottomPanel()
    {
    	initInputControlsField();
    	
    	java.awt.GridBagConstraints gridBagConstraints;
    	Color bk = new Color(219,226,238);
       // Formatter = new DecimalFormat("#####.##");
    	 
        bottomPanel  = new JPanel(new java.awt.GridLayout(1,3,40,0));
	    bottomPanel.setBackground(bk);
       //bottomPanel.setBorder(BorderFactory.createLineBorder(new Color(235,233,215),8));
       
	    
        first=new JPanel(new java.awt.GridLayout(3,1,0,0));
        first.setBackground(bk);
        
        
       
        
        javax.swing.JPanel temp1;
        temp1 = new  JPanel(new java.awt.GridLayout(1,2,0,0));
        temp1.setBackground(bk);
        JLabel lbl = new JLabel("Viscosity ", JLabel.LEFT);
	    Viscosity = new JSlider(JSlider.HORIZONTAL,  0, 9 , 0);
	    Viscosity.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    
		    	valChange = true;
		    	int temp=((int)((JSlider) e.getSource()).getValue());
		    	current_viscosity=temp;
		    	for(int f=0;f<Total_cylinder;f++)
		    	{
		    		if(number_of_atoms!=4)
		    		{
		    			if(temp!=0)
		    			{
		    				//for cylinder lid
		    				Transform3D tr = new Transform3D();
		    				TransformGroup objtr=(TransformGroup)hm.get("cylinder_lid");
		    				objtr.getTransform(tr);
		    				tr.setScale(new Vector3d(1,1,1));
		    				tr.setTranslation(new Vector3d(0,0.18,0));
		    				objtr.setTransform(tr);

		    			}
		    			else
		    			{
		    				//	System.out.println("f is equal to "+f);
		    				Transform3D tr = new Transform3D();
		    				TransformGroup objtr=(TransformGroup)hm.get("cylinder_lid");
		    				objtr.getTransform(tr);
		    				tr.setScale(new Vector3d(0,0,0));
		    				//	tr.setTranslation(new Vector3d(0,0.18,0));
		    				objtr.setTransform(tr);

		    			}
		    		}
		    		else
		    		{
		    			if(temp!=0)
		    			{
		    				//for cylinder lid
		    				Transform3D tr = new Transform3D();
		    				TransformGroup objtr=(TransformGroup)hm.get("cylinder_lid");
		    				objtr.getTransform(tr);
		    				tr.setScale(new Vector3d(1,1,1));
		    				tr.setTranslation(new Vector3d(0,0.21,0));
		    				objtr.setTransform(tr);

		    			}
		    			else
		    			{
		    				//	System.out.println("f is equal to "+f);
		    				Transform3D tr = new Transform3D();
		    				TransformGroup objtr=(TransformGroup)hm.get("cylinder_lid");
		    				objtr.getTransform(tr);
		    				tr.setScale(new Vector3d(0,0,0));
		    				//	tr.setTranslation(new Vector3d(0,0.18,0));
		    				objtr.setTransform(tr);

		    			}


		    		}
		    		if(f==temp)
		    		{
		    			if(number_of_atoms==4)
		    			{
		    				Transform3D tr = new Transform3D();
		    				TransformGroup objtr=(TransformGroup)hm.get("medium_cylinder"+f);
		    				objtr.getTransform(tr);
		    				tr.setScale(new Vector3d(1,1,1));
		    				tr.setTranslation(new Vector3d(0,-0.14,0.1));
		    				//tr.setTranslation(new Vector3d(0,-0.1,0));
		    				objtr.setTransform(tr);
		    			}
		    			else
		    			{
		    				Transform3D tr = new Transform3D();
		    				TransformGroup objtr=(TransformGroup)hm.get("medium_cylinder"+f);
		    				objtr.getTransform(tr);
		    				tr.setScale(new Vector3d(1,0.8,1));
		    				tr.setTranslation(new Vector3d(0,-0.1,0));
		    				//tr.setTranslation(new Vector3d(0,-0.1,0));
		    				objtr.setTransform(tr);
		    				
		    			}
		    		}
		    		else
		    		{
		    			Transform3D tr = new Transform3D();
		    			TransformGroup objtr=(TransformGroup)hm.get("medium_cylinder"+f);
		    			objtr.getTransform(tr);
		    			tr.setScale(new Vector3d(0,0,0));
		    			objtr.setTransform(tr);
		    		}
		    	}
		    }
	    });
	    Viscosity.setBackground(bk);
        temp1.add(lbl);
	   	temp1.add(Viscosity);
	   	first.add(temp1);
        
        
	   	temp1 = new  JPanel(new java.awt.GridLayout(1,2,0,0));
        temp1.setBackground(bk);
        
        lbl = new JLabel("X coordinate of Magnet ", JLabel.LEFT);
	    slider_x = new JSlider(JSlider.HORIZONTAL,  -90, 90 , 0);
	    slider_x.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    
		    	valChange = true;
		    	double temp=((double)((JSlider) e.getSource()).getValue())/100.0;
		    	Transform3D tr = new Transform3D();
		    	TransformGroup objtr=(TransformGroup)hm.get("magnet");
		    	objtr.getTransform(tr);
		    	magnet_x_translate=temp;
		      	tr.setTranslation(new Vector3d(magnet_x_translate,magnet_y_translate,0));
		      	//System.out.println("X is " + magnet_x_translate);
		    	objtr.setTransform(tr);		        
		    }
	    });
	    slider_x.setBackground(bk);
	    temp1.add(lbl);
	   	temp1.add(slider_x);
	   	first.add(temp1);
	    
	   	
        
	   	temp1 = new  JPanel(new java.awt.GridLayout(1,2,0,0));
        temp1.setBackground(bk);
        
	   	lbl = new JLabel("Y coordinate of Magnet ", JLabel.LEFT);
	   	slider_y = new JSlider(JSlider.HORIZONTAL, -50*2, 50*2,0);
		    slider_y.addChangeListener(new ChangeListener() {
			    public void stateChanged(ChangeEvent e) {
			    	valChange = true;
			    	double temp=((double)((JSlider) e.getSource()).getValue())/(100.0*2);
			    	Transform3D tr = new Transform3D();
			    	TransformGroup objtr=(TransformGroup)hm.get("magnet");
			    	objtr.getTransform(tr);
			    	magnet_y_translate=temp;
			    	if(temp>0)
			    	{
			    		tr.setTranslation(new Vector3d(magnet_x_translate,magnet_y_translate/5,0));
			    	}
			    	else
			    	{
			    		tr.setTranslation(new Vector3d(magnet_x_translate,magnet_y_translate/3.5,0));	
			    	}
			        
			    	objtr.setTransform(tr);
			    	double temp_r=(1+magnet_y_translate)*r0[1];
			    	double temp_y=-1*k[1]*(temp_r-r0[1]);
			    	Force.setState(1);
			    	Force.setCurrentValue((float)temp_r,(float)temp_y);        
			        Force.addGraphValue((float)temp_y);//-freeBody.getDisplacment()*500));
			        Force.drawGraph();
			        Force.setYScale(10);
			   
			        temp_r=(1+magnet_y_translate)*r0[1];
			    	temp_y=(0.5*k[1]*(temp_r-r0[1])*(temp_r-r0[1]));
			    	Energy.setState(1);
			    	Energy.setCurrentValue((float)temp_r,(float)temp_y*2);        
			        Energy.addGraphValue((float)temp_y*2);//-freeBody.getDisplacment()*500));
			        Energy.drawGraph();
			        Energy.setYScale(10);
			        
			    }
		    });
	
	 slider_y.setBackground(bk);
	 temp1.add(lbl);
	 temp1.add(slider_y);
	 first.add(temp1);
	 
	 
	 second=new JPanel(new java.awt.GridLayout(4,1,0,0));
	 second.setBackground(bk);
	 lbl = new JLabel("Radius of Atoms ", JLabel.LEFT);
	 second.add(lbl);
	 
	 
	 javax.swing.JPanel temp;
	 temp=new JPanel(new java.awt.GridLayout(1,2,0,0));
	 temp.setBackground(bk);
	 lbl = new JLabel("First Atom", JLabel.LEFT);
     atom_first = new JSlider(JSlider.HORIZONTAL,  0, 10 ,0);
     atom_first.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	//valChange = true;
		    	double temp=((double)((JSlider) e.getSource()).getValue());
		    	//System.out.println(0.5+temp*0.1);
		    	Transform3D tr = new Transform3D();
		    	TransformGroup objtr=(TransformGroup)hm.get("atom1");
		    	objtr.getTransform(tr);
		    	radius[1]=(1+temp*0.1);
		    	System.out.println(radius[1]);
		     	tr.setScale(new Vector3d(radius[1],radius[1],radius[1]));
		     	objtr.setTransform(tr);     
		    }
	 });
     atom_first.setBackground(bk);
	 temp.add(lbl);
	 temp.add(atom_first);
	 second.add(temp);
	
	 temp=new JPanel(new java.awt.GridLayout(1,2,0,0));
	 temp.setBackground(bk);
	 lbl = new JLabel("Second Atom", JLabel.LEFT);
	 atom_second = new JSlider(JSlider.HORIZONTAL,  0, 10 ,0);
     atom_second.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	double temp=((double)((JSlider) e.getSource()).getValue());
		    	Transform3D tr = new Transform3D();
		    	TransformGroup objtr=(TransformGroup)hm.get("atom2");
		    	objtr.getTransform(tr);
		    	radius[2]=(1+temp*0.1);
		    	tr.setScale(new Vector3d(radius[2],radius[2],radius[2]));
		     	objtr.setTransform(tr);     
		    }
	 });
     atom_second.setBackground(bk);
	 temp.add(lbl);
	 temp.add(atom_second);
	 second.add(temp);
	 
	 
	 
	 temp=new JPanel(new java.awt.GridLayout(1,2,0,0));
	 temp.setBackground(bk);
	 lbl = new JLabel("Third Atom", JLabel.LEFT);
     atom_third = new JSlider(JSlider.HORIZONTAL,  0, 10 ,0);
     atom_third.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	double temp=((double)((JSlider) e.getSource()).getValue());
		    	Transform3D tr = new Transform3D();
		    	TransformGroup objtr=(TransformGroup)hm.get("atom3");
		    	objtr.getTransform(tr);
		    	radius[3]=(1+temp*0.1);
		    	tr.setScale(new Vector3d(radius[3],radius[3],radius[3]));
		     	objtr.setTransform(tr);     
		    }
	 });
     atom_third.setBackground(bk);
	 temp.add(lbl);
	 temp.add(atom_third);
	 second.add(temp);
	
	 
	 temp=new JPanel(new java.awt.GridLayout(1,2,0,0));
	 temp.setBackground(bk);
	 lbl = new JLabel("Fourth Atom", JLabel.LEFT);
	 atom_fourth = new JSlider(JSlider.HORIZONTAL,  0, 10 ,0);
     atom_fourth.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	double temp=((double)((JSlider) e.getSource()).getValue());
		    	Transform3D tr = new Transform3D();
		    	TransformGroup objtr=(TransformGroup)hm.get("atom4");
		    	objtr.getTransform(tr);
		    	radius[4]=(1+temp*0.1);
		    	tr.setScale(new Vector3d(radius[4],radius[4],radius[4]));
		     	objtr.setTransform(tr);     
		    }
	 });
     atom_fourth.setBackground(bk);
	 temp.add(lbl);
	 temp.add(atom_fourth);
	 //second.add(temp);
	 
	 
	 
	 third=new JPanel(new java.awt.GridLayout(3,3,0,0));
	 temp=new JPanel(new java.awt.GridLayout(1,2,0,0));
	 temp.setBackground(bk);
	 lbl = new JLabel("Bond ", JLabel.LEFT);
     temp.add(lbl);
     lbl = new JLabel("Value K", JLabel.LEFT);
     temp.add(lbl);
     lbl = new JLabel("Equi. Dist.", JLabel.LEFT);
     temp.add(lbl);
	 third.add(temp);
	 
	 temp=new JPanel(new java.awt.GridLayout(1,2,0,0));
	 temp.setBackground(bk);
	 lbl = new JLabel("1 and 2", JLabel.LEFT);
     temp.add(lbl);
     k[1]=5;
     value_12 = new JSlider(JSlider.HORIZONTAL,  1,10 ,5);
     value_12.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	double temp=((double)((JSlider) e.getSource()).getValue());
		    	k[1]=temp;
		    }
	 });
     value_12.setBackground(bk);
     temp.add(value_12);
     r0[1]=1.54;
     r0[2]=1.54;
     distance_12 = new JSlider(JSlider.HORIZONTAL,  100, 200 ,154);
     distance_12.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	double temp=((double)((JSlider) e.getSource()).getValue());
		    	r0[1]=temp/100.0;
		//    	System.out.println(r0[1]);
		    }
	 });
     distance_12.setBackground(bk);
     temp.add(distance_12);
	 third.add(temp);
	 
	 
	 temp=new JPanel(new java.awt.GridLayout(1,2,0,0));
	 temp.setBackground(bk);
	 lbl = new JLabel("2 and 3", JLabel.LEFT);
     temp.add(lbl);
     value_23 = new JSlider(JSlider.HORIZONTAL,  1, 10 ,2);
     value_23.setBackground(bk);
     temp.add(value_23);
     distance_23 = new JSlider(JSlider.HORIZONTAL,  1, 10 ,2);
     distance_23.setBackground(bk);
     temp.add(distance_23);
	 third.add(temp);
	 
	 temp=new JPanel(new java.awt.GridLayout(1,2,0,0));
	 temp.setBackground(bk);
	 lbl = new JLabel("3 and 4", JLabel.LEFT);
     temp.add(lbl);
     value_34 = new JSlider(JSlider.HORIZONTAL,  1, 10 ,2);
     value_34.setBackground(bk);
     temp.add(value_34);
     distance_34 = new JSlider(JSlider.HORIZONTAL,  1, 10 ,2);
     distance_34.setBackground(bk);
     temp.add(distance_34);
	 //third.add(temp);

	 
	 
	 
	 fifth=new JPanel(new java.awt.GridLayout(3,1,0,0));
	 temp=new JPanel(new java.awt.GridLayout(1,2,0,0));
	 temp.setBackground(bk);
	 lbl = new JLabel("Center of Atoms", JLabel.LEFT);
     temp.add(lbl);
     fifth.add(temp);
     
	 temp=new JPanel(new java.awt.GridLayout(1,2,0,0));
	 temp.setBackground(bk);
	 lbl = new JLabel("Second Atom", JLabel.LEFT);
     temp.add(lbl);
     center2 = new JSlider(JSlider.HORIZONTAL,  -10,10 ,0);
     center2.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	double temp=((double)((JSlider) e.getSource()).getValue());
		    	Transform3D tr = new Transform3D();
		    	TransformGroup objtr=(TransformGroup)hm.get("atom2");
		    	objtr.getTransform(tr);
		    	//radius[4]=(1+temp*0.1);
		    	center_x[2]=temp*0.01;
		    	//System.out.println(center_x[2]);
		    	tr.setTranslation(new Vector3d(center_x[2],center_y[2],0));
		     	objtr.setTransform(tr);
		     	enableStage(4);
		    }
	 });
     center2.setBackground(bk);
     temp.add(center2);
     fifth.add(temp);

     temp=new JPanel(new java.awt.GridLayout(1,2,0,0));
	 temp.setBackground(bk);
	 lbl = new JLabel("Third Atom", JLabel.LEFT);
     temp.add(lbl);
     center3 = new JSlider(JSlider.HORIZONTAL,  -10,10 ,0);
     center3.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	double temp=((double)((JSlider) e.getSource()).getValue());
		    	Transform3D tr = new Transform3D();
		    	TransformGroup objtr=(TransformGroup)hm.get("atom3");
		    	objtr.getTransform(tr);
		    	//radius[4]=(1+temp*0.1);
		    	center_x[3]=temp*0.01;
		    //	System.out.println(center_x[2]);
		    	tr.setTranslation(new Vector3d(center_x[3],center_y[3],0));
		     	objtr.setTransform(tr);
		     	enableStage(5);
		    }
	 });
     center3.setBackground(bk);
     temp.add(center3);
     fifth.add(temp);

     temp=new JPanel(new java.awt.GridLayout(1,2,0,0));
	 temp.setBackground(bk);
	 lbl = new JLabel("Fourth Atom", JLabel.LEFT);
     temp.add(lbl);
     center4 = new JSlider(JSlider.HORIZONTAL,  -10,10 ,0);
     center4.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	double temp=((double)((JSlider) e.getSource()).getValue());
		    	Transform3D tr = new Transform3D();
		    	TransformGroup objtr=(TransformGroup)hm.get("atom4");
		    	objtr.getTransform(tr);
		    	//radius[4]=(1+temp*0.1);
		    	center_x[4]=temp*0.01;
		    //	System.out.println(center_x[2]);
		    	tr.setTranslation(new Vector3d(center_x[4],center_y[4],0));
		     	objtr.setTransform(tr);
		    }
	 });
     center4.setBackground(bk);
     temp.add(center4);
     //fifth.add(temp);

	 
	 
	 
	 
	 
	 
	 
	 
	 fourth=new JPanel(new java.awt.GridLayout(2,2,0,0));
	 //temp= new JPanel(new java.awt.GridLayout(4,1,0,0));
 	 diatomic = new JButton("Diatomic");
 	/* diatomic.addChangeListener(new ChangeListener() {
	    public void stateChanged(ChangeEvent e) {
	    	number_of_atoms=2;
	    	enableStage(1);
	    	}
     });*/
 	 diatomic.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
        	 number_of_atoms=2;
 	    	enableStage(1);
         }
     });


     diatomic.setEnabled(true);
     fourth.add(diatomic);
     
     triatomic = new JButton("Triatomic");
     triatomic.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
        	 number_of_atoms=2;
 	    	enableStage(2);
 	     }
     });
     triatomic.setEnabled(true);
     fourth.add(triatomic);
     

     quadatomic = new JButton("PolyAtomic");
     quadatomic.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
        	 number_of_atoms=2;
 	    	enableStage(3);
         }
     });
     quadatomic.setEnabled(true);
     fourth.add(quadatomic);
     
     Start_experiment = new JButton("Start Expe.");
     Start_experiment.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
        	 number_of_atoms=2;
 	    	enableStage(9);
 	     }
     });
     Start_experiment.setEnabled(true);
     fourth.add(Start_experiment);
     

     
	 
	 bottomPanel.add(first);
	 bottomPanel.add(fifth);
	 bottomPanel.add(second);
	 bottomPanel.add(third);
	 bottomPanel.add(fourth);   
	 
//	 enableStage(-2);
	 bottomPanel.setVisible(true);
	 hm.put("BottomPanel",bottomPanel);
	    
    }
    
    private void initInputControlsField() {
	}

	private  void enable(Container root, boolean enable) {
	    Component children[] = root.getComponents();
	    for(int i = 0; i < children.length; i++) 
			    children[i].setEnabled(enable);
    }
    
  
    
    private void onNextStage()
    {
    	    	
    	valChange = true; // Clear the graph. or Graph will restart on Play 
    	
    	
    	//Energy.clearGraphValue();
	   // RightPendulamGraph.clearGraphValue();
//    	resetOutputParameters(); // Clear the Output Parameters
  //  	enableStage(stage);
    	bottomPanel.setVisible(true);
    	JPanel  ioparm=(JPanel)hm.get("inputOutputPanel");
    	ioparm.setVisible(true);
    	
    	JPanel  ioparm1=(JPanel)hm.get("ButtonPanel");
    	ioparm1.setVisible(false);
    	
    	setInstructionText();

   }
    
    private void ReadMolecule()
    {
    	
    }
    
    private void enableStage(int s){
    /*	
    	diatomic.setEnabled(false);
		triatomic.setEnabled(false);
		quadatomic.setEnabled(false);
		slider_x.setEnabled(true);
		slider_y.setEnabled(true);
		atom_first.setEnabled(true);
		atom_second.setEnabled(true);
		atom_third.setEnabled(true);
		atom_fourth.setEnabled(true);
		value_12.setEnabled(true);
		value_23.setEnabled(true);
		value_34.setEnabled(true);
		distance_12.setEnabled(true);
		distance_23.setEnabled(true);
		distance_34.setEnabled(true);
		*/
    	if(s==1)// for diatomic molecule
    	{
    		center2.setEnabled(true);
    		center3.setEnabled(false);
    		center4.setEnabled(false);
    		diatomic.setEnabled(true);
    		triatomic.setEnabled(true);
    		quadatomic.setEnabled(true);
    		slider_x.setEnabled(false);
    		slider_y.setEnabled(false);
    		atom_first.setEnabled(true);
    		atom_second.setEnabled(true);
    		atom_third.setEnabled(false);
    		atom_fourth.setEnabled(false);
    		value_12.setEnabled(true);
    		value_23.setEnabled(false);
    		value_34.setEnabled(false);
    		distance_12.setEnabled(true);
    		distance_23.setEnabled(false);
    		distance_34.setEnabled(false);
    		Transform3D tr;
    		TransformGroup objtr;
    		tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("atom3");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
        	objtr.setTransform(tr);
        	
            
            tr = new Transform3D();
            objtr=(TransformGroup)hm.get("atom4");
            objtr.getTransform(tr);
            tr.setScale(new Vector3d(0,0,0));
            objtr.setTransform(tr);
         
            
            //  Second Bond	
            	tr = new Transform3D();
            	objtr=(TransformGroup)hm.get("bond2");
            	objtr.getTransform(tr);
            	tr.setScale(new Vector3d(0,0,0));
            	objtr.setTransform(tr);
            //  Third Bond	
            	tr = new Transform3D();
            	objtr=(TransformGroup)hm.get("bond3");
            	objtr.getTransform(tr);
            	tr.setScale(new Vector3d(0,0,0));
            	objtr.setTransform(tr);
            	
            	
            	tr = new Transform3D();
            	objtr=(TransformGroup)hm.get("support2");
            	objtr.getTransform(tr);
             	tr.setTranslation(new Vector3d(center_x[2],center_y[2]+0.08/2,0));
            	objtr.setTransform(tr);
            	
            	
                
            	
             	tr = new Transform3D();
            	objtr=(TransformGroup)hm.get("magneticbead");
            	objtr.getTransform(tr);
            	tr.setTranslation(new Vector3d(center_x[2],center_y[2]+0.08,0));
            	objtr.setTransform(tr);
            	//magneticbead_x=center_x[3];
            	//magneticbead_y=center_y[3]+0.08;

        	
        	number_of_atoms=2;
        
    	}
    	else if(s==2)// for triatomic
    	{
    		center2.setEnabled(true);
    		center3.setEnabled(true);
    		center4.setEnabled(false);
    		diatomic.setEnabled(true);
    		triatomic.setEnabled(true);
    		quadatomic.setEnabled(true);
    		slider_x.setEnabled(false);
    		slider_y.setEnabled(false);
    		atom_first.setEnabled(true);
    		atom_second.setEnabled(true);
    		atom_third.setEnabled(true);
    		atom_fourth.setEnabled(false);
    		value_12.setEnabled(true);
    		value_23.setEnabled(true);
    		value_34.setEnabled(false);
    		distance_12.setEnabled(true);
    		distance_23.setEnabled(true);
    		distance_34.setEnabled(false);
    		
    		Transform3D tr;
    		TransformGroup objtr;
    		tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("atom3");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(1,1,1));
        	tr.setTranslation(new Vector3d(center_x[3],center_y[3],0));
        	objtr.setTransform(tr);
        
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("atom4");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
        	objtr.setTransform(tr);
        
        //  Second Bond	
        	double temp=(center_x[2]-center_x[3])*(center_x[2]-center_x[3]) +(center_y[2]-center_y[3])*(center_y[2]-center_y[3]);
        	bond_center_x[2]=(center_x[2]+center_x[3])/2;
        	bond_center_y[2]=(center_y[2]+center_y[3])/2;
        	bond_length[2]=Math.sqrt(temp);
        	bond_rotation[2]=Math.atan((center_y[3]-center_y[2])/(center_x[3]-center_x[2]));
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("bond2");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(1,bond_length[2],1));
        	tr.setRotation(new AxisAngle4d(0,0,1,(Math.PI/2)+bond_rotation[2]));
        	tr.setTranslation(new Vector3d(bond_center_x[2], bond_center_y[2] , 0));
        	objtr.setTransform(tr);
        
        	//  Third Bond	
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("bond3");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
        	objtr.setTransform(tr);
        	
         	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("support2");
        	objtr.getTransform(tr);
         	tr.setTranslation(new Vector3d(center_x[3],center_y[3]+0.08/2,0));
        	objtr.setTransform(tr);
        	
        	
         	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("magneticbead");
        	objtr.getTransform(tr);
        	tr.setTranslation(new Vector3d(center_x[3],center_y[3]+0.08,0));
        	objtr.setTransform(tr);
        	//magneticbead_x=center_x[3];
        	//magneticbead_y=center_y[3]+0.08;

    		
    		number_of_atoms=3;
    		
    	}
    	else if(s==3)// for PolyAtomic
    	{
    		center2.setEnabled(false);
    		center3.setEnabled(false);
    		center4.setEnabled(false);
    		diatomic.setEnabled(false);
    		triatomic.setEnabled(false);
    		quadatomic.setEnabled(false);
    		slider_x.setEnabled(false);
    		slider_y.setEnabled(false);
    		atom_first.setEnabled(false);
    		atom_second.setEnabled(false);
    		atom_third.setEnabled(false);
    		atom_fourth.setEnabled(false);
    		value_12.setEnabled(false);
    		value_23.setEnabled(false);
    		value_34.setEnabled(false);
    		distance_12.setEnabled(false);
    		distance_23.setEnabled(false);
    		distance_34.setEnabled(false);
    		Start_experiment.setEnabled(true);
    
    
    		number_of_atoms=4;
    	
    		Transform3D tr;
    		TransformGroup objtr;
    		tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("atom1");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
  //      	tr.setTranslation(new Vector3d(center_x[3],center_y[3],0));
        	objtr.setTransform(tr);
        
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("atom2");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
  //      	tr.setTranslation(new Vector3d(center_x[3],center_y[3],0));
        	objtr.setTransform(tr);
        
  
    		
    		tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("atom3");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
  //      	tr.setTranslation(new Vector3d(center_x[3],center_y[3],0));
        	objtr.setTransform(tr);
        
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("atom4");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
        //	tr.setTranslation(new Vector3d(center_x[4],center_y[4],0));
        	objtr.setTransform(tr);
  
          	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("bond1");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
        //	tr.setTranslation(new Vector3d(center_x[4],center_y[4],0));
        	objtr.setTransform(tr);
  
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("bond2");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
      //  	tr.setTranslation(new Vector3d(center_x[4],center_y[4],0));
        	objtr.setTransform(tr);
  
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("support1");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
    //    	tr.setTranslation(new Vector3d(center_x[4],center_y[4],0));
        	objtr.setTransform(tr);
  
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("support2");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
  //      	tr.setTranslation(new Vector3d(center_x[4],center_y[4],0));
        	objtr.setTransform(tr);
  
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("magneticbead");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
        //	tr.setTranslation(new Vector3d(center_x[4],center_y[4],0));
        	objtr.setTransform(tr);
  
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("magnet");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
        //	tr.setTranslation(new Vector3d(center_x[4],center_y[4],0));
        	objtr.setTransform(tr);
  
          	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("plane");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
  //      	tr.setTranslation(new Vector3d(center_x[3],center_y[3],0));
        	objtr.setTransform(tr);
      
        	
        	
        //	TransformGroup objtr;
            for(int k=0;k<poly_count;k++)
            {
            	tr = new Transform3D();
            	objtr=(TransformGroup)hm.get("molecule"+k);
            	objtr.getTransform(tr);
            	tr.setScale(new Vector3d(1,1,1));
            	tr.setTranslation(new Vector3d(poly_centers[k][0],poly_centers[k][1],poly_centers[k][2]));
        //    	System.out.println(poly_centers[k][0]+ " "+ poly_centers[k][1]+ " "+ poly_centers[k][2]);
            	objtr.setTransform(tr);
            }
            //int k=0;
            for(int k=0;k<bond_count;k++) //###############
            {
            	
            	
            	int atom1=bond_between[k][0];
            	int atom2=bond_between[k][1];
            
           // 	System.out.println("bond Between " + atom1 + " and "+atom2);
            	
            	double d0=(poly_centers[atom1][0]-poly_centers[atom2][0])*(poly_centers[atom1][0]-poly_centers[atom2][0]);
            	double d1=(poly_centers[atom1][1]-poly_centers[atom2][1])*(poly_centers[atom1][1]-poly_centers[atom2][1]);
            	double d2=(poly_centers[atom1][2]-poly_centers[atom2][2])*(poly_centers[atom1][2]-poly_centers[atom2][2]);
            	
            	
            	double bond_length=Math.sqrt(d0+d1+d2);
            
            	tr = new Transform3D();
            	objtr=(TransformGroup)hm.get("bond_between"+k);
            	objtr.getTransform(tr);
            	
            	//a × b = (a2b3 − a3b2) i + (a3b1 − a1b3) j + (a1b2 − a2b1) k = (a2b3 − a3b2, a3b1 − a1b3, a1b2 − a2b1).
            	//a.b
            	double a[]=new double[4];
            	double b[]=new double [4];
            	a[1]=0;
            	a[2]=1;
            	a[3]=0;
            	b[1]=poly_centers[atom2][0]-poly_centers[atom1][0];
            	b[2]=poly_centers[atom2][1]-poly_centers[atom1][1];
            	b[3]=poly_centers[atom2][2]-poly_centers[atom1][2];
            	
            	double perp_x=a[2]*b[3]-a[3]*b[2];
            	double perp_y=a[3]*b[1]-a[1]*b[3];
            	double perp_z=a[1]*b[2]-a[2]*b[1];
            	
            	double mod_a=Math.sqrt((a[1]*a[1]+a[2]*a[2]+a[3]*a[3]));
            	double mod_b=Math.sqrt((b[1]*b[1]+b[2]*b[2]+b[3]*b[3]));
            	double a_dot_b=a[1]*b[1]+a[2]*b[2]+a[3]*b[3];
            	double angle =Math.acos(a_dot_b/(mod_a*mod_b));
            	
            	tr.setScale(new Vector3d(1,bond_length,1));
            	tr.setRotation(new AxisAngle4d(perp_x,perp_y,perp_z,angle));
            	tr.setTranslation(new Vector3d((poly_centers[atom2][0]+poly_centers[atom1][0])/2, (poly_centers[atom2][1]+poly_centers[atom1][1])/2 ,(poly_centers[atom2][2]+poly_centers[atom1][2])/2));
            	objtr.setTransform(tr);
            	
            	//System.out.println(bond_between[0][0]);
            	//tr.setScale(new Vector3d(1,1,1));
            	//tr.setTranslation(new Vector3d(poly_centers[k][0],poly_centers[k][1],poly_centers[k][2]));
            	//objtr.setTransform(tr);
            }
 
         	           
    		number_of_atoms=4;
    	}
    	
    	else if(s==9)// for Start_Experiment
    	{
    		center2.setEnabled(false);
    		center3.setEnabled(false);
    		center4.setEnabled(false);
    		diatomic.setEnabled(false);
    		triatomic.setEnabled(false);
    		quadatomic.setEnabled(false);
    		slider_x.setEnabled(false);
    		slider_y.setEnabled(false);
    		atom_first.setEnabled(false);
    		atom_second.setEnabled(false);
    		atom_third.setEnabled(false);
    		atom_fourth.setEnabled(false);
    		value_12.setEnabled(false);
    		value_23.setEnabled(false);
    		value_34.setEnabled(false);
    		distance_12.setEnabled(false);
    		distance_23.setEnabled(false);
    		distance_34.setEnabled(false);
    		Start_experiment.setEnabled(false);
    		number_of_atoms=4;
    	
    		Transform3D tr;
    		TransformGroup objtr;
    		tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("atom1");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
  //      	tr.setTranslation(new Vector3d(center_x[3],center_y[3],0));
        	objtr.setTransform(tr);
        
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("atom2");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
  //      	tr.setTranslation(new Vector3d(center_x[3],center_y[3],0));
        	objtr.setTransform(tr);
        
  
    		
    		tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("atom3");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
  //      	tr.setTranslation(new Vector3d(center_x[3],center_y[3],0));
        	objtr.setTransform(tr);
        
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("atom4");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
        //	tr.setTranslation(new Vector3d(center_x[4],center_y[4],0));
        	objtr.setTransform(tr);
  
          	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("bond1");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
        //	tr.setTranslation(new Vector3d(center_x[4],center_y[4],0));
        	objtr.setTransform(tr);
  
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("bond2");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
      //  	tr.setTranslation(new Vector3d(center_x[4],center_y[4],0));
        	objtr.setTransform(tr);
  
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("support1");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(1,1,1));
        	tr.setTranslation(new Vector3d(0.4,0,0));
        	objtr.setTransform(tr);
  
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("support2");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(1,1,1));
        	tr.setTranslation(new Vector3d(-0.4,0,0));
        	objtr.setTransform(tr);
  
        	
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("magnet");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(1,1,1));
        	//tr.setTranslation(new Vector3d(0,0,0));
        	objtr.setTransform(tr);
  

        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("plane1");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(1,1,0.4));
        	tr.setTranslation(new Vector3d(0,-0.34,0));
        	objtr.setTransform(tr);
 
        	

         	topmost_atom_coordinates[0]=poly_centers[0][0];
        	topmost_atom_coordinates[1]=poly_centers[0][1];
        	topmost_atom_coordinates[2]=poly_centers[0][2];
        	lowest_atom_coordinates[0]=poly_centers[0][0];
        	lowest_atom_coordinates[1]=poly_centers[0][1];
        	lowest_atom_coordinates[2]=poly_centers[0][2];
            for(int k=0;k<poly_count;k++)
            {
            	if(poly_type[k].equals("H"))
            	{
            		continue;
            	}
            	if(poly_centers[k][1] > topmost_atom_coordinates[1] )
            	{
            		topmost_atom=k;
            		topmost_atom_coordinates[0]=poly_centers[k][0];
                	topmost_atom_coordinates[1]=poly_centers[k][1];
                	topmost_atom_coordinates[2]=poly_centers[k][2];
            	}
            	if(poly_centers[k][1] < lowest_atom_coordinates[1] )
            	{
            		lowest_atom=k;
            		lowest_atom_coordinates[0]=poly_centers[k][0];
            		lowest_atom_coordinates[1]=poly_centers[k][1];
            		lowest_atom_coordinates[2]=poly_centers[k][2];
            	}
  //          	tr.setTranslation(new Vector3d(poly_centers[k][0],poly_centers[k][1],poly_centers[k][2]));
            }
 
          	net_translation=-0.535;
            //	System.out.println("NET Translation is" + 0.54);
          	double displacement= -0.54 -(lowest_atom_coordinates[1]* 0.6 - 0.04);
          	//displacement=0;
          	System.out.println("Displacement is " +displacement);
            	tr = new Transform3D();
            	objtr=(TransformGroup)hm.get("support2");
            	objtr.getTransform(tr);
            	tr.setScale(new Vector3d(0.5,1,0.5));
            	tr.setTranslation(new Vector3d(lowest_atom_coordinates[0]*0.6,lowest_atom_coordinates[1]* 0.6 -0.04 + displacement,lowest_atom_coordinates[2]*0.6));
            	objtr.setTransform(tr);
           
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("poly_atomic");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0.6,0.6,0.6));
        	tr.setTranslation(new Vector3d(0,displacement,0));
        	objtr.setTransform(tr);
 
      
          	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("support1");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0.5,1,0.5));
        	tr.setTranslation(new Vector3d(topmost_atom_coordinates[0]*0.6,topmost_atom_coordinates[1]*0.6+0.04+displacement,topmost_atom_coordinates[2]*0.6));
        	objtr.setTransform(tr);
  
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("magneticbead");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(1,1,1));
          	tr.setTranslation(new Vector3d(topmost_atom_coordinates[0]*0.6,topmost_atom_coordinates[1]*0.6+0.08+displacement,topmost_atom_coordinates[2]*0.6));
        	objtr.setTransform(tr);
  

        	
        	
        	
      	}
  
    	
    	else if (s==4)// Second Atom center Adjusting
    	{

    		double temp_atom_x=center_x[2];
    		double temp_atom_y=center_y[2];
        	double temp=(center_x[1]-temp_atom_x)*(center_x[1]-temp_atom_x) +(center_y[1]-temp_atom_y)*(center_y[1]-temp_atom_y);
        	bond_center_x[1]=(center_x[1]+temp_atom_x)/2;
        	bond_center_y[1]=(center_y[1]+temp_atom_y)/2;
        	bond_length[1]=Math.sqrt(temp);
        	bond_rotation[1]=Math.atan((temp_atom_y-center_y[1])/(temp_atom_x-center_x[1]));
        	Transform3D tr = new Transform3D();
        	TransformGroup objtr=(TransformGroup)hm.get("bond1");
        //	objtr.addChild(m_j3d.createCylinder(0.01f,(float)bond_length[1], new Vector3d(0,0,0), new Vector3d(1,1,1),new Vector3d(0,0,0), new Color3f(0,1,0), "hello",hm));
        	objtr.getTransform(tr);
        	//tr.setScale(new Vector3d(1,bond_length[1],1));
        	tr.setRotation(new AxisAngle4d(0,0,1,(Math.PI/2)+bond_rotation[1]));
        	tr.setTranslation(new Vector3d(bond_center_x[1], bond_center_y[1] , 0));
        	objtr.setTransform(tr);
        	
        	
        if(number_of_atoms>2)
        {
        	temp_atom_x=center_x[3];
    		temp_atom_y=center_y[3];
        	temp=(center_x[2]-temp_atom_x)*(center_x[2]-temp_atom_x) +(center_y[2]-temp_atom_y)*(center_y[2]-temp_atom_y);
        	bond_center_x[2]=(center_x[2]+temp_atom_x)/2;
        	bond_center_y[2]=(center_y[2]+temp_atom_y)/2;
        	bond_length[2]=Math.sqrt(temp);
        	bond_rotation[2]=Math.atan((temp_atom_y-center_y[2])/(temp_atom_x-center_x[2]));
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("bond2");
        //	objtr.addChild(m_j3d.createCylinder(0.01f,(float)bond_length[1], new Vector3d(0,0,0), new Vector3d(1,1,1),new Vector3d(0,0,0), new Color3f(0,1,0), "hello",hm));
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(1,bond_length[2],1));
        	tr.setRotation(new AxisAngle4d(0,0,1,(Math.PI/2)+bond_rotation[2]));
        	tr.setTranslation(new Vector3d(bond_center_x[2], bond_center_y[2] , 0));
        	objtr.setTransform(tr);
        	
        }	
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("support2");
        	objtr.getTransform(tr);
         	tr.setTranslation(new Vector3d(center_x[number_of_atoms],center_y[number_of_atoms]+0.08/2,0));
        	objtr.setTransform(tr);
        	
        	
         	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("magneticbead");
        	objtr.getTransform(tr);
        	tr.setTranslation(new Vector3d(center_x[number_of_atoms],center_y[number_of_atoms]+0.08,0));
        	objtr.setTransform(tr);

        	
        	
    	}
    	
    	else if (s==5)// Third Atom center Adjusting
    	{
    		double temp_atom_x=center_x[3];
    		double temp_atom_y=center_y[3];
        	double temp=(center_x[2]-temp_atom_x)*(center_x[2]-temp_atom_x) +(center_y[2]-temp_atom_y)*(center_y[2]-temp_atom_y);
        	bond_center_x[2]=(center_x[2]+temp_atom_x)/2;
        	bond_center_y[2]=(center_y[2]+temp_atom_y)/2;
        	bond_length[2]=Math.sqrt(temp);
        	bond_rotation[2]=Math.atan((temp_atom_y-center_y[2])/(temp_atom_x-center_x[2]));
        	Transform3D tr = new Transform3D();
        	TransformGroup objtr=(TransformGroup)hm.get("bond2");
        //	objtr.addChild(m_j3d.createCylinder(0.01f,(float)bond_length[1], new Vector3d(0,0,0), new Vector3d(1,1,1),new Vector3d(0,0,0), new Color3f(0,1,0), "hello",hm));
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(1,bond_length[2],1));
        	tr.setRotation(new AxisAngle4d(0,0,1,(Math.PI/2)+bond_rotation[2]));
        	tr.setTranslation(new Vector3d(bond_center_x[2], bond_center_y[2] , 0));
        	objtr.setTransform(tr);
        	
        	
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("support2");
        	objtr.getTransform(tr);
         	tr.setTranslation(new Vector3d(center_x[number_of_atoms],center_y[number_of_atoms]+0.08/2,0));
        	objtr.setTransform(tr);
        	
        	
         	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("magneticbead");
        	objtr.getTransform(tr);
        	tr.setTranslation(new Vector3d(center_x[number_of_atoms],center_y[number_of_atoms]+0.08,0));
        	objtr.setTransform(tr);
        	
    	}

    	else if(s==10)//start
    	{
    		diatomic.setEnabled(false);
    		triatomic.setEnabled(false);
    		quadatomic.setEnabled(false);
    		slider_x.setEnabled(true);
    		slider_y.setEnabled(true);
    		atom_first.setEnabled(false);
    		atom_second.setEnabled(false);
    		atom_third.setEnabled(false);
    		atom_fourth.setEnabled(false);
    		value_12.setEnabled(false);
    		value_23.setEnabled(false);
    		value_34.setEnabled(false);
    		distance_12.setEnabled(false);
    		distance_23.setEnabled(false);
    		distance_34.setEnabled(false);
    		center2.setEnabled(false);
    		center3.setEnabled(false);
    		center4.setEnabled(false);
    		Viscosity.setEnabled(true);
    	}
    	/*switch(s){
    	case -2: enable(first ,false);  enable(second ,false); enable(third,false);  break;	
    	case -1:   enable(first ,false);  enable(second ,false);  break;			
    	case 0:   		enable(first ,true);  enable(second ,true);  break;
    	case 1:    	
    		enable(first,true); 
    		enable(second,true); 
    		
    		nextButton.setVisible(false); break;		
    			
    	case 2:
    		stage =0;
    		break;
    	case 10: 
    	}
    	*/
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
       
    // Resume Button Action
    private void startSimulation(java.awt.event.ActionEvent evt)
    {
    	    	
    	ImageIcon icon = m_j3d.createImageIcon("stop.png"); 
    	startButton.setIcon(icon);
    	startButton.setText("Stop");        
        timer.start();
        System.out.println("Timer started");
              
    }
    
  

    private void timerActionPerformed(java.awt.event.ActionEvent evt)
    {
    	//System.out.println(magnet_y_translate);
    //	double bond_length=1;
   
    	/*
    	Energy_x+=0.02;
    	Energy_y+=0.02;
    	Energy.setState(1);
    	Energy.setCurrentValue((float)Energy_x,(float)(Energy_y*Energy_y)/10);        
        Energy.addGraphValue((float)(Energy_y*Energy_y));//-freeBody.getDisplacment()*500));
        Energy.drawGraph();
        Energy.setYScale(10);
        //Energy.setXOffset((int)Energy_x);
        
        */
    	//System.out.println("hello" + magnet_y_translate);
   /* 	double temp_r=(1+magnet_y_translate)*r0[1];
    	double temp_y=(0.5*k[1]*(temp_r-r0[1])*(temp_r-r0[1]));
    	Energy.setState(1);
    	Energy.setCurrentValue((float)temp_r,(float)temp_y);        
        Energy.addGraphValue((float)temp_y);//-freeBody.getDisplacment()*500));
        Energy.drawGraph();
        Energy.setYScale(10);
        */
      //  System.out.println("engergy " +0.5*k[1]*(temp_r-r0[1])*(temp_r-r0[1]));
      
   /*     temp_r=(1+magnet_y_translate)*r0[1];
    	temp_y=-1*k[1]*(temp_r-r0[1]);
    	Force.setState(1);
    	Force.setCurrentValue((float)temp_r,(float)temp_y);        
        Force.addGraphValue((float)temp_y);//-freeBody.getDisplacment()*500));
        Force.drawGraph();
        Force.setYScale(10);
    */  
        
        Transform3D tr;
        TransformGroup objtr;
/*
     	Transform3D tr = new Transform3D();
    	TransformGroup objtr=(TransformGroup)hm.get("magnet");
    	objtr.getTransform(tr);
   // 	tr.rotZ(Math.toRadians(x));
   // 	tr.setScale(new Vector3d(x,y,1));
    	objtr.setTransform(tr);
  */ 
        
      //  System.out.println("CURRENT VISCOSITY IS "+current_viscosity);
        if(number_of_atoms==4)
        {
        
        	//Scaling=Scaling + 0.01;
          	Scaling=0.5-(magnet_y_translate/4);
          			if(magnet_y_translate > 0)Scaling+=current_viscosity/150.0;
          			else
          				Scaling-=current_viscosity/150.0;
          	//System.out.println("MAGNET Y TRANSLATE "+ magnet_y_translate);
          	double temp_Scaling=0.5+(magnet_y_translate/4);
          	if(magnet_y_translate >0)temp_Scaling-=current_viscosity/150.0;
          	else
          		temp_Scaling+=current_viscosity/150.0;
          	System.out.println("SCALING IS "+Scaling + " "+ temp_Scaling + " "+current_viscosity);
        	net_translation=-0.535;
            //	System.out.println("NET Translation is" + 0.54);
          	double displacement= -0.535 -(lowest_atom_coordinates[1]* Scaling - 0.04);
          	
          	tr = new Transform3D();
            objtr=(TransformGroup)hm.get("support2");
            objtr.getTransform(tr);
            tr.setScale(new Vector3d(0.5,1,0.5));
            tr.setTranslation(new Vector3d(lowest_atom_coordinates[0]*temp_Scaling,lowest_atom_coordinates[1]* Scaling -0.04 + displacement,lowest_atom_coordinates[2]*temp_Scaling));
            objtr.setTransform(tr);
           
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("poly_atomic");
        	objtr.getTransform(tr);
        	//tr.setScale(new Vector3d(Scaling,Scaling,Scaling));
        	tr.setScale(new Vector3d(temp_Scaling,Scaling,temp_Scaling));
        	tr.setTranslation(new Vector3d(0,displacement,0));
        	objtr.setTransform(tr);
 
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("support1");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0.5,1,0.5));
        	tr.setTranslation(new Vector3d(topmost_atom_coordinates[0]*temp_Scaling,topmost_atom_coordinates[1]*Scaling+0.04+displacement,topmost_atom_coordinates[2]*temp_Scaling));
        	objtr.setTransform(tr);
  
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("magneticbead");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(1,1,1));
          	tr.setTranslation(new Vector3d(topmost_atom_coordinates[0]*temp_Scaling,topmost_atom_coordinates[1]*Scaling+0.08+displacement,topmost_atom_coordinates[2]*temp_Scaling));
        	objtr.setTransform(tr);
        	return;
        }
  
      //double bond_length=1;
      double atom_radius1=0.02;
      double atom_radius2=0.02;
      double support1_length=.1;
      double magneticbead_radius=0.03;
      double disp_x=0;
      double disp_y=0;
     // double scale_x=1/(1-magnet_y_translate);
      double scale_x=(1+magnet_y_translate);
      if(magnet_y_translate>0)
      {
    	  scale_x-=current_viscosity/150.0;
      }
      else
      {
    	  scale_x+=current_viscosity/150.0;
      }
      double scale_y=(1-magnet_y_translate);
      if(magnet_y_translate >0 )
      {
    	  	scale_y+=current_viscosity/150.0;
      }
      // 	  scale_y+=current_viscosity/150.0);
      else
      {
    	  	scale_y-=current_viscosity/150.0;
      }
      double scale_z;

     	tr = new Transform3D();
    	objtr=(TransformGroup)hm.get("atom1");
    	objtr.getTransform(tr);
    	objtr.setTransform(tr);
     	
     	double temp_atom_x[]=new double[5];
     	double temp_atom_y[]=new double[5];
     	disp_x=center_x[2]-center_x[1];
     	disp_y=center_y[2]-center_y[1];
     	tr = new Transform3D();
    	objtr=(TransformGroup)hm.get("atom2");
    	objtr.getTransform(tr);
    	tr.setTranslation(new Vector3d(center_x[1]+disp_x*scale_x,center_y[1]+disp_y*scale_y,0));
    	objtr.setTransform(tr);
    	temp_atom_x[2]=center_x[1]+disp_x*scale_x;
    	temp_atom_y[2]=center_y[1]+disp_y*scale_y;
     	
     	
     	
   
    	double temp=(center_x[1]-temp_atom_x[2])*(center_x[1]-temp_atom_x[2]) +(center_y[1]-temp_atom_y[2])*(center_y[1]-temp_atom_y[2]);
    	bond_center_x[1]=(center_x[1]+temp_atom_x[2])/2;
    	bond_center_y[1]=(center_y[1]+temp_atom_y[2])/2;
    	bond_length[1]=Math.sqrt(temp);
    	bond_rotation[1]=Math.atan((temp_atom_y[2]-center_y[1])/(temp_atom_x[2]-center_x[1]));
    	tr = new Transform3D();
    	objtr=(TransformGroup)hm.get("bond1");
    //	objtr.addChild(m_j3d.createCylinder(0.01f,(float)bond_length[1], new Vector3d(0,0,0), new Vector3d(1,1,1),new Vector3d(0,0,0), new Color3f(0,1,0), "hello",hm));
    	objtr.getTransform(tr);
    	//tr.setScale(new Vector3d(1,bond_length[1],1));
    	tr.setScale(new Vector3d(1/(scale_y),bond_length[1],1));
   // 	System.out.println(1/scale_y);
    	tr.setRotation(new AxisAngle4d(0,0,1,(Math.PI/2)+bond_rotation[1]));
    	tr.setTranslation(new Vector3d(bond_center_x[1], bond_center_y[1] , 0));
    	objtr.setTransform(tr);
    	//System.out.println(bond_length[1]);
     	
     	
    	
    	
    	if(number_of_atoms>2)
    	{
    		disp_x=center_x[3]-center_x[1];
         	disp_y=center_y[3]-center_y[1];
         	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("atom3");
        	objtr.getTransform(tr);
        	tr.setTranslation(new Vector3d(center_x[1]+disp_x*scale_x,center_y[1]+disp_y*scale_y,0));
        	objtr.setTransform(tr);
        	temp_atom_x[3]=center_x[1]+disp_x*scale_x;
        	temp_atom_y[3]=center_y[1]+disp_y*scale_y;
  
          	temp=(temp_atom_x[2]-temp_atom_x[3])*(temp_atom_x[2]-temp_atom_x[3]) +(temp_atom_y[2]-temp_atom_y[3])*(temp_atom_y[2]-temp_atom_y[3]);
        	bond_center_x[2]=(temp_atom_x[2]+temp_atom_x[3])/2;
        	bond_center_y[2]=(temp_atom_y[2]+temp_atom_y[3])/2;
        	bond_length[2]=Math.sqrt(temp);
        	bond_rotation[2]=Math.atan((temp_atom_y[3]-temp_atom_y[2])/(temp_atom_x[3]-temp_atom_x[2]));
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("bond2");
        //	objtr.addChild(m_j3d.createCylinder(0.01f,(float)bond_length[1], new Vector3d(0,0,0), new Vector3d(1,1,1),new Vector3d(0,0,0), new Color3f(0,1,0), "hello",hm));
        	objtr.getTransform(tr);
        	//tr.setScale(new Vector3d(1,bond_length[1],1));
        	tr.setScale(new Vector3d(1/(scale_y),bond_length[2],1));
       // 	System.out.println(1/scale_y);
        	tr.setRotation(new AxisAngle4d(0,0,1,(Math.PI/2)+bond_rotation[2]));
        	tr.setTranslation(new Vector3d(bond_center_x[2], bond_center_y[2] , 0));
        	objtr.setTransform(tr);
    	}
    	
     	
     	tr = new Transform3D();
    	objtr=(TransformGroup)hm.get("support2");
    	objtr.getTransform(tr);
    	//tr.setScale(new Vector3d(1,bond_length,1));
    	tr.setTranslation(new Vector3d(temp_atom_x[number_of_atoms],temp_atom_y[number_of_atoms]+0.08/2,0));
     	objtr.setTransform(tr);
     	
     	tr = new Transform3D();
    	objtr=(TransformGroup)hm.get("magneticbead");
    	objtr.getTransform(tr);
    	//tr.setScale(new Vector3d(1,bond_length,1));
    	tr.setTranslation(new Vector3d(temp_atom_x[number_of_atoms],temp_atom_y[number_of_atoms]+0.08,0));
     	objtr.setTransform(tr);
     	
  //   	bond_length=1;
     	//bond_length-=0.02;
    /*	if(bond_length<0.03)
     	{
     		bond_length=1;
     	}
     */	
     	if(magnet_y_translate > 0 )
     	{
     	//	System.out.println(bond_length);
    // 		bond_length=1-magnet_y_translate*10;
     		//System.out.println(bond_length);
     	}
     	else if(magnet_y_translate <0)
     	{	
     //		bond_length=1+magnet_y_translate*-2;
     	}
     	
     	
     	tr = new Transform3D();
    	objtr=(TransformGroup)hm.get("structure");
    	objtr.getTransform(tr);
   // 	tr.rotZ(Math.toRadians(270));
    	//tr.setScale(new Vector3d(1,0.4,1));
    	//tr.setTranslation(new Vector3d(0,-0.18,0));
     	objtr.setTransform(tr);
    
     	
     	// for decreasing size of support
     	double x=0.4;
     	tr = new Transform3D();
    	objtr=(TransformGroup)hm.get("support2");
    	objtr.getTransform(tr);
   // 	tr.rotZ(Math.toRadians(270));
    	//tr.setTranslation(new Vector3d(0,-0.18,0));
  //  	tr.setScale(new Vector3d(x,x,x));
     	objtr.setTransform(tr);
    
    /*	tr = new Transform3D();
    	objtr=(TransformGroup)hm.get("molecule");
    	objtr.getTransform(tr);
   // 	tr.rotZ(Math.toRadians(270));
    //	tr.setScale(new Vector3d(x,x,x));
    	tr.setTranslation(new Vector3d(0,-0.022,0));
     	objtr.setTransform(tr);
    */
   /*  	tr = new Transform3D();
    	objtr=(TransformGroup)hm.get("support1");
    	objtr.getTransform(tr);
   // 	tr.rotZ(Math.toRadians(270));
    	tr.setScale(new Vector3d(x,x,x));
    	tr.setTranslation(new Vector3d(0,-0.11,0));
     	objtr.setTransform(tr);
    */ 	
        return;            
    }
    
    private void pauseSimulation()
    {
    	
		timer.stop();
	//	enableStage(-2);   
		ImageIcon icon = m_j3d.createImageIcon("start.png"); 
    	startButton.setIcon(icon);
    	startButton.setText("Start");
    //	reStartButton.setEnabled(true);
         // bottomPanel.setVisible(true);
      //  nextButton.setEnabled(true);
    	
    	rightPanel.setVisible(true);
		//enableStage(stage);
		//Energy.setState(1);
	//	RightPendulamGraph.setState(1);
        //startButton.setEnabled(true);
		       
        valChange = false;
        //java.util.BitSet visibleNodes = new java.util.BitSet( 
	     // 		 switchGroup.numChildren() );
       // for(int i=0; i<4; i++) visibleNodes.set( i ); 
    	 
//	 	assign the visibility mask to the Switch
		 //switchGroup.setChildMask( visibleNodes );
        if(stage==1)
        {
        	
      //  Energy.clearGraphValue();
      //  	RightPendulamGraph.clearGraphValue();
        }
        
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
    
	public void getLength(int flag)
	{
		if(flag==0)
		{
	//		return fields[0]/LeftPendulamBody.LengthPrapotionalityConstant;
			
		}
		else
		{
//			return fields[4]/LeftPendulamBody.LengthPrapotionalityConstant;
		}
				// for Left Pendulam flag=0;
    }
	public float getMass(int flag)
	{
		if(flag==0)
		{
			return fields[1];
		}
		else
		{
			return fields[5];
		}
	}
	public double getGravity(int flag)
	{
		if(flag==0)
		{
			return fields[2];
		}
		else
		{
			return fields[6];
		}
	}
	public float getIntialPhase(int flag)
	{
		if(flag==0)
		{
			return fields[3];
		}
		else
		{
			return fields[7];
		}
		
	}
	
    
	class PendulumBody  {
		 
		  private TransformGroup TransGroup;
		  private Vector3d Position;
		  private double m_dt =0;
		  private float Mass=0;
		  private float TimeTaken=0;
		  private double Length=0;
		  private double Displacement=0;
		  private double Velocity=0;
		  private double Theta=0;
		  private double AngularFrequency=0;
		  private double TimePeriod=0;
		  private Vector3d Scale=new Vector3d(1,1,1);
		  private double Gravity =9.8;
		  private float IntialPhaseAngle=45;
		  private Vector3d SpherePosition;
		  private String SphereHashName;
		  private String LineHashName ;
		  private StopWatch stopwatch =new StopWatch();
		   private long StartTime = 0;
		  private long StopTime = 0;
		  private long ElapsedTime =0;
		  private double LengthPrapotionalityConstant=1/0.18;
		  private double MassPrapotionalityConstant=1/2.0;

		   

		  
		 
		  // Constructor
		  public PendulumBody(Vector3d aPos,TransformGroup aPendulamTransformGroup,float aMass,double pendulamLength,double aGravity,float aIntialPhaseAngle,String aSphereHashName,String aLineHashName) {
			    super();		    
			    Position = aPos;
			    TransGroup = aPendulamTransformGroup;
			    Mass = aMass;
			    Length = pendulamLength;
			    IntialPhaseAngle=aIntialPhaseAngle;
			    TimeTaken =0;
			    Theta=0;
			    SphereHashName=aSphereHashName;
			    LineHashName=aLineHashName;
			    Gravity=aGravity;
			    SpherePosition=new Vector3d(0,-Length,0);
			    this.SetOrientation(IntialPhaseAngle);
			    
	            
		  }
		 
		  public double getTimePeriod()
		  {
			
			  return TimePeriod; 
		  }

		public double getGravity() 
		{
			
			return Gravity;
		}

		public double getLength() {
			return Length*LengthPrapotionalityConstant;
			
		}

		// Initialization
		  public void init(double pendulamLength ,float aMass,float aIntialPhaseAngle,double aGravity)
		  {
			  stopwatch.start();
			  m_dt=0;
			  ElapsedTime =0;
			  TimeTaken = 0;
			  Mass = aMass;
			  Length = pendulamLength;
			  SpherePosition=new Vector3d(0,-Length,0);
			  this.UpdateThread();
			  this.UpdateSpherePosition();
			  this.UpdateSphereScale();
			  
			  
			  Gravity=aGravity;
			  AngularFrequency =Math.sqrt(Gravity/(Length*LengthPrapotionalityConstant)); //10*
			  IntialPhaseAngle =  aIntialPhaseAngle ; 
			  this.SetOrientation(IntialPhaseAngle);
			  Theta =IntialPhaseAngle;
			  TimePeriod = 2 * Math.PI * Math.sqrt( Length*LengthPrapotionalityConstant/Gravity);
			  
			 
			  
		  }
		   // Simulation
		  public void  UpdateSphereScale()
		  {
			  TransformGroup SphereTransGroup= (TransformGroup)hm.get(SphereHashName );
			  Transform3D trans = new Transform3D();
			  SphereTransGroup.getTransform(trans);
			  Vector3d s = new Vector3d();
		      trans.getScale(s );			
		      s.x=Math.pow(Mass*MassPrapotionalityConstant,1/3.0);
		      s.y=s.x;
		      s.z=s.x;
			  trans.setScale(s);
		
			  
			  SphereTransGroup.setTransform(trans);
			  
		  }
		  public void UpdateThread()
		  { 
			  Shape3D line = (Shape3D)hm.get(LineHashName);
			  line.setGeometry(m_j3d.createlinegeometry(new Point3d(0,0,0), new Point3d(0,-Length,0), new Color3f(1,1,1)));
			  
		  }
		  public void UpdateSpherePosition()
		  {
			  TransformGroup SphereTransGroup= (TransformGroup)hm.get(SphereHashName );
			  Transform3D trans = new Transform3D();
			  SphereTransGroup.getTransform(trans);
			  Vector3d s = new Vector3d();
		      trans.getScale(s );			
		
			  trans.setScale(s);
		
			  trans.setTranslation(SpherePosition); 
			  SphereTransGroup.setTransform(trans);
	     }
		 public double ElapsedTime ()
		 {
			 
			 return m_dt ; 
		 }
		 public void StopTime()
		 {
			 stopwatch.stop();
		 }
		 public void update(double dt)
		 {
			  m_dt=m_dt+dt;
			  Displacement = Theta * Math.cos(AngularFrequency * ElapsedTime () ) ;
			  Velocity = Math.sqrt(2*9.8*Length*LengthPrapotionalityConstant*(Math.cos(Displacement*Math.PI/180)- Math.cos(Theta*Math.PI/180)));
			 
		 }
		  
		  public void UpdateMotion(double deg)
		  {
			  Transform3D trans = new Transform3D();
			  TransGroup.getTransform(trans);
			  Vector3d s = new Vector3d();
		      trans.getScale(s );			
			  trans.rotZ(Math.toRadians(deg));
			  trans.setScale(s);
			  trans.setTranslation(Position); 
			  TransGroup.setTransform(trans);
			  
		  }
		  
		  // Get and set Methods
		  public void setTransformGroup(TransformGroup tgp) {TransGroup = tgp;}
		  
		  public double getDisplacementVal()
		  { 
		      Displacement = Theta * Math.cos(AngularFrequency * ElapsedTime () ) ;
		      
			  return Displacement;
		  }
		  
		  public double Time_Period()
		  {
			 return TimePeriod; 
		  }
		  
		  public double getMass()
		  {
			 return Mass; 
		  }
		  
		  
		  public double getVel()
		  {
			 return Velocity; 
		  }
		  public double getXdisp()
		  {
			 return Length*LengthPrapotionalityConstant*Math.sin(Displacement*Math.PI/180); 
		  }
		  public double getYdisp()
		  {
			  
			 return Length*LengthPrapotionalityConstant*(1 - Math.cos(Displacement*Math.PI/180));
			 
		  }
		  public double getDisplacment()
		  {
			 return getYdisp()*getYdisp() + getXdisp()*getXdisp(); 
		  }
		  
		 
		  
		  public void SetOrientation(float deg)
		  {
			  	 
			  Transform3D trans = new Transform3D();
			  TransGroup.getTransform(trans);
			  Vector3d s = new Vector3d();
			  trans.getScale(s );			
			  trans.rotZ(Math.toRadians(deg));
			  trans.setScale(s);
			  trans.setTranslation(Position); 
			  TransGroup.setTransform(trans); 
			  
		  }
		  public void Move(Vector3d position)
		  {
			  Transform3D trans = new Transform3D();
			  TransGroup.getTransform(trans);
			  Vector3d s = new Vector3d();
		      trans.getScale(s );			
			
			  trans.setScale(s);
			  trans.setTranslation(position); 
			  TransGroup.setTransform(trans);
		  }
		  public void SetLength(double length)
		  {
			  double actual_length= length/LengthPrapotionalityConstant;
			  Length=actual_length;
			  init(Length , Mass, IntialPhaseAngle,Gravity);
		 }
		  public void SetIntialPhase(float deg)
		  {
			  IntialPhaseAngle=deg;
			  init(Length , Mass, IntialPhaseAngle,Gravity);
		 }
		  public void SetMass(float  aMass)
		  {
			   Mass=aMass;
			   init(Length , Mass, IntialPhaseAngle,Gravity);
		 }
		  public void SetGravity(double aGravity)
		  {
			  Gravity=aGravity;
			  init(Length , Mass, IntialPhaseAngle,Gravity);
		 }
		 
		  public void Runninginit(double pendulamLength ,float aMass,float aIntialPhaseAngle,double aGravity)
		  {
			  stopwatch.start();
			  m_dt=0;
			  ElapsedTime =0;
			  TimeTaken = 0;
			  Mass = aMass;
			  Length = pendulamLength;
			  SpherePosition=new Vector3d(0,-Length,0);
			  this.UpdateThread();
			  this.UpdateSpherePosition();
			  this.UpdateSphereScale();
			  
			  
			  Gravity=aGravity;
			  AngularFrequency =Math.sqrt(Gravity/(Length*LengthPrapotionalityConstant)); //10*
			  IntialPhaseAngle =  aIntialPhaseAngle ; 
			  this.SetOrientation(IntialPhaseAngle);
			  Theta =IntialPhaseAngle;
			  TimePeriod = 2 * Math.PI * Math.sqrt( Length*LengthPrapotionalityConstant/Gravity);
			  
			 
			  
		  }
		  public double getKE()
		  {
			  return (1/2.0)*Mass*Velocity*Velocity;
		  }
		  public double getPE()
		  {
			  return (1/2.0)*Mass*Gravity*getYdisp();
		  }
		  
	}


	
	
    
}///////////////// Defination COmplete




