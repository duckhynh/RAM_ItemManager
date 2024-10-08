package Tool;

import java.util.ArrayList;
import java.util.List;
import Tool.GetInput;

public class Menu extends ArrayList<String> implements IMenu{

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
}
