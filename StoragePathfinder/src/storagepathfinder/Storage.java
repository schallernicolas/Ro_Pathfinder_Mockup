/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storagepathfinder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import storagepathfinder.util.SquareColSorter;

/**
 *
 * @author nsc
 */
public class Storage {
    private static ArrayList<StorageSquare> storageSquares = new ArrayList<>();
    private ArrayList<Obstacle> obstacles = new ArrayList<>();
    
    public Storage(){
    }
    
    protected void generateStorage(){
        generateStorageSquares();
        generateObstacles();
        storageSquares.forEach((n) -> populateNeigbors(n));
        generateElevatorConnections();
        //storageSquares.forEach((n) -> n.printSquareInformation());
        
    }
    
    //set of storage squares
    private void generateStorageSquares(){
        //ground floor
        storageSquares.add(new StorageSquare("015", 0, 0, 0));
        storageSquares.add(new StorageSquare("008", 0, 0, 1));
        storageSquares.add(new StorageSquare("001", 0, 0, 2));
        storageSquares.add(new StorageSquare("016", 0, 1, 0));
        storageSquares.add(new StorageSquare("009", 0, 1, 1));
        storageSquares.add(new StorageSquare("002", 0, 1, 2));
        storageSquares.add(new StorageSquare("017", 0, 2, 0));
        storageSquares.add(new StorageSquare("010", 0, 2, 1));
        storageSquares.add(new StorageSquare("003", 0, 2, 2));
        storageSquares.add(new StorageSquare("018", 0, 3, 0));
        storageSquares.add(new StorageSquare("011", 0, 3, 1));
        storageSquares.add(new StorageSquare("004", 0, 3, 2));
        storageSquares.add(new StorageSquare("019", 0, 4, 0));
        storageSquares.add(new StorageSquare("012", 0, 4, 1));
        storageSquares.add(new StorageSquare("005", 0, 4, 2));
        storageSquares.add(new StorageSquare("020", 0, 5, 0));
        storageSquares.add(new StorageSquare("013", 0, 5, 1));
        storageSquares.add(new StorageSquare("006", 0, 5, 2));
        storageSquares.add(new StorageSquare("021", 0, 6, 0));
        storageSquares.add(new StorageSquare("014", 0, 6, 1));
        storageSquares.add(new StorageSquare("007", 0, 6, 2));
        storageSquares.add(new StorageSquare("030", 0, 7, 0));
        storageSquares.add(new StorageSquare("031", 0, 7, 1));
        storageSquares.add(new StorageSquare("040", 0, 8, 0));
        storageSquares.add(new StorageSquare("041", 0, 8, 1));
        storageSquares.add(new StorageSquare("042", 0, 8, 2));
        storageSquares.add(new StorageSquare("043", 0, 8, 3));
        storageSquares.add(new StorageSquare("050", 0, 9, 0));
        storageSquares.add(new StorageSquare("051", 0, 9, 1));
        storageSquares.add(new StorageSquare("052", 0, 9, 2));
        storageSquares.add(new StorageSquare("053", 0, 9, 3));
        storageSquares.add(new StorageSquare("060", 0, 10, 0));
        storageSquares.add(new StorageSquare("061", 0, 10, 1));
        storageSquares.add(new StorageSquare("062", 0, 10, 2));
        storageSquares.add(new StorageSquare("063", 0, 10, 3));
        
        //first floor
        storageSquares.add(new StorageSquare("115", 1, 0, 0));
        storageSquares.add(new StorageSquare("108", 1, 0, 1));
        storageSquares.add(new StorageSquare("101", 1, 0, 2));
        storageSquares.add(new StorageSquare("116", 1, 1, 0));
        storageSquares.add(new StorageSquare("109", 1, 1, 1));
        storageSquares.add(new StorageSquare("102", 1, 1, 2));
        storageSquares.add(new StorageSquare("117", 1, 2, 0));
        storageSquares.add(new StorageSquare("110", 1, 2, 1));
        storageSquares.add(new StorageSquare("103", 1, 2, 2));
        storageSquares.add(new StorageSquare("118", 1, 3, 0));
        storageSquares.add(new StorageSquare("111", 1, 3, 1));
        storageSquares.add(new StorageSquare("104", 1, 3, 2));
        storageSquares.add(new StorageSquare("119", 1, 4, 0));
        storageSquares.add(new StorageSquare("112", 1, 4, 1));
        storageSquares.add(new StorageSquare("105", 1, 4, 2));
        storageSquares.add(new StorageSquare("120", 1, 5, 0));
        storageSquares.add(new StorageSquare("113", 1, 5, 1));
        storageSquares.add(new StorageSquare("106", 1, 5, 2));
        storageSquares.add(new StorageSquare("121", 1, 6, 0));
        storageSquares.add(new StorageSquare("114", 1, 6, 1));
        storageSquares.add(new StorageSquare("107", 1, 6, 2));
        storageSquares.add(new StorageSquare("130", 1, 7, 0));
        storageSquares.add(new StorageSquare("131", 1, 7, 1));
        storageSquares.add(new StorageSquare("132", 1, 7, 2));
        storageSquares.add(new StorageSquare("133", 1, 7, 3));
        storageSquares.add(new StorageSquare("140", 1, 8, 0));
        storageSquares.add(new StorageSquare("141", 1, 8, 1));
        storageSquares.add(new StorageSquare("142", 1, 8, 2));
        storageSquares.add(new StorageSquare("143", 1, 8, 3));
        storageSquares.add(new StorageSquare("150", 1, 9, 0));
        storageSquares.add(new StorageSquare("151", 1, 9, 1));
        storageSquares.add(new StorageSquare("152", 1, 9, 2));
        storageSquares.add(new StorageSquare("153", 1, 9, 3));
        storageSquares.add(new StorageSquare("160", 1, 10, 0));
        storageSquares.add(new StorageSquare("161", 1, 10, 1));
        storageSquares.add(new StorageSquare("162", 1, 10, 2));
        storageSquares.add(new StorageSquare("163", 1, 10, 3));
        
        //second floor
        storageSquares.add(new StorageSquare("215", 2, 0, 0));
        storageSquares.add(new StorageSquare("208", 2, 0, 1));
        storageSquares.add(new StorageSquare("201", 2, 0, 2));
        storageSquares.add(new StorageSquare("216", 2, 1, 0));
        storageSquares.add(new StorageSquare("209", 2, 1, 1));
        storageSquares.add(new StorageSquare("202", 2, 1, 2));
        storageSquares.add(new StorageSquare("217", 2, 2, 0));
        storageSquares.add(new StorageSquare("210", 2, 2, 1));
        storageSquares.add(new StorageSquare("203", 2, 2, 2));
        storageSquares.add(new StorageSquare("218", 2, 3, 0));
        storageSquares.add(new StorageSquare("211", 2, 3, 1));
        storageSquares.add(new StorageSquare("204", 2, 3, 2));
        storageSquares.add(new StorageSquare("219", 2, 4, 0));
        storageSquares.add(new StorageSquare("212", 2, 4, 1));
        storageSquares.add(new StorageSquare("205", 2, 4, 2));
        storageSquares.add(new StorageSquare("220", 2, 5, 0));
        storageSquares.add(new StorageSquare("213", 2, 5, 1));
        storageSquares.add(new StorageSquare("206", 2, 5, 2));
        storageSquares.add(new StorageSquare("221", 2, 6, 0));
        storageSquares.add(new StorageSquare("214", 2, 6, 1));
        storageSquares.add(new StorageSquare("207", 2, 6, 2));
        storageSquares.add(new StorageSquare("230", 2, 7, 0));
        storageSquares.add(new StorageSquare("231", 2, 7, 1));
        storageSquares.add(new StorageSquare("232", 2, 7, 2));
        storageSquares.add(new StorageSquare("233", 2, 7, 3));
        storageSquares.add(new StorageSquare("240", 2, 8, 0));
        storageSquares.add(new StorageSquare("241", 2, 8, 1));
        storageSquares.add(new StorageSquare("242", 2, 8, 2));
        storageSquares.add(new StorageSquare("243", 2, 8, 3));
        storageSquares.add(new StorageSquare("250", 2, 9, 0));
        storageSquares.add(new StorageSquare("251", 2, 9, 1));
        storageSquares.add(new StorageSquare("252", 2, 9, 2));
        storageSquares.add(new StorageSquare("253", 2, 9, 3));
        storageSquares.add(new StorageSquare("260", 2, 10, 0));
        storageSquares.add(new StorageSquare("261", 2, 10, 1));
        storageSquares.add(new StorageSquare("262", 2, 10, 2));
        storageSquares.add(new StorageSquare("263", 2, 10, 3));
    }
    
