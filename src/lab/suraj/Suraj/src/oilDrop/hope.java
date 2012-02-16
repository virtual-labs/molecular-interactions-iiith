/*
 
 * $Revision: 1.5 $
 * $Date: 2007/08/01 21:37:58 $
 * $State: Exp $
 */


package oilDrop;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.media.j3d.Group;
import javax.media.j3d.LineArray;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Switch;
import javax.media.j3d.Text3D;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.swing.BorderFactory;
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
import javax.vecmath.Point3f;
import javax.vecmath.TexCoord2f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import iiit.vlabdemos.common.HorizontalGraph;
import iiit.vlabdemos.common.Resources;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.geometry.Text2D;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;
import iiit.vlabdemos.common.HorizontalGraph;

import iiit.vlabdemos.common.HorizontalGraph;
import iiit.vlabdemos.common.J3DShape;
//import iiit.vlabdemos.common.MyButton;
//import iiit.vlabdemos.common.PanelWindowWrapper;
import iiit.vlabdemos.common.Resources;
import iiit.vlabdemos.common.StopWatch;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;

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
//import java.io.DataInputStream;
//import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.media.j3d.Group;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Switch;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
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
public class hope extends javax.swing.JPanel {
//  Variables declaration - do not modify//GEN-BEGIN:variables
	
	static float ftotal_nonbond=0;
    static float utotal_nonbonded=0;
    
    static float first=0;
    static float second=0;
    
    static float distance_single=0;
    static float temprho1=(float) 0.10;;
    static float temprho2=(float) 0.10;;
    static float tempepselon=(float) 0.10;;
	
//////////////////////////GUI componenet ///////////////////////////
    private javax.swing.JPanel simulationPanel;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JPanel topPanel;  
	private javax.swing.JPanel rightPanel;
   
////////////////////////////Java3D componenet ///////////////////////////
	
    private SimpleUniverse 	univ = null;			// Simple Universe Java3D
    private BranchGroup 	scene = null;			// BranchGroup Scene graph
    private Switch 			switchGroup=null;
    
	private HorizontalGraph		Energy =null;
	private HorizontalGraph		Force =null;
    
   // private  javax.swing.JButton field =  null;
    
    private int stage=0;
    private boolean elec_Field =false;
    private boolean timer_on=false;
    private Timer timer=null;				// Timer for simulation
    private double lapase=0;
    private float  r_clock=0;
    private boolean fallOrRise=false;
    
    int v_flag = 16;
   
    HashMap hm = new HashMap();
    
    private double y_ref=0.0f;
    private double creaseAngle = 60.0;
    
    Point2D m_acc=new Point2D.Float(6, 4.8f); //6, 4.8f|9, 7.1f|0, 7.9f

    ArrayList<OilDrop> m_ions = new ArrayList<OilDrop>();
    ArrayList<OilDrop> m_ions2 = new ArrayList<OilDrop>();
    //ArrayList<OilDrop> m_charges = new ArrayList<OilDrop>();
    //ArrayList<Integer> visible_ions = new ArrayList<Integer>();
    int visible_ions[]= new int[5];
    
                               
    
    public BranchGroup createSceneGraph(Canvas3D canvas) {
	// Create the root of the branch graph
	   
    	BranchGroup objRoot = new BranchGroup();
		objRoot.setCapability( BranchGroup.ALLOW_CHILDREN_EXTEND );
		objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		objRoot.setCapability( BranchGroup.ALLOW_DETACH );
	
		// Set the ENvironment
		objRoot.addChild(CreateLabSetup());
		objRoot.addChild(createIonsSwitchGroup());
	//	objRoot.addChild(setTextInstructions());
		objRoot.addChild(createTextureBox("table.jpg",new Vector3d(-0.45,-0.49, 0.2),new Vector3d(1,-.03,2), new Color3f(1f, 1f, 1f)));
		
		//objRoot.addChild(createBox(new Vector3d(-0.7,0.6, -3.7),new Vector3d(1.7,.4,.01), new Color3f(.5f, .5f, .5f)));
		
		/////// Light ////////////////////////
		Color3f light1Color = new Color3f(1f, 1f, 1f);
		
	    BoundingSphere bounds =  new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
	
	    Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
	
	    DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
	
	    light1.setInfluencingBounds(bounds);    
	    objRoot.addChild(light1);
	
	    AmbientLight ambientLight =  new AmbientLight(new Color3f(.5f,.5f,.5f));
	
	    ambientLight.setInfluencingBounds(bounds);
	
	    objRoot.addChild(ambientLight);
		
		return objRoot;

    }



    private Canvas3D createUniverse(Container container) {
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
	
		ViewingPlatform viewingPlatform = univ.getViewingPlatform();
		
		// This will move the ViewPlatform back a bit so the
		// objects in the scene can be viewed.
	    univ.getViewingPlatform().setNominalViewingTransform();
	
		// Ensure at least 5 msec per frame (i.e., < 200Hz)
		univ.getViewer().getView().setMinimumFrameCycleTime(5);
	
		return c;
    }
    
    
    
    private void destroy() {
        univ.cleanup();
    }

    
    private Group createBox(Vector3d pos,Vector3d scale,Color3f colr) {
        // Create a transform group node to scale and position the object.
    	//new Point3d(0.0, 0.0, 0.0)
        Transform3D t = new Transform3D();
        //t.set(scale, pos);
        t.setScale(scale);        
        t.setTranslation(pos);
        
        TransformGroup objTrans = new TransformGroup(t);

        // Create a simple shape leaf node and add it to the scene graph
        //Shape3D shape = new Box(1.0, 1.0, 1.0);
       

        // Create a new ColoringAttributes object for the shape's
        // appearance and make it writable at runtime.
        Appearance app = new Appearance();
        ColoringAttributes ca = new ColoringAttributes();
        ca.setColor(colr);
        app.setCapability(app.ALLOW_COLORING_ATTRIBUTES_WRITE);
        app.setColoringAttributes(ca);
        
        objTrans.addChild(new Box(1.0f, 1.0f, 1.0f,app));
        
  

        return objTrans;
      }
    
     private Group createTextureBox(String texfile,Vector3d pos,Vector3d scale,Color3f colr) {
        // Create a transform group node to scale and position the object.
    	//new Point3d(0.0, 0.0, 0.0)
        Transform3D t = new Transform3D();
        //t.set(scale, pos);
        t.setScale(scale);        
        t.setTranslation(pos);
        
        TransformGroup objtrans = new TransformGroup(t);
        objtrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objtrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        // Create a simple shape leaf node and add it to the scene graph
        //Shape3D shape = new Box(1.0, 1.0, 1.0);       

        // Create a new ColoringAttributes object for the shape's
        // appearance and make it writable at runtime.
        Appearance app = new Appearance();
        ColoringAttributes ca = new ColoringAttributes();
        ca.setColor(colr);
        //app.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
        app.setColoringAttributes(ca);
        
        Texture tex = new TextureLoader(Resources.getResource(texfile),TextureLoader.BY_REFERENCE | TextureLoader.Y_UP,this).getTexture();
	    app.setTexture(tex);
	    TextureAttributes texAttr = new TextureAttributes();
	    texAttr.setTextureMode(TextureAttributes.MODULATE);
	    app.setTextureAttributes(texAttr);
	    
	    objtrans.addChild(new Box(1.0f, 1.0f, 1.0f,Box.GENERATE_TEXTURE_COORDS | Box.GENERATE_TEXTURE_COORDS_Y_UP, app));
        
        return objtrans;
      }
    
