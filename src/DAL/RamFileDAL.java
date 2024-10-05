/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import data.RAMItem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hung
 */
public class RamFileDAL implements IRamFileDAL {

    @Override
    public List<RAMItem> loadFromFile() {
        List<RAMItem> ramItems = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("RAMModules.dat"))) {
        ramItems = (List<RAMItem>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found, starting fresh.");
            return new ArrayList<>(); // Return an empty list
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>(); // Return an empty list
        }
        return ramItems;
    }

    @Override
    public boolean saveToFile(List<RAMItem> ramItems) {
        if (ramItems == null) {
            System.out.println("Cannot save null list.");
            return false;
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("RAMModules.dat"))) {
            oos.writeObject(ramItems);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