    private void generateObstacles(){
        //here, we add all our obstacles
        // set the cost unreachably high to emphasize the way around
        obstacles.add(new Obstacle("008", "015", 100));
        obstacles.add(new Obstacle("004", "011", 100));
        obstacles.add(new Obstacle("004", "012", 100));
        obstacles.add(new Obstacle("004", "005", 100));
        obstacles.add(new Obstacle("006", "012", 100));
        obstacles.add(new Obstacle("020", "013", 100));
        obstacles.add(new Obstacle("014", "007", 100));
        obstacles.add(new Obstacle("014", "031", 100));
        obstacles.add(new Obstacle("050", "060", 100));
        obstacles.add(new Obstacle("061", "062", 100));
        obstacles.add(new Obstacle("062", "063", 100));
        obstacles.add(new Obstacle("031", "007", 100));
        obstacles.add(new Obstacle("031", "042", 100));
        
    }
    
    protected void populateNeigbors(StorageSquare square){
        //here, we check if neighbors are available. We only consinder neighbors in rows and columns, not floors
        storageSquares
                .stream()
                .filter(s -> s.getFloorNr() == square.getFloorNr())
                .filter(s -> s.getColNr() == square.getColNr()-1 || s.getColNr() == square.getColNr() || s.getColNr() == square.getColNr()+1)
                .filter(s -> s.getRowNr() == square.getRowNr()-1 || s.getRowNr() == square.getRowNr() || s.getRowNr() == square.getRowNr()+1)
                .filter(s -> !s.equals(square))
                .forEach(s -> square.addNeighbor(s, getPriceOfNeighbor(square, s)));
    }
    