      private Group createSphere(Vector3d pos,Vector3d scale,Color3f colr) {
        // Create a transform group node to scale and position the object.
    	  
    	Appearance ap =new Appearance();
    	ColoringAttributes ca = new ColoringAttributes();
   	    ca.setColor(colr);
   	    ap.setColoringAttributes(ca);
   	    //ap.setMaterial(new Material(new Color3f(0.0f,0.0f,0.0f),new Color3f(0.0f,0.0f,0.0f),new Color3f(0.6f,0.5f,0.0f),new Color3f(1.0f,1.0f,1.0f),70.f));
   	    
   	    Transform3D tr = new Transform3D();
   	    TransformGroup objTrans = new TransformGroup();
   	    objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
   	    objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	tr.setScale(scale);
    	tr.setTranslation(pos);
    	objTrans.setTransform(tr);
    	objTrans.addChild( new Box(0.03f,0.03f,0.03f,ap));

        return objTrans;
      }
    
      
      private Group createLine(Point3d p1, Point3d p2,Vector3d pos, Vector3d scale,Color3f colr) { //,
          // Create a transform group node to scale and position the object.
      	
      	//Anti-clockwise
    	    int size=2;
		    
		    LineArray line =new LineArray(size,LineArray.COORDINATES | LineArray.COLOR_3);
		    
		    for(int i=0; i< size; i++)    	line.setColor(i,colr);
		    
		    
		    Point3d line_verts[] = new Point3d[size];
		    
		    line_verts[0] = p1;
		    line_verts[1] = p2;
		    
		    line.setCoordinates(0, line_verts);
		    
		    Appearance app = new Appearance();
		    LineAttributes la = new LineAttributes();
		    la.setLineWidth(2);
		    app.setLineAttributes(la);
		 		    
		    Shape3D shape = new Shape3D(line,app);
		    
		    Transform3D ts = new Transform3D();
	   	    TransformGroup objTrans = new TransformGroup();
	   	    objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	   	    objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	    	ts.setScale(scale);
	    	ts.setTranslation(pos);
	    	objTrans.setTransform(ts);
	    	objTrans.addChild(shape);
  	     
		    return objTrans;
      }
        	
        
    
/*    private BranchGroup createPlottedGraph(Vector3d pos,Vector3d scale,Color3f colr) {
    	
    	graphBG = new BranchGroup();
    	graphBG.setCapability(BranchGroup.ALLOW_DETACH );
    	graphBG.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
    	graphBG.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

    	// Create a transform group node to scale and position the object.
    	
        Transform3D t = new Transform3D();        
        t.setScale(scale);        
        t.setTranslation(pos);
        
        TransformGroup objTrans = new TransformGroup(t);

        // Create a simple shape leaf node and add it to the scene graph
        
        graphBG.addChild(objTrans);

        return graphBG;
      }*/
    
     private Group loadObjectFile(String objfile,Vector3d pos,Vector3d scale,Vector3d rot,Color3f colr) {
    	
    	
    	 int flags = ObjectFile.RESIZE;
    	 flags |= ObjectFile.TRIANGULATE;
    	 flags |= ObjectFile.STRIPIFY;
    	 
    	 ObjectFile f = new ObjectFile(flags,
    			 (float)(creaseAngle * Math.PI / 180.0));
    	 Scene s = null;
    	 URL filename = Resources.getResource(objfile);
    	 //pendulum
    	/* try {
    	//	  s = f.load(filename);
    		  
    		  }	catch (FileNotFoundException e) {
    			  System.err.println(e);
    			  System.exit(1);
    		} catch (ParsingErrorException e) {
    			System.err.println(e);
    			System.exit(1);
    			}catch (IncorrectFormatException e) {
    				System.err.println(e);
    				System.exit(1);
    				}
    	*/		
    			Transform3D t = new Transform3D();
    			float rad = (float)Math.PI/180;
    			t.rotX(rad*rot.x);
    	        t.setScale(scale);        
    	        t.setTranslation(pos);
    	        
    	        TransformGroup objTrans = new TransformGroup(t);
    	    	objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	    	objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	        
    	//		BranchGroup bg = s.getSceneGroup();
    			
//    			Shape3D newShape = (Shape3D) bg.getChild(0);
//    			bg.removeChild(0);
//    			Appearance app = new Appearance();
//    			Color3f objColor = new Color3f(0.7f, 0.2f, 0.8f);
//    			Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
//    			app.setMaterial(new Material(objColor, black, objColor, black, 80.0f));
//
//    			newShape.setAppearance(app);
//
//    			objTrans.addChild(newShape);
    			
//    			Map<String, Shape3D> nameMap = s.getNamedObjects();   
//    			   
//    			for (String name : nameMap.keySet()) {  
//    			        System.out.printf("Name: %s\n", name);   
//    			} 
    			//bg.addChild(trans);
    			//objTrans.addChild(bg);
    //			objTrans.addChild(s.getSceneGroup());
        

         return objTrans;
      }
     
