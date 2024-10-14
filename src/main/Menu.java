package main;

import java.util.ArrayList;
import tool.GetInput;

public class Menu extends ArrayList<String> implements IMenu{
    
    private static final String BACK_TO_MENU_PROMPT = "Do you want to go back to Menu? (Y/N): ";

    public Menu(){
    }


    @Override
    public void addItem(String s) {
        this.add(s);
    }

    @Override
    public int getChoice() {
        return GetInput.getInt(" => Enter your choice: ", 1, this.size());
    }
    
    @Override
    public void showMenu() {
        System.out.println("------------------- MENU -------------------");
        for (int i = 0; i < this.size(); i++) {
            System.out.printf("| %2d.  %-25s %10s|\n", i+1, this.get(i), " ");
        }
        System.out.println("--------------------------------------------");
    }

    @Override
    public boolean confirmYesNo(String prompt) {
        while (true) {
            String flag = GetInput.getString(prompt);
            if (flag.equalsIgnoreCase("Y")) {
                return true;
            } else if (flag.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.println("Please enter 'Y' or 'N' only.");
            }
        }
    }
    // New method to simplify calling the back-to-menu confirmation
    public boolean confirmBackToMenu() {
        return confirmYesNo(BACK_TO_MENU_PROMPT);
    }
}
