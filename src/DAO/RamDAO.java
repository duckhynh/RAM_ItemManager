/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.RAMItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Hung
 */
public class RamDAO implements IRamDAO{
    List<RAMItem> ramItems = new ArrayList<>();
    private static final String FILE_NAME = "RAMModules.dat";
    
    @Override
    public String generateCode(String type) {
        List<RAMItem> list = this.searchByType(type);
        int y;
        if (list == null || list.isEmpty()) {
            y = 1;
        } else {
            Collections.sort(list, new Comparator<RAMItem>() {
                @Override
                public int compare(RAMItem r1, RAMItem r2) {
                    return r1.getCode().compareTo(r2.getCode());
                }
            });
            RAMItem lastRAM = list.get(list.size() - 1);
            String lastCode = lastRAM.getCode();
            String[] parts = lastCode.split("_");
            y = Integer.parseInt(parts[1]) + 1;
        }
        return "RAM" + type + "_" + y;
    }
    @Override
    public boolean isExistCode(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isExistCode'");
    }
    @Override
    public boolean addItem(RAMItem ram) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addItem'");
    }
    @Override
    public List<RAMItem> search(String criterion, String value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }
    @Override
    public List<RAMItem> searchByType(String type) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchByType'");
    }
    @Override
    public List<RAMItem> searchByBus(String bus) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchByBus'");
    }
    @Override
    public List<RAMItem> searchByBrand(String brand) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchByBrand'");
    }
    @Override
    public boolean update(String id, RAMItem newRAM) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    @Override
    public RAMItem delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    @Override
    public List<String> print() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'print'");
    }
    @Override
    public boolean load() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'load'");
    }
    @Override
    public boolean save() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
    @Override
    public List<RAMItem> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    
}