     private Group CreateLabSetup() {
    	 
    	TransformGroup objtrans= new TransformGroup();
 	    Transform3D tr = new Transform3D();
 	    float rad = (float)Math.PI/180;
 		tr.rotX(rad*5);
 		objtrans.setTransform(tr);
 		  
      	  
 		//objtrans.addChild("table.jpg",new Vector3d(-0.2,-.4, -0.1),new Vector3d(.6,.3,1), new Vector3d(0,0,0),new Color3f(1, 0, 0));
 		
 		//////////////////////////Oil droping Tube/////////////////////////////////////////
 		Appearance ap = new Appearance ();
 	    //ap.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE); 
 	    ColoringAttributes ca =new ColoringAttributes();
 	    //ca.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);			    
	    //ca.setCapability(ColoringAttributes.ALLOW_COLOR_READ);
 	    ca.setColor(new Color3f(0.501f,0.501f,0.501f));
 	    ap.setColoringAttributes(ca);
 	    //hm.put("tubeApp",ap);
 	    
 	    TransparencyAttributes ta = new TransparencyAttributes();
	    ta.setTransparencyMode (TransparencyAttributes.FASTEST);
	    ta.setTransparency (0.3f);
	    ap.setTransparencyAttributes (ta);
	        
 		
 	    tr = new Transform3D();
 	    TransformGroup tg = new TransformGroup();
 	    //tr.rotZ(rad*90);
 	    tr.setTranslation(new Vector3d(-0.0785,-0.07,0.5));
 	    tg.setTransform(tr);
 	    
 	    /* cylinder in which molecelue drops */
 	    Cylinder cylinder = new Cylinder(0.091f,0.148f,ap);
 	    
 	   Appearance ap2 = new Appearance ();
	    //ap.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE); 
	    ColoringAttributes ca2 =new ColoringAttributes();
	    //ca.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);			    
	    //ca.setCapability(ColoringAttributes.ALLOW_COLOR_READ);
	    ca2.setColor(new Color3f(0.501f,0.01f,0.01f));
	    ap2.setColoringAttributes(ca2);
 	    
 	   TransparencyAttributes ta1 = new TransparencyAttributes();
	    ta1.setTransparencyMode (TransparencyAttributes.FASTEST);
	    ta1.setTransparency (0.3f);
	    ap2.setTransparencyAttributes (ta1);
	    
 	    Cylinder cylinder_in = new Cylinder(0.031f,0.148f,ap2);
 	    tg.addChild(cylinder);
 	   tg.addChild(cylinder_in);
 	    
 	    objtrans.addChild(tg);
 	    //////////////////////////////////////////////////////////////////////
 	    
 	    Transform3D trx = new Transform3D();
	    trx.rotZ(rad*-90);
	    tr =new Transform3D();
	   
	    tr.setScale(new Vector3d(0.1,0.15,0.1));
	    tr.setTranslation(new Vector3d(.25,-0.14, 0.0));
	    tr.mul(trx);
	   
	    tg = (TransformGroup) loadObjectFile("resources/geometry/capa_Scene.obj",new Vector3d(2,1, 0.0),new Vector3d(0.1,0.1,0.4),new Vector3d(0,0,0), new Color3f(0.0f, 0.5f, 0.0f));
	    tg.setTransform(tr);
	    
	    objtrans.addChild(tg);
	    
	    
	    ///////////////////////////////// X-ry and Micrscope////////////////////////////////////////
	    trx = new Transform3D();
	    trx.rotZ(rad*90);
	    tr =new Transform3D();
	   
	    tr.setScale(new Vector3d(0.1,0.1,0.3));
	    tr.setTranslation(new Vector3d(-.5,-0.11, 0.1));
	    tr.mul(trx);
	   
	    tg = (TransformGroup) loadObjectFile("resources/geometry/capa_Scene.obj",new Vector3d(2,0, 0.0),new Vector3d(0.1,0.1,0.4),new Vector3d(0,0,0), new Color3f(0.0f, 0.5f, 0.0f));
	    tg.setTransform(tr);
	    
	    objtrans.addChild(tg);
	    ///////////////////////////// Power sys //////////////////////////
 	    
	    objtrans.addChild(loadObjectFile("resources/geometry/verreEau.obj",new Vector3d(-.465,0, 0.2),new Vector3d(0.1,.22,0.1),new Vector3d(0,0,0), new Color3f(1, 0, 0)));
	    //objtrans.addChild(loadObjectFile("resources/geometry/petitReveil.obj",new Vector3d(-.55,-0.15, 0.3f),new Vector3d(0.08,.08,0.1),new Vector3d(0,0,0), new Color3f(1, 0, 0)));
 	    
	    tg = new TransformGroup();
  		tr = new Transform3D();
  		tr.rotX(rad*-5);
  	    tr.setScale(new Vector3d(1.2,1.7,1));        
  		tr.setTranslation(new Vector3d(-0.52,-0.01, .2f));
  		  
  		tg.setTransform(tr);
  		
  		PowerSys pw = new PowerSys();
  		tg.addChild(pw);
  		 
  		objtrans.addChild(tg);
  		
  		//objtrans.addChild(createBox(new Vector3d(-.26+.1, -0.01, .2f),new Vector3d(.16/2.9,.01,.01), new Color3f(0f, 0.5f, 0f)));
  		//objtrans.addChild(createBox(new Vector3d(.036, -0.01, .2f),new Vector3d(.117/2.9,.01,.01), new Color3f(0f, 0.5f, 0f)));
	    
  		objtrans.addChild(createBox(new Vector3d(-.15+0.15/2-0.015, -0.22, .2f),new Vector3d(.3/2.9,.01,.01), new Color3f(.5f, 0.0f, 0f)));
 	    
	    ///////////////////////   Microscopic View /////////////////
	   
	    ap = new Appearance ();
	    cylinder = new Cylinder(0.1f,0.1f,ap);
	    
 	   // ap.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE); 
 	    ca =new ColoringAttributes();
 	    //ca.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);			    
	    //ca.setCapability(ColoringAttributes.ALLOW_COLOR_READ);
 	    ca.setColor(new Color3f(0.701f,0.701f,0.701f));
 	    ap.setColoringAttributes(ca);
 	    //hm.put("microApp",ap);  
	    
	    
 	    
	    tr = new Transform3D();
 	    tg = new TransformGroup();
 	    tr.rotX(rad*95);
 	    tr.setScale(new Vector3d(2,0.1,2));
 	    tr.setTranslation(new Vector3d(0.5,0.1,0.5));
 	    tg.setTransform(tr);
 	    
 	    
 	   
 	    tg.addChild(cylinder);
 	    
 	    objtrans.addChild(tg); 
 	    
 	    /////////////////////////////// Oil Drop /////////////////////////////
 	    
 	    ap =new Appearance();
	    ca = new ColoringAttributes();
	    ca.setColor(new Color3f(0.0f,0.0f,0.8f));
	    ap.setColoringAttributes(ca);
	    ap.setMaterial(new Material(new Color3f(0.6f,0.5f,0.0f),new Color3f(0.0f,0.0f,0.0f),new Color3f(0.6f,0.5f,0.0f),new Color3f(1.0f,1.0f,1.0f),70.f));
	    
	    Sphere sphere = new Sphere(0.03f,ap);
	    Sphere sphere2 = new Sphere(0.03f,ap);
	    
	    tr = new Transform3D();
 	    tg = new TransformGroup();
 	    tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
 	    tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
 	    tr.setScale(new Vector3d(.2,.2,.2));
 	    tr.setTranslation(new Vector3d(-0.1,0.1,0.0));
 	    tg.setTransform(tr);
 	    tg.addChild(sphere);
	    
	    objtrans.addChild(tg);
	    
	    tr = new Transform3D();
	    TransformGroup tg1 = new TransformGroup();
	    tg1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
 	    tg1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
 	    tr.setScale(new Vector3d(.2,.2,.2));
	    tr.setTranslation(new Vector3d(-0.11,0.1,0.0));
	    tg1.setTransform(tr);
 	    tg1.addChild(sphere2);
	    
	    objtrans.addChild(tg1);
	    
	    OilDrop oil= new OilDrop(new Vector3d(-0.1,0.3,0.0),new Vector3d(0,-1,0) ,new Vector3d(.2,.2,.2), tg , new Vector3d(-0.2,-0.3,0.0) ,new Vector3d(0.2,0.4,.0));
	    OilDrop oilnew= new OilDrop(new Vector3d(-0.1,0.3+0.2,0.0),new Vector3d(0,-1,0) ,new Vector3d(.2,.2,.2), tg1 , new Vector3d(-0.2,-0.3,0.0) ,new Vector3d(0.2,0.4,.0));
	    hm.put("oil",oil);
	    hm.put("oilnew",oilnew);
	    y_ref = -0.25;
	    //oil.transgp.addChild(sphere);
	    /////////////////////////////////////////////////////////////////
	    /////////////////////////////// Oil Drop /////////////////////////////
 	    
 	    ap =new Appearance();
	    ca = new ColoringAttributes();
	    ca.setColor(new Color3f(0.0f,0.0f,0.8f));
	    ap.setColoringAttributes(ca);
	    ap.setMaterial(new Material(new Color3f(0.6f,0.5f,0.0f),new Color3f(0.0f,0.0f,0.0f),new Color3f(0.6f,0.5f,0.0f),new Color3f(1.0f,1.0f,1.0f),70.f));
	    
	    sphere = new Sphere(0.03f,ap);
	    sphere2 = new Sphere(0.03f,ap);
	    tr = new Transform3D();
 	    tg = new TransformGroup();
 	    tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
 	    tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
 	    tr.setScale(new Vector3d(.4,.4,.4));
 	    tr.setTranslation(new Vector3d(0.47,0.32,0.6));
 	    tg.setTransform(tr);
 	    //tg1.setTransform(tr);
 	    tg.addChild(sphere);
	    
	    objtrans.addChild(tg);
	    

	    tg1 = new TransformGroup();
	    tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
 	    tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	    tr.setTranslation(new Vector3d(0.47,0.32,0.6));
	    tg1.setTransform(tr);
 	    tg1.addChild(sphere2);
 	    objtrans.addChild(tg1);
	    
/*	    TransformGroup tg2n = new TransformGroup();
	    
	    tr.setTranslation(new Vector3d(0.47,0.32,0.6));
	    tg2n.setTransform(tr);
 	    tg2n.addChild(sphere2);
	    
	    objtrans.addChild(tg2n);
	*/    
	    OilDrop oil2= new OilDrop(new Vector3d(0.47,0.32,0.6),new Vector3d(0,-1,0) ,new Vector3d(.2,.2,.2), tg , new Vector3d(-0.2,-0.5,0.0) ,new Vector3d(0.2,0.8,.0));
	    OilDrop oil2new= new OilDrop(new Vector3d(0.47,0.32,0.6),new Vector3d(0,-1,0) ,new Vector3d(.2,.2,.2), tg1 , new Vector3d(-0.2,-0.5,0.0) ,new Vector3d(0.2,0.8,.0));
	    hm.put("oil2",oil2);
	    hm.put("oil2new",oil2new);
	    
	    // Cover the Oil by putting the Black color Box
	    objtrans.addChild(createBox(new Vector3d(.45, 0.37, 0.7f),new Vector3d(.05,.07,.005), new Color3f(0.0f, 0.0f, 0.0f)));
	    objtrans.addChild(createBox(new Vector3d(.45, -0.195, 0.7f),new Vector3d(.04,.13,.005), new Color3f(0.0f, 0.0f, 0.0f)));
       
	    //////////////////////////////////////////// Sniper View
	    
	    SniperView micro_view = new SniperView(new Vector3d(-0.15,-0.1,0.3),new Vector3d(0.1,0.1,0.3),0.01f,new Color3f(1f,1f,1f));
	    
	    tr = new Transform3D();
 	    tg = new TransformGroup();
 	    tr.rotX(rad*-5);
 	    //tr.setScale(new Vector3d(.2,.2,.2));
 	    tr.setTranslation(new Vector3d(0.45,0.1,0.5));
 	    tg.setTransform(tr);
 	    tg.addChild(micro_view);
 	    
	    objtrans.addChild(tg);
	    
	      			    
        return objtrans;
      }
     

     
     private Group createIonsSwitchGroup()
     {
    	 //responsible for various positions
    	Vector3d ions_pos[] = new Vector3d[10];
    	Vector3d ions_posn[] = new Vector3d[10];
    	Vector3d ions_vel[] = new Vector3d[10];
    	ions_pos[0] = new Vector3d(0.4,0.2,0.6); ions_vel[0]=new Vector3d(.1,.5,0.0);
    	ions_pos[1] = new Vector3d(0.45,0.1,0.6);ions_vel[1]=new Vector3d(.5,-.5,0.0);
    	ions_pos[2] = new Vector3d(0.4,0.0,0.6); ions_vel[2]=new Vector3d(.5,.2,0.0);
    	ions_pos[3] = new Vector3d(0.5,0.0,0.6); ions_vel[3]=new Vector3d(.5,-1,0.0);
    	ions_pos[4] = new Vector3d(0.5,0.2,0.6); ions_vel[4]=new Vector3d(1,1,0.0);
    	
    	float set=(float) 0.02;
    	ions_posn[0] = new Vector3d(0.4,0.2+set,0.6); ions_vel[0]=new Vector3d(.1,.5,0.0);
    	ions_posn[1] = new Vector3d(0.45,0.1+set,0.6);ions_vel[1]=new Vector3d(.5,-.5,0.0);
    	ions_posn[2] = new Vector3d(0.4,0.0+set,0.6); ions_vel[2]=new Vector3d(.5,.2,0.0);
    	ions_posn[3] = new Vector3d(0.5,0.0+set,0.6); ions_vel[3]=new Vector3d(.5,-1,0.0);
    	ions_posn[4] = new Vector3d(0.5,0.2+set,0.6); ions_vel[4]=new Vector3d(1,1,0.0);
    	
    	switchGroup = new Switch( Switch.CHILD_MASK );
  		switchGroup.setCapability( Switch.ALLOW_SWITCH_WRITE );
  		int p=0; int v=0;
  		
  		TransformGroup tg = (TransformGroup)createSphere(ions_pos[p],new Vector3d(.3,.1,.01),new Color3f(0f, 0f, 0f));
  	    switchGroup.addChild(tg);
  	    m_ions.add(new OilDrop(ions_pos[p++],ions_vel[v++],new Vector3d(1,1,1),tg,new Vector3d(0.3,-.15,0.6),new Vector3d(0.65,0.25,0.6)));
  	  //  m_ions2.add(new OilDrop(ions_posn[p-1],ions_vel[v-1],new Vector3d(1,1,1),tg,new Vector3d(0.3,-.15,0.6),new Vector3d(0.65,0.25,0.6)));
  	 // Vector3d pos,Vector3d vel,Vector3d scale,TransformGroup tg,Vector3d minbound,Vector3d maxbound
  	    
  	    
  		tg = (TransformGroup)createSphere(ions_pos[p],new Vector3d(.3,.1,.01),new Color3f(0f, 0f, 0f));
	    switchGroup.addChild(tg);
  	    m_ions.add(new OilDrop(ions_pos[p++],ions_vel[v++],new Vector3d(1,1,1),tg,new Vector3d(0.35,-.15,0.6),new Vector3d(0.65,0.25,0.6)));
  	  //m_ions2.add(new OilDrop(ions_posn[p-1],ions_vel[v-1],new Vector3d(1,1,1),tg,new Vector3d(0.3,-.15,0.6),new Vector3d(0.65,0.25,0.6)));
  	    
  	    tg = (TransformGroup)createSphere(ions_pos[p],new Vector3d(.3,.1,.01),new Color3f(0f, 0f, 0f));
	    switchGroup.addChild(tg);
	    m_ions.add(new OilDrop(ions_pos[p++],ions_vel[v++],new Vector3d(1,1,1),tg,new Vector3d(0.35,-.15,0.6),new Vector3d(0.65,0.2,0.6)));
	  //  m_ions2.add(new OilDrop(ions_posn[p-1],ions_vel[v-1],new Vector3d(1,1,1),tg,new Vector3d(0.3,-.15,0.6),new Vector3d(0.65,0.25,0.6)));
	    
  	    tg = (TransformGroup)createSphere(ions_pos[p],new Vector3d(.3,.1,.01),new Color3f(0f, 0f, 0f));
	    switchGroup.addChild(tg);
	    m_ions.add(new OilDrop(ions_pos[p++],ions_vel[v++],new Vector3d(1,1,1),tg,new Vector3d(0.35,-.15,0.6),new Vector3d(0.65,0.25,0.6)));
	   // m_ions2.add(new OilDrop(ions_posn[p-1],ions_vel[v-1],new Vector3d(1,1,1),tg,new Vector3d(0.3,-.15,0.6),new Vector3d(0.65,0.25,0.6)));
	    
	    tg = (TransformGroup)createSphere(ions_pos[p],new Vector3d(.3,.1,.01),new Color3f(0f, 0f, 0f));
	    switchGroup.addChild(tg);
	    m_ions.add(new OilDrop(ions_pos[p++],ions_vel[v++],new Vector3d(1,1,1),tg,new Vector3d(0.35,-.15,0.6),new Vector3d(0.65,0.2,0.6)));
	 //   m_ions2.add(new OilDrop(ions_posn[p-1],ions_vel[v-1],new Vector3d(1,1,1),tg,new Vector3d(0.3,-.15,0.6),new Vector3d(0.65,0.25,0.6)));
  	  
	    /////////////////////////// Create same number of charges
 	    
	    ///////////////////////Charge position
  /*  	ions_pos[5] = new Vector3d(0.46,0.27,0.6);  ions_vel[5]=new Vector3d(0,-1.4,0);
    	ions_pos[6] = new Vector3d(0.495,0.27,0.6); ions_vel[6]=new Vector3d(0,-1.4,0);
    	ions_pos[7] = new Vector3d(0.475,0.29,0.6); ions_vel[7]=new Vector3d(0,-1.4,0);
    	ions_pos[8] = new Vector3d(0.465,0.255,0.6);ions_vel[8]=new Vector3d(0,-1.4,0);
    	ions_pos[9] = new Vector3d(0.485,0.255,0.6);ions_vel[9]=new Vector3d(0,-1.4,0);
    	
	    tg = (TransformGroup)createSphere(ions_pos[p],new Vector3d(.1,.05,.01),new Color3f(0f, 0f, 1f));
	    switchGroup.addChild(tg);
	  //  m_charges.add(new OilDrop(ions_pos[p++],ions_vel[v++] ,new Vector3d(.2,.2,.2), tg , new Vector3d(-0.2,-0.5,0.0) ,new Vector3d(0.2,0.5,.0)));
	    
	    tg = (TransformGroup)createSphere(ions_pos[p],new Vector3d(.1,.05,.01),new Color3f(0f, 0f, 1f));
	    switchGroup.addChild(tg);
	    ///m_charges.add(new OilDrop(ions_pos[p++],ions_vel[v++] ,new Vector3d(.2,.2,.2), tg , new Vector3d(-0.2,-0.5,0.0) ,new Vector3d(0.2,0.5,.0)));
	    
	    tg = (TransformGroup)createSphere(ions_pos[p],new Vector3d(.1,.05,.01),new Color3f(0f, 0f, 1f));
	    switchGroup.addChild(tg);
//	    m_charges.add(new OilDrop(ions_pos[p++],ions_vel[v++] ,new Vector3d(.2,.2,.2), tg , new Vector3d(-0.2,-0.5,0.0) ,new Vector3d(0.2,0.5,.0)));
	    
	    tg = (TransformGroup)createSphere(ions_pos[p],new Vector3d(.1,.05,.01),new Color3f(0f, 0f, 1f));
	    switchGroup.addChild(tg);
	//    m_charges.add(new OilDrop(ions_pos[p++],ions_vel[v++] ,new Vector3d(.2,.2,.2), tg , new Vector3d(-0.2,-0.5,0.0) ,new Vector3d(0.2,0.5,.0)));
	    
	    tg = (TransformGroup)createSphere(ions_pos[p],new Vector3d(.1,.05,.01),new Color3f(0f, 0f, 1f));
	    switchGroup.addChild(tg);
	  //  m_charges.add(new OilDrop(ions_pos[p++],ions_vel[v++] ,new Vector3d(.2,.2,.2), tg , new Vector3d(-0.2,-0.5,0.0) ,new Vector3d(0.2,0.5,.0)));
  		
	*/    
	    
//  	create the logical mask to control Node visibility
  		 java.util.BitSet visibleNodes = new java.util.BitSet( 
  		 switchGroup.numChildren() );

//  	make the third, sixth and seventh nodes visible
  		visibleNodes.set( 0 ); visible_ions[0] =1;
  		visibleNodes.set( 1 ); visible_ions[1] =1;
  		visibleNodes.set( 2 ); visible_ions[2] =1;
  		visibleNodes.set( 3 ); visible_ions[3] =1;
  		visibleNodes.set( 4 ); visible_ions[4] =1;
  		
  		// Label
  		visibleNodes.set( 10 );
  		visibleNodes.set( 11 );
  		visibleNodes.set( 12 );
  		visibleNodes.set( 13 );
  		visibleNodes.set( 14 );
  		visibleNodes.set( 15 );
  		
  		
//  	line
  		visibleNodes.set( v_flag ); //==16
  		//visibleNodes.set( 17 );
  		
  		
  		
  		
  		//////////////////////
  		//visibleNodes.set( 5 );
  		//visibleNodes.set( 6 );
  		//visibleNodes.set( 7 );
  		//visibleNodes.set( 8 );
  		//visibleNodes.set( 9 );
  		
  		
  		 
  		 
//  	 assign the visibility mask to the Switch
  		 
  		switchGroup.setChildMask( visibleNodes );
  		
  		 return switchGroup;
  		
     }

