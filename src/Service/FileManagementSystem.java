/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAL.IRamFileDAL;
import DAL.RamFileDAL;
import DAO.IRamDAO;
import DAO.RamDAO;
import data.RAMItem;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.YearMonth;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Hung
 */
public class FileManagementSystem implements IFileManagement{
    private RamDAO ramDAO = new RamDAO();
    private RamFileDAL fileDAL = new RamFileDAL();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void createNewItem() {
        System.out.print("Enter RAM Type: ");
        String type = scanner.nextLine();
        System.out.print("Enter Bus Speed: ");
        String bus = scanner.nextLine();
        System.out.print("Enter Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Production Month-Year (YYYY-MM): ");
        String productMonthYear = YearMonth.parse(scanner.nextLine()).toString();

        String code = ramDAO.generateCode(type);
        RAMItem ramItem = new RAMItem(code, type, bus, brand, quantity, productMonthYear, true);
        if (ramDAO.addItem(ramItem)) {
            System.out.println("New RAM item added successfully!");
        } else {
            System.out.println("RAM item with this code already exists.");
        }
    }

    @Override
    public void searchItem() {
        System.out.println("Search by:\n1. Type\n2. Bus\n3. Brand");
        int choice = Integer.parseInt(scanner.nextLine());
        String criterion = choice == 1 ? "type" : choice == 2 ? "bus" : "brand";

        System.out.print("Enter value to search: ");
        String value = scanner.nextLine();

        List<RAMItem> result = ramDAO.search(criterion, value);
        if (result.isEmpty()) {
            System.out.println("No matching RAM items found.");
        } else {
            result.forEach(System.out::println);
        }
    }

    @Override
    public void updateItem() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter RAM Code to update: ");
    String code = scanner.nextLine();

    // Tìm mục RAM hiện tại theo mã
    List<RAMItem> foundItems = ramDAO.searchByType(code); // Giả sử mã là kiểu
    if (foundItems.isEmpty()) {
        System.out.println("RAM item with this code not found.");
        return;
    }

    // Giả sử chỉ có một mã RAM duy nhất, lấy mục đầu tiên
    RAMItem currentRAM = foundItems.get(0);
    
    // Hiển thị thông tin hiện tại
    System.out.println("Current RAM Information: " + currentRAM);

    // Nhập thông tin mới từ người dùng
    System.out.print("Enter new type (or press Enter to keep current): ");
    String newType = scanner.nextLine();
    System.out.print("Enter new bus (or press Enter to keep current): ");
    String newBus = scanner.nextLine();
    System.out.print("Enter new brand (or press Enter to keep current): ");
    String newBrand = scanner.nextLine();
    System.out.print("Enter new quantity (or press Enter to keep current): ");
    String newQuantityStr = scanner.nextLine();

    // Tạo một đối tượng RAMItem mới với thông tin cập nhật
    RAMItem updatedRAM = new RAMItem(
    currentRAM.getCode(),
    newType.isEmpty() ? currentRAM.getType() : newType,
    newBus.isEmpty() ? currentRAM.getBus() : newBus,
    newBrand.isEmpty() ? currentRAM.getBrand() : newBrand,
    newQuantityStr.isEmpty() ? currentRAM.getQuantity() : Integer.parseInt(newQuantityStr),
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
            System.out.println("RAM item marked as inactive.");
        } else {
            System.out.println("RAM item with this code not found.");
        }
    }

    @Override
    public void loadFile() {
        List<RAMItem> ramItems = fileDAL.loadFromFile();
        ramItems.forEach(ramDAO::addItem);
        System.out.println("Data loaded from file.");
    }

    @Override
     public void saveFile() {
        boolean success = fileDAL.saveToFile(ramDAO.getAll());
        if (success) {
            System.out.println("Data saved to file successfully.");
        } else {
            System.out.println("Failed to save data.");
        }
    }


    @Override
    public void printAllRAMItems() {
    if (ramDAO.getAll().isEmpty()) {
        System.out.println("No RAM items available.");
        return;
    }

    // In tiêu đề bảng
    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
    System.out.printf("| %-4s | %-12s | %-10s | %-10s | %-10s | %-7s | %-15s | %-20s |\n", 
                  "No.", "Code", "Type", "Bus", "Brand", "Qty", "Production Date", "Active");

    System.out.println("|------|--------------|------------|------------|------------|---------|-----------------|----------------------|");

    // In dữ liệu các mục RAM
    int index = 1;
    for (RAMItem ramItem : ramDAO.getAll()) {
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
    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
}
    }
    
