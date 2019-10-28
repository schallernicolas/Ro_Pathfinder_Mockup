/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storagepathfinder;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author nsc
 */
public class StoragePathfinder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StoragePathfinder spf = new StoragePathfinder();
        Storage storage = new Storage();
        storage.generateStorage();
        List<StorageSquare> squares = storage.getStorageSquares();
        // from square "004"
        String startSquare = "015";
        // to square "012"
        String endSquare = "063";
        StorageSquare start = null;
        StorageSquare end = null;
        
        try{
            start = storage.getSquareByName(startSquare);
            end = storage.getSquareByName(endSquare);
        } catch (SquareNotPresentInStorageException e){
            System.out.println("Either specified start or end square is not present in storage! Start suare was: " + startSquare + "; end square was " + endSquare);
        }
        
        List<StorageSquare> shortestPathBetweenTwoNodes = spf.findShortestPathBetweenTwoNodes(start, end, squares);
        spf.printShortestPath(shortestPathBetweenTwoNodes);
    }
    
    private List<StorageSquare> findShortestPathBetweenTwoNodes(StorageSquare startNode, StorageSquare endNode, List<StorageSquare> squares){
        HashMap<StorageSquare, StorageSquare> parentMap = new HashMap<>();
        HashSet<StorageSquare> visited = new HashSet<>();
        Map<StorageSquare, Double> distances = initDistances(squares);

        Queue<StorageSquare> priorityQueue = initQueue();

        //  enque StartNode, with distance 0
        startNode.setDistanceToStart(0);
        distances.put(startNode, new Double(0));
        priorityQueue.add(startNode);
        StorageSquare current = null;

        while (!priorityQueue.isEmpty()) {
            current = priorityQueue.remove();

            if (!visited.contains(current)) {
                visited.add(current);
                // if last element in PQ reached
                if (current.equals(endNode)) {
                    return reconstructPath(startNode, endNode, parentMap);
                }

                for (Map.Entry<StorageSquare, Integer> entry : current.getNeighbors().entrySet()) {
                    StorageSquare neighbor = entry.getKey();
                    if (!visited.contains(neighbor)) {

                        // calculate predicted distance to the end node
                        double predictedDistance = Math.abs((neighbor.getColNr() + neighbor.getRowNr())-(endNode.getColNr() + endNode.getRowNr()));

                        // 1. calculate distance to neighbor. 2. calculate dist from start node
                        double neighborDistance = entry.getValue();
                        double totalDistance = current.getDistanceToStart() + neighborDistance + predictedDistance;

                        // check if distance smaller
                        if (totalDistance < distances.get(neighbor)) {
                            // update current neighbor's distance
                            distances.put(neighbor, totalDistance);
                            // used for PriorityQueue
                            neighbor.setDistanceToStart(totalDistance);
                            // set parent
                            parentMap.put(neighbor, current);
                            // enqueue
                            priorityQueue.add(neighbor);
                        }
                    }
                }
            }
        }
        return null;
    }
    
    private PriorityQueue<StorageSquare> initQueue() {
        return new PriorityQueue<>(10, new Comparator<StorageSquare>() {
            public int compare(StorageSquare x, StorageSquare y) {
                if (x.getDistanceToStart() < y.getDistanceToStart()) {
                    return -1;
                }
                if (x.getDistanceToStart() > y.getDistanceToStart()) {
                    return 1;
                }
                return 0;
            };
        });
}
    
    private Map<StorageSquare, Double> initDistances(List<StorageSquare> storageSquares){
        Map<StorageSquare, Double> distances = new HashMap<>();

        Iterator<StorageSquare> iter = storageSquares.iterator();
        while (iter.hasNext()) {
            StorageSquare node = iter.next();
            // set everything to an unreachable value
            distances.put(node, Double.POSITIVE_INFINITY);
        }
        return distances;
    } 
    
    private List<StorageSquare> reconstructPath(StorageSquare start, StorageSquare goal,
            Map<StorageSquare, StorageSquare> parentMap) {
        // construct output list
        LinkedList<StorageSquare> path = new LinkedList<>();
        StorageSquare currNode = goal;
        while (!currNode.equals(start)) {
            path.addFirst(currNode);
            currNode = parentMap.get(currNode);
        }
        path.addFirst(start);
        return path;
    }
    
    private void printShortestPath(List<StorageSquare> path){
        System.out.print("Shortest Paht:");
        for(StorageSquare square : path){
            System.out.print(" " + square.getName());
        }
        System.out.println();
    }
}