    /**
     * Creates new form Applet3D
     */
    public hope(Container container) {
        // Initialize the GUI components
    	JPopupMenu.setDefaultLightWeightPopupEnabled(false);
        initComponents();

        // Create Canvas3D and SimpleUniverse; add canvas to drawing panel
        Canvas3D c = createUniverse(container);
        simulationPanel.add(c, BorderLayout.CENTER);

        // Create the content branch and add it to the universe
        scene = createSceneGraph(c);
        univ.addBranchGraph(scene);
    }
    
    public double round(double a)
    {

	    int s = (int) (a*10.0);

	    return s/10.0;
    }	    

    
    
    
    // ----------------------------------------------------------------
    
    // Applet framework

    public static class MyApplet extends JApplet {
        hope mainPanel;

        public void init() {
            setLayout(new BorderLayout());
            mainPanel = new hope(this);
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
            setTitle("Virtual Lab Applet");
            getContentPane().add(new hope(this), BorderLayout.CENTER);
            pack();
        }
    }

    
    
    public static void nonbond_force () throws NumberFormatException, IOException
	{
    	ftotal_nonbond=0;
   
    	distance_single=(float)Math.abs(first) ;
    	float temprho=(temprho1+temprho2)/2;
		float rho_r=(temprho/distance_single);
		
    	float rho=(temprho/distance_single);
		float pow12=(float) ((float)12* Math.pow(rho,12));
		float pow6=(float) ((float) 6*Math.pow(rho,6));
		float distance_root=(float) Math.pow(distance_single,-2);
		
		//float du_dr=tempe*((12*pow12/distance)-2*(6*pow6/distance));
		//float dq_dr=(float) (-(332.0716*tempq1*tempq2)/(distance*distance));
		ftotal_nonbond=4*tempepselon*(pow12-pow6)*distance_root;
    	System.out.println("distance  "+distance_single);
    	
    	first+=0.001;
	}
    
    
    public static void nonbond_energy () throws NumberFormatException, IOException
	{
		utotal_nonbonded=0;
		int iter=0;
		
		distance_single=(float)Math.abs(first) ;
			
			//temprhotempepselon=(float) 1;
			float temprho=(temprho1+temprho2)/2;
			float rho_r=(temprho/distance_single);
			//float rho_r=1;
			//tempepselon=(float)1;
			float pow12=(float) Math.pow(rho_r,12);
			float pow6=(float) Math.pow(rho_r,6);
			float uvanderval=(float) (tempepselon*(pow12-2*pow6));
			
			//float electrostatic=(float) ((332.0716*tempq1*tempq2)/distance);
			//System.out.println("u nonbonded vand " +uvanderval);
			//System.out.println("u nonbonded  electrostatic"+electrostatic);
			utotal_nonbonded=utotal_nonbonded+uvanderval;
		 }
   
