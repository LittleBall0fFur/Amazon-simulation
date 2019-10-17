
import java.lang.*;
import java.util.ArrayList;

public class Pathfinding {

    Grid grid;

    public void FindPath(Node startPos, Node EndPos) {
        //Startnode en targetnode aanmaken
    
        ArrayList<Node> openSet = new ArrayList<Node>();
        ArrayList<Node> closedSet = new ArrayList<Node>();
        openSet.Add(startNode);

        while(openSet.Count > 0){
            Node currentNode = openSet.get(0);
            //Eerst kijken welke fcost het laagst is
            //Zijn ze gelijk, dan kijken naar de hcost

            openSet.Remove(node);
            closedSet.Add(node);

            if(currentNode == targetNode){
                RetracePath(startNode, endNode);
            }
            for (Node neighbour : grid.GetNeighbours(currentNode.GetX(), currentNode.GetY())) {
            //    if (!neighbour.walkable || closedSet.Contains(neighbour)) {
            //        continue;
            //    }

                int newCostToNeighbour = currentNode.gCost + GetDistance(currentNode, neighbour);
                if (newCostToNeighbour < neighbour.gCost  || !openSet.Contains(neighbour)) {
                    neighbour.gCost = newCostToNeighbour;
                    neighbour.hCost = GetDistance(neighbour, targetNode);
                    neighbour.parent = node;

                    if (!openSet.Contains(neighbour))
                        openSet.Add(neighbour);
                
                }
            }
        }
    }

    private void RetracePath(Node startNode, Node endNode){

    }

    private int GetDistance(Node nodeA, Node nodeB) {
        int dstX = Math.abs(nodeA.gridX - nodeB.gridX);
        int dstY = Math.abs(nodeA.gridY - nodeB.gridY);

        if (dstX > dstY)
            return 14dstY + 10 (dstX-dstY);
        return 14*dstX + 10 * (dstY-dstX);
        }
         
    
}