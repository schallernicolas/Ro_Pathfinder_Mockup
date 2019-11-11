/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storagepathfinder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author admin
 */
public class Tour {
    
    private ArrayList<StorageSquare> tour = new ArrayList<>();
    // Cache
    private double distance = 0;
    
    public Tour(int size){
        for (int i = 0; i < size; i++) {
            tour.add(null);
        }
    }
    
    public Tour(ArrayList tour){
        this.tour = (ArrayList) tour.clone();
    }
    
    public ArrayList getTour(){
        return tour;
    }
    
    public void generateIndividual(List<StorageSquare> squareList) {
        ArrayList<StorageSquare> listToShuffle = new ArrayList<>();
        for (int i = 1; i < squareList.size()-1; i++) {
          listToShuffle.add(squareList.get(i));
        }
        // Randomly reorder the tour --not for Robatech!
        //Collections.shuffle(listToShuffle);
        listToShuffle.add(0, squareList.get(0));
        listToShuffle.add(squareList.get(squareList.size()-1));
        tour = listToShuffle;
        //tour = squareList;
    }
    
    public void setSquare(int pos, StorageSquare square) {
        tour.set(pos, square);
        // If the tours been altered we need to reset the fitness and distance
        distance = 0;
    }
    
    public StorageSquare getSquare(int pos){
        return tour.get(pos);
    }
    
    // Gets the total distance of the tour
    public double getDistance(){
        if (distance == 0) {
            int tourDistance = 0;
            for (int i=0; i < tour.size(); i++) {
                if(i+1 < tour.size()){
                    distance += AStarPathfinder.findShortestPathBetweenTwoNodesOnSameFloor(tour.get(i), tour.get(i+1));
                }
            }
        }
        return distance;
    }
    
    public void setVisitInformation(){
        int visitOrder = 1;
        Iterator iter = tour.iterator();
        while (iter.hasNext()) {
            StorageSquare square = (StorageSquare)iter.next();
            if(square.getVisitOrder() >= 1){
                square.incrementTimesToVisit();
                iter.remove();
            } else {
                square.setVisitOrder(visitOrder);
                square.incrementTimesToVisit();
                visitOrder++;
            }
        }
    }
    
    public void setSpediAsEndpoint(StorageSquare spediSquare){
        Iterator iter = tour.iterator();
        while (iter.hasNext()) {
            if(((StorageSquare)iter.next()).getName().equals("003")){
                iter.remove();
            }
        }
        tour.add(spediSquare);
    }
    
    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < tour.size(); i++) {
            StorageSquare square = getSquare(i);
            geneString += square+"|";
        }
        return geneString;
    }
}