    /**
     * @param args the command line arguments
     * @throws IOException 
     * @throws NumberFormatException 
     */
    public static void main(String args[]) throws NumberFormatException, IOException {
    	
    	nonbond_energy();
     	nonbond_force();
     	
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
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        
        
//      new GridLayout(2, 1)
        setLayout(new java.awt.BorderLayout());
        
        bottomPanel = new javax.swing.JPanel(); // input from user at bottom
        simulationPanel = new javax.swing.JPanel(); // 3D rendering at center
        topPanel= new javax.swing.JPanel();    // Pause, resume, graph at top
        rightPanel= new javax.swing.JPanel();
         
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
        
        
        timer = new Timer(50,new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //...Perform a task...
            	try {
					timerActionPerformed(evt);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
                            

        
    }// </editor-fold>//GEN-END:initComponents

    private void topPanel() {
    	
    	java.awt.GridBagConstraints gridBagConstraints;
    	
    	javax.swing.JPanel guiPanel = new javax.swing.JPanel(); // Pause, resume at top
    	guiPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);  
                
      //  javax.swing.JButton pauseButton = new javax.swing.JButton();  
        javax.swing.JButton resumeButton = new javax.swing.JButton(); 
         
             
       // pauseButton.setText("Pause");  
        resumeButton.setText("Start");
        
        
       // guiPanel.add(pauseButton, gridBagConstraints);
        guiPanel.add(resumeButton, gridBagConstraints);
        
        
        
        topPanel.setLayout(new java.awt.BorderLayout());
        topPanel.add(guiPanel, java.awt.BorderLayout.NORTH);
        
        
        
        resumeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
             // set the experimetn
              /*PPPPP*/
            	first=0;
              startExperiment();
              timer.start(); 
     		  
            }
        });
    }

