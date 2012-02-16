package ViscosEnvironment;

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
import java.io.FileReader;
import java.io.IOException;
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
import javax.media.j3d.GeometryArray;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.media.j3d.Group;
import javax.media.j3d.QuadArray;
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
import javax.vecmath.Color3b;
import javax.vecmath.Color3f;
import javax.vecmath.Color4b;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;


/**
 * Simple Java 3D program that can be run as an application or as an applet.
 */
public class Magnetic_Tweezer_with_Environment extends javax.swing.JPanel  implements MouseListener, MouseMotionListener,MouseWheelListener{
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
	//JButton diatomic;
	//JButton triatomic;
	JButton quadatomic;
	//JButton Start_experiment;
	
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
	
	

	int Total_cylinder=10;
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
	private static float magnet_y_translate=(float) 0.0;
//	private double bond_length=1;
	private double Energy_x=0;
	private double Energy_y=0;
	// Timer for simulation    
	
	

	private int stage = 0;
	private double zval=2.41;
	private double Pendulam_length=0.18;
	private float sphere_radius =0.015f;
	private String WallImage="wall3.jpg";
	
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
    			FileInputStream fstream = new FileInputStream("/home/abhishek/workspace/VPL/bin/iiit/vlabdemos/common/resources/icons/cholestrol.txt");
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
    		String data_file1 = "hexane.pdb";
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
                    //System.out.println("poly_type[i]");

                    String C="C";
    				String H="H";
    				String O="O";


    				if(poly_type[i].contains(C))
    				{
    					tr=new Transform3D();
    					TransformGroup temp1 = new TransformGroup(tr);
    					temp1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    					temp1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    					temp1.addChild(m_j3d.createSphere(new Vector3d(0,0,0), 0.015f, new Color3f(0f,0,0), new Color3f(0,0,0), new Color3f(0,0,0), new Color3f(0,0,0), 0f));
    			    	hm.put("molecule"+i, temp1);
    			    	PolyAtomic.addChild(temp1);
    				}
    				if(poly_type[i].contains(H))
    				{
    					tr=new Transform3D();
    					TransformGroup temp1 = new TransformGroup(tr);
    					temp1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    					temp1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    					temp1.addChild(m_j3d.createSphere(new Vector3d(0,0,0), 0.01f, new Color3f(0f,0,0), new Color3f(0,0,0), new Color3f(0,0,0), new Color3f(0,0,1), 0f));
    					hm.put("molecule"+i, temp1);
    					PolyAtomic.addChild(temp1);
    				
    				}
    				if(poly_type[i].contains(O))
    				{
    					tr=new Transform3D();
    					TransformGroup temp1 = new TransformGroup(tr);
    					temp1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    					temp1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    					temp1.addChild(m_j3d.createSphere(new Vector3d(0,0,0), 0.02f, new Color3f(0.2f,0,0), new Color3f(1,0,0), new Color3f(1,0,0), new Color3f(1,0,0), 1f));
    					hm.put("molecule"+i, temp1);
    					PolyAtomic.addChild(temp1);
    					
    				}
    				
    				temp_centers[i][0]=Double.parseDouble(tokens[5].trim());
    				temp_centers[i][1]=Double.parseDouble(tokens[6].trim());
    				temp_centers[i][2]=Double.parseDouble(tokens[7].trim());
    				//temp_centers[i][0]=Double.parseDouble(tokens[4].trim());
    				//temp_centers[i][1]=Double.parseDouble(tokens[5].trim());
    				//temp_centers[i][2]=Double.parseDouble(tokens[6].trim());
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
    				if(poly_type[k].contains("C") && poly_type[l].contains("C"))
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
    				else if((poly_type[k].contains("C") && poly_type[l].contains("O")) || (poly_type[k].contains("O") && poly_type[l].contains("C"))) 
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
    				else if((poly_type[k].contains("O") && poly_type[l].contains("H")) || (poly_type[k].contains("H") && poly_type[l].contains("O"))) 
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
    				else if((poly_type[k].contains("C") && poly_type[l].contains("H")) || (poly_type[k].contains("H") && poly_type[l].contains("C"))) 
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
		
		/*
		 * Adding Cylinder
		 */
		
		tr=new Transform3D();
    	TransformGroup medium_cylinder0 = new TransformGroup(tr);
    	medium_cylinder0.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	medium_cylinder0.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    //	medium_cylinder.addChild(m_j3d.createCylinder(0.51f,(float)0.528, new Vector3d(0.1,-0.25,0.1), new Vector3d(1,1,1),new Vector3d(0,0,0), new Color3f(1,0,0), "hello",hm));
    	
    	Appearance appearance = new Appearance();
    	TransparencyAttributes ta = new TransparencyAttributes();
    	ta.setTransparencyMode (ta.BLENDED);
    	ta.setTransparency(1f);
    	appearance.setTransparencyAttributes(ta);
    	
    	/* Defining the Cylinder*/
      Point3d p1 = new Point3d(-0.25, -0.45, 0);
  	  Point3d p2 = new Point3d(0.25, -0.45, 0);
  	  Point3d p3 = new Point3d(0.25, 0.4, 0);
  	  Point3d p4 = new Point3d(-0.25, 0.4, 0);

   	  //Color4b c = new Color4b((byte)255, (byte)0, (byte)0, (byte)255);
  	  Color3b c = new Color3b((byte)255, (byte)0, (byte)0);

  	  QuadArray quads = new QuadArray(4,GeometryArray.COORDINATES | GeometryArray.COLOR_3);
  	    // GeometryArray.COORDINATES | GeometryArray.COLOR_3);

  	  quads.setCoordinate(0, p1);
  	  quads.setCoordinate(1, p2);
  	  quads.setCoordinate(2, p3);
  	  quads.setCoordinate(3, p4);
  	  quads.setColor(0, c);
  	  quads.setColor(1, c);
  	  quads.setColor(2, c);
  	  quads.setColor(3, c);
  	  
  	  /* Cylinder Ends */
  	  
  	Shape3D shape = new Shape3D();
  	shape.setGeometry(quads);
  	shape.setAppearance(appearance);
  	medium_cylinder0.addChild(shape);
    	
    	tr=new Transform3D();
    	TransformGroup medium_cylinder1 = new TransformGroup(tr);
    	medium_cylinder1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	medium_cylinder1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	
    	Appearance appearance1 = new Appearance();
    	TransparencyAttributes ta1 = new TransparencyAttributes();
    	ta1.setTransparencyMode (ta1.BLENDED);
    	ta1.setTransparency(0.9f);
    	appearance1.setTransparencyAttributes(ta1);
    	    	
    	Shape3D shape1 = new Shape3D();
    	shape1.setGeometry(quads);
    	shape1.setAppearance(appearance1);
    	medium_cylinder1.addChild(shape1);
    	
    	tr=new Transform3D();
    	TransformGroup cylinder_lid = new TransformGroup(tr);
    	cylinder_lid.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	cylinder_lid.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	cylinder_lid.addChild(m_j3d.createCylinder(0.51f,(float)1.28, new Vector3d(0.1,0.1,0.1), new Vector3d(1,1,1),new Vector3d(0,0,0), new Color3f(1,0,0), "hello",hm));
    	
    	/****************************/
    	tr=new Transform3D();
    	TransformGroup medium_cylinder2 = new TransformGroup(tr);
    	medium_cylinder2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	medium_cylinder2.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	
    	Appearance appearance2 = new Appearance();
    	TransparencyAttributes ta2 = new TransparencyAttributes();
    	ta2.setTransparencyMode (ta2.BLENDED);
    	ta2.setTransparency(0.8f);
    	appearance2.setTransparencyAttributes(ta2);
    	    	
    	Shape3D shape2 = new Shape3D();
    	shape2.setGeometry(quads);
    	shape2.setAppearance(appearance2);
    	medium_cylinder2.addChild(shape2);
    	
    	/***********************/
    	tr=new Transform3D();
    	TransformGroup medium_cylinder3 = new TransformGroup(tr);
    	medium_cylinder3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	medium_cylinder3.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	
    	Appearance appearance3 = new Appearance();
    	TransparencyAttributes ta3 = new TransparencyAttributes();
    	ta3.setTransparencyMode (ta3.BLENDED);
    	ta3.setTransparency(0.7f);
    	appearance3.setTransparencyAttributes(ta3);
    	    	
    	Shape3D shape3 = new Shape3D();
    	shape3.setGeometry(quads);
    	shape3.setAppearance(appearance3);
    	medium_cylinder3.addChild(shape3);
    	
    	/***********************/
    	tr=new Transform3D();
    	TransformGroup medium_cylinder4 = new TransformGroup(tr);
    	medium_cylinder4.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	medium_cylinder4.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	
    	Appearance appearance4 = new Appearance();
    	TransparencyAttributes ta4 = new TransparencyAttributes();
    	ta4.setTransparencyMode (ta4.BLENDED);
    	ta4.setTransparency(0.65f);
    	appearance4.setTransparencyAttributes(ta4);
    	    	
    	Shape3D shape4 = new Shape3D();
    	shape4.setGeometry(quads);
    	shape4.setAppearance(appearance4);
    	medium_cylinder4.addChild(shape4);
        	
    	/****************************/
    	
    	tr=new Transform3D();
    	TransformGroup medium_cylinder5 = new TransformGroup(tr);
    	medium_cylinder5.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	medium_cylinder5.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	
    	Appearance appearance5 = new Appearance();
    	TransparencyAttributes ta5 = new TransparencyAttributes();
    	ta5.setTransparencyMode (ta5.BLENDED);
    	ta5.setTransparency(0.6f);
    	appearance5.setTransparencyAttributes(ta5);
    	    	
    	Shape3D shape5 = new Shape3D();
    	shape5.setGeometry(quads);
    	shape5.setAppearance(appearance5);
    	medium_cylinder5.addChild(shape5);
        	
    	/****************************/
    	
    	tr=new Transform3D();
    	TransformGroup medium_cylinder6 = new TransformGroup(tr);
    	medium_cylinder6.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	medium_cylinder6.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	
    	Appearance appearance6 = new Appearance();
    	TransparencyAttributes ta6 = new TransparencyAttributes();
    	ta6.setTransparencyMode (ta6.BLENDED);
    	ta6.setTransparency(0.55f);
    	appearance6.setTransparencyAttributes(ta6);
    	    	
    	Shape3D shape6 = new Shape3D();
    	shape6.setGeometry(quads);
    	shape6.setAppearance(appearance6);
    	medium_cylinder6.addChild(shape6);
        	
    	/****************************/
    	
    	tr=new Transform3D();
    	TransformGroup medium_cylinder7 = new TransformGroup(tr);
    	medium_cylinder7.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	medium_cylinder7.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	
    	Appearance appearance7 = new Appearance();
    	TransparencyAttributes ta7 = new TransparencyAttributes();
    	ta7.setTransparencyMode (ta7.BLENDED);
    	ta7.setTransparency(0.5f);
    	appearance7.setTransparencyAttributes(ta7);
    	    	
