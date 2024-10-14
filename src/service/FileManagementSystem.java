/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DAL.RamFileDAL;
import DAO.RamDAO;
import data.RAMItem;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import tool.GetInput;

/**
 *
 * @author Hung
 */
public class FileManagementSystem implements IFileManagement{
    private final RamDAO ramDAO = new RamDAO();
    private final Scanner scanner = new Scanner(System.in);
    
    private List<RAMItem> ramItems;
    private RamFileDAL ramFileDAL = new RamFileDAL(); 

    public FileManagementSystem() {
        ramItems = new ArrayList<>();
        ramFileDAL = new RamFileDAL();
    }

    @Override
    public void createNewItem() {
        String type = GetInput.getString("Enter RAM: ");
        int bus = GetInput.getInt("Enter Bus(must be a number):",0, 9999);        
        String brand = GetInput.getString("Enter Brand: ");
        int quantity = GetInput.getInt("Enter Quantity(must be a number):",0,9999);
        YearMonth productMonthYear = GetInput.getYearMonth("Enter Production Month-Year (YYYY-MM): ");

        String code = ramDAO.generateCode(type);
        RAMItem ramItem = new RAMItem(code, type, String.valueOf(bus), brand, quantity, productMonthYear.toString(), true);
        if (ramDAO.addItem(ramItem)) {
            System.out.println("New RAM item added successfully!");
        } else {
            System.out.println("RAM item with this code already exists.");
        }
    }

    @Override
        public void searchItem() {
            boolean keepSearching = true;

            while (keepSearching) {
            System.out.println("----------------------------------------");
            System.out.println("|              SUB MENU                |");
            System.out.println("----------------------------------------");
            System.out.println("| Search by:                           |");
            System.out.println("| 1. Type                              |");
            System.out.println("| 2. Bus                               |");
            System.out.println("| 3. Brand                             |");
            System.out.println("| 4. Return to Main Menu               |");
            System.out.println("----------------------------------------");

                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 4) {
                    keepSearching = false; // Exit the loop to return to the main menu
                    continue;
                }

                String criterion;
                switch (choice) {
                    case 1:
                        criterion = "type";
                        break;
                    case 2:
                        criterion = "bus";
                        break;
                    case 3:
                        criterion = "brand";
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        continue; // Skip the rest and prompt again
                }
                
                System.out.print("Enter value to search: ");
                String value = scanner.nextLine();

                List<RAMItem> result = ramDAO.search(criterion, value);
                if (result.isEmpty()) {
                    System.out.println("No matching RAM items found.");
                } else {
                 // In đường viền trên
                System.out.println("===============================================================");
                // In tiêu đề
                System.out.printf("| %-10s | %-10s | %-10s | %-10s | %-8s |\n", 
                                  "Code", "Type", "Bus", "Brand", "Qty");
                System.out.println("===============================================================");

                // In dữ liệu từng sản phẩm RAM
                result.forEach(item -> System.out.printf("| %-10s | %-10s | %-10s | %-10s | %-8d |\n",
                    item.getCode(), item.getType(), item.getBus(), item.getBrand(), item.getQuantity()));

                // In đường viền dưới
                System.out.println("===============================================================");
                }
            }
        }


            @Override
            public void updateItem() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter RAM Code to update: ");
            String code = scanner.nextLine();

             // Tìm tất cả mục RAM hiện tại theo mã
            List<RAMItem> foundItems = ramDAO.searchByType(code); // Tìm kiếm theo mã
            if (foundItems.isEmpty()) {
                System.out.println("RAM item with this code not found.");
                return;
            }

            // Hiển thị thông tin hiện tại cho tất cả các mục
            System.out.println("Current RAM Information:");
            for (int i = 0; i < foundItems.size(); i++) {
                System.out.println((i + 1) + ". " + foundItems.get(i));
            }

            // Nhập chỉ số của mục muốn cập nhật
            System.out.print("Select item number to update (1-" + foundItems.size() + "): ");
            int selectedIndex = Integer.parseInt(scanner.nextLine()) - 1;

            // Kiểm tra chỉ số hợp lệ
            if (selectedIndex < 0 || selectedIndex >= foundItems.size()) {
                System.out.println("Invalid selection. Update canceled.");
                return;
            }

            RAMItem currentRAM = foundItems.get(selectedIndex); // Lấy mục RAM được chọn

            String busString = currentRAM.getBus(); // Lấy giá trị bus dưới dạng String
            int currentBus = Integer.parseInt(busString); // Chuyển đổi sang int

        
            // Nhập thông tin mới từ người dùng
            String newType = GetInput.getNewString("Enter new type (or press Enter to keep current):", currentRAM.getType());
            int newBus = GetInput.getNewInt("Enter new bus (or press Enter to keep current):", 0, 9999, currentBus);
            String newBrand = GetInput.getNewString("Enter new brand (or press Enter to keep current):", currentRAM.getBrand());          
            int newQuantityStr = GetInput.getNewInt("Enter new quantity (or press Enter to keep current): ", 0, 9999, currentRAM.getQuantity());

            // Tạo một đối tượng RAMItem mới với thông tin cập nhật
            RAMItem updatedRAM = new RAMItem(
            currentRAM.getCode(),
            newType.isEmpty() ? currentRAM.getType() : newType,
            (newBus==currentBus) ? currentRAM.getBus() : String.valueOf(newBus),
            newBrand.isEmpty() ? currentRAM.getBrand() : newBrand,
            (newQuantityStr==0) ? currentRAM.getQuantity() : newQuantityStr,
            currentRAM.getProductMonthYear(), // Giữ nguyên ngày sản xuất
            currentRAM.isActive() // Thêm trạng thái 'active'
        );

        // Cập nhật thông tin trong danh sách RAM
            ramDAO.update(code, updatedRAM);
            System.out.println("RAM item updated successfully!");
        }


        @Override
        public void deleteItem() {
            System.out.print("Enter RAM Code to delete: ");
            String code = scanner.nextLine();
            RAMItem ramItem = ramDAO.delete(code);
            if (ramItem != null) {
                System.out.println("RAM has been deleted.");
            } else {
                System.out.println("RAM item with this code not found.");
            }
        }


        @Override
        public void printAllRAMItems() {
        // Fetch all RAM items
        List<RAMItem> ramItems = ramDAO.getAll();

        // Check if the list is empty
        if (ramItems.isEmpty()) {
            System.out.println("No RAM items available.");
            return;
        }

        // Print table header
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-4s | %-12s | %-10s | %-10s | %-10s | %-7s | %-15s | %-20s |\n", 
                          "No.", "Code", "Type", "Bus", "Brand", "Qty", "Production Date", "Active");
        System.out.println("|------|--------------|------------|------------|------------|---------|-----------------|----------------------|");

        // Print data for active RAM items
        int index = 1;
        for (RAMItem ramItem : ramItems) {
            if (ramItem.isActive()) {
                System.out.printf("| %-4d | %-12s | %-10s | %-10s | %-10s | %-7d | %-15s | %-20s |\n",
                                  index++,
                                  ramItem.getCode(),
                                  ramItem.getType(),
                                  ramItem.getBus(),
                                  ramItem.getBrand(),
                                  ramItem.getQuantity(),
                                  ramItem.getProductMonthYear(),
                                  ramItem.isActive() ? "Yes" : "No");
            }
        }

        // Print table footer
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
    }

        @Override
        public boolean saveToFile() {
            RamFileDAL f = new RamFileDAL();
            f.savefile(ramDAO.getAll());
            return true;
        }
    }


    