private void rightPanel() {
        
    	int w=300;
        int h=200;
        
    	rightPanel.setLayout(new java.awt.GridLayout(2,1,0,1));
    	javax.swing.JPanel guiPanel = new javax.swing.JPanel(); // Pause, resume at top
   
    	JPanel p = new JPanel(new java.awt.GridLayout(1,1,0,0));
        Energy = new HorizontalGraph(w,h,"dis","Energy");
        Energy.setHeading("Energy Vs Displacement");
        Energy.setAxisUnit("A","Kcal");
        Energy.setYAxisColor(new Color(0.0f,0.54f,0.27f));        
        Energy.fitToYwindow(true);
        //Energy.setXOffset(1);
        Energy.setYOffset(120);
        Energy.setYScale(50);
        p.add(Energy);
        rightPanel.add(p);        

      	p = new JPanel(new java.awt.GridLayout(1,1,0,0));
        Force= new HorizontalGraph(w,h,"dis","Force");
        Force.setHeading("Force Vs Displacement");
        Force.setAxisUnit("A","N");//u***
        Force.setYAxisColor(new Color(0.0f,0.54f,0.27f));        
        Force.fitToYwindow(true);
        //Force.setXOffset(1);
        Force.setYOffset(120);
        p.add(Force);
        rightPanel.add(p);
    
        rightPanel.setVisible(true);
    }
    
     
    private void bottomPanel()
    {
	    java.awt.GridBagConstraints gridBagConstraints;
	    
	    bottomPanel.setLayout(new java.awt.GridBagLayout());
	   
	    gridBagConstraints = new java.awt.GridBagConstraints();
	    gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
	    JLabel lbl = new JLabel("Viscosity", JLabel.CENTER);
	    hm.put("ef_lbl", lbl);
	    
	    JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 5, 1);
	    slider.addChangeListener(new ChangeListener() {
			    public void stateChanged(ChangeEvent e) {
			    	setElectricField(((JSlider) e.getSource()).getValue());//SoluteVolumeChange(e);
			    }
			  });
	    
	    
	    hm.put("ef_slider", slider);
	    
	    bottomPanel.add(lbl,gridBagConstraints);    	
        bottomPanel.add(slider,gridBagConstraints);
        
        lbl = new JLabel("", JLabel.CENTER);
        lbl.setForeground(Color.red);
        hm.put("ef_val", lbl);  
        
        bottomPanel.add(lbl,gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
	    gridBagConstraints.insets = new java.awt.Insets(4, 50, 4, 4);
        
	    javax.swing.JButton btn = new javax.swing.JButton(); 
	    btn.setText("");
	    btn.setEnabled(false);
	    hm.put("efOn_btn", btn);        
	    
	    bottomPanel.add(btn,gridBagConstraints);
	    
	    btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	elec_Field = true; 
            	v_flag = 17;
            	//Text3D txt = (Text3D)hm.get("ef_txt");
            	//txt.setString(" : On");
            	JButton btn = (JButton)hm.get("efOn_btn"); 
            	btn.setEnabled(false);
            	
//            	Appearance app = (Appearance)hm.get("tubeApp");
//            	ColoringAttributes ca = new ColoringAttributes();
//    			ca.setColor(new Color3f(0.82f,0.41f,0.11f));
//    			app.setColoringAttributes(ca);
    			
    			//hm.put("microApp",ap);
//    			app = (Appearance)hm.get("microApp");
//            	ca = new ColoringAttributes();
//    			ca.setColor(new Color3f(0.82f,0.41f,0.11f));
//    			app.setColoringAttributes(ca);
            	// toggle
            }
        });
	    
	    btn = new JButton(); 
	    btn.setText("");
	    btn.setEnabled(false);
	    hm.put("start_timer", btn); 
	    
	    btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	timer_on = true;
            	lapase =0;            	
            	fallOrRise=!fallOrRise;
            	
            	JButton btn = (JButton)hm.get("start_timer");
        		btn.setEnabled(false);
        		
            	btn = (JButton)hm.get("stop_timer");
        		btn.setEnabled(true);
        		
            }
        });
	    
	    bottomPanel.add(btn,gridBagConstraints);
	    
	    lbl = new JLabel("-", JLabel.CENTER);
	    lbl.setForeground(Color.red);
	    hm.put("time_lbl", lbl);
	    bottomPanel.add(lbl,gridBagConstraints);
	    
	    btn = new JButton(); 
	    btn.setText("");
	    btn.setEnabled(false);
	    hm.put("stop_timer", btn); 
        
	    btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	timer_on = false;
            	
            	            	
            	JButton btn = (JButton)hm.get("stop_timer");
        		btn.setEnabled(false);
        		
            	btn = (JButton)hm.get("start_timer");
        		btn.setEnabled(true);
        		
            }
        });
	    
	   
	    bottomPanel.add(btn,gridBagConstraints);
	   
	    
	        

    }
   
    private double  dist(Vector3d ball,Vector3d text)
    {
      	
       double x =Math.pow((ball.x - text.x),2.0);	
       double y =Math.pow(( ball.y - text.y),2.0);	
	    
       return x+y;

   }
    // timer Button Action
    private void timerActionPerformed(java.awt.event.ActionEvent evt) throws NumberFormatException, IOException {//GEN-FIRST:event_resumeButtonActionPerformed
	   
    	OilDrop oil=  (OilDrop) hm.get("oil");
    	OilDrop mag_oil=  (OilDrop) hm.get("oil2");
    	
    	OilDrop oilnew=  (OilDrop) hm.get("oilnew");
    	OilDrop mag_oilnew=  (OilDrop) hm.get("oil2new");
    	
    	/*if(elec_Field){
    		oil.setAccerlationY(new Vector3d(0,m_acc.getX(),0));
    		mag_oil.setAccerlationY(new Vector3d(0,m_acc.getY(),0));
    	}else{*/
    	/*	oil.setAccerlationY(new Vector3d(0,0,0));
    		mag_oil.setAccerlationY(new Vector3d(0,0,0));
    		oilnew.setAccerlationY(new Vector3d(0,0,0));
    		mag_oilnew.setAccerlationY(new Vector3d(0,0,0));
    	//}*/
    	oil.update(0.0008);
    	oilnew.update(0);
    	
    	nonbond_energy();
     	nonbond_force();
    	
    	Energy.setState(1);
       	float disp=0;
    	
    	Energy.setCurrentValue((float)disp/200,(float)((utotal_nonbonded)));      // Note that disp denotes the net displacement.  
    	Energy.addGraphValue((float)(utotal_nonbonded*10));//-freeBody.getDisplacment()*500))
    	Energy.drawGraph();
    	Energy.setYScale(10);
    	
    	Force.setState(1);
    	Force.setCurrentValue((float)disp/200,(float)ftotal_nonbond/10);        
        Force.addGraphValue((float)ftotal_nonbond/2000);//-freeBody.getDisplacment()*500));
        Force.drawGraph();
        Force.setYScale(10);
        
    	
    	r_clock += 0.0008f;
    	if(timer_on) {
    		lapase +=0.0008;
    		if(fallOrRise){
    			//Text3D txt = (Text3D)hm.get("t1_txt");    		
    			//txt.setString(" : " + String.valueOf(lapase).substring(2, 4)+ " sec");
    		}else {
    			//Text3D txt = (Text3D)hm.get("t2_txt");    		
    			//txt.setString(" : " + String.valueOf(lapase).substring(2, 4)+ " sec");
    		}
    		
    		 
    	}
    	JLabel lbl = (JLabel)hm.get("time_lbl");
		lbl.setText(String.valueOf(r_clock).substring(2, 4)+ " sec");
		
    	
    	Vector3d ref_pos = mag_oil.getPosition();
    	Vector3d ref_posnew = mag_oilnew.getPosition();
    	
    	for(int i=0; i<m_ions.size();i++){
    		OilDrop o = m_ions.get(i);
    		o.update(0.008);
    		Vector3d pos =o.getPosition();
    		if(dist(pos,ref_pos)<0.001){
    			visible_ions[i]=0;//***********************??//
    		}
    		
    	}
    	
    	for(int i=0; i<m_ions2.size();i++){
    		OilDrop o = m_ions2.get(i);
    		o.update(0.008);
    		Vector3d pos =o.getPosition();
    		if(dist(pos,ref_posnew)<0.001){
    			visible_ions[i]=0;
    		}
    	
    	}
    	
    /*	for(int i=0; i<m_charges.size();i++){
    		OilDrop o = m_charges.get(i);
    		if(elec_Field){
        		o.setAccerlationY(new Vector3d(0,m_acc.getY(),0));
        	}else{
        		o.setAccerlationY(new Vector3d(0,0,0));
        	}
    		
    		if(oil.getPositionY() < y_ref + .2){
    			o.update(0.0015);
        	}else if ( stage ==3 && oil.getPositionY() < y_ref + .3 ){ //.25
        		o.update(0.0015);
        	}
    		
    		
    	}*/
    	
    	// y_ref = Bottom of tube
    	if(oil.getPositionY() < y_ref + .2){
    		mag_oil.update(0.0015);
    	}else if ( stage ==3 && oil.getPositionY() < y_ref + .3 ){ //.25
    		mag_oil.update(0.0015);	
    	}
 /////////////////////////////// Check for collision /////////////////////////
 //////////////////////////////////////////////////////////////////////////////
    	java.util.BitSet visibleNodes = new java.util.BitSet( 
    	  		 switchGroup.numChildren() );
    	
 ///////////////////////////////////////////////////////////////////
    	
//    	 Field is On
				
    	// Turn on Timer Clock
    	if(stage ==0 && oil.getPositionY() < y_ref + .26 ){ //y_ref = -0.25;
    		JButton btn = (JButton)hm.get("start_timer");
    		btn.setEnabled(true);//etVisible(true);
    		
    		//Text3D txt = (Text3D)hm.get("inst_txt");
        	//txt.setString("Step2: Record Time Of Fall");
        	
        	stage =1;
    		// next electric field On
    	}else if( stage ==1 && oil.getPositionY() < y_ref + .08 ){ //y_ref + .18
    		JButton btn = (JButton)hm.get("efOn_btn"); 
    		btn.setText("");
        	btn.setEnabled(true);
        	// enable stop timer
        	     	
    		
    		//Text3D txt = (Text3D)hm.get("inst_txt");
        	//txt.setString("Step3: Turn Electric Field On");
        	
    		stage =2;
    	}else if( stage ==2 && oil.getPositionY() > y_ref + .08 ){ //
    		//JButton btn = (JButton)hm.get("efOn_btn"); 
        	//btn.setEnabled(false);
        	
    		//Text3D txt = (Text3D)hm.get("inst_txt");
        	//txt.setString("Step4: Record Time Of Rise");
        	
    		stage =3;
    	}else if ( stage ==3 && oil.getPositionY() > y_ref + .3 ){ //.25
    		// stop the Experiment
    		
    		timer.stop();
    		JLabel lab = (JLabel) hm.get("ef_lbl");
        	lab.setEnabled(true);
        	lab = (JLabel) hm.get("ef_val");
        	lab.setEnabled(true);
        	
        	JSlider slider = (JSlider) hm.get("ef_slider");
        	slider.setEnabled(true);        	
        	JButton btn = (JButton)hm.get("efOn_btn");
        	btn.setText("");
        	btn.setEnabled(false);
        	//btn = (JButton)hm.get("start_timer"); 
        	btn.setEnabled(false);
        	stage =4;
        	v_flag = 16;
        	
        	//Text3D txt = (Text3D)hm.get("inst_txt");
        	//txt.setString("Calculate the Charge of Electron ");
        	
        	visibleNodes.set( 10 );	visibleNodes.set( 13 );
        	visibleNodes.set( 11 );	visibleNodes.set( 14 );
        	visibleNodes.set( 12 ); visibleNodes.set( 15 );
      		
      		
    	}
    	
    	if(oil.isCollidedWithWall()){
    		timer.stop();
    		//Text3D txt = (Text3D)hm.get("inst_txt");
        	//txt.setString("Step1: Drop the Oil");
        	
        	//txt = (Text3D)hm.get("error_txt");
        	//txt.setString("Fail : Restart the Experiment");
        	
    		JLabel lab = (JLabel) hm.get("ef_lbl");
        	lab.setEnabled(true);
        	lab = (JLabel) hm.get("ef_val");
        	lab.setEnabled(true);
        	
        	JSlider slider = (JSlider) hm.get("ef_slider");
        	slider.setEnabled(true);        	
        	JButton btn = (JButton)hm.get("efOn_btn"); 
        	btn.setText("");
        	btn.setEnabled(false);
        	//btn = (JButton)hm.get("start_timer"); 
        	//btn.setEnabled(false);
        	
        	visibleNodes.set( 10 );	visibleNodes.set( 13 );
        	visibleNodes.set( 11 );	visibleNodes.set( 14 );
        	visibleNodes.set( 12 ); visibleNodes.set( 15 );
        	v_flag = 16;
    	    
    	}
    	
    	if(elec_Field && oil.getPositionY() > y_ref + .09){ //.08
    		elec_Field = false;
    	}
    	
    	for(int i=0; i<visible_ions.length;i++)
    	{
    		if(visible_ions[i] ==1)
    			visibleNodes.set( i );
    		else 
    			visibleNodes.set( i+5 );
    		
    	}
    	
    	visibleNodes.set( v_flag );
    	    	
    	switchGroup.setChildMask( visibleNodes );
    	
	    return;
    }	

    
    private void startExperiment()
    {
    	OilDrop oil=  (OilDrop) hm.get("oil");
    	oil.setPosition(new Vector3d(-0.1,0.09,0.0));
    	oil.setVeleocity(new Vector3d(0,-1,0));
    	oil.setAccerlationY(new Vector3d(0,0,0));
    	
    	oil=  (OilDrop) hm.get("oil2");
    	oil.setPosition(new Vector3d(0.47,0.32,0.6));
    	oil.setVeleocity(new Vector3d(0,-1.4,0));
    	oil.setAccerlationY(new Vector3d(0,0,0));
    	
    /*	OilDrop oilnew=  (OilDrop) hm.get("oilnew");
    	oilnew.setPosition(new Vector3d(-0.1,0.09,0.0));
    	oilnew.setVeleocity(new Vector3d(0,-1,0));
    	oilnew.setAccerlationY(new Vector3d(0,0,0));
    
    	oilnew=  (OilDrop) hm.get("oil2new");
    	oilnew.setPosition(new Vector3d(0.47,0.32,0.6));
    	oilnew.setVeleocity(new Vector3d(0,-1.4,0));
    	oilnew.setAccerlationY(new Vector3d(0,0,0));
    	*/
    	setCharge_IonsPosition();
    	
    	JLabel lab = (JLabel) hm.get("ef_lbl");
    	lab.setEnabled(false);
    	lab = (JLabel) hm.get("ef_val");
    	lab.setEnabled(false);
    	
    	JSlider slider = (JSlider) hm.get("ef_slider");
    	slider.setEnabled(false);
    	
    	
    	JButton btn = (JButton)hm.get("start_timer"); 
    	btn.setEnabled(false);
    	btn = (JButton)hm.get("stop_timer"); 
    	btn.setEnabled(false);
    	btn = (JButton)hm.get("efOn_btn");
    	btn.setText("");
    	btn.setEnabled(false);
    	
    	elec_Field = false;
    	
    	
    	stage =0; 
    	
    	//Text3D txt = (Text3D)hm.get("ef_txt");
    	//txt.setString(" : Off");
    	
    	//txt = (Text3D)hm.get("t1_txt");
    	//txt.setString(" : ");
    	
    	//txt = (Text3D)hm.get("t2_txt");
    	//txt.setString(" : ");
    	
    	//txt = (Text3D)hm.get("error_txt");
    	//txt.setString("");
    	
    	timer_on = false;
    	fallOrRise = false;
    	lapase =0;
    	v_flag = 16;
    	r_clock =0;
    	
//    	Appearance app = (Appearance)hm.get("tubeApp");
//    	ColoringAttributes ca = new ColoringAttributes();
//		ca.setColor(new Color3f(0.501f,0.501f,0.501f));
//		app.setColoringAttributes(ca);
		
		//hm.put("microApp",ap);
//		app = (Appearance)hm.get("microApp");
//    	ca = new ColoringAttributes();
//		ca.setColor(new Color3f(0.701f,0.701f,0.701f));
//		app.setColoringAttributes(ca);
    	
 //		Set Gemo applicationf Shape3D  	
//    	Text2D text2d  = (Text2D)hm.get("hdr_txt");
//    	text2d.setGeometry(new Text2D("Millikan's Oil Drop Experiment", new Color3f(1.0f,1.0f, 1.0f),
// 	    		"Times New Roman", 24, Font.BOLD));
    	
	    
    }
    
    private void setCharge_IonsPosition() {
    	Vector3d ions_pos[] = new Vector3d[10];
    	Vector3d ions_vel[] = new Vector3d[10];
    	///////// ions
    	int xv=0;
    	int yv=0;
    	Random a =  new Random();	
 	   	int sign=a.nextInt(3) ;
 	   	if(sign ==0) {xv=0; yv=1;}
 	   	else if(sign==1){xv=1; yv=-1;}
 	   	else if(sign==2){xv=1; yv=1;}; 	   	
    	ions_pos[0] = new Vector3d(0.4,0.2,0.6); ions_vel[0]=new Vector3d(.1*xv,.5*yv,0.0);
    	
    	sign=a.nextInt(3) ;
 	   	if(sign ==0) {xv=2; yv=1;}
 	   	else if(sign==1){xv=1; yv=-1;}
 	   	else if(sign==2){xv=1; yv=1;};
    	ions_pos[1] = new Vector3d(0.45,0.1,0.6);ions_vel[1]=new Vector3d(.5*xv,-.5*yv,0.0);
    	
    	sign=a.nextInt(3) ;
 	   	if(sign ==0) {xv=2; yv=1;}
 	   	else if(sign==1){xv=1; yv=-1;}
 	   	else if(sign==2){xv=1; yv=1;};
    	ions_pos[2] = new Vector3d(0.4,0.0,0.6); ions_vel[2]=new Vector3d(.5*xv,.2*yv,0.0);
    	
    	sign=a.nextInt(3) ;
 	   	if(sign ==0) {xv=2; yv=1;}
 	   	else if(sign==1){xv=1; yv=-1;}
 	   	else if(sign==2){xv=1; yv=1;};
    	ions_pos[3] = new Vector3d(0.5,0.0,0.6); ions_vel[3]=new Vector3d(.5*xv,-1*yv,0.0);
    	
    	sign=a.nextInt(3) ;
 	   	if(sign ==0) {xv=2; yv=1;}
 	   	else if(sign==1){xv=1; yv=-1;}
 	   	else if(sign==2){xv=1; yv=1;};
    	ions_pos[4] = new Vector3d(0.5,0.2,0.6); ions_vel[4]=new Vector3d(1*xv,1*yv,0.0);
    	
    	sign=a.nextInt(3) ;
 	   	if(sign ==0) {xv=2; yv=1;}
 	   	else if(sign==1){xv=1; yv=-1;}
 	   	else if(sign==2){xv=1; yv=1;};
    	/////////// charges
    	ions_pos[5] = new Vector3d(0.46,0.27,0.6);  ions_vel[5]=new Vector3d(0,-1.4,0);
    	ions_pos[6] = new Vector3d(0.495,0.27,0.6); ions_vel[6]=new Vector3d(0,-1.4,0);
    	ions_pos[7] = new Vector3d(0.475,0.29,0.6); ions_vel[7]=new Vector3d(0,-1.4,0);
    	ions_pos[8] = new Vector3d(0.465,0.255,0.6);ions_vel[8]=new Vector3d(0,-1.4,0);
    	ions_pos[9] = new Vector3d(0.485,0.255,0.6);ions_vel[9]=new Vector3d(0,-1.4,0);
    	
    	final int SIZE = m_ions.size();
    	final int SIZE2 = m_ions2.size();
    	java.util.BitSet visibleNodes = new java.util.BitSet( 
      	  		 switchGroup.numChildren() );
    	for(int i=0; i< SIZE;i++){
    		OilDrop o = m_ions.get(i);
    		o.setPosition(ions_pos[i]);
    		o.setVeleocity(ions_vel[i]);
    		o.setAccerlationY(new Vector3d(0,0,0));
    		// charge
    	/*	o = m_charges.get(i);
    		o.setPosition(ions_pos[SIZE + i]);
    		o.setVeleocity(ions_vel[SIZE + i]);
    		o.setAccerlationY(new Vector3d(0,0,0));
    		*/
    		visible_ions[i] =1;
    		
    	}
    	
    	for(int i=0; i< SIZE2;i++){
    		OilDrop o = m_ions2.get(i);
    		o.setPosition(ions_pos[i]);/****************/
    		o.setVeleocity(ions_vel[i]);
    		o.setAccerlationY(new Vector3d(0,0,0));
    		// charge
    	/*	o = m_charges.get(i);
    		o.setPosition(ions_pos[SIZE + i]);
    		o.setVeleocity(ions_vel[SIZE + i]);
    		o.setAccerlationY(new Vector3d(0,0,0));
    		*/
    		visible_ions[i] =1;
    		
    	}
    	//visibleNodes.set( 0 );
    	visibleNodes.set(16);
    	    		
	   	switchGroup.setChildMask( visibleNodes );
    }
    
    public void setElectricField(int val){
    	//new Point2D.Float(4,2) = oil =4 , mag_oil =2
    	System.out.println("sL : "+ val);
    	JLabel lbl = (JLabel)hm.get("ef_val");
    	lbl.setText(String.valueOf(val) +" V");
    	switch(val){
    	case 5:
    		m_acc.setLocation(10, 7.9f);
    		break;
    	case 4:
    		m_acc.setLocation(9, 7.1f);
    		break;
    	case 3:
    		m_acc.setLocation(8, 6.3f);
    		
    		break;
    	case 2:
    		m_acc.setLocation(7, 5.5f);
    		
    		break;
    	case 1:
    		m_acc.setLocation(6, 4.8);
    		break;
    	}
    }
}