    	Shape3D shape7 = new Shape3D();
    	shape7.setGeometry(quads);
    	shape7.setAppearance(appearance7);
    	medium_cylinder7.addChild(shape7);
        	
    	/****************************/
    	tr=new Transform3D();
    	TransformGroup medium_cylinder8 = new TransformGroup(tr);
    	medium_cylinder8.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	medium_cylinder8.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	
    	Appearance appearance8 = new Appearance();
    	TransparencyAttributes ta8 = new TransparencyAttributes();
    	ta8.setTransparencyMode (ta8.BLENDED);
    	ta8.setTransparency(0.45f);
    	appearance8.setTransparencyAttributes(ta8);
    	    	
    	Shape3D shape8 = new Shape3D();
    	shape8.setGeometry(quads);
    	shape8.setAppearance(appearance8);
    	medium_cylinder8.addChild(shape8);
        	
    	/****************************/
    	tr=new Transform3D();
    	TransformGroup medium_cylinder9 = new TransformGroup(tr);
    	medium_cylinder9.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	medium_cylinder9.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	
    	Appearance appearance9 = new Appearance();
    	TransparencyAttributes ta9 = new TransparencyAttributes();
    	ta9.setTransparencyMode (ta9.BLENDED);
    	ta9.setTransparency(0.4f);
    	appearance9.setTransparencyAttributes(ta9);
    	    	
    	Shape3D shape9 = new Shape3D();
    	shape9.setGeometry(quads);
    	shape9.setAppearance(appearance9);
    	medium_cylinder9.addChild(shape9);
        	
    	/****************************/
    	       	
    	/****************************/
    //	plane.addChild(m_j3d.createBox(new Vector3d(0,-0.34,-0.8),new Vector3d(0.3,.01,1),new Vector3d(0,0,0),new Color3f(0f, 1f, 0f),"resources/images/grey4.jpg"));
    	plane.addChild(m_j3d.createBox(new Vector3d(0,-0.34,-0.4),new Vector3d(0.3,.01,1),new Vector3d(0,0,0),new Color3f(0f, 1f, 0f),"grey4.jpg"));
    	plane1.addChild(m_j3d.createBox(new Vector3d(0,-0.2,-0.4),new Vector3d(0.3,0.001,1),new Vector3d(0,0,0),new Color3f(0f, 1f, 0f),"grey4.jpg"));
    	background.addChild(m_j3d.createBox(new Vector3d(0,0,-2),new Vector3d(2,2,1),new Vector3d(0,0,0),new Color3f(0f, 1f, 0f),"bg.jpg"));
    	northpole.addChild(m_j3d.createBox(new Vector3d(-0.12,0.4,-0.06),new Vector3d(0.04,.03,0.02),new Vector3d(0,0,0),new Color3f(0f, 1f, 0f),"bar_magnetN.gif"));
    	southpole.addChild(m_j3d.createBox(new Vector3d(0.12,0.4,-0.06),new Vector3d(0.04,.03,0.02),new Vector3d(0,0,0),new Color3f(0f, 1f, 0f),"bar_magnetS.gif"));
    	
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
    	
    	magnet_x=0.0;
    	magnet_y=0.4;
     	
    	TransformGroup objtr;
    	 int temp4=0;
    	 
    	 hm.put("medium_cylinder0",medium_cylinder0);
      	 hm.put("medium_cylinder1",medium_cylinder1);
      	 hm.put("medium_cylinder2",medium_cylinder2);
      	 hm.put("medium_cylinder3",medium_cylinder3);
     	 hm.put("medium_cylinder4",medium_cylinder4);
     	 hm.put("medium_cylinder5",medium_cylinder5);
     	 hm.put("medium_cylinder6",medium_cylinder6);
     	 hm.put("medium_cylinder7",medium_cylinder7);
     	 hm.put("medium_cylinder8",medium_cylinder8);
     	 hm.put("medium_cylinder9",medium_cylinder9);
 	/*	for( int k=0;k<Total_cylinder;k++)
 		{
 			tr = new Transform3D();
 			
            //objtr=(TransformGroup)hm.get("medium_cylinder"+k);
         	//objtr.getTransform(tr);
         	//tr.setScale(new Vector3d(0,0,0));
         	//tr.setTranslation(new Vector3d(0,-0.1,0));
         	//objtr.setTransform(tr);
 			hm.put("medium_cylinder"+k,medium_cylinder);
 	//		another_medium.addChild(temp2);
 			temp4++;
 		}*/
    	
    	hm.put("plane",plane);
    	hm.put("cylinder_lid",cylinder_lid);
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
   // ***     hm.put("medium_cylinder",medium_cylinder); 
        hm.put("poly_atomic",PolyAtomic);
        hm.put("Medium",another_medium);        
        hm.put("Start_Experiment", magneticbead);
        
    	System.out.println("temp count is testing 1");
    	
    	objtr=(TransformGroup)hm.get("medium_cylinder0");
    	objtr.getTransform(tr);
    	tr.setScale(new Vector3d(1,1,1));
    	tr.setTranslation(new Vector3d(0,-0.0,0));
    	//tr.setTranslation(new Vector3d(0,-0.1,0));
    	objtr.setTransform(tr);
    	
      	objtr=(TransformGroup)hm.get("medium_cylinder1");
    	objtr.getTransform(tr);
    	tr.setScale(new Vector3d(1,1,1));
    	tr.setTranslation(new Vector3d(0,0,0));
    	//tr.setTranslation(new Vector3d(0,-0.1,0));
    	objtr.setTransform(tr);  	
    	
    	objtr=(TransformGroup)hm.get("medium_cylinder2");
    	objtr.getTransform(tr);
    	tr.setScale(new Vector3d(1,1,1));
    	tr.setTranslation(new Vector3d(0,0,0));
    	//tr.setTranslation(new Vector3d(0,-0.1,0));
    	objtr.setTransform(tr);  	
    	/*******************/
    	
    	objtr=(TransformGroup)hm.get("medium_cylinder3");
    	objtr.getTransform(tr);
    	tr.setScale(new Vector3d(1,1,1));
    	tr.setTranslation(new Vector3d(0,0,0));
    	//tr.setTranslation(new Vector3d(0,-0.1,0));
    	objtr.setTransform(tr);  	
    	/*******************/
    	
    	objtr=(TransformGroup)hm.get("medium_cylinder4");
    	objtr.getTransform(tr);
    	tr.setScale(new Vector3d(1,1,1));
    	tr.setTranslation(new Vector3d(0,0,0));
    	//tr.setTranslation(new Vector3d(0,-0.1,0));
    	objtr.setTransform(tr);  	
    	/*******************/
    	
    	objtr=(TransformGroup)hm.get("medium_cylinder5");
    	objtr.getTransform(tr);
    	tr.setScale(new Vector3d(1,1,1));
    	tr.setTranslation(new Vector3d(0,0,0));
    	//tr.setTranslation(new Vector3d(0,-0.1,0));
    	objtr.setTransform(tr);  	
    	/*******************/
    	
    	objtr=(TransformGroup)hm.get("medium_cylinder6");
    	objtr.getTransform(tr);
    	tr.setScale(new Vector3d(1,1,1));
    	tr.setTranslation(new Vector3d(0,0,0));
    	//tr.setTranslation(new Vector3d(0,-0.1,0));
    	objtr.setTransform(tr);  	
    	/*******************/
    	
    	objtr=(TransformGroup)hm.get("medium_cylinder7");
    	objtr.getTransform(tr);
    	tr.setScale(new Vector3d(1,1,1));
    	tr.setTranslation(new Vector3d(0,0,0));
    	//tr.setTranslation(new Vector3d(0,-0.1,0));
    	objtr.setTransform(tr);  	
    	/*******************/
    	
    	objtr=(TransformGroup)hm.get("medium_cylinder8");
    	objtr.getTransform(tr);
    	tr.setScale(new Vector3d(1,1,1));
    	tr.setTranslation(new Vector3d(0,0,0));
    	//tr.setTranslation(new Vector3d(0,-0.1,0));
    	objtr.setTransform(tr);  	
    	/*******************/
    	
    	objtr=(TransformGroup)hm.get("medium_cylinder9");
    	objtr.getTransform(tr);
    	tr.setScale(new Vector3d(1,1,1));
    	tr.setTranslation(new Vector3d(0,0,0));
    	//tr.setTranslation(new Vector3d(0,-0.1,0));
    	objtr.setTransform(tr);  	
    	/*******************/
    	
     /*   if(number_of_atoms==4)
        {
        	tr = new Transform3D();
        	//System.out.println("No Problem");
        	objtr=(TransformGroup)hm.get("medium_cylinder"+number);
        	//System.out.println("here 3 object"+objtr);
        	//System.out.println("Problem");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(1,1,1));
        	tr.setTranslation(new Vector3d(0,-0.10,0));
        	//tr.setTranslation(new Vector3d(0,-0.1,0));
        	objtr.setTransform(tr);
        }
        else
        {
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("medium_cylinder"+number);
        	System.out.println("where problem was"+objtr);
        	objtr.getTransform(tr);
        	System.out.println("Problem Else2");
        	tr.setScale(new Vector3d(1,0.8,1));
        	tr.setTranslation(new Vector3d(0,-0.10,0));
        	//tr.setTranslation(new Vector3d(0,-0.1,0));
        	objtr.setTransform(tr);
        }*/
        
        //int temp4=0;
/***		for( int k=0;k<Total_cylinder;k++)
		{
			tr = new Transform3D();
            objtr=(TransformGroup)hm.get("medium_cylinder"+k);
            System.out.println("here 4 object"+objtr);
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
        	//tr.setTranslation(new Vector3d(0,-0.1,0));
        	objtr.setTransform(tr);
	//		hm.put("medium_cylinder","hello");
	//		another_medium.addChild(temp2);
	//		temp4++;
		}*/
		
    	tr = new Transform3D();
        objtr=(TransformGroup)hm.get("medium_cylinder0");
        System.out.println("here 4 object"+objtr);
    	objtr.getTransform(tr);
    	tr.setScale(new Vector3d(0,0,0));
    	//tr.setTranslation(new Vector3d(0,-0.1,0));
    	objtr.setTransform(tr);
    	
    	tr = new Transform3D();
        objtr=(TransformGroup)hm.get("medium_cylinder1");
        System.out.println("here 4 object"+objtr);
    	objtr.getTransform(tr);
    	tr.setScale(new Vector3d(0,0,0));
    	objtr.setTransform(tr);
    	
