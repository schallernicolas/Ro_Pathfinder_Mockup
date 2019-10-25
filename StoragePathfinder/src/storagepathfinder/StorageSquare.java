/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storagepathfinder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author nsc
 */
public class StorageSquare {
    private final String name;
    private final int floorNr;
    private final int colNr;
    private final int rowNr;
    private HashMap<String, Integer> neighbors;
    
    public StorageSquare(String name, int floorNr, int colNr, int rowNr){
        this.name = name; 
        this.floorNr = floorNr;
        this.colNr = colNr;
        this.rowNr = rowNr;
        neighbors = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public int getFloorNr() {
        return floorNr;
    }

    public int getColNr() {
        return colNr;
    }

    public int getRowNr() {
        return rowNr;
    }

    public HashMap<String, Integer> getNeighbors() {
        return neighbors;
    }
    
    public void addNeighbor(StorageSquare neighbor, int price){
        neighbors.put(neighbor.name, price);
    }
    
    //only for debug purposes!
    public void printSquareInformation(){
        System.out.println("Square " + name);
        neighbors.forEach((key, value) -> {
            System.out.println("     Neigbor " + key + ", Price: " + value);
        });
    }      
}
