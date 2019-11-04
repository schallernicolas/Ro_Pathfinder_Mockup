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
public class SquareNotPresentInStorageException extends Exception {
    private String message;
    
    
    public SquareNotPresentInStorageException(String square){
        setSquareInformation(square);
    }
    
    public void setSquareInformation(String square){
        this.message = "Square '" + square + "' is not present in storage.";
    }
    
    @Override
    public String getMessage(){
        return message;
    } 
    
}