    	tr = new Transform3D();
        objtr=(TransformGroup)hm.get("medium_cylinder2");
        System.out.println("here 4 object"+objtr);
    	objtr.getTransform(tr);
    	tr.setScale(new Vector3d(0,0,0));
    	//tr.setTranslation(new Vector3d(0,-0.1,0));
    	objtr.setTransform(tr);
    	/****************************/
    	tr = new Transform3D();
        objtr=(TransformGroup)hm.get("medium_cylinder3");
        System.out.println("here 4 object"+objtr);
    	objtr.getTransform(tr);
    	tr.setScale(new Vector3d(0,0,0));
    	//tr.setTranslation(new Vector3d(0,-0.1,0));
    	objtr.setTransform(tr);
    	/****************************/
    	
    	tr = new Transform3D();
        objtr=(TransformGroup)hm.get("medium_cylinder4");
        System.out.println("here 4 object"+objtr);
    	objtr.getTransform(tr);
    	tr.setScale(new Vector3d(0,0,0));
    	//tr.setTranslation(new Vector3d(0,-0.1,0));
    	objtr.setTransform(tr);
    	/****************************/
    	
    	tr = new Transform3D();
        objtr=(TransformGroup)hm.get("medium_cylinder5");
        System.out.println("here 4 object"+objtr);
    	objtr.getTransform(tr);
    	tr.setScale(new Vector3d(0,0,0));
    	//tr.setTranslation(new Vector3d(0,-0.1,0));
    	objtr.setTransform(tr);
    	/****************************/
    	
    	tr = new Transform3D();
        objtr=(TransformGroup)hm.get("medium_cylinder6");
        System.out.println("here 4 object"+objtr);
    	objtr.getTransform(tr);
    	tr.setScale(new Vector3d(0,0,0));
    	//tr.setTranslation(new Vector3d(0,-0.1,0));
    	objtr.setTransform(tr);
    	/****************************/
    	
    	tr = new Transform3D();
        objtr=(TransformGroup)hm.get("medium_cylinder7");
        System.out.println("here 4 object"+objtr);
    	objtr.getTransform(tr);
    	tr.setScale(new Vector3d(0,0,0));
    	//tr.setTranslation(new Vector3d(0,-0.1,0));
    	objtr.setTransform(tr);
    	/****************************/
    	
    	tr = new Transform3D();
        objtr=(TransformGroup)hm.get("medium_cylinder8");
        System.out.println("here 4 object"+objtr);
    	objtr.getTransform(tr);
    	tr.setScale(new Vector3d(0,0,0));
    	//tr.setTranslation(new Vector3d(0,-0.1,0));
    	objtr.setTransform(tr);
    	/****************************/
    	
    	tr = new Transform3D();
        objtr=(TransformGroup)hm.get("medium_cylinder9");
        System.out.println("here 4 object"+objtr);
    	objtr.getTransform(tr);
    	tr.setScale(new Vector3d(0,0,0));
    	//tr.setTranslation(new Vector3d(0,-0.1,0));
    	objtr.setTransform(tr);
    	/****************************/
    	
    	
		//for cylinder lid
		tr = new Transform3D();
        objtr=(TransformGroup)hm.get("cylinder_lid");
    	objtr.getTransform(tr);
    	tr.setScale(new Vector3d(0,0,0));
    	tr.setTranslation(new Vector3d(0,0.2,0));
    	objtr.setTransform(tr);
	
	//	System.out.println("TOTAL CYLINDERS"+temp4);

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
      	Start_Experiment.addChild(medium_cylinder0);
      	Start_Experiment.addChild(medium_cylinder1);
      	Start_Experiment.addChild(medium_cylinder2);
      	Start_Experiment.addChild(medium_cylinder3);
      	Start_Experiment.addChild(medium_cylinder4);
      	Start_Experiment.addChild(medium_cylinder5);
      	Start_Experiment.addChild(medium_cylinder6);
      	Start_Experiment.addChild(medium_cylinder7);
      	Start_Experiment.addChild(medium_cylinder8);
      	Start_Experiment.addChild(medium_cylinder9);
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
   // 	structure.addChild(medium_cylinder);// *****
   // 	structure.addChild(support1);
    //	structure.addChild(support2);
    	

    	// all buttons
    	//diatomic.setEnabled(true);
    	//triatomic.setEnabled(true);
    	//quadatomic.setEnabled(true);
    	
    	//all sliders
    	//Start_experiment.setEnabled(false);
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
    public Magnetic_Tweezer_with_Environment(Container container) {
        // Initialize the GUI components
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);
        initComponents();

