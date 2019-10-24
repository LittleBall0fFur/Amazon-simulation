
import java.lang.*;
import java.util.ArrayList;

public class Pathfinding {

    Grid grid;

    public void FindPath(Node startPos, Node EndPos) {
        Node startNode = startPos;
        Node targetNode = endPos;
    
        Heap<Node> openSet = new Heap<Node>(grid.MaxSize());
        ArrayList<Node> closedSet = new ArrayList<Node>();
        openSet.Add(startNode);

        while(openSet.Count > 0){
            Node currentNode = openSet.RemoveFirst();
            for(int i = 1; i <openSet.Count; i++){
                if(OpenSet(i).fCost < node.fCost || openSet(i).fCost == node.fCost){
                    if(openSet(i).hCost < node.hCost){
                        node = openSet(i);
                    }
                }
            }

            openSet.Remove(node);
            closedSet.Add(node);

            if(currentNode == targetNode){
                RetracePath(startNode, endNode);
                return;
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
        List<Node> path = new ArrayList<Node>();
        Node currentNode = endNode;

        while (currentNode != startNode){
            path.Add(currentNode);
            currentNode = currentNode.parent;
        }
        
        path.Reverse();

        grid.path = path;
        
    }

    private int GetDistance(Node nodeA, Node nodeB) {
        int dstX = Math.abs(nodeA.gridX - nodeB.gridX);
        int dstY = Math.abs(nodeA.gridY - nodeB.gridY);

        if (dstX > dstY)
            return 14*dstY + 10 * (dstX-dstY);
            return 14*dstX + 10 * (dstY-dstX);
        }
         
    
}