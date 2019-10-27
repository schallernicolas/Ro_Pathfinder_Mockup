/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storagepathfinder;

import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author nsc
 */
public class Storage {
    private ArrayList<StorageSquare> storageSquares = new ArrayList<>();
    private ArrayList<Obstacle> obstacles = new ArrayList<>();
    
    public Storage(){
    }
    
    protected void generateStorage(){
        generateStorageSquares();
        generateObstacles();
        storageSquares.forEach((n) -> populateNeigbors(n));
        storageSquares.forEach((n) -> n.printSquareInformation());
        
    }
    
    //test set of storage squares
    private void generateStorageSquares(){
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

    protected ArrayList<StorageSquare> getStorageSquares() {
        return storageSquares;
    }
}