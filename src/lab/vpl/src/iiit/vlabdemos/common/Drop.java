package iiit.vlabdemos.common;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

public class Drop {
	
	    public  TransformGroup transgp;
		public 	Vector3d m_pos;
		private Vector3d m_minBound;
		private Vector3d m_maxBound;
		public float m_dt;
		public double m_disp;
		public Vector3d m_vel;
		public Vector3d m_acc;
		public double m_d0;
		public int flag;
		public int count;

		public Drop(Vector3d pos,Vector3d vel,Vector3d scale,TransformGroup tg,Vector3d minbound,Vector3d maxbound)
		{
			m_minBound =minbound;
			m_maxBound =maxbound;
			m_pos = pos;
			m_vel = vel;
			m_acc = new Vector3d(0,0,0);
			transgp = tg;
			flag=0;
			count=0;

		}
		
		public void update(double dt)
		{
			m_pos.x =m_pos.x+ m_vel.x*dt + 0.5*m_acc.x*dt*dt;
			m_pos.y =m_pos.y+ m_vel.y*dt + 0.5*m_acc.y*dt*dt;
			
			m_vel.x = m_vel.x + m_acc.x*dt;
			m_vel.y = m_vel.y + m_acc.y*dt;
			
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
	   


