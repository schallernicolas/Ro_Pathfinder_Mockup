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
        List<StorageSquare> squareList = new ArrayList<>();
        try{
            squareList.add(storage.getSquareByName("018"));
            squareList.add(storage.getSquareByName("004"));
            squareList.add(storage.getSquareByName("040"));
            squareList.add(storage.getSquareByName("001"));
            squareList.add(storage.getSquareByName("063"));
            squareList.add(storage.getSquareByName("013"));
            squareList.add(storage.getSquareByName("021"));
            squareList.add(storage.getSquareByName("043"));
            squareList.add(storage.getSquareByName("003"));
            squareList.add(storage.getSquareByName("009"));
            squareList.add(storage.getSquareByName("051"));
            squareList.add(storage.getSquareByName("008"));
        }catch(SquareNotPresentInStorageException e){
            System.out.println("Lol, no!");
        }        
        
        SimulatedAnnealing sa = new SimulatedAnnealing(squareList);
        sa.simulateAnnealing();
    }
    
    private static void printShortestPath(List<StorageSquare> path){
        System.out.print("Shortest Paht:");
        for(StorageSquare square : path){
            System.out.print(" " + square.getName());
        }
        System.out.println();
    }
}
