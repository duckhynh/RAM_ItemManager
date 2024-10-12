/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import data.RAMItem;
import java.util.List;

/**
 *
 * @author Hung
 */
interface IFileManagement {
    void createNewItem();
    void searchItem();
    void updateItem();
    void deleteItem();
    boolean saveToFile();
    void printAllRAMItems();
}
