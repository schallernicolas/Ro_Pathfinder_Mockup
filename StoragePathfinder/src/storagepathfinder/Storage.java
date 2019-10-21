/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storagepathfinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 *
 * @author nsc
 */
public class Storage {
    private ArrayList<StorageSquare> storageSquares = new ArrayList<>();
    //Todo: DataStructure for obstacles
    private HashMap<String, HashMap<String, Integer>> obstacles = new HashMap<>();
    
    
    public Storage(){
        generateStorage();
        // manually add neighbors to test algorithm
        StorageSquare square04 = storageSquares.get(11);
        StorageSquare square05 = storageSquares.get(14);
        StorageSquare square12 = storageSquares.get(13);
        StorageSquare square11 = storageSquares.get(10);
        square04.removeNeighbor(square11);
        square04.removeNeighbor(square05);
        square04.removeNeighbor(square12);
        square05.removeNeighbor(square04);
        square12.removeNeighbor(square04);
        square11.removeNeighbor(square04);
        /*square04.addNeighbor(square05, 3);
        square04.addNeighbor(square12, 3);
        square04.addNeighbor(square11, 2);
        square05.addNeighbor(square04, 3);
        square12.addNeighbor(square04, 3);
        square11.addNeighbor(square04, 2);*/
        storageSquares.set(11, square04);
        storageSquares.set(14, square05);
        storageSquares.set(13, square12);
        storageSquares.set(10, square11);
        System.out.println();
        storageSquares.forEach((n) -> n.printSquareInformation());
    }
    
    public List<StorageSquare> getStorageSquares(){
        return storageSquares;
    }
    
    private void generateStorage(){
        generateStorageSquares();
        generateObstacles();
        
        storageSquares.forEach((n) -> populateNeigbors(n));
        //storageSquares.forEach((n) -> n.printSquareInformation());
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
        //initialize work variables
        String squareName = "";
        HashMap squareObstacle = new HashMap<String, Integer>();
       
        // for square 015
        squareObstacle.put("008", 2);
        squareName = "015";
        obstacles.put(squareName, squareObstacle);
        squareObstacle.clear();
        
        // for square 008
        squareObstacle.put("015", 2);
        squareName = "008";
        obstacles.put(squareName, squareObstacle);
        squareObstacle.clear();
        
        //for square 004
        squareObstacle.put("011", 2);
        squareObstacle.put("012", 3);
        squareObstacle.put("005", 3);
        squareName = "004";
        obstacles.put(squareName, squareObstacle);
        squareObstacle.clear();
        
        //for square 011
        squareObstacle.put("004", 2);
        squareName = "011";
        obstacles.put(squareName, squareObstacle);
        squareObstacle.clear();
        
        //for square 012
        squareObstacle.put("004", 3);
        squareName = "012";
        obstacles.put(squareName, squareObstacle);
        squareObstacle.clear();
        
        //for square 005
        squareObstacle.put("004", 3);
        squareObstacle.put("006", 2);
        squareName = "005";
        obstacles.put(squareName, squareObstacle);
        squareObstacle.clear();
        
    }
    
    private void populateNeigbors(StorageSquare square){
        //here, we check if neighbors are available. We only consinder neighbors in rows and columns, not floors
        storageSquares
                .stream()
                .filter(s -> s.getFloorNr() == square.getFloorNr())
                .filter(s -> s.getColNr() == square.getColNr()-1 || s.getColNr() == square.getColNr() || s.getColNr() == square.getColNr()+1)
                .filter(s -> s.getRowNr() == square.getRowNr()-1 || s.getRowNr() == square.getRowNr() || s.getRowNr() == square.getRowNr()+1)
                .filter(s -> !s.equals(square))
                .forEach(s -> square.addNeighbor(s, 1/*getPriceOfNeighbor(square, s)*/));
    }
    
    private int getPriceOfNeighbor(StorageSquare square, StorageSquare neighbor){
        HashMap<String, Integer> obstaclesForSquare;
        obstaclesForSquare = obstacles.get(square.getName());
        if(obstaclesForSquare == null){
            System.out.println(square.getName());
            return 1;
        }
        Integer price = obstaclesForSquare.get(neighbor.getName());
        if (price == null){
            return 1;
        } else {
            return price;
        }
    }
}