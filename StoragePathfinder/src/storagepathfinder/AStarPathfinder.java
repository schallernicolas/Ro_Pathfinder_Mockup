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
public class AStarPathfinder {
        
    private static List<StorageSquare> squares = Storage.getStorageSquares();
    
    public static double findShortestPathBetweenTwoNodes(StorageSquare startNode, StorageSquare endNode){
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
                    // if actual path is asked, use commented method below
                    //return reconstructPath(startNode, endNode, parentMap);
                    return current.getDistanceToStart();
                }

                for (Map.Entry<StorageSquare, Integer> entry : current.getNeighbors().entrySet()) {
                    StorageSquare neighbor = entry.getKey();
                    if (!visited.contains(neighbor)) {

                        // calculate predicted distance to the end node
                        double predictedDistance = 0.0;
                        if(neighbor.getFloorNr() == endNode.getFloorNr()){
                            predictedDistance = Math.abs((neighbor.getColNr() + neighbor.getRowNr())-(endNode.getColNr() + endNode.getRowNr()));
                        } else {
                            
                        }

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
        return 0;
    }
    
    private static PriorityQueue<StorageSquare> initQueue() {
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
    
    private static Map<StorageSquare, Double> initDistances(List<StorageSquare> storageSquares){
        Map<StorageSquare, Double> distances = new HashMap<>();

        Iterator<StorageSquare> iter = storageSquares.iterator();
        while (iter.hasNext()) {
            StorageSquare node = iter.next();
            // set everything to an unreachable value
            distances.put(node, Double.POSITIVE_INFINITY);
        }
        return distances;
    } 
    
    private static List<StorageSquare> reconstructPath(StorageSquare start, StorageSquare goal,
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
}
