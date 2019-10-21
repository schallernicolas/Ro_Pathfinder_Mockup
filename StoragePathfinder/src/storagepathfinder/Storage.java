/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storagepathfinder;

import java.util.ArrayList;

/**
 *
 * @author nsc
 */
public class Storage {
    private ArrayList<StorageSquare> storageSquares;
    
    public Storage(){
        generateStorage();
    }
    
    private void generateStorage(){
        generateStorageSquares();
        populateNeighbors();
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
    
    private void populateNeighbors(){
        storageSquares.forEach((n) -> findAndValueNeigbors(n));
    }
    
    private void findAndValueNeigbors(StorageSquare square){
        
    }
    
    
}
