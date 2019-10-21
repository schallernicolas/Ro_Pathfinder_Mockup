/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storagepathfinder;

/**
 *
 * @author nsc
 */
public class Obstacle {
    private String first;
    private String second;
    private int price;
    
    public Obstacle(String first, String second, int price){
        this.first = first;
        this.second = second;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
    
    protected boolean squaresHaveObstacle(String first, String second){
        return (first.equals(this.first) && second.equals(this.second)) || 
                (first.equals(this.second) && second.equals(this.first));
    }
    protected boolean squareAffected(String squareName){
        return squareName.equals(first) || squareName.equals(first);
    }
    
    
    
}
