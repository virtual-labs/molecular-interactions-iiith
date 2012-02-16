package iiit.vlabdemos.bouncingball;
/*
The Joy of Java 3D

by Greg Hopkins

Copyright Copyright 2001


*/

/*
Positioning the Objects

In Java 3D, locations are described by using x, y, z coordinates. Increasing coordinates 
go along the x-axis to the right, along the y-axis  upwards, and along the z-axis out of 
the screen. This is called a "right-handed" coordinate system because the thumb and first
two fingers of your right hand can be used to represent the three directions. All the 
distances are measured in meters.


To place your objects in the scene, you start at point (0,0,0), and then move the objects 
wherever you want. Moving the objects is called a "transformation", so the classes you 
use are: TransformGroup and Transform3D.  You add both the object and the Transform3D to 
a TransformGroup  before adding the TransformGroup to the rest of your scend



Step                                                             Example
1.  Create a transform, a transform                         Transform = new Transform3D();
group and an object                                          transformGroup tg  = new TransformGroup();
                                                 
                                                            Cone cone = new Cone(0.5f, 0.5f);
--------------------------------------------------------------------------------------------
2.  Specify a location for the object                        Vector3f vector = new Vector3f(-.2f,.1f , -.4f);
--------------------------------------------------------------------------------------------
3.  Set the transform to move (translate)                   Transform.setTranslation(vector); 
the object to that location  
--------------------------------------------------------------------------------------------
4.  Add the transform to the transform group                tg.setTransform(transform);
--------------------------------------------------------------------------------------------
5.  Add the object to the transform group                    tg.addChild(cone);


This example displays the different objects on each axis.
*/

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Position {

  public Position() {
    SimpleUniverse universe = new SimpleUniverse();
    BranchGroup group = new BranchGroup();
    // X axis made of spheres
    for (float x = -1.0f; x <= 1.0f; x = x + 0.1f) {
      Sphere sphere = new Sphere(0.05f);
      TransformGroup tg = new TransformGroup();
      Transform3D transform = new Transform3D();
      Vector3f vector = new Vector3f(x, .0f, .0f);
      transform.setTranslation(vector);
      tg.setTransform(transform);
      tg.addChild(sphere);
      group.addChild(tg);
    }
    // Y axis made of cones
    for (float y = -1.0f; y <= 1.0f; y = y + 0.1f) {
      TransformGroup tg = new TransformGroup();
      Transform3D transform = new Transform3D();
      Cone cone = new Cone(0.05f, 0.1f);
      Vector3f vector = new Vector3f(.0f, y, .0f);
      transform.setTranslation(vector);
      tg.setTransform(transform);
      tg.addChild(cone);
      group.addChild(tg);
    }
    // Z axis made of cylinders
    for (float z = -1.0f; z <= 1.0f; z = z + 0.1f) {
      TransformGroup tg = new TransformGroup();
      Transform3D transform = new Transform3D();
      Cylinder cylinder = new Cylinder(0.05f, 0.1f);
      Vector3f vector = new Vector3f(.0f, .0f, z);
      transform.setTranslation(vector);
      tg.setTransform(transform);
      tg.addChild(cylinder);
      group.addChild(tg);
    }

    Color3f light1Color = new Color3f(.1f, 1.4f, .1f); // green light
    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
        100.0);
    Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
    DirectionalLight light1 = new DirectionalLight(light1Color,
        light1Direction);
    light1.setInfluencingBounds(bounds);
    group.addChild(light1);
    universe.getViewingPlatform().setNominalViewingTransform();

    // add the group of objects to the Universe
    universe.addBranchGraph(group);
  }

  public static void main(String[] args) {
    new Position();
  }
}

           
       