class OilDrop
{


	public  TransformGroup transgp;
	public 	Vector3d m_pos;
	public 	Vector3d m_posn;
	private Vector3d m_minBound;
	private Vector3d m_maxBound;
	public float m_dt;
	
	public float first;
	public float second;
	
	public double m_disp;
	public Vector3d m_vel;
	public Vector3d m_acc;
	public double m_d0;
	public int flag;
	public int count;

	public OilDrop(Vector3d pos,Vector3d vel,Vector3d scale,TransformGroup tg,Vector3d minbound,Vector3d maxbound)
	{
		m_minBound =minbound;
		m_maxBound =maxbound;
		m_pos = pos;
		//m_posn = pos;
		m_vel = vel;
		m_acc = new Vector3d(0,0,0);
		transgp = tg;
		flag=0;
		count=0;

	}
	
	public void update(double dt)
	{
		
		
		first=(float) m_pos.x;
		second=(float) m_pos.y;
		
		
		
		m_pos.x =m_pos.x+ m_vel.x*dt + 0.5*m_acc.x*dt*dt;
		m_pos.y =m_pos.y+ m_vel.y*dt + 0.5*m_acc.y*dt*dt;
		
		//m_posn.x =m_posn.x+ m_vel.x*dt + 0.5*m_acc.x*dt*dt;
		//m_posn.y =m_posn.y+ m_vel.y*dt + 0.5*m_acc.y*dt*dt;
		
		m_vel.x = m_vel.x + m_acc.x*dt;
		m_vel.y = m_vel.y + m_acc.y*dt;
		
//		m_veln.x = m_veln.x + m_accn.x*dt;
//		m_veln.y = m_veln.y + m_accn.y*dt;
		
		if(m_pos.x>m_maxBound.x || m_pos.x<m_minBound.x)
		{
			m_vel.x = m_vel.x*(-1);
	//		trans.setScale(new Vector3d(0.7,1.0,1.0));
		}
		if(m_pos.y>m_maxBound.y || m_pos.y<m_minBound.y)
		{
			m_vel.y = m_vel.y*(-1);
	//		trans.setScale(new Vector3d(1.0,0.7,1.0));
		}

		Transform3D trans = new Transform3D();
		transgp.getTransform(trans);
		//Vector3d s = new Vector3d();
		//trans.getScale(s );
		
		//trans.setScale(s);
		trans.setTranslation(m_pos);
		transgp.setTransform(trans);
		

	}
	
	
	public void setPosition(Vector3d pos){
		m_pos = pos;
		Transform3D trans = new Transform3D();
		transgp.getTransform(trans);
		
		System.out.println("hi "+pos);
		trans.setTranslation(m_pos);
		transgp.setTransform(trans);
		
	}
	
