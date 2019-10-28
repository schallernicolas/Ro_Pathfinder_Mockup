/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storagepathfinder;

import java.util.List;

/**
 *
 * @author admin
 */
public class SimulatedAnnealing {
    
    private List<StorageSquare> squareList;
    // Set initial temp
    double temp = 10000;

    // Cooling rate
    double coolingRate = 0.003;
    
    public SimulatedAnnealing(List<StorageSquare> squareList){
        this.squareList = squareList;
    }
    
    public static double acceptanceProbability(double energy, double newEnergy, double temperature) {
        // If the new solution is better, accept it
        if (newEnergy < energy) {
            return 1.0;
        }
        // If the new solution is worse, calculate an acceptance probability
        return Math.exp((energy - newEnergy) / temperature);
    }
    
    public void simulateAnnealing(){
        // Initialize intial solution
        Tour currentSolution = new Tour(squareList.size());
        
        currentSolution.generateIndividual(squareList);
        System.out.println("Initial solution distance: " + currentSolution.getDistance());
        
        // Set as current best
        Tour best = new Tour(currentSolution.getTour());
        
        while (temp > 1) {
            // Create new neighbour tour
            Tour newSolution = new Tour(currentSolution.getTour());

            int tourPos1 = getRandomSwapPosition();
            int tourPos2 = getRandomSwapPosition();

            StorageSquare squareToSwap1 = newSolution.getSquare(tourPos1);
            StorageSquare squareToSwap2 = newSolution.getSquare(tourPos2);

            newSolution.setSquare(tourPos2, squareToSwap1);
            newSolution.setSquare(tourPos1, squareToSwap2);
            
            double currentEnergy = currentSolution.getDistance();
            double neighbourEnergy = newSolution.getDistance();

            // Decide if we should accept the neighbour
            if (acceptanceProbability(currentEnergy, neighbourEnergy, temp) > Math.random()) {
                currentSolution = new Tour(newSolution.getTour());
            }

            // Keep track of the best solution found
            if (currentSolution.getDistance() < best.getDistance()) {
                best = new Tour(currentSolution.getTour());
            }
            
            // Cool system
            temp *= 1-coolingRate;
        }
        
        System.out.println("Final solution distance: " + best.getDistance());
        System.out.println("Tour: " + best);
    }
    
    private int getRandomSwapPosition(){
        int randomPosition = 0;
        while(true){
            randomPosition = (int) (squareList.size() * Math.random());
            if(randomPosition > 1 && randomPosition < squareList.size()-2){
                return randomPosition;
            }            
        }
    }
    
}
