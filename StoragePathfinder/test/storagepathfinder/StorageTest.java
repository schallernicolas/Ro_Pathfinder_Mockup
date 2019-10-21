/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storagepathfinder;

import java.util.ArrayList;
import java.util.HashMap;
import junit.framework.Assert;
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
    private HashMap<String, HashMap<String, Integer>> obstacles;
    
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
        
        obstacles = new HashMap<>();
        
        //initialize work variables
        String squareName = "";
        HashMap squareObstacle = new HashMap<String, Integer>();
       
        // for square 01
        squareObstacle.put("02", 2);
        squareObstacle.put("04", 2);
        squareName = "01";
        obstacles.put(squareName, squareObstacle);
        
        testStorage.setStorageSquares(storageSquares);
        testStorage.setObstacles(obstacles);
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testIfNeighborsAreCorrectlyPopulated(){
        testStorage.getStorageSquares().forEach((n) -> testStorage.populateNeigbors(n));
        
        assertEquals(null, expected, actual);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
