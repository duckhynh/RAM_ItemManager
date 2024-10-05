/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import data.RAMItem;
import java.util.List;

/**
 *
 * @author Hung
 */

public interface IRamFileDAL {
    List<RAMItem> loadFromFile();
    boolean saveToFile(List<RAMItem> ramItems);
}
