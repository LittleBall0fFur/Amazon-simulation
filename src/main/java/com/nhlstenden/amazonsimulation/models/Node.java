
public class Node{
    private double x;
    private double z;

    public int gCost;
    public int hCost;
    public int fCost = gCost + hCost;
    
    public Node (double x, double z){
        this.x = x;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getZ() {
        return z;
    }

    public int fCost()
    
}