    protected void generateElevatorConnections() {
        StorageSquare elevatorSquareG0 = null;
        StorageSquare elevatorSquareG1 = null;
        StorageSquare elevatorSquareG2 = null;
        try{
            elevatorSquareG0 = getSquareByName("011");
            elevatorSquareG1 = getSquareByName("111");
            elevatorSquareG2 = getSquareByName("211");
        }catch (SquareNotPresentInStorageException e){
            return;
        }
        //connections from ground floor
        elevatorSquareG0.addNeighbor(elevatorSquareG1, 20);
        elevatorSquareG0.addNeighbor(elevatorSquareG2, 30);
        //connections from first floor
        elevatorSquareG1.addNeighbor(elevatorSquareG0, 20);
        elevatorSquareG1.addNeighbor(elevatorSquareG2, 20);
        //connections from second floor
        elevatorSquareG2.addNeighbor(elevatorSquareG1, 20);
        elevatorSquareG2.addNeighbor(elevatorSquareG0, 30);
            
    }
    
    private int getPriceOfNeighbor(StorageSquare square, StorageSquare neighbor){
        
        Optional<Obstacle> obstacle = obstacles
                                        .stream()
                                        .filter(o -> o.squaresHaveObstacle(square.getName(), neighbor.getName()))
                                        .findFirst();
        if(!obstacle.isPresent()){
            return 1;
        } else {
            return obstacle.get().getPrice();
        }
    }
    
