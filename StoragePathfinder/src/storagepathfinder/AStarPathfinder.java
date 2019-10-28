/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storagepathfinder;

import java.util.HashMap;

/**
 *
 * @author nsc
 */
public class AStarPathfinder {
    private Storage storage;
    private StorageSquare startSquare;
    private StorageSquare endSquare;
    private double INFINITY = Double.POSITIVE_INFINITY;
    
    public AStarPathfinder(Storage storage, String startSquare, String endSquare) throws SquareNotPresentInStorageException{
        this.storage = storage;
        this.startSquare = storage.getSquareByName(startSquare);
        this.endSquare = storage.getSquareByName(startSquare);
        setHeuristicCostsRecursively(this.endSquare);
    }
    /**
     * 
     * @desc: this method uses the A*-Algorithm to determine the shortest distance between 2 storage-squares
     */
    protected void getShortestDistanceTwoPoints(){
        
    }
    
    protected void setHeuristicCostsRecursively(StorageSquare square){
        calculateHeuristicCostToEndSquare(square);
        HashMap<StorageSquare, Integer> neighbors = endSquare.getNeighbors();
        
        for(StorageSquare neighbor : neighbors.keySet()){
            if(neighbor.getHeuristicCost() != INFINITY){
                setHeuristicCostsRecursively(square);
            }
        }
    }
    
    private void calculateHeuristicCostToEndSquare(StorageSquare square){
        int distance = Math.abs((square.getColNr() + square.getRowNr())-(endSquare.getColNr() + endSquare.getRowNr()));
        square.setHeuristicCost(distance);
    }
    
    
    
}