        centerPanel(container);
//        scene.addChild(bgleg);
    }

    
    // ----------------------------------------------------------------
    
    // Applet framework

    public static class MyApplet extends JApplet {
    	Magnetic_Tweezer_with_Environment mainPanel;

        public void init() {
            setLayout(new BorderLayout());
            mainPanel = new Magnetic_Tweezer_with_Environment(this);
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
            getContentPane().add(new Magnetic_Tweezer_with_Environment(this), BorderLayout.CENTER);
            pack();
        }
    }

    // Create a form with the specified labels, tooltips, and sizes.
    /**
     * @param args the command line arguments
     */
    
 static double tempk_bond=0,tempr_bond=0;
    
    static int row=1;
	static int count=0;
	static int iter_bond=0;
	static float ftotal_bend=0;
	
	static int[] bend1 = new int[100000];
	static int[] bend2 = new int[100000];
	static int[] bend3 = new int[100000];
	
	static int iter_bend=0;
	static int[] bend_type = new int[100000];
	
	static float[][] position = new float[100000][4];
	static float[][] temp = new float[100000][4];
	
	static float[][] r = new float[1000][1000];
	static float[][] rnew = new float[1000][1000];
	
	static float[][] u = new float[1000][1000];
	static float[][] f = new float[1000][1000];
	
	static float utotal=0;
	static float utotal_bend=0;
	static double ftotal=0;
	
	static int[] bond1 = new int[100000];
	static int[] bond2 = new int[100000];
	
	static int[] bond_type = new int[100000];
	static char atom[] = new char[100000];
	
	static double[] bond_k = new double[100000];
	static double[] bond_r = new double[100000];

	static float[] bend_k = new float[100000];
	static float[] bend_theta = new float[100000];
	
	static float[] fx_i = new float[100000];
	static float[] fx_j = new float[100000];
	static float[] fx_k = new float[100000];
	
	static float[] fy_i = new float[100000];
	static float[] fy_j = new float[100000];
	static float[] fy_k = new float[100000];
	
	static float[] fz_i = new float[100000];
	static float[] fz_j = new float[100000];
	static float[] fz_k = new float[100000];
	
	//dihedral
	static int iter_dihed=0;
	static int[] dihed_type = new int[100000];
	
	static int[] dihed1 = new int[100000];
	static int[] dihed2 = new int[100000];
	static int[] dihed3 = new int[100000];
	static int[] dihed4 = new int[100000];
	
	static float[] dihed_k = new float[100000];
	static float[] dihed_theta = new float[100000];
	
	static float[] dihed_delta= new float[100000];
	static float[] dihed_n = new float[100000];
	static float K1;
	static float utotal_dihed=0;
	
	static float ftotal_dihed=0;
	
	static float[] fx_dihed_i = new float[100000];
	static float[] fx_dihed_j = new float[100000];
	static float[] fx_dihed_k = new float[100000];
	
	static float[] fy_dihed_i = new float[100000];
	static float[] fy_dihed_j = new float[100000];
	static float[] fy_dihed_k = new float[100000];
	
	static float[] fz_dihed_i = new float[100000];
	static float[] fz_dihed_j = new float[100000];
	static float[] fz_dihed_k = new float[100000];
	
	static float[] fx_dihed_l = new float[100000];
	static float[] fy_dihed_l = new float[100000];
	static float[] fz_dihed_l = new float[100000];
    
    
	// Non bonded
	static float utotal_nonbonded=0;
	static float[] epsilon = new float[100000];
	static float[] rho = new float[100000];
	static float[] q = new float[100000];
	
	static float utotal_nonbond=0;
	static float ftotal_nonbond=0;
	static int iter_nonbond=0;
	
	static int[] nonbond1 = new int[100000];
	static int[] nonbond2 = new int[100000];
	
	static int[] nonbond_type = new int[100000];
	
	static float[] fx_nonbond_i = new float[100000];
	static float[] fx_nonbond_j = new float[100000];
	static float[] fz_nonbond_i = new float[100000];
	
	static float[] fy_nonbond_i = new float[100000];
	static float[] fy_nonbond_j = new float[100000];
	static float[] fz_nonbond_j = new float[100000];
	
	static float disp=0;
	
	public static void read() throws NumberFormatException, IOException
	{
		FileReader file = new FileReader("hexane.pdb"); 
		//hexane.pdb contains number type ,and x y z coordinate of each atom in hexane ( in coloumns 1,2 ,5, 6, 7 respectively ). 
		BufferedReader buffer = new BufferedReader(file); 
		String string; 
		
		while((string = buffer.readLine()) != null) 
		{ 
			String[] result = string.split(" ");// split string on the basis of space
			String[] n= string.split(" ");
			
			int pos=0;
			if(result[0].equals("ATOM"))
			{	
				for(int i=1;i<100;i++)
				{
					if(result[i].equals(""))
						count++;

					else
					{
						n[pos]=result[i];
						pos++;
					}	
					
					if(pos==8)
						break;
				}
					
				// here n contains the same content as a row in the hexane.pdb file 
				Float f = new Float(n[4]);
				position[row][0]=f;// storing the x coordinate of atom in line "row" of pdb file.
				temp[row][0]=f;
					
				Float f1 = new Float(n[5]);
				position[row][1]=f1;// storing the x coordinate of atom in line "row" of pdb file.
				temp[row][1]=f1;
						
				Float f2 = new Float(n[6]);
				position[row][2]=f2;// storing the x coordinate of atom in line "row" of pdb file.
				temp[row][2]=f2;
				
				atom[row]=n[1].charAt(0);// storing the atom type in line "row" of pdb file.
			}
			//row indicates the line of the pdb file the program is currently in.
			row++;
		}	
	}
	public static void bonds() throws IOException
	{
		iter_bond=0;
		FileReader file = new FileReader("hexane.bonds"); 
		// hexane.bonds contains the atom which are bonded in coloumn 1 and 2 and the type ( 0 for c-c and 1 for c-h )
		BufferedReader buffer = new BufferedReader(file); 
		
		String string; 
		
		while((string = buffer.readLine()) != null) 
		{ 
			// iter_bond is the index used to iterates through each bond.
			iter_bond++;
			String[] result = string.split(" ");
			String[] n= string.split(" ");
			int pos=0;

			if (iter_bond>1) //assuming only 1 line header
			{
				for(int i=0;i<100;i++)
					{
						if(result[i].equals(""))
							count++;
						
						else
						{	
							n[pos]=result[i];
							pos++;
						}	
					
						if(pos==3)
							break;
					}
				        // here n contains the same content as a row in the hexane.bonds file 
						bond_type[iter_bond]=Integer.parseInt(n[2]);// stores the type of bond
						bond1[iter_bond]=Integer.parseInt(n[0]);// stores the first atom of bond (coloumn 1 of file)
						bond2[iter_bond]=Integer.parseInt(n[1]);// stores the second atom of bond (coloumn 2 of file)
				}
			}

		}
	
	
	public static void energy1 () throws NumberFormatException, IOException
	{
		utotal=0;
		int iter=0;
		FileReader file = new FileReader("hexane.para"); 
		
		/*
		 * The formula used for calculating energy is (1/2)*k*(deltar)*(deltar);
		 * deltar=r-r(0) where r denotes the bond length between two bonds in hexane and r(0) the equilibrium bond length
		 * Here "k" and r(0) are extracted from the "hexane.para" file . Theses values vary only with the types of bond .i.e one value of "k" for c-c bonds and 1 for c-h bonds.
		 * Thus we have to calculate "r".
		 * This is done by extracting the x,y,z coordinates of both the atoms that form a bond and using sqrt((x1-x2)square+(y1-y2)square+(z1-z2)square)
		 */
		
		BufferedReader buffer = new BufferedReader(file); 
		
		String string; 
		
		while((string = buffer.readLine()) != null) 
		{ 
			int temp1,temp2;
			iter++;
			String[] result = string.split(" ");
			String[] n= string.split(" ");
			int pos=0;
		
			if (iter>1) //assuming only 1 line header
			{
				for(int i=0;i<100;i++)
					{
						if(result[i].equals(""))
							count++;
						
						else
						{	
							n[pos]=result[i];
							pos++;
						}	
					
						if(pos==3)
							break;
					}
				        // here n contains the same content as a row in the hexane.para file 
						int k1=Integer.parseInt(n[0]);// In k1 the type of bond is stored( 0 for c-c and 1 for c-h)

				        Float f = new Float(n[1]);//the "k" value used in formula (1/2)*k*(deltar)*(deltar) ; for the type of atom , as in value of k1 is stored.
						bond_k[k1]=f;
						
						Float f1 = new Float(n[2]);//the "r(0)" value used in formula deltar=r-r(0) ; for the type of atom , as in value of k1 is stored.
						bond_r[k1]=f1;
						
				}
		}	
	}
	
	public static void energy2()
	{
		utotal=0;
		for (int j=2;j<= iter_bond ;j++)// iterarting for each bond in the atom.
		{
			/*
			 *temp[bond1[j]][0]] contains the x coordinate of first atom in the bond.
			 *temp[bond2[j]][0]] contains the x coordinate of second atom in the bond.
			 *
			 *similarly temp[bond1[j]][1]] contains the y coordinate of first atom in the bond.
			 *and so on ...
			 */
			
			float rx=(temp[bond1[j]][0]-temp[bond2[j]][0]);
			float ry=(temp[bond1[j]][1]-temp[bond2[j]][1]);
			float rz=(temp[bond1[j]][2]-temp[bond2[j]][2]);
			System.out.println(temp[bond1[j]][0]);
							
			float r=(float) Math.sqrt((rx*rx)+(ry*ry)+(rz*rz));
			//"r" here is the "r"	in deltar= r- r(0)	
			
			if(bond_type[j] == 0)
			{
				tempk_bond=bond_k[0];
				tempr_bond=bond_r[0];
			}
			
			else if (bond_type[j] == 1)
			{
				tempk_bond=bond_k[1];
				tempr_bond=bond_r[1];	
			}
			//Here tempk_bond and tempr_bond contains the "k" and "r" values corresponding to the type of bond .....
			//(1/2)*k*(deltar)*(deltar);
			
			float u =(float) (0.5*tempk_bond*(r-tempr_bond)*(r-tempr_bond));
			utotal=utotal+u;// adding values of all the previously iterated bonds with the current so that at the end , we get the net value.
		 }
		
		System.out.println("utotal"+" "+utotal);
		}
		
	public static void force()
	{
		/*
		 * force on the x coordinate of an atom is obtained by differentiating the energy wrt. the x coordinate.
		 * expression on differentiating is fx= k*deltar*(x1-x2)/r
		 * fx here represents the force on the 1st atom of the bond, in x direction.
		 * the force on 2nd atom will be same in magnitude and opposite in direction.
		 * 
		 */
		
		ftotal=0;
		for (int j=2;j<=iter_bond ;j++)
		{
			float rx=(temp[bond1[j]][0]-temp[bond2[j]][0]);
			float ry=(temp[bond1[j]][1]-temp[bond2[j]][1]);
			float rz=(temp[bond1[j]][2]-temp[bond2[j]][2]);
							
			float r=(float) Math.sqrt((rx*rx)+(ry*ry)+(rz*rz));
			//"r" here is the "r"	in deltar= r- r(0)	
			
			double tempk=0;
			double tempr=0;
			if(bond_type[j] == 0)
			{
				tempk=bond_k[0];
				tempr=bond_r[0];
			}
			
			else if (bond_type[j] == 1)
			{
				tempk=bond_k[1];
				tempr=bond_r[1];	
			}
			//Here tempk_bond and tempr_bond contains the "k" and "r" values corresponding to the type of bond .....
			
		double fcommon=tempk*(r-tempr)/r;
		double fx=(fcommon*(temp[bond1[j]][0]-temp[bond2[j]][0]));
		double fy=(fcommon*(temp[bond1[j]][1]-temp[bond2[j]][1]));
		double fz=(fcommon*(temp[bond1[j]][2]-temp[bond2[j]][2]));
		
		double f=(float) Math.sqrt(fx*fx+fy*fy+fz*fz);// Adding the force vectorially.
		ftotal= (2*f)+ftotal;// adding values of all the previously iterated bonds with the current so that at the end , we get the net value.
		// a factor of 2 has been multiplied as bond contains 2 atoms.the force on second atom would be equal in magnitude to the first.Thus the 2.
		
		}
		System.out.println("ftotal "+ftotal+"\n");
		
	}
	
	public static void bends() throws IOException
	{
		iter_bend=0;
		FileReader file = new FileReader("hexane.bends"); 
		BufferedReader buffer = new BufferedReader(file); 
		
		String string; 
		
		while((string = buffer.readLine()) != null) 
		{ 
			iter_bend++;
			String[] result = string.split(" ");
			String[] n= string.split(" ");
			int pos=0;
/*			if(result[0].equals("ATOM")) :CHANGE FORMAT WHEN NECCESSARY	*/
		
			if (iter_bend>1) //assuming only 1 line header
			{
				for(int i=0;i<100;i++)
					{
						if(result[i].equals(""))
							count++;
						
						else
						{	
							n[pos]=result[i];
							pos++;
						}	
					
						if(pos==4)///////////////////////////////////////////////////
							break;
					}
					
						bend_type[iter_bend]=Integer.parseInt(n[3]);
						bend1[iter_bend]=Integer.parseInt(n[0]);
						bend2[iter_bend]=Integer.parseInt(n[1]);
						bend3[iter_bend]=Integer.parseInt(n[2]);
				}
			}

		}
	
	public static void energy_bend () throws NumberFormatException, IOException
	{
		int no=0;
		utotal_bend=0;
		int iter=0;
		FileReader file = new FileReader("hexane.bend.para"); 
		BufferedReader buffer = new BufferedReader(file); 
		
		String string; 
		
		while((string = buffer.readLine()) != null) 
		{ 
			int temp1,temp2;
			iter++;
			String[] result = string.split(" ");
			String[] n= string.split(" ");
			int pos=0;
/*			if(result[0].equals("ATOM")) :CHANGE FORMAT WHEN NECCESSARY	*/
		
			if (iter>1) //assuming only 1 line header
			{
				for(int i=0;i<100;i++)
					{
						if(result[i].equals(""))
							count++;
						
						else
						{	
							n[pos]=result[i];
							pos++;
						}	
					
						if(pos==3)
							break;
					}
					
						int k1=Integer.parseInt(n[0]);

				        Float f = new Float(n[1]);
						bend_k[k1]=f;
						
						Float f1 = new Float(n[2]);
						bend_theta[k1]=f1;
						bend_theta[k1]=(float) (bend_theta[k1]*3.14/180);
						
				}
		}	
		for (int j=2;j<= iter_bend ;j++)
		{
			float rx=(position[bend1[j]][0]-position[bend2[j]][0]);
			float ry=(position[bend1[j]][1]-position[bend2[j]][1]);
			float rz=(position[bend1[j]][2]-position[bend2[j]][2]);
			float rij=(float) Math.sqrt((rx*rx)+(ry*ry)+(rz*rz));
			
			float rx1=(position[bend3[j]][0]-position[bend2[j]][0]);
			float ry1=(position[bend3[j]][1]-position[bend2[j]][1]);
			float rz1=(position[bend3[j]][2]-position[bend2[j]][2]);
			float rjk=(float) Math.sqrt((rx1*rx1)+(ry1*ry1)+(rz1*rz1));
			
			float dot=(rx*rx1)+(ry*ry1)+(rz*rz1);
			
			float temproary=rij*rjk;
			float theta=(float) (Math.acos(dot/temproary));
			//no++;
			//System.out.println("n"+"  "+no);
			
			float tempk=0,tempr=0;
			if(bend_type[j] == 0)
			{
				tempk=bend_k[0];
				tempr=bend_theta[0];
			}
			
			else if (bend_type[j] == 1)
			{
				tempk=bend_k[1];
				tempr=bend_theta[1];	
			}
			
			else if (bend_type[j] == 2)
			{
				tempk=bend_k[2];
				tempr=bend_theta[2];	
			}
			
			float u_bend=(float) (0.5*2*tempk*(theta-tempr)*(theta-tempr));
			utotal_bend=utotal_bend+u_bend;
		
			//System.out.println("u_bend"+" "+u_bend);
			//System.out.println("  "); 
		}
		System.out.println("utotal_bend"+" "+utotal_bend);
		}
		
	 public static void dihedral() throws IOException
		{
			iter_dihed=0;
			
			FileReader file = new FileReader("hexane.dihedral"); 
			BufferedReader buffer = new BufferedReader(file); 
			
			String string; 
			
			while((string = buffer.readLine()) != null) 
			{ 
				iter_dihed++;
				String[] result = string.split(" ");
				String[] n= string.split(" ");
				int pos=0;
	/*			if(result[0].equals("ATOM")) :CHANGE FORMAT WHEN NECCESSARY	*/
			
				if (iter_dihed>1) //assuming only 1 line header
				{
					for(int i=0;i<200;i++)
					{
							if(result[i].equals(""))
								count++;
							
							else
							{	
								n[pos]=result[i];
								pos++;
							}	
							
							if(pos==5)// one additional feild for an xtra atom
								break;
						}
			
							dihed_type[iter_dihed]=Integer.parseInt(n[4]);
							
							dihed1[iter_dihed]=Integer.parseInt(n[0]);
							dihed2[iter_dihed]=Integer.parseInt(n[1]);
							dihed3[iter_dihed]=Integer.parseInt(n[2]);
							dihed4[iter_dihed]=Integer.parseInt(n[3]);
					
						}
				}
			
			}
	  
		
		public static void force_bend()
		{
			ftotal_bend=0;
			float faltuf1=0,faltuf2=0,faltuf3=0;
			for (int j=2;j<= iter_bend ;j++)
			{
				float rxij=(position[bend1[j]][0]-position[bend2[j]][0]);
				float ryij=(position[bend1[j]][1]-position[bend2[j]][1]);
				float rzij=(position[bend1[j]][2]-position[bend2[j]][2]);
				float rij=(float) Math.sqrt((rxij*rxij)+(ryij*ryij)+(rzij*rzij));//adv : can call force b4 eng;
				
				float rxjk=(position[bend3[j]][0]-position[bend2[j]][0]);
				float ryjk=(position[bend3[j]][1]-position[bend2[j]][1]);
				float rzjk=(position[bend3[j]][2]-position[bend2[j]][2]);
				float rjk=(float) Math.sqrt((rxjk*rxjk)+(ryjk*ryjk)+(rzjk*rzjk));
				
				float dot=rxij*rxjk+ryij*ryjk+rzij*rzjk;
				
				float temproary=rij*rjk;
				float theta=(float) (Math.acos(dot/temproary));
								
				float tempk=0,tempr=0;
				if(bend_type[j] == 0)
				{
					tempk=bend_k[0];
					tempr=bend_theta[0];
				}
				
				else if (bend_type[j] == 1)
				{
					tempk=bend_k[1];
					tempr=bend_theta[1];	
				}
				
				else if (bend_type[j] == 2)
				{
					tempk=bend_k[2];
					tempr=bend_theta[2];	
				}
				
				
			float p=rij*rjk;
			float dv_dt=2*tempk*(tempr-theta);
			
			float dq_dxi=(rxjk/p)-(dot*rxij)/(rij*rij*rij*rjk);//check for parenthesis
			float dq_dyi=(ryjk/p)-(dot*ryij)/(rij*rij*rij*rjk);
			float dq_dzi=(rzjk/p)-(dot*rzij)/(rij*rij*rij*rjk);
			
			float dq_dxk=(rxij/p)-(dot*rxjk)/(rij*rjk*rjk*rjk);
			float dq_dyk=(ryij/p)-(dot*rxjk)/(rij*rjk*rjk*rjk);
			float dq_dzk=(rzij/p)-(dot*rzjk)/(rij*rjk*rjk*rjk);//check till here.
			
			float dq_dxj=-(dq_dxi+dq_dxk);
			float dq_dyj=-(dq_dyi+dq_dyk);
			float dq_dzj=-(dq_dzi+dq_dzk);
			
			float sint=(float) (1/Math.sin(tempr));
			
			if(j==2)
				faltuf1=-sint*dq_dxi*dv_dt;
			if(j==3)
				faltuf2=-sint*dq_dxi*dv_dt;
			if(j==4)
				faltuf3=-sint*dq_dxi*dv_dt;
			
			fx_i[bend1[j]]=-sint*dq_dxi*dv_dt+fx_i[bend1[j]];
			fy_i[bend1[j]]=-sint*dq_dyi*dv_dt+fy_i[bend1[j]];
			fz_i[bend1[j]]=-sint*dq_dzi*dv_dt+fz_i[bend1[j]];
			fx_j[bend2[j]]=-sint*dq_dxj*dv_dt+fx_j[bend2[j]];
			fy_j[bend2[j]]=-sint*dq_dyj*dv_dt+fy_j[bend2[j]];
			fz_j[bend2[j]]=-sint*dq_dzj*dv_dt+fz_j[bend2[j]];
			fx_k[bend3[j]]=-sint*dq_dxk*dv_dt+fx_k[bend3[j]];
			fy_k[bend3[j]]=-sint*dq_dyk*dv_dt+fy_k[bend3[j]];
			fz_k[bend3[j]]=-sint*dq_dzk*dv_dt+fz_k[bend3[j]];
			
			}
		
			for (int jnk=2;jnk<= iter_bend ;jnk++)
			{
			
			float fi=(float) Math.sqrt((fx_i[jnk]*fx_i[jnk])+ (fy_i[jnk]*fy_i[jnk])+(fz_i[jnk]*fz_i[jnk]));
			float fj=(float) Math.sqrt((fx_j[jnk]*fx_j[jnk])+ (fy_j[jnk]*fy_j[jnk])+(fz_j[jnk]*fz_j[jnk]));
			float fk=(float) Math.sqrt((fx_k[jnk]*fx_k[jnk])+ (fy_k[jnk]*fy_k[jnk])+(fz_k[jnk]*fz_k[jnk]));
			
			float fnet=fi+fj+fk;//check -> atom 1 has 3 bends.all calculated separately;
			ftotal_bend+=fnet;
			}
			
			System.out.println("ftotal_bend  "+ftotal_bend+"\n");
			
		}
		
		 public static void energy_dihed1 () throws NumberFormatException, IOException
			{
		    	
		    	K1=0;
				int no=0;
				utotal_dihed=0;
				int iter=0;
				FileReader file = new FileReader("hexane.dihedral.para"); 
				BufferedReader buffer = new BufferedReader(file); 
				
				String string; 
				
				while((string = buffer.readLine()) != null) 
				{ 
					int temp1,temp2;
					iter++;
					String[] result = string.split(" ");
					String[] n= string.split(" ");
					int pos=0;
		/*			if(result[0].equals("ATOM")) :CHANGE FORMAT WHEN NECCESSARY	*/
				
					if (iter>1) //assuming only 1 line header
					{
						for(int i=0;i<1000;i++)
							{
								if(result[i].equals(""))
									count++;
								
								else
								{	
									n[pos]=result[i];
									pos++;
								}	
							
								if(pos==4)// 2 additional paramaters .
									break;
							}
							
								int k1=Integer.parseInt(n[0]);

								Float f = new Float(n[1]);
								dihed_k[k1]=f;
														
								Float f3 = new Float(n[2]);
								dihed_n[k1]=f3;
								
								Float f2 = new Float(n[3]);                                    // check
								dihed_delta[k1]=f2;
						}
				}	
			}
		 public static void energy_dihed2 () throws NumberFormatException, IOException
			{	
				for (int j=2;j<= iter_dihed ;j++)
				{
					//no++;
					//System.out.println("n"+"  "+no);
					
					float rx=(temp[dihed1[j]][0]-temp[dihed2[j]][0]);
					float ry=(temp[dihed1[j]][1]-temp[dihed2[j]][1]);
					float rz=(temp[dihed1[j]][2]-temp[dihed2[j]][2]);
							
					float rx1=(temp[dihed2[j]][0]-temp[dihed3[j]][0]);
					float ry1=(temp[dihed2[j]][1]-temp[dihed3[j]][1]);
					float rz1=(temp[dihed2[j]][2]-temp[dihed3[j]][2]);
					
					float rx2=(temp[dihed3[j]][0]-temp[dihed4[j]][0]);
					float ry2=(temp[dihed3[j]][1]-temp[dihed4[j]][1]);
					float rz2=(temp[dihed3[j]][2]-temp[dihed4[j]][2]);
					
					float Ax = (ry*rz1)-(ry1*rz);
					float Ay = (rz*rx1)-(rx*rz1);
					float Az = (rx*ry1)-(ry*rx1);
					float rAinv=(float) Math.sqrt((Ax*Ax)+(Ay*Ay)+(Az*Az));
				   
					float Bx = (ry1*rz2)-(ry2*rz1);
					float By = (rz1*rx2)-(rx1*rz2);
					float Bz = (rx1*ry2)-(ry1*rx2);
					float rBinv=(float) Math.sqrt((Bx*Bx)+(By*By)+(Bz*Bz));
					
					float Cx = (ry1*Az)-(Ay*rz1);
					float Cy = (rz1*Ax)-(rx1*Az);
					float Cz = (rx1*Ay)-(ry1*Ax);
					float rCinv=(float) Math.sqrt((Cx*Cx)+(Cy*Cy)+(Cz*Cz));
				
					
					float cosphi=(((Ax/rAinv)*(Bx/rBinv)) +((Ay/rAinv)*(By/rBinv))+((Az/rAinv)*(Bz/rBinv)));
					float sinphi=(((Cx/rCinv)*(Bx/rBinv)) +((Cy/rCinv)*(By/rBinv))+((Cz/rCinv)*(Bz/rBinv)));
					
					float phi=0;
					if(cosphi < -1)
						cosphi=-1;
					
					//phi= (float) Math.acos(cosphi);
					phi= (float) -Math.atan2(sinphi,cosphi);
				
					//System.out.println("phi="+phi);
					
					float tempk=0,temp_delta=0,tempn=0;
					if(dihed_type[j] == 0)
					{
						tempk=dihed_k[0];
						temp_delta=dihed_delta[0];
						tempn=dihed_n[0];
					}
					
					else if (dihed_type[j] == 1)
					{
						tempk=dihed_k[1];
						temp_delta=dihed_delta[1];	
						tempn=dihed_n[1];
					}
					
					else if (dihed_type[j] == 2)
					{
						tempk=dihed_k[2];
						temp_delta=dihed_delta[2];	
						tempn=dihed_n[2];
					}
					
					float angle= (tempn*phi)-temp_delta;
					float u_dihed = (float) (tempk*(1+Math.cos(angle)));
					K1 += -(tempn*tempk*Math.sin(angle));//check if k1 revels same vale with sin and minus sin
				
					utotal_dihed=utotal_dihed+u_dihed;
				
					//System.out.println("u_dihed"+" "+u_dihed);
					//System.out.println("  "); 
				}
				System.out.println("utotal_dihed"+" "+utotal_dihed);
				//System.out.println("K1"+" "+K1);
				//System.out.println("  "); 
				}
		    
		
	
    public static void move()
	{
    	disp=0;
	
		//double randNumberx = Math.random()/10;
		//double randNumbery = Math.random()/10;
		//double randNumberz  = Math.random()/10;
		
		//randNumberx=randNumberx-;
		//randNumbery=randNumbery-0.05;
		//randNumberz=randNumberz-0.05;
		
		for (int j=2;j<= iter_bond ;j++)
		{
    		temp[j][0]=position[j][0]-(magnet_y_translate)/10;
    		temp[j][1]=position[j][1]-(magnet_y_translate)/10;
    		temp[j][2]=position[j][2]-(magnet_y_translate)/10;
		}
		
		System.out.println("y "+magnet_y_translate);
		
		//System.out.println(position[1][0]);
		//position[2][0]= (float) (position[2][0]+0.000001);
		
	}
    public static void nonbond_force ( int cas) throws NumberFormatException, IOException
	{
    	//ftotal_nonbond=0;
    	for (int j=2;j<= iter_nonbond ;j++)
		{
    		float rx=(temp[nonbond1[j]][0]-temp[nonbond2[j]][0]);
			float ry=(temp[nonbond1[j]][1]-temp[nonbond2[j]][1]);
			float rz=(temp[nonbond1[j]][2]-temp[nonbond2[j]][2]);
							
			float distance=(float) Math.sqrt((rx*rx)+(ry*ry)+(rz*rz));
						
			float tempe=0,tempr=0,tempq1=0,tempq2=0;
			if(atom[nonbond1[j]]=='C'&& atom[nonbond2[j]]=='C')
			{
				tempe=(float) Math.sqrt(epsilon[0]*epsilon[0]);
				tempr=rho[0];//c**
				tempq1=q[0];
				tempq2=q[0];
			}
			
			else 
			{
				tempe=(float) Math.sqrt(epsilon[0]*epsilon[1]);
				tempr=(float) Math.sqrt(rho[0]*rho[1]);	
				tempq1=q[1];
				tempq2=q[0];
			}
			
			
			float rho_r=(tempr/distance);
			float pow12=(float) Math.pow(rho_r,12);
			float pow6=(float) Math.pow(rho_r,6);
			float distance_root=(float) Math.pow(distance,-0.5);
			
			float du_dr=tempe*((12*pow12/distance)-2*(6*pow6/distance));
			float dq_dr=(float) (-(332.0716*tempq1*tempq2)/(distance*distance));
			float du_dxi=distance_root*rx;
			float du_dyi=distance_root*ry;
			float du_dzi=distance_root*rz;
			
			fx_nonbond_i[nonbond1[j]]+= (du_dr+dq_dr)* du_dxi ;
			fy_nonbond_i[nonbond1[j]]+= (du_dr+dq_dr)* du_dyi ;
			fz_nonbond_i[nonbond1[j]]+= (du_dr+dq_dr)* du_dzi ;
			
			fx_nonbond_j[nonbond1[j]]-= (du_dr+dq_dr)* du_dxi ;
			fy_nonbond_j[nonbond1[j]]-= (du_dr+dq_dr)* du_dyi ;
			fz_nonbond_j[nonbond1[j]]-= (du_dr+dq_dr)* du_dzi ;
			
		}
    	
		for (int jnk=2;jnk<= iter_nonbond ;jnk++)
		{
		
		float fi=(float) Math.sqrt((fx_nonbond_i[jnk]*fx_nonbond_i[jnk])+ (fy_nonbond_i[jnk]*fy_nonbond_i[jnk])+(fz_nonbond_i[jnk]*fz_nonbond_i[jnk]));
		float fj=(float) Math.sqrt((fx_nonbond_j[jnk]*fx_nonbond_j[jnk])+ (fy_nonbond_j[jnk]*fy_nonbond_j[jnk])+(fz_nonbond_j[jnk]*fz_nonbond_j[jnk]));
			
		float fnet=fi+fj;
		if (cas==1)
			ftotal_nonbond+=fnet;
		else
			ftotal_nonbond-=fnet;
		}
    	System.out.println("Nonbond force  "+ftotal_nonbond);
	}
    
    public static void nonbond_energy1 () throws NumberFormatException, IOException
	{
		utotal_nonbonded=0;
		int iter=0;
		FileReader file = new FileReader("hexane.nonbond.para"); 
		BufferedReader buffer = new BufferedReader(file); 
		
		String string; 
		
		while((string = buffer.readLine()) != null) 
		{ 
			int temp1,temp2;
			iter++;
			String[] result = string.split(" ");
			String[] n= string.split(" ");
			int pos=0;
/*			if(result[0].equals("ATOM")) :CHANGE FORMAT WHEN NECCESSARY	*/
		
			if (iter>1) //assuming only 1 line header
			{
				for(int i=0;i<100;i++)
					{
						if(result[i].equals(""))
							count++;
						
						else
						{	
							n[pos]=result[i];
							pos++;
						}	
					
						if(pos==4)
							break;
					}
					
						int k1=-1;
						if((n[0].contains("C")))//putting c to be type 0
							k1=0;

						else// is h -> more if else if more type of atoms
							k1=1;
						
				        Float f = new Float(n[1]);
				        epsilon[k1]=f;
					
						Float f1 = new Float(n[2]);
						rho[k1]=f1;
						
						Float f2 = new Float(n[3]);
						q[k1]=f2;
				}
		}	
	}
    public static void nonbond_energy2 () throws NumberFormatException, IOException{
    	utotal_nonbonded=0;
		for (int j=2;j<= iter_nonbond ;j++)
		{
			float rx=(temp[nonbond1[j]][0]-temp[nonbond2[j]][0]);
			float ry=(temp[nonbond1[j]][1]-temp[nonbond2[j]][1]);
			float rz=(temp[nonbond1[j]][2]-temp[nonbond2[j]][2]);
							
			float distance=(float) Math.sqrt((rx*rx)+(ry*ry)+(rz*rz));
						
			//for any othr molecule must consider interactions other than c-c n c-h
			float tempe=0,tempr=0,tempq1=0,tempq2=0;
			if(atom[nonbond1[j]]=='C'&& atom[nonbond2[j]]=='C')
			{
				tempe=(float) Math.sqrt(epsilon[0]*epsilon[0]);
				tempr=rho[0];//c**
				tempq1=q[0];
				tempq2=q[0];
			}
			
			else 
			{
				tempe=(float) Math.sqrt(epsilon[0]*epsilon[1]);
				tempr=(float) Math.sqrt(rho[0]*rho[1]);	
				tempq1=q[1];
				tempq2=q[0];
			}
			
			float rho_r=(tempr/distance);
			float pow12=(float) Math.pow(rho_r,12);
			float pow6=(float) Math.pow(rho_r,6);
			float uvanderval=(float) (tempe*(pow12-2*pow6));
			
			float electrostatic=(float) ((332.0716*tempq1*tempq2)/distance);
			System.out.println("u nonbonded vand" +uvanderval);
			System.out.println("u nonbonded  electrostatic"+electrostatic);
			utotal_nonbonded=utotal_nonbonded+uvanderval+electrostatic;
		 }
		System.out.println("u nonbonded " +utotal_nonbonded);
		}
    
    public static void non_bonding() throws IOException
	{
		iter_nonbond=0;
		FileReader file = new FileReader("hexane.nonbonded"); 
		BufferedReader buffer = new BufferedReader(file); 
		
		String string; 
		
		while((string = buffer.readLine()) != null) 
		{ 
			iter_nonbond++;
			String[] result = string.split(" ");
			String[] n= string.split(" ");
			int pos=0;
/*			if(result[0].equals("ATOM")) :CHANGE FORMAT WHEN NECCESSARY	*/
		
			if (iter_nonbond>1) //assuming only 1 line header
			{
				for(int i=0;i<100;i++)
					{
						if(result[i].equals(""))
							count++;
						
						else
						{	
							n[pos]=result[i];
							pos++;
						}	
					
						if(pos==3)
							break;
					}
					
						nonbond_type[iter_nonbond]=Integer.parseInt(n[2]);
						nonbond1[iter_nonbond]=Integer.parseInt(n[0]);
						nonbond2[iter_nonbond]=Integer.parseInt(n[1]);
				}
			}
		System.out.println(iter_nonbond);
		}
    
    public static void force_dihed()
	{
    	float f1x =0,f1y =0, f1z = 0, f3x =0, f3y =0,  f3z =0, f2x = 0,f2y =0,f2z = 0;
    	int no=0;
    	
		ftotal_dihed=0;
		float faltuf1=0,faltuf2=0,faltuf3=0;
		for (int j=2;j<= iter_dihed ;j++)
		{
			// verification begins
			/*if(j==4)
				faltuf2=f2x;
			
			no++;
			System.out.println("n"+"  "+no);*/
			//verification ends
			
			float rx=-(position[dihed1[j]][0]-position[dihed2[j]][0]);
			float ry=-(position[dihed1[j]][1]-position[dihed2[j]][1]);
			float rz=-(position[dihed1[j]][2]-position[dihed2[j]][2]);
			
			float rx1=-(position[dihed2[j]][0]-position[dihed3[j]][0]);
			float ry1=-(position[dihed2[j]][1]-position[dihed3[j]][1]);
			float rz1=-(position[dihed2[j]][2]-position[dihed3[j]][2]);                       // check 2-3 or 3-2
			
			float rx2=-(position[dihed3[j]][0]-position[dihed4[j]][0]);
			float ry2=-(position[dihed3[j]][1]-position[dihed4[j]][1]);
			float rz2=-(position[dihed3[j]][2]-position[dihed4[j]][2]);                        // check 3-4 or 4-3
				
			//cross product.
			float Ax = (ry*rz1)-(ry1*rz);
			float Ay = (rz*rx1)-(rx*rz1);
			float Az = (rx*ry1)-(ry*rx1);
			float rAinv=(float) Math.sqrt((Ax*Ax)+(Ay*Ay)+(Az*Az));
		   
			float Bx = (ry1*rz2)-(ry2*rz1);
			float By = (rz1*rx2)-(rx1*rz2);
			float Bz = (rx1*ry2)-(ry1*rx2);
			float rBinv=(float) Math.sqrt((Bx*Bx)+(By*By)+(Bz*Bz));
			
			float Cx = (ry1*Az)-(Ay*rz1);
			float Cy = (rz1*Ax)-(rx1*Az);
			float Cz = (rx1*Ay)-(ry1*Ax);
			float rCinv=(float) Math.sqrt((Cx*Cx)+(Cy*Cy)+(Cz*Cz));
		
			float cosphi=(((Ax/rAinv)*(Bx/rBinv)) +((Ay/rAinv)*(By/rBinv))+((Az/rAinv)*(Bz/rBinv)));
			float sinphi=(((Cx/rCinv)*(Bx/rBinv)) +((Cy/rCinv)*(By/rBinv))+((Cz/rCinv)*(Bz/rBinv)));
			
			//System.out.println(cosphi);// cosphi was verified with vmd
			//System.out.println("sin"+" "+ sinphi);
			
			Bx *= rBinv;
			By *= rBinv;
			Bz *= rBinv;
			
			if (Math.abs(sinphi) > 0.1 )//check if this is correct.
		    {
				//System.out.println("form if");
				Ax *= rAinv;
				Ay *= rAinv;
				Az *= rAinv;
				
				float  dcosdAx = rAinv*(cosphi*Ax-Bx);
			    float  dcosdAy = rAinv*(cosphi*Ay-By);
			    float  dcosdAz = rAinv*(cosphi*Az-Bz);
			            
			    float  dcosdBx = rBinv*(cosphi*Bx-Ax);
			    float  dcosdBy = rBinv*(cosphi*By-Ay);
			    float  dcosdBz = rBinv*(cosphi*Bz-Az);
			    
			      K1 = K1/sinphi;

			      f1x = K1*(ry1*dcosdAz - rz1*dcosdAy);
			      f1y = K1*(rz1*dcosdAx - rx1*dcosdAz);
			      f1z = K1*(rx1*dcosdAy - ry1*dcosdAx);
			                                                   
			      f3x = K1*(rz1*dcosdBy - ry1*dcosdBz);
			      f3y = K1*(rx1*dcosdBz - rz1*dcosdBx);
			      f3z = K1*(ry1*dcosdBx - rx1*dcosdBy);
			                                                   
			      f2x = K1*(rz*dcosdAy - ry*dcosdAz + ry2*dcosdBz - rz2*dcosdBy);
			      f2y = K1*(rx*dcosdAz - rz*dcosdAx + rz2*dcosdBx - rx2*dcosdBz);
			      f2z = K1*(ry*dcosdAx - rx*dcosdAy + rx2*dcosdBy - ry2*dcosdBx);
		    }
			      
			else
		    {
				//System.out.println("form else");
				Cx *= rCinv;
				Cy *= rCinv;
				Cz *= rCinv;
				
				// check 
				float   dsindCx = rCinv*(sinphi*Cx-Bx);
				float   dsindCy = rCinv*(sinphi*Cy-By);
				float   dsindCz = rCinv*(sinphi*Cz-Bz);

				float  dsindBx = rBinv*(sinphi*Bx-Cx);
				float  dsindBy = rBinv*(sinphi*By-Cy);
				float  dsindBz = rBinv*(sinphi*Bz-Cz);
				
				   K1 = -K1/cosphi;
				   //System.out.println("K1="+K1);

				   f1x = K1*((ry1*ry1 + rz1*rz1)*dsindCx - rx1*ry1*dsindCy - rx1*rz1 *dsindCz);
				   f1y = K1*((rz1*rz1 + rx1*rx1)*dsindCy - ry1*rz1*dsindCz- ry1*rx1  *dsindCx);
				   f1z = K1*((rx1*rx1 + ry1*ry1)*dsindCz - rz1*rx1*dsindCx- rz1*ry1  *dsindCy);
				
				 f2x = (float) (K1*(-(ry1*ry + rz1*rz)*dsindCx +(2.0*rx1*ry - rx*ry1)*dsindCy +(2.0*rx1*rz - rx*rz1)*dsindCz +dsindBz*ry2 - dsindBy*rz2));
				 f2y = (float) (K1*(-(rz1*rz + rx1*rx)*dsindCy +(2.0*ry1*rz - ry*rz1)*dsindCz +(2.0*ry1*rx - ry*rx1)*dsindCx +dsindBx*rz2 - dsindBz*rx2));
				 f2z = (float) (K1*(-(rx1*rx + ry1*ry)*dsindCz +(2.0*rz1*rx - rz*rx1)*dsindCx +(2.0*rz1*ry - rz*ry1)*dsindCy +dsindBy*rx2 - dsindBx*ry2));
		    
				 f3x=K1*(dsindBy*rz1-dsindBz*ry1);
				 f3y=K1*(dsindBx*rz1-dsindBz*rx1);
				 f3z=K1*(dsindBx*ry1-dsindBy*rx1);
		   }
				// if(j==2)
				//		faltuf1=f1x;
				 
				//if(j==4)
				//		faltuf3=f3x;
			
			//System.out.println(f1x+" "+(f2x-f1x)+" "+(f3x-f2x));
			fx_dihed_i[dihed1[j]]+= f2x ;
			fy_dihed_i[dihed1[j]]+= f2y ;
			fz_dihed_i[dihed1[j]]+= f2z ;
			
			fx_dihed_j[dihed2[j]]+= f2x- f1x ;
			fy_dihed_j[dihed2[j]]+= f2y- f1y ;
			fz_dihed_j[dihed2[j]]+= f2z- f1z ;
			
			fx_dihed_k[dihed3[j]]+= f3x - f2x ;
			fy_dihed_k[dihed3[j]]+= f3y - f2y ;
			fz_dihed_k[dihed3[j]]+= f3z - f2z ;
			
			fx_dihed_l[dihed4[j]]+= -f3x  ;
			fy_dihed_l[dihed4[j]]+= -f3y  ;
			fz_dihed_l[dihed4[j]]+= -f3z  ;
			//System.out.println("\n");
		}
		
		for (int jnk=2;jnk<= iter_dihed ;jnk++)
		{
		
		float fi=(float) Math.sqrt((fx_dihed_i[jnk]*fx_dihed_i[jnk])+ (fy_dihed_i[jnk]*fy_dihed_i[jnk])+(fz_dihed_i[jnk]*fz_dihed_i[jnk]));
		float fj=(float) Math.sqrt((fx_dihed_j[jnk]*fx_dihed_j[jnk])+ (fy_dihed_j[jnk]*fy_dihed_j[jnk])+(fz_dihed_j[jnk]*fz_dihed_j[jnk]));
		float fk=(float) Math.sqrt((fx_dihed_k[jnk]*fx_dihed_k[jnk])+ (fy_dihed_k[jnk]*fy_dihed_k[jnk])+(fz_dihed_k[jnk]*fz_dihed_k[jnk]));
		float fl=(float) Math.sqrt((fx_dihed_l[jnk]*fx_dihed_l[jnk])+ (fy_dihed_l[jnk]*fy_dihed_l[jnk])+(fz_dihed_l[jnk]*fz_dihed_l[jnk]));
		
		float fnet=fi+fj+fk+fl;
		ftotal_dihed+=fnet;
		}
		K1=0;
		
		//System.out.println(faltuf2);
		System.out.println("ftotal_dihed"+" "+ftotal_dihed+"\n");
		
	}
   
    
    public double kamal=0;
    public static void main(String args[]) throws NumberFormatException, IOException {
    	
    	read();
     	// this function reads the x,y,z coordinate for each of the atom of hexane and stores it in the array "position"
     	/*
     	 position[i][0] denotes the x coordinate of "i"th atom.
     	 position[i][1] denotes the x coordinate of "i"th atom.
     	 position[i][2] denotes the x coordinate of "i"th atom.
     	 */
     	
    	bonds();
     	energy1();
     	energy2();
     	force();
     		
     	bends();
     	//energy_bend1 ();
     	//energy_bend2 ();
     	force_bend();
     	   
     	energy_dihed1();
     	energy_dihed2();
     	force_dihed();
     	
     	non_bonding();
     	nonbond_energy1();
     	nonbond_energy2();
     	nonbond_force(1);
    	
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
	    Viscosity = new JSlider(JSlider.HORIZONTAL,  0, 19 , 0);
	    Viscosity.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    
		    	valChange = true;
		    	int temp=((int)((JSlider) e.getSource()).getValue());
		    	current_viscosity=temp;
		    	kamal=current_viscosity;
		    //	kamal=kamal/10;
		    //	System.out.println(kamal);
		    	for (int lol=0;lol<=9;lol++)
		    	{
		    		
		    		if (temp/2==lol)
		    		{
		    	if (temp/2==0)
		    	{
		    		
		    		Transform3D tr = new Transform3D();
    				TransformGroup objtr=(TransformGroup)hm.get("medium_cylinder0");
    				System.out.println("temp=0");
    				System.out.println("medium_cylinder0"+objtr);
    				objtr.getTransform(tr);
    				tr.setScale(new Vector3d(1,0.8,1));
    				tr.setTranslation(new Vector3d(0,-0.1,0));
    				objtr.setTransform(tr);
    				
    				objtr=(TransformGroup)hm.get("medium_cylinder1");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder2");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder3");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder4");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder5");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder6");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder7");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder8");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder9");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
		    	}
		    	else if(temp/2 ==1)
		    	{
		    	//	System.out.println("Going in else");
		    		Transform3D tr = new Transform3D();
    			//	System.out.println("temp/5="+temp/5);
    				TransformGroup objtr=(TransformGroup)hm.get("medium_cylinder1");
    				System.out.println("temp=1");
    				System.out.println("medium_cylinder0"+objtr);
    				objtr.getTransform(tr);
    				tr.setScale(new Vector3d(1,0.8,1));
    				tr.setTranslation(new Vector3d(0,-0.1,0));
    				objtr.setTransform(tr);
    				
    				objtr=(TransformGroup)hm.get("medium_cylinder0");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder2");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder3");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder4");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder5");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder6");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder7");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder8");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder9");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
		    	}
		    	else if(temp/2 ==2)
		    	{
		    	//	System.out.println("Going in else");
		    		Transform3D tr = new Transform3D();
		    		System.out.println("temp=2");
    				TransformGroup objtr=(TransformGroup)hm.get("medium_cylinder2");
    				System.out.println("medium_cylinde2"+objtr);
    				System.out.println(objtr);
    				objtr.getTransform(tr);
    				tr.setScale(new Vector3d(1,0.8,1));
    				tr.setTranslation(new Vector3d(0,-0.1,0));
    				objtr.setTransform(tr);
    				
    				objtr=(TransformGroup)hm.get("medium_cylinder0");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder1");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder3");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder4");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder5");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder6");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder7");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder8");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder9");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
		    	}
		    	else if(temp/2 ==3)
		    	{
		    	//	System.out.println("Going in else");
		    		Transform3D tr = new Transform3D();
		    		System.out.println("temp=3");
    				TransformGroup objtr=(TransformGroup)hm.get("medium_cylinder3");
    				System.out.println("medium_cylinde3"+objtr);
    				System.out.println(objtr);
    				objtr.getTransform(tr);
    				tr.setScale(new Vector3d(1,0.8,1));
    				tr.setTranslation(new Vector3d(0,-0.1,0));
    				objtr.setTransform(tr);
    				
    				objtr=(TransformGroup)hm.get("medium_cylinder0");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder1");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder2");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder4");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder5");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder6");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder7");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder8");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder9");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
		    	}
		    	else if(temp/2 ==4)
		    	{
		    	//	System.out.println("Going in else");
		    		Transform3D tr = new Transform3D();
		    		System.out.println("temp=4");
    				TransformGroup objtr=(TransformGroup)hm.get("medium_cylinder4");
    				System.out.println("medium_cylinde4"+objtr);
    				System.out.println(objtr);
    				objtr.getTransform(tr);
    				tr.setScale(new Vector3d(1,0.8,1));
    				tr.setTranslation(new Vector3d(0,-0.1,0));
    				objtr.setTransform(tr);
    				
    				objtr=(TransformGroup)hm.get("medium_cylinder0");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder1");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder3");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder2");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder5");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder6");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder7");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder8");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder9");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
		    	}
		    	else if(temp/2 ==5)
		    	{
		    	//	System.out.println("Going in else");
		    		Transform3D tr = new Transform3D();
		    		System.out.println("temp=5");
    				TransformGroup objtr=(TransformGroup)hm.get("medium_cylinder5");
    				System.out.println("medium_cylinde5"+objtr);
    				System.out.println(objtr);
    				objtr.getTransform(tr);
    				tr.setScale(new Vector3d(1,0.8,1));
    				tr.setTranslation(new Vector3d(0,-0.1,0));
    				objtr.setTransform(tr);
    				
    				objtr=(TransformGroup)hm.get("medium_cylinder0");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder1");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder3");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder4");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder2");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder6");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder7");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder8");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder9");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
		    	}
		    	else if(temp/2 ==6)
		    	{
		    	//	System.out.println("Going in else");
		    		Transform3D tr = new Transform3D();
    			//	System.out.println("temp/5="+temp/5);
		    		System.out.println("temp=6");
    				TransformGroup objtr=(TransformGroup)hm.get("medium_cylinder6");
    				System.out.println("medium_cylinde2"+objtr);
    				System.out.println(objtr);
    				objtr.getTransform(tr);
    				tr.setScale(new Vector3d(1,0.8,1));
    				tr.setTranslation(new Vector3d(0,-0.1,0));
    				objtr.setTransform(tr);
    				
    				objtr=(TransformGroup)hm.get("medium_cylinder0");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder1");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder3");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder4");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder5");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder2");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder7");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder8");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder9");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
		    	}
		    	else if(temp/2 ==7)
		    	{
		    	//	System.out.println("Going in else");
		    		Transform3D tr = new Transform3D();
		    		System.out.println("temp=7");
    			//	System.out.println("temp/5="+temp/5);
    				TransformGroup objtr=(TransformGroup)hm.get("medium_cylinder7");
    				System.out.println("medium_cylinde2"+objtr);
    				System.out.println(objtr);
    				objtr.getTransform(tr);
    				tr.setScale(new Vector3d(1,0.8,1));
    				tr.setTranslation(new Vector3d(0,-0.1,0));
    				objtr.setTransform(tr);
    				
    				objtr=(TransformGroup)hm.get("medium_cylinder0");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder1");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder3");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder4");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder5");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder6");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder2");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder8");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder9");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
		    	}
		    	else if(temp/2 ==8)
		    	{
		    		System.out.println("temp=8");
		    	//	System.out.println("Going in else");
		    		Transform3D tr = new Transform3D();
    			//	System.out.println("temp/5="+temp/5);
    				TransformGroup objtr=(TransformGroup)hm.get("medium_cylinder8");
    				System.out.println("medium_cylinde2"+objtr);
    				System.out.println(objtr);
    				objtr.getTransform(tr);
    				tr.setScale(new Vector3d(1,0.8,1));
    				tr.setTranslation(new Vector3d(0,-0.1,0));
    				objtr.setTransform(tr);
    				
    				objtr=(TransformGroup)hm.get("medium_cylinder0");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder1");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder3");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder4");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder5");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder6");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder7");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder2");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder9");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
		    	}
		    	else if(temp/2 ==9)
		    	{
		    		System.out.println("temp=9");
		    	//	System.out.println("Going in else");
		    		Transform3D tr = new Transform3D();
    			//	System.out.println("temp/5="+temp/5);
    				TransformGroup objtr=(TransformGroup)hm.get("medium_cylinder9");
    				System.out.println("medium_cylinde2"+objtr);
    				System.out.println(objtr);
    				objtr.getTransform(tr);
    				tr.setScale(new Vector3d(1,0.8,1));
    				tr.setTranslation(new Vector3d(0,-0.1,0));
    				objtr.setTransform(tr);
    				
    				objtr=(TransformGroup)hm.get("medium_cylinder0");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder1");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder3");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder4");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder5");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder6");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder7");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder8");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
	    			
	    			objtr=(TransformGroup)hm.get("medium_cylinder2");
	    			objtr.getTransform(tr);
	    			tr.setScale(new Vector3d(0,0,0));
	    			objtr.setTransform(tr);
		    	}
		    		}
		    		else
		    		{
		    			System.out.println("temp/5="+temp/5);
		    	/*		Transform3D tr = new Transform3D();
		    			TransformGroup objtr=(TransformGroup)hm.get("medium_cylinder2");
		    			objtr.getTransform(tr);
		    			tr.setScale(new Vector3d(0,0,0));
		    			objtr.setTransform(tr);
		    			
		    			objtr=(TransformGroup)hm.get("medium_cylinder2");
		    			objtr.getTransform(tr);
		    			tr.setScale(new Vector3d(0,0,0));
		    			objtr.setTransform(tr);
		    			
		    			tr = new Transform3D();
		    			objtr=(TransformGroup)hm.get("medium_cylinder2");
		    			objtr.getTransform(tr);
		    			tr.setScale(new Vector3d(0,0,0));
		    			objtr.setTransform(tr);*/
		    		}
		    	}
		    	/*for(int f=0;f<Total_cylinder;f++)
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
		    				TransformGroup objtr=(TransformGroup)hm.get("medium_cylinder"+temp);
		    				System.out.println("object here 5"+objtr);
		    				objtr.getTransform(tr);
		    				tr.setScale(new Vector3d(1,1,1));
		    				tr.setTranslation(new Vector3d(0,-0.14,0.1));
		    				//tr.setTranslation(new Vector3d(0,-0.1,0));
		    				objtr.setTransform(tr);
		    			}
		    			else
		    			{
		    				Transform3D tr = new Transform3D();
		    				System.out.println("temp="+temp);
		    				TransformGroup objtr=(TransformGroup)hm.get("medium_cylinder"+temp);
		    				objtr.getTransform(tr);
		    				tr.setScale(new Vector3d(1,0.8,1));
		    				tr.setTranslation(new Vector3d(0,-0.1,0));
		    				objtr.setTransform(tr);
		    				
		    			}
		    		}
		    		else
		    		{
		    			Transform3D tr = new Transform3D();
		    			TransformGroup objtr=(TransformGroup)hm.get("medium_cylinder"+0);
		    			System.out.println("here 2 object"+objtr);
		    			objtr.getTransform(tr);
		    			tr.setScale(new Vector3d(0,0,0));
		    			objtr.setTransform(tr);
		    		}
		    	}*/
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
			    	magnet_y_translate=(float) temp;
			    	if(temp>0)
			    	{
			    		tr.setTranslation(new Vector3d(magnet_x_translate,magnet_y_translate/5,0));
			    	}
			    	else
			    	{
			    		tr.setTranslation(new Vector3d(magnet_x_translate,magnet_y_translate/3.5,0));	
			    	}
			    	
			    	objtr.setTransform(tr);
			    	
			    	energy2();
			    	//energy_bend2();
			     	force_bend();
			     	   
			    
			     	try {
						energy_dihed2();
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			     	force_dihed();
			     
			     	     	
			     	//nonbond_energy();
			     	//nonbond_force();
			    	
			    	force();
			    	move();
			    	
			    	Force.setState(1);
			    	Force.setCurrentValue((float)temp,(float)(((utotal+200*utotal_dihed)/2)));        
			        Force.addGraphValue((float)((utotal+utotal_dihed)));//-freeBody.getDisplacment()*500));
			        Force.drawGraph();
			        Force.setYScale(10);
			   
			
			    	Energy.setState(1);
			    	float tempu=(float) ((ftotal_dihed/10000)+(ftotal/10));
			    	Energy.setCurrentValue((float)temp,(float)tempu/2);        
			        Energy.addGraphValue((float)ftotal/100);//-freeBody.getDisplacment()*500));
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
 	/* diatomic = new JButton("Diatomic");
 	/* diatomic.addChangeListener(new ChangeListener() {
	    public void stateChanged(ChangeEvent e) {
	    	number_of_atoms=2;
	    	enableStage(1);
	    	}
     });*/
 	/* diatomic.addActionListener(new java.awt.event.ActionListener() {
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
     */

     quadatomic = new JButton("Hexane");
     quadatomic.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
        	 number_of_atoms=2;
 	    	enableStage(3);
         }
     });
     quadatomic.setEnabled(true);
     fourth.add(quadatomic);
     
     /*Start_experiment = new JButton("Start Expe.");
     Start_experiment.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
        	 number_of_atoms=2;
 	    	enableStage(9);
 	     }
     });
     Start_experiment.setEnabled(true);
     fourth.add(Start_experiment);*/
     

     
	 
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
  /*  		center2.setEnabled(true);
    		center3.setEnabled(false);
    		center4.setEnabled(false);
    		///diatomic.setEnabled(true);
    		//triatomic.setEnabled(true);
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
     */   
    	}
    /*	else if(s==2)// for triatomic
    	{
    		center2.setEnabled(true);
    		center3.setEnabled(true);
    		center4.setEnabled(false);
    		//diatomic.setEnabled(true);
    		//triatomic.setEnabled(true);
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
    		
    	}*/
    	else if(s==3)// for PolyAtomic
    	{
    		center2.setEnabled(false);
    		center3.setEnabled(false);
    		center4.setEnabled(false);
    		//diatomic.setEnabled(false);
    		//triatomic.setEnabled(false);
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
    		//Start_experiment.setEnabled(true);
    
    
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
        	
     /***   	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("medium_cylinder");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0,0,0));
    //    	tr.setTranslation(new Vector3d(center_x[4],center_y[4],0));
        	objtr.setTransform(tr);
  */
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
    		
    		center2.setEnabled(false);
    		center3.setEnabled(false);
    		center4.setEnabled(false);
    		//diatomic.setEnabled(false);
    		//triatomic.setEnabled(false);
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
    		//Start_experiment.setEnabled(false);
    		number_of_atoms=4;
    	
    		//Transform3D tr;
    		//TransformGroup objtr;
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
        	
   /***     	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("medium_cylinder");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(1,1,1));
        	tr.setTranslation(new Vector3d(0.4,0,0));
        	objtr.setTransform(tr);
  */
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
        	
 /***       	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("medium_cylinder");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0.5,1,0.5));
        	tr.setTranslation(new Vector3d(topmost_atom_coordinates[0]*0.6,topmost_atom_coordinates[1]*0.6+0.04+displacement,topmost_atom_coordinates[2]*0.6));
        	objtr.setTransform(tr);
  */
        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("magneticbead");
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(1,1,1));
          	tr.setTranslation(new Vector3d(topmost_atom_coordinates[0]*0.6,topmost_atom_coordinates[1]*0.6+0.08+displacement,topmost_atom_coordinates[2]*0.6));
        	objtr.setTransform(tr);
  

        	
        	
        	
    	}
    
    	else if(s==3)// for Start_Experiment
    	{
    		
      	}
  
    	
    	else if (s==4)// Second Atom center Adjusting
    	{

   /* 		double temp_atom_x=center_x[2];
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

        */	
        	
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
    		//diatomic.setEnabled(false);
    		//triatomic.setEnabled(false);
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
          	//System.out.println("SCALING IS "+Scaling + " "+ temp_Scaling + " "+current_viscosity);
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
        	
/***        	tr = new Transform3D();
        	objtr=(TransformGroup)hm.get("medium_cylinder"+number);
        	objtr.getTransform(tr);
        	tr.setScale(new Vector3d(0.5,1,0.5));
        	tr.setTranslation(new Vector3d(topmost_atom_coordinates[0]*temp_Scaling,topmost_atom_coordinates[1]*Scaling+0.04+displacement,topmost_atom_coordinates[2]*temp_Scaling));
        	objtr.setTransform(tr);
  ***/
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




