/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storagepathfinder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        
        ArrayList<Tour> tourList;
        
        try{
            tourList = splitVisitOrder(squaresToVisit);
        }catch(SquareNotPresentInStorageException e){
            e.printStackTrace();
            return;
        }
        
        SimulatedAnnealing sa = new SimulatedAnnealing();
        
        ArrayList<StorageSquare> finalTour = new ArrayList<>();
        ArrayList<StorageSquare> initialTour = new ArrayList<>();
        for(Tour tour : tourList){
            initialTour.addAll(tour.getTour());
            finalTour.addAll(sa.simulateAnnealing(tour).getTour());
        }
        
        try{
            initialTour.add(Storage.getSquareByName("003"));
        }catch(SquareNotPresentInStorageException e){
            e.printStackTrace();
            return;
        }
        
        Tour best = new Tour(finalTour);
        Tour firstTour = new Tour(initialTour);
        System.out.println("Initial tour: " + firstTour);
        System.out.println("Initial solution distance: " + firstTour.getDistance());
        System.out.println("Final tour: " + best);
        System.out.println("Final solution distance: " + best.getDistance());
        storage.printStorage();
    }
    
    private static ArrayList splitVisitOrder(List<StorageSquare> squaresToVisit) throws SquareNotPresentInStorageException{
        ArrayList<StorageSquare> firstFloor = new ArrayList<>();
        ArrayList<StorageSquare> secondFloor = new ArrayList<>();
        ArrayList<StorageSquare> thirdFloor = new ArrayList<>();
        ArrayList<Tour> tourList = new ArrayList<>();
        
        for(StorageSquare square : squaresToVisit){
            switch(square.getName().substring(0, 1)){
                case "0": 
                    if(!square.getName().equals("003")){
                        firstFloor.add(square);
                    }                    
                    break;                             
                case "1":
                    secondFloor.add(square);
                    break;
                case "2":
                    thirdFloor.add(square);
                    break;
                default:
                    throw new SquareNotPresentInStorageException(square.getName());
            }
        }
        Tour tour = null;
        if(!firstFloor.isEmpty() && (!secondFloor.isEmpty() || !secondFloor.isEmpty())){
            tour = new Tour(firstFloor);
            tour.setStartAndEndpoint(Storage.getSquareByName("010"), Storage.getSquareByName("011"));
            tourList.add(tour);
        }else if(!firstFloor.isEmpty()){
            tour = new Tour(firstFloor);
            tour.setStartAndEndpoint(Storage.getSquareByName("010"), Storage.getSquareByName("003"));
            tourList.add(tour);
        }
        if(!secondFloor.isEmpty()){
            tour = new Tour(secondFloor);
            tour.setStartAndEndpoint(Storage.getSquareByName("111"), Storage.getSquareByName("111"));
            tourList.add(tour);
        }
        if(!thirdFloor.isEmpty()){
            tour = new Tour(thirdFloor);
            tour.setStartAndEndpoint(Storage.getSquareByName("211"), Storage.getSquareByName("211"));
            tourList.add(tour);
        }
        return tourList;
    }

    private static void printShortestPath(List<StorageSquare> path) {
        System.out.print("Shortest Path:");
        for (StorageSquare square : path) {
            System.out.print(" " + square.getName());
        }
        System.out.println();
    }
}
