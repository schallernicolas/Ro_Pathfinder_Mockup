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
        if (args.length == 0) {
            try {
                squaresToVisit.add(Storage.getSquareByName("010"));
                squaresToVisit.add(Storage.getSquareByName("109"));
                squaresToVisit.add(Storage.getSquareByName("110"));
                squaresToVisit.add(Storage.getSquareByName("102"));
                squaresToVisit.add(Storage.getSquareByName("007"));
                squaresToVisit.add(Storage.getSquareByName("063"));
                squaresToVisit.add(Storage.getSquareByName("013"));
                squaresToVisit.add(Storage.getSquareByName("021"));
                squaresToVisit.add(Storage.getSquareByName("003"));
                squaresToVisit.add(Storage.getSquareByName("043"));
                squaresToVisit.add(Storage.getSquareByName("008"));
                squaresToVisit.add(Storage.getSquareByName("009"));
                squaresToVisit.add(Storage.getSquareByName("051"));
                squaresToVisit.add(Storage.getSquareByName("060"));
                squaresToVisit.add(Storage.getSquareByName("040"));
                squaresToVisit.add(Storage.getSquareByName("240"));
                squaresToVisit.add(Storage.getSquareByName("250"));
                squaresToVisit.add(Storage.getSquareByName("260"));
                squaresToVisit.add(Storage.getSquareByName("003"));
                squaresToVisit.add(Storage.getSquareByName("003"));
                squaresToVisit.add(Storage.getSquareByName("013"));

            } catch (SquareNotPresentInStorageException e) {
                System.out.println(e.getMessage());
            }
        } else {
            String[] squares = null;
            squares = args[0].split(";");
            for (String square : squares) {
                if(square.toLowerCase().equals("lif")){
                    square = "010";
                } else {
                    square = square.substring(0,3);
                }
                try {
                    squaresToVisit.add(storage.getSquareByName(square));
                } catch (SquareNotPresentInStorageException e) {
                    System.out.println("WARNING: " + e.getMessage());
                }
            }
        }
        
        if(squaresToVisit.isEmpty()){
            System.out.println("No squares to visit have been specified, exiting..");
            return;
        }

        SimulatedAnnealing sa = new SimulatedAnnealing(squaresToVisit);
        sa.simulateAnnealing();
        storage.printStorage();
    }

    private static void printShortestPath(List<StorageSquare> path) {
        System.out.print("Shortest Path:");
        for (StorageSquare square : path) {
            System.out.print(" " + square.getName());
        }
        System.out.println();
    }
}
