package RayTracing;

public class Plane extends Surface {
	Vector normal;
	double offset;
	int materialIndex;
	
	public Plane(Vector normal, double offset, int materialIndex) {
		this.normal=normal;
		this.offset=-1*offset;
		this.materialIndex=materialIndex-1;
	}
	
	
	public int getMaterialIndex() {
		return this.materialIndex;
	}
	
	public Vector[] intersect(Ray ray) {
		double t=this.findT(ray);
		if(t<=0) {
			return null;
		}
		Vector[] res= {ray.origin.add(ray.direction.scalarMulti(t)), new Vector(t,0,0)};
		return res;
	}
	
	
	public double findT(Ray ray) {
		double directionDotNormal=ray.direction.dotProd(this.normal);
		if (directionDotNormal==0) {
			return 0;
		}
		double t = -1*(ray.origin.dotProd(this.normal)+this.offset)/(directionDotNormal);
		return t;
	}
	
	
	
	
	public Vector projectAPoint(Vector u, Vector v) {
		Ray ray=new Ray(v, this.normal);
		Vector[] intersect=this.intersect(ray);
		if (intersect==null){
			Vector normal=this.normal.scalarMulti(-1);
			ray.direction=normal;
			intersect=this.intersect(ray);
		}
		
		return (intersect[0].sub(u)).normalize();
		
		
	}
	
	
	public Vector findNormal(Vector v) {
		return new Vector(this.normal.x, this.normal.y, this.normal.z).normalize();
	}
	
	public boolean isPointOnPlane(Vector v) {
		if(isSumZero(this.normal.dotProd(v), this.offset)) {
			return true;
		}
		return false;
		}
	
	
	public static boolean isSumZero(double x, double y) {   //returns |x+y|==0
		double epsilon=0.000001F;
		if (Math.abs(x+y)<=epsilon) {
			return true;
		}
		return false;
	}
	
	

}
