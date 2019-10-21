/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storagepathfinder.src;

/**
 *
 * @author nsc
 */
public class Obstacle {
    private StorageSquare first;
    private StorageSquare second;
    private int price;
    
    private Obstacle(StorageSquare first, StorageSquare second, int price){
        this.first = first;
        this.second = second;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
    
    public boolean squaresHaveObstacle(StorageSquare first, StorageSquare second){
        return (first.equals(this.first) && second.equals(this.second)) || 
                (first.equals(this.second) && second.equals(this.first));
    }
    
    
    
}
