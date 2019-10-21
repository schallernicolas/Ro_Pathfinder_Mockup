/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storagepathfinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author nsc
 */
public class StorageSquare {
    private String name;
    private int floorNr;
    private int colNr;
    private int rowNr;
    private HashMap<StorageSquare, Integer> neighbors;
    
    public StorageSquare(String name, int floorNr, int colNr, int rowNr){
        this.name = name; 
        this.floorNr = floorNr;
        this.colNr = colNr;
        this.rowNr = rowNr;
        neighbors = new HashMap<StorageSquare, Integer>();
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

    public HashMap<StorageSquare, Integer> getNeighbors() {
        return neighbors;
    }
    
    public void addNeighbor(StorageSquare neighbor, int price){
        neighbors.put(neighbor, price);
    }
    
    public void test(){
        List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
        myList.stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
    }
    
    public void printSquareInformation(){
        System.out.println("Square " + name);
        neighbors.forEach((key, value) -> {
            System.out.println("     Neigbor " + key + ", Price: " + value);
        });
    }      
}
