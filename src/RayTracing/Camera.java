package RayTracing;

public class Camera {
	Vector position;
	double screenDistance;
	double screenWidth;
	Vector direction;
	Vector right;
	Vector up;
	Vector focalPoint;
	boolean fishEyeLens;
	double kFishValue;
	
	
	
	
	public Camera(Vector position, Vector lookAtPoint, Vector up, 
			double screenDistance, double screenWidth, boolean fishEyeLens, double kFishValue) {
		
		this.position=position;
		this.direction=lookAtPoint.sub(this.position).normalize();
		this.right=this.direction.crossProd(up).normalize();  
		this.up=this.right.crossProd(this.direction).normalize();
		this.screenDistance=screenDistance;
		Vector focalPoint=this.position.add((this.direction.scalarMulti(this.screenDistance)));
		this.focalPoint=focalPoint;
		this.screenWidth=screenWidth;
		this.fishEyeLens=fishEyeLens;
		this.kFishValue=kFishValue;
		
	}
	
	
	

	
	
	
	
	

}









