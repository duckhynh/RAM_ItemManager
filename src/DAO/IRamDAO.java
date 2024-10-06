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
    
      // Sinh mã RAM dựa trên loại (type)
      String generateCode(String type);
    
      // Kiểm tra mã RAM có tồn tại hay không
      boolean isExistCode(String id);
      
      // Thêm RAM vào danh sách
      boolean addItem(RAMItem ram);
      
      // Tìm kiếm RAM theo tiêu chí và giá trị
      List<RAMItem> search(String criterion, String value);
      
      // Tìm kiếm RAM theo loại (type)
      List<RAMItem> searchByType(String type);
      
      // Tìm kiếm RAM theo bus
      List<RAMItem> searchByBus(String bus);
      
      // Tìm kiếm RAM theo thương hiệu (brand)
      List<RAMItem> searchByBrand(String brand);
      
      // Cập nhật thông tin RAM dựa trên mã
      boolean update(String id, RAMItem newRAM);
      
      // Xóa RAM (thực hiện bằng cách đặt active = false)
      RAMItem delete(String id);
      
      // Hiển thị danh sách RAM đã sắp xếp (chỉ hiển thị các RAM active)
      List<String> print();
      
      // Load dữ liệu từ file
      boolean load();
      
      // Lưu dữ liệu vào file
      boolean save();
      
      // Lấy tất cả các RAM
      List<RAMItem> getAll();
   
}
    

