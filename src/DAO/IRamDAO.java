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
    
     public interface IRAMDAO {
    String generateCode(String type);
    boolean isExistCode(String id);
    boolean addItem(RAMItem ram);
    List<RAMItem> search(String criterion, String value);
    List<RAMItem> searchByType(String type);
    List<RAMItem> searchByBus(String bus);
    List<RAMItem> searchByBrand(String brand);
    boolean update(String id, RAMItem newRAM);
    RAMItem delete(String id);
    List<String> print();
    boolean load();
    void saveFile();
    List<RAMItem> getAll();

   
}

   
}
    

