/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storagepathfinder;

import java.util.ArrayList;
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
        List<StorageSquare> squaresToVisit = new ArrayList<>();
        try{
            squaresToVisit.add(storage.getSquareByName("018"));
            squaresToVisit.add(storage.getSquareByName("001"));
            squaresToVisit.add(storage.getSquareByName("063"));
            squaresToVisit.add(storage.getSquareByName("013"));
            squaresToVisit.add(storage.getSquareByName("021"));
            squaresToVisit.add(storage.getSquareByName("043"));
            squaresToVisit.add(storage.getSquareByName("003"));
            squaresToVisit.add(storage.getSquareByName("009"));
            squaresToVisit.add(storage.getSquareByName("051"));
            squaresToVisit.add(storage.getSquareByName("008"));
        }catch(SquareNotPresentInStorageException e){
            System.out.println("Lol, no!");
        }        
        
        SimulatedAnnealing sa = new SimulatedAnnealing(squaresToVisit);
        sa.simulateAnnealing();
        storage.printStorage();
    }
    
    private static void printShortestPath(List<StorageSquare> path){
        System.out.print("Shortest Path:");
        for(StorageSquare square : path){
            System.out.print(" " + square.getName());
        }
        System.out.println();
    }
}
