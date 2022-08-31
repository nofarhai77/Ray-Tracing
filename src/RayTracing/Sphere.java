package RayTracing;

public class Sphere extends Surface{
	
	Vector center;
	double radius;
	int materialIndex;
	
	
	public Sphere(Vector center, double radius, int materialIndex) {
		this.center=center;
		this.radius=radius;
		this.materialIndex=materialIndex-1;
	}
	
	
	public int getMaterialIndex() {
		return this.materialIndex;
	}
	
	
	public Vector[] intersect(Ray ray) {
		double t=this.findT(ray);
		if (t<=0) {
			return null;
		}
		Vector[] res= {ray.origin.add(ray.direction.scalarMulti(t)), new Vector(t,0,0)};
		return res;
		
		
	}
	
	
	
	public double findT(Ray ray) {
		Vector L=this.center.sub(ray.origin); 
		double tca=L.dotProd(ray.direction);
		
		if(tca<0) {
			return 0;
		}
		double squared_d= (L.dotProd(L))-Math.pow(tca, 2);
		double squared_r=Math.pow(this.radius, 2);
		 
		 if(squared_d>squared_r) {
			 return 0;
		 }
		 
		 double thc=Math.sqrt(squared_r-squared_d);
		 double t1=tca-thc;
		 double t2=tca+thc;
		 return(Math.min(t1, t2));
		 
		
	}
	
	
	
	public Vector findNormal(Vector v) {
		Vector res=(v.sub(this.center)).normalize();
		return res;
	}
	
}
