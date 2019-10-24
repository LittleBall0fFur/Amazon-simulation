import java.lang.*;

public class Node extends IHeapItem<Node>{
    private double x;
    private double z;

    public int gCost;
    public int hCost;
    public Node parent;
    int heapIndex;
    private int compare;
    
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

    public int fCost(){
        return gCost + hCost; 
    }

    public int HeapIndex(){
        return heapIndex;
        heapIndex = value;
    }

    public int CompareTo(Node nodeToCompare){
        compare = fcost.CompareTo(nodeToCompare.fCost);
        if(compare == 0){
            compare = hCost.CompareTo(nodeToCompare.hCost);
        }
        return -compare;
    }
}