/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storagepathfinder.util;

import java.util.Comparator;
import storagepathfinder.StorageSquare;

/**
 *
 * @author nsc
 */
public class SquareColSorter implements Comparator<StorageSquare> {

    @Override
    public int compare(StorageSquare o1, StorageSquare o2) {
        return new Integer(o1.getColNr()).compareTo(o2.getColNr());
    }
    
}
