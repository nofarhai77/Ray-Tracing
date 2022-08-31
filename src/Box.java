package RayTracing;



public class Box extends Surface {
	Vector center;
	double edgeLength;
	int materialIndex;
	
	public Box(Vector center, double edgeLength, int materialIndex) {
		this.center=center;
		this.edgeLength=edgeLength;
		this.materialIndex=materialIndex-1;
		
		
	}
	
	public int getMaterialIndex() {
		return this.materialIndex;
	}
	
	
	
		
	public Vector[] intersect(Ray ray ) {
		
		double h=this.edgeLength/2;
		Plane[] planes=this.findBoxPlanes();
		
		Vector[] points=new Vector[6];
		
		double[] t=new double[6];
		for(int i=0; i<6; i++) {
			Vector[] intersect=planes[i].intersect(ray); 
			if(intersect==null) {
				continue;
			}
			points[i]=intersect[0];
			t[i]=intersect[1].x;
		}
		
		
		
	
		
	/*	   y 	
	 
	    p4 -------------- p7
		  /|            /|
		 / |           / |
		/  |          /  |
	 p5	--------------p6 | 
		|  |          |  |
		|p0| _________|__| p3  z       
     	|  /          |  /
     	| /           | / 
      p1|/____________|/ p2 
		
		x                   */
		
		//vertices of box
		Vector p0=new Vector(this.center.x-h, this.center.y-h, this.center.z-h);
		Vector p1=new Vector(this.center.x+h, this.center.y-h, this.center.z-h);
		Vector p2=new Vector(this.center.x+h, this.center.y-h, this.center.z+h);
		Vector p3=new Vector(this.center.x-h, this.center.y-h, this.center.z+h);
		Vector p4=new Vector(this.center.x-h, this.center.y+h, this.center.z-h);
		Vector p5=new Vector(this.center.x+h, this.center.y+h, this.center.z-h);
		//Vector p6=new Vector(this.center.x+h, this.center.y+h, this.center.z+h);
		Vector p7=new Vector(this.center.x-h, this.center.y+h, this.center.z+h);
		
		
		int[] isInFace=new int[6];
		
		if(points[0]!=null) {
			if (compare(points[0].x,p0.x) &&(points[0].y>=p0.y)&&(points[0].y<=p4.y)&&(points[0].z>=p0.z)&&(points[0].z<=p3.z)) {
				isInFace[0]=1;
			}
		}
		
		if(points[1]!=null) {
			if (compare(points[1].x,p1.x) &&(points[1].y>=p1.y)&&(points[1].y<=p5.y)&&(points[1].z>=p1.z)&&(points[1].z<=p2.z)) {
				isInFace[1]=1;
			}
		}
		
		if(points[2]!=null) {
			if (compare(points[2].y,p1.y) &&(points[2].x>=p0.x)&&(points[2].x<=p1.x)&&(points[2].z>=p0.z)&&(points[2].z<=p3.z)) {
				isInFace[2]=1;
			}
		}
		
		if(points[3]!=null) {
			if (compare(points[3].y,p4.y) &&(points[3].x>=p4.x)&&(points[3].x<=p5.x)&&(points[3].z>=p4.z)&&(points[3].z<=p7.z)) {
				isInFace[3]=1;
			}
		}
		
		if(points[4]!=null) {
			if (compare(points[4].z,p0.z) &&(points[4].x>=p0.x)&&(points[4].x<=p1.x)&&(points[4].y>=p0.y)&&(points[4].y<=p4.y)) {
				isInFace[4]=1;
			}
		}
		
		if(points[5]!=null) {
			if (compare(points[5].z,p3.z)&&(points[5].x>=p3.x)&&(points[5].x<=p2.x)&&(points[5].y>=p3.y)&&(points[5].y<=p7.y)) {
				isInFace[5]=1;
			}
		}
		
		
		int index=-1;  
		double min=Double.MAX_VALUE;    
		for(int i=0; i<6; i++) {
			if (isInFace[i]==1) {
				double d=ray.origin.findDistance(points[i]);
				if (d<=min) {
					min=d;
					index=i;
				}
			}
		}
		
		if (index==-1) {
			return null;
		}
		Vector[] res= {points[index], new Vector(t[index],0,0)};
		return res;
		
		
	}
	
	
	private Plane[] findBoxPlanes() {
		double h=this.edgeLength/2;
		
		double offset1x=-(this.center.x-h);
		Vector n1x =new Vector(-1,0,0);
		
		double offset2x=(this.center.x+h);
		Vector n2x=new Vector(1,0,0);
		
		double offset1y=-(this.center.y-h);
		Vector n1y =new Vector(0,-1,0);
		
		double offset2y=(this.center.y+h); 
		Vector n2y =new Vector(0,1,0);
		
		double offset1z=-(this.center.z-h);
		Vector n1z =new Vector(0,0,-1);
		
		double offset2z=(this.center.z+h);
		Vector n2z =new Vector(0,0,1);
		
		
		Plane[] planes=new Plane[6];
		
		Plane plane0= new Plane(n1x, offset1x, this.materialIndex);
		planes[0]=plane0;
		
		Plane plane1= new Plane(n2x, offset2x, this.materialIndex);
		planes[1]=plane1;
		
		Plane plane2= new Plane(n1y, offset1y, this.materialIndex);
		planes[2]=plane2;
		
		Plane plane3= new Plane(n2y, offset2y, this.materialIndex);
		planes[3]=plane3;
		
		Plane plane4= new Plane(n1z, offset1z, this.materialIndex);
		planes[4]=plane4;
		
		Plane plane5= new Plane(n2z, offset2z, this.materialIndex);
		planes[5]=plane5;
		
		return planes;
		
	}
	
	
	
	public Vector findNormal(Vector v) {    
		Plane[] planes=this.findBoxPlanes();
		Plane plane=null;
		for(int i=0; i<6; i++) {
			if (planes[i].isPointOnPlane(v)==true) {
				plane=planes[i];
				break;
			}
		}
		
		Vector res=new Vector(plane.normal.x, plane.normal.y, plane.normal.z);
		return res;
	}
	
	public static boolean compare(double x, double y) {
		double epsilon=0.0000001F;
		if (Math.abs(x-y)<=epsilon) {
			return true;
		}
		return false;
	}
	
		
	
	
	

}
