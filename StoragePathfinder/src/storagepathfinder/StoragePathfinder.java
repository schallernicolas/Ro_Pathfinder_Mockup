/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storagepathfinder;

import java.util.List;

/**
 *
 * @author nsc
 */
public class StoragePathfinder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Storage storage = new Storage();
        storage.generateStorage();
        List<StorageSquare> squares = storage.getStorageSquares();
        String startSquare = "004";
        String endSquare = "012";        
        AStarPathfinder aStarPathfinder = new AStarPathfinder(squares);
        List<StorageSquare> shortestPath;
        
        storage.printStorage();
        
        try{   
            shortestPath = aStarPathfinder.findShortestPathBetweenTwoNodes(storage.getSquareByName(startSquare), storage.getSquareByName(endSquare));
        } catch (SquareNotPresentInStorageException e){
            System.out.println("Either specified start or end square is not present in storage! Start square was: " + startSquare + "; end square was: " + endSquare);
            return;
        }
        
        printShortestPath(shortestPath);
    }
    
    private static void printShortestPath(List<StorageSquare> path){
        System.out.print("Shortest Path:");
        for(StorageSquare square : path){
            System.out.print(" " + square.getName());
        }
        System.out.println();
    }
}