	public void setVeleocity(Vector3d vel){
		m_vel = vel;
		
	}
	
	public boolean isCollidedWithWall(){
		if(m_pos.x>m_maxBound.x || m_pos.x<m_minBound.x)
  			return true;
  		else if(m_pos.y>m_maxBound.y || m_pos.y<m_minBound.y)
  			return true; 
		return false;
	}
	

	public void setAccerlationY(Vector3d acc){
		 m_acc = acc;
	}
	
	public TransformGroup getTransformGroup(){
		return transgp;
	}
	
	public double getPositionY(){
		return m_pos.getY();
	}
	
	public Vector3d getPosition(){
		return m_pos;
	}
	
	
	
	
	
}


//////////////////////////////////////////////////////////////////////

class SniperView extends Shape3D   {
	public SniperView(Vector3d min, Vector3d max, float step, Color3f colr) {
	    super();
	    		    	    
	    
	    Color3f red = new Color3f(.6120f, 0.0f, 0.0f);
	    Color3f green = new Color3f(1.0f, 0.0f, 0.0f);	   
	   
	    // x and y line
	    int size = 4;
	   
	    
	    //for(double x = min.x; x <= max.x; x+= step ) size++;
	    for(double y = min.y; y <= max.y; y+= step ) size+=2;
	    
	    Point3d line_verts[] = new Point3d[size];
	    double x_mid = (min.x + max.x)/2;
	    double y_mid = (min.y + max.y)/2;
	    int k=0;
	    line_verts[k++] = new Point3d(x_mid,min.y,min.z);
	    line_verts[k++] = new Point3d(x_mid,max.y,min.z);
	    line_verts[k++] = new Point3d(min.x,y_mid,min.z);
	    line_verts[k++] = new Point3d(max.x,y_mid,min.z);
	   
	    int n=0;
	    for(double y = min.y; y <= max.y; y+= step ){
	    	line_verts[k++] = new Point3d(x_mid -.01,min.y + n*step,min.z);
	    	line_verts[k++] = new Point3d(x_mid +.01,min.y + n*step,min.z);
	    	n++;
	    }
	    
	    
	    LineArray xline =new LineArray(size,LineArray.COORDINATES | LineArray.COLOR_3);
	    xline.setCoordinates(0, line_verts);
	    
	    for(int i=0; i< size; i++)    	xline.setColor(i,green);
	    
	 		    
	    setGeometry(xline);
	    
	    setAppearance(new Appearance());
    
  }
	
	
 
}


class PowerSys extends Shape3D   {
	
	  public PowerSys() {
		    super();  	    
		    /*
		    Color3f red = new Color3f(.6120f, .6120f, .6120f);
		    Color3f green = new Color3f(1.0f, 0.0f, 0.0f);	   
		   	
		    int size=16;
		    
		    LineArray xline =new LineArray(size,LineArray.COORDINATES | LineArray.COLOR_3);
		    
		    for(int i=0; i< size; i++)    	xline.setColor(i,green);
		    
		    
		    Point3d line_verts[] = new Point3d[size];
		    
		    line_verts[0] = new Point3d(.1, 0, 0);
		    line_verts[1] = new Point3d(-.1, 0, 0);
		    line_verts[2] = new Point3d(-.1, -.125, 0);
		    line_verts[3] = new Point3d(.1, -.125, 0);
		    
		    line_verts[4] = new Point3d(-.1, -.125, 0);
		    line_verts[5] = new Point3d(-.1, -0.06, 0);
		    
		    line_verts[6] = new Point3d(-.1, -0.04, 0);
		    line_verts[7] = new Point3d(-.1, -.02, 0);
		    
		    line_verts[8] = new Point3d(-.1, -.04, 0);
		    line_verts[9] = new Point3d(-.1, -.03, 0);
		    
		    line_verts[10] = new Point3d(-.12, -0.01, 0); xline.setColor(10,red);
		    line_verts[11] = new Point3d(-.08, -0.01, 0); xline.setColor(11,red);
		    line_verts[12] = new Point3d(-.11, -0.02, 0); xline.setColor(12,red);
		    line_verts[13] = new Point3d(-.09, -0.02, 0); xline.setColor(13,red);
		    
		    line_verts[14] = new Point3d(-.1, -.01, 0);
		    line_verts[15] = new Point3d(-.1, -.00, 0);
		    
		    
		    xline.setCoordinates(0, line_verts);
		 		    
		    setGeometry(xline);		
		    
		    Appearance app = new Appearance();
		    LineAttributes la = new LineAttributes();
		    la.setLineWidth(2);
		    app.setLineAttributes(la);
		    
		    setAppearance(app);*/
	    
	  }
	 
}

class Line extends Shape3D   {
	
	  public Line(Point3d p1, Point3d p2,Color3f colr) {
		    super();  	    
		    
		   	
		    int size=2;
		    
		    LineArray xline =new LineArray(size,LineArray.COORDINATES | LineArray.COLOR_3);
		    
		    for(int i=0; i< size; i++)    	xline.setColor(i,colr);
		    
		    
		    Point3d line_verts[] = new Point3d[size];
		    
		    line_verts[0] = p1;
		    line_verts[1] = p2;
		    
		    xline.setCoordinates(0, line_verts);
		 		    
		    setGeometry(xline);		
		    
		    Appearance app = new Appearance();
		    LineAttributes la = new LineAttributes();
		    la.setLineWidth(2);
		    app.setLineAttributes(la);
		    
		    setAppearance(app);
	    
	  }
	 
}

/***************************************************************************/