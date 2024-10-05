/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.RAMItem;
import java.util.List;

/**
 *
 * @author Hung
 */
public interface IRamDAO {
    
    // Get all codes of RAM items matching the provided type
    List<String> genCode(String type);
    
    // Retrieve RAMItem by its unique code or an exact match
    RAMItem getRAM(RAMItem ram);
    
     // Check if an item with the given code exists
    boolean isExist(String id);
    
    // Add a new RAM item
    boolean addItem(RAMItem ramItem);
    
    // Search for RAM items based on a given criterion (e.g., type, brand, bus) and value
    void search(String criterion, String value);
    
    // Update an existing RAM item identified by oldId with new RAM item data
    boolean update(String oldId, RAMItem newRamItem);
    
    // Delete a RAM item by its id (code), returning the deleted item
    RAMItem delete(String id);
    
    // Print all active RAM items
    List<String> print();  // Returns a list of string representations of RAM items
    
    // Retrieve all RAM items
    List<RAMItem> getAll();
    
    // Load RAM data from a file or data source
    boolean load();
    
    // Save RAM data to a file or data source
    boolean save();
}
    