    protected void setStorageSquares(ArrayList<StorageSquare> storageSquares) {
        this.storageSquares = storageSquares;
    }

    protected void setObstacles(ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    protected static ArrayList<StorageSquare> getStorageSquares() {
        return storageSquares;
    }
    
    protected StorageSquare getSquareByName(String name) throws SquareNotPresentInStorageException{
        Optional<StorageSquare> square = storageSquares
                                            .stream()
                                            .filter(s -> s.getName().equals(name))
                                            .findFirst();
        if(square.isPresent()){
            return square.get();
        } else {
            throw new SquareNotPresentInStorageException(name);
        }
    }
    
    protected StorageSquare getElevatorSquareForFloor(int floorNr) throws SquareNotPresentInStorageException{
        Optional<StorageSquare> square = storageSquares
                                            .stream()
                                            .filter(s -> s.getName().equals(floorNr + "11"))
                                            .findFirst();
        if(square.isPresent()){
            return square.get();
        } else {
            throw new SquareNotPresentInStorageException(floorNr + "11");
        }
    }
    
    /**
     * @desc: This method prints the whole storage on the command line by row.
     */
    protected void printStorage() {
        int floorId = 0;
        for (int i = 0; i <= 2; i++) {
            System.out.println("\nStorage floor " + i);
            int rowId = 0;
            while (rowId<4) {
                List<StorageSquare> rowSquares = getRow(floorId, rowId);
                printSeparationLine();
                printEmptyLine();

                System.out.print("|");
                StorageSquare squareToPrint = null;

                for (int j = 0; j <= 10; j++) {

                    for (int k = 0; k <= rowSquares.size() - 1; k++) {
                        if (rowSquares.get(k).getColNr() == j) {
                            squareToPrint = rowSquares.get(k);
                        }
                    }
                    if (squareToPrint != null) {
                        System.out.print(String.format("  %s  |", squareToPrint.getName()));
                    } else {
                        System.out.print("       |");
                    }
                    squareToPrint = null;

                }
                System.out.print("\n");

                System.out.print("|");
                for (int j = 0; j <= 10; j++) {

                    for (int k = 0; k <= rowSquares.size() - 1; k++) {
                        if (rowSquares.get(k).getColNr() == j) {
                            squareToPrint = rowSquares.get(k);
                        }
                    }
                    if (squareToPrint != null) {
                        if (squareToPrint.getVisitOrder() != 0) {
                            if(squareToPrint.getTimesToVisit() == 1){
                                System.out.print(String.format("%4d   |", squareToPrint.getVisitOrder()));
                            } else {
                                System.out.print(String.format("%2d %2dx |", squareToPrint.getVisitOrder(), squareToPrint.getTimesToVisit()));
                            }
                            
                        } else {
                            System.out.print("       |");
                        }
                    } else {
                        System.out.print("       |");
                    }
                    squareToPrint = null;

                }
                System.out.print("\n");
                rowId++;
            }
            printSeparationLine();
            System.out.println();
            floorId++;
        }

    }
    
    protected void printSeparationLine(){
        System.out.print("+");
        for(int i = 0; i<=10; i++){
            System.out.print("-------+");
        }
        System.out.print("\n");
    }
    
    protected void printEmptyLine(){
        System.out.print("|");
        for(int i = 0; i<=10; i++){
            System.out.print("       |");
        }
        System.out.print("\n");
    }
    
    
    
    protected List<StorageSquare> getRow(int floorId, int rowId){
        List<StorageSquare> rowSquares; 
        rowSquares = storageSquares
                            .stream()
                            .filter(s -> s.getFloorNr() == floorId)
                            .filter(s -> s.getRowNr() == rowId)
                            .sorted(new SquareColSorter())
                            .collect(Collectors.toList());
        return rowSquares;
    }
}