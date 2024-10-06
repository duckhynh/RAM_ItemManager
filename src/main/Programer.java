/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Tool.Menu;

/**
 *
 * @author Hung
 */
public class Programer {
     public static void main(String[] args) {
 

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
                       
                    } while (!menu.confirmYesNo("Do you want to go back to Menu? (Y/N): "));
                    break;
                case 2:
                    do {
                        
                    } while (!menu.confirmYesNo(" Do you want to go back to Menu? (Y/N): "));
                    break;
                case 3:
                    do {
                        
                    } while (!menu.confirmYesNo(" Do you want to go back to Menu? (Y/N): "));
                    break;
                case 4:
                    do {
                        
                    } while (!menu.confirmYesNo(" Do you want to go back to Menu? (Y/N): "));
                    break;
                case 5:
                    do {
                        
                    } while (!menu.confirmYesNo(" Do you want to go back to Menu? (Y/N): "));
                    break;
                    case 6:
                   
                    System.out.println("Changes have been successfully saved.");
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
