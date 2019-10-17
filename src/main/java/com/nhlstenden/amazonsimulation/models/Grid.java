import java.util.ArrayList;

public class Grid {
    private ArrayList<ArrayList<Node>> grid;

    private int width;
    private int height;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;

        this.grid = new ArrayList<>();
        //Rijen maken
        for(int i = 0; i < height; i++){
            grid.add(new ArrayList<Node>());
            //Kolommen in de rijen maken
            for(int j = 0; j <width; j++){
                this.grid.get(i).add(new Node(i,j));
            }
        }
    }

    public ArrayList<Node> getNeighbours (int x, int y){
        ArrayList<Node> n = new ArrayList<>();
        //Kijken naar directe buren
        try{n.add(this.grid.get(x-1).get(y-1)); } catch(Exception e){}
        try{n.add(this.grid.get(x).get(y-1));} catch(Exception e){}
        try{n.add(this.grid.get(x+1).get(y-1));} catch(Exception e){}

        try{n.add(this.grid.get(x-1).get(y));} catch(Exception e){}
        try{n.add(this.grid.get(x+1).get(y));} catch(Exception e){}

        try{n.add(this.grid.get(x-1).get(y+1));} catch(Exception e){}
        try{n.add(this.grid.get(x).get(y+1));} catch(Exception e){}
        try{n.add(this.grid.get(x+1).get(y+1));} catch(Exception e){}
 
        return n;
    }
}