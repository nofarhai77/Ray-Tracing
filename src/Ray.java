package RayTracing;

import java.util.List;

public class Ray {
	
	Vector origin;
	Vector direction;
	
	
	public Ray(Vector origin, Vector direction) {
		this.origin=origin;
		this.direction=direction.normalize();
	}
	
	
	
	public Surface findClosestIntersectedSurface (List<Surface> list) {
		double tMin=Double.MAX_VALUE;
		Surface closest = null;
		for (int i=0; i<list.size(); i++) {
			Surface surface=list.get(i);
			Vector[] intersection=surface.intersect(this);
			if (intersection!=null){
				double t=intersection[1].x;
				if(t<tMin) {
					tMin=t;
					closest=surface;
				}
			}
		}
		return closest;
			
	}
	
	
	public Vector closestIntersectPoint(List<Surface> list) {
		Surface closest=this.findClosestIntersectedSurface(list);
		return (closest.intersect(this)[0]);
	}
	
	
}
