/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storagepathfinder;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Optional;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nsc
 */
public class StorageTest {
    private Storage testStorage;
    private ArrayList<StorageSquare> storageSquares;
    private ArrayList<Obstacle> obstacles;
    
    public StorageTest() {
        
    }
    
    @Before
    public void setUp() {
        testStorage = new Storage();
        storageSquares = new ArrayList<>();
        storageSquares.add(new StorageSquare("01", 0, 0, 0));
        storageSquares.add(new StorageSquare("02", 0, 0, 1));
        storageSquares.add(new StorageSquare("03", 0, 0, 2));
        storageSquares.add(new StorageSquare("04", 0, 1, 0));
        storageSquares.add(new StorageSquare("05", 0, 1, 1));
        storageSquares.add(new StorageSquare("06", 0, 1, 2));
        storageSquares.add(new StorageSquare("07", 0, 2, 0));
        storageSquares.add(new StorageSquare("08", 0, 2, 1));
        storageSquares.add(new StorageSquare("09", 0, 2, 2));
        
        obstacles = new ArrayList<>();
        
        //initialize work variables
        
        obstacles.add(new Obstacle("01", "02", 2));
        obstacles.add(new Obstacle("01", "04", 3));
        obstacles.add(new Obstacle("05", "01", 4));
        obstacles.add(new Obstacle("05", "06", 3));
        obstacles.add(new Obstacle("07", "05", 2));
        
        
        testStorage.setStorageSquares(storageSquares);
        testStorage.setObstacles(obstacles);
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testIfNeighborsAreCorrectlyPopulated(){
        testStorage.getStorageSquares().forEach((n) -> testStorage.populateNeigbors(n));
        ArrayList<StorageSquare> squares = testStorage.getStorageSquares();
        //first, check if neighbors of square 1 have the correct cost value and the correct neighbors
        Optional<StorageSquare> square01 = squares
                                            .stream()
                                            .filter(s -> s.getName().equals("01"))
                                            .findFirst();
        assertEquals(true, square01.isPresent());
        HashMap<String, Integer> neighbors = square01.get().getNeighbors();
        assertEquals(3, neighbors.size());
        assertEquals((int)2, (int)neighbors.get("02"));
        assertEquals((int)3, (int)neighbors.get("04"));
        assertEquals((int)4, (int)neighbors.get("05"));
        
        //Now, go for square 5 and do the same checks. Check also for correct standard value (1)
        Optional<StorageSquare> square05 = squares
                                            .stream()
                                            .filter(s -> s.getName().equals("05"))
                                            .findFirst();
        assertEquals(true, square05.isPresent());
        neighbors = square05.get().getNeighbors();
        assertEquals(8, neighbors.size());
        assertEquals((int)4, (int)neighbors.get("01"));
        assertEquals((int)1, (int)neighbors.get("02"));
        assertEquals((int)1, (int)neighbors.get("03"));
        assertEquals((int)1, (int)neighbors.get("04"));
        assertEquals((int)3, (int)neighbors.get("06"));
        assertEquals((int)2, (int)neighbors.get("07"));
        assertEquals((int)1, (int)neighbors.get("08"));
        assertEquals((int)1, (int)neighbors.get("09"));
        
        
        //check id 
        
        
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
