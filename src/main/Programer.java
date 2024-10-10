/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import DAO.RamDAO;
import Service.FileManagementSystem;
import Tool.Menu;
import java.util.Scanner;

/**
 *
 * @author Hung
 */
public class Programer {
     public static void main(String[] args) {
         
        FileManagementSystem filemanagementsystem = new FileManagementSystem();
        
        Menu menu = new Menu();
        menu.addItem("Add an Item");
        menu.addItem("Search SubMenu");
        menu.addItem("Update Item Information");
        menu.addItem("Delete Item");
        menu.addItem("Show All Items");
        menu.addItem("Store Data to Files");
        menu.addItem("Exit");

        boolean exit = false;
        while (!exit) {
            menu.showMenu();
            int choice = menu.getChoice();
            switch (choice) {
                case 1:
                    do {
                      filemanagementsystem.createNewItem();
                    } while (!menu.confirmYesNo("Do you want to go back to Menu? (Y/N): "));
                    break;
                case 2:
                    do {
                      filemanagementsystem.searchItem();
                    } while (!menu.confirmYesNo(" Do you want to go back to Menu? (Y/N): "));
                    break;
                case 3:
                    do {
                      filemanagementsystem.updateItem();
                    } while (!menu.confirmYesNo(" Do you want to go back to Menu? (Y/N): "));
                    break;
                case 4:
                    do {
                      filemanagementsystem.deleteItem();
                    } while (!menu.confirmYesNo(" Do you want to go back to Menu? (Y/N): "));
                    break;
                case 5:
                    do {
                      filemanagementsystem.printAllRAMItems();
                    } while (!menu.confirmYesNo(" Do you want to go back to Menu? (Y/N): "));
                    break;
                    case 6:
                    filemanagementsystem.saveToFile();
                    break;
                case 7:
                    exit = true;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            
        }
    }
}
