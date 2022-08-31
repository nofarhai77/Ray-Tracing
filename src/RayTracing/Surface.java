package RayTracing;

public abstract class Surface {
	
	abstract public int getMaterialIndex();
	
	abstract public Vector[] intersect(Ray ray);
	
	abstract public Vector findNormal(Vector v);
	
}
