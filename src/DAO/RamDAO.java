/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAL.RamFileDAL;
import data.RAMItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Hung
 */
public class RamDAO implements IRamDAO{
        List<RAMItem> ramItems = new ArrayList<>();
        protected static final String FILE_NAME = "RAMModules.dat";

       // Danh sách RAMItem
        private final List<RAMItem> ramList;
        
         public RamDAO() {
            this.ramList = new ArrayList<>(); 
            loadFromFile();
        }

        private void loadFromFile() {
           RamFileDAL fileManager = new RamFileDAL();

            List<RAMItem> itemsFromFile = fileManager.readfile();
            if (itemsFromFile != null) {
                ramList.addAll(itemsFromFile);
            }
        }

        public boolean saveToFile() {
            RamFileDAL f = new RamFileDAL();
            f.savefile(getAll());
            return true;
        }

        // Sinh mã RAM dựa trên loại (type)
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

        // Kiểm tra mã RAM có tồn tại hay không
        public boolean isExistCode(String id) {
            for (RAMItem ram : ramList) {
                if (ram.getCode().equals(id)) {
                    return true;
                }
            }
            return false;
        }

        // Thêm RAM vào danh sách
        public boolean addItem(RAMItem ram) {
            if (!isExistCode(ram.getCode())) {
                ramList.add(ram);
                return true;
            }
            return false;  // Nếu mã đã tồn tại, không thêm
        }

        // Tìm kiếm RAM theo tiêu chí (criterion: "type", "bus", "brand") và giá trị (value)
        public List<RAMItem> search(String criterion, String value) {
            List<RAMItem> result = new ArrayList<>();

            for (RAMItem ram : ramList) {
                if (matches(ram, criterion, value)) {
                    result.add(ram);
                }
            }

            return result;
        }
        
        // Phương thức trợ giúp để kiểm tra xem RAMItem có khớp với tiêu chí đã cho không
        private boolean matches(RAMItem ram, String criterion, String value) {
            if (criterion.equals("type")) {
                return ram.getType().equals(value);
            } else if (criterion.equals("bus")) {
                return ram.getBus().equals(value);
            } else if (criterion.equals("brand")) {
                return ram.getBrand().equals(value);
            }
            return false; // nếu không khớp tiêu chí nào
        }
        // Tìm kiếm RAM theo loại (type)
        public List<RAMItem> searchByType(String type) {
            return search("type", type);
        }

        // Tìm kiếm RAM theo bus
        public List<RAMItem> searchByBus(String bus) {
            return search("bus", bus);
        }

        // Tìm kiếm RAM theo thương hiệu (brand)
        public List<RAMItem> searchByBrand(String brand) {
            return search("brand", brand);
        }

        // Cập nhật thông tin RAM dựa trên mã
        public boolean update(String id, RAMItem newRAM) {
            for (RAMItem ram : ramList) {
                if (ram.getCode().equals(id)) {
                    ram.setType(newRAM.getType());
                    ram.setBus(newRAM.getBus());
                    ram.setBrand(newRAM.getBrand());
                    ram.setQuantity(newRAM.getQuantity());
                    ram.setProductMonthYear(newRAM.getProductMonthYear());
                    return true;
                }
            }
            return false;
        }

        // Xóa RAM (thực hiện bằng cách đặt active = false)
        public RAMItem delete(String id) {
            for (RAMItem ram : ramList) {
                if (ram.getCode().equals(id)) {
                    ram.setActive(false);
                    return ram;
                }
            }
            return null;
        }


        // Hiển thị danh sách RAM đã sắp xếp (chỉ hiển thị các RAM active)
        public List<String> print() {
            List<RAMItem> activeList = new ArrayList<>();
            for (RAMItem ram : ramList) {
                if (ram.isActive()) {
                    activeList.add(ram);
                }
            }
            Collections.sort(activeList, new Comparator<RAMItem>() {
                @Override
                public int compare(RAMItem r1, RAMItem r2) {
                    int typeComp = r1.getType().compareTo(r2.getType());
                    if (typeComp != 0) return typeComp;
                    int busComp = r1.getBus().compareTo(r2.getBus());
                    if (busComp != 0) return busComp;
                    return r1.getBrand().compareTo(r2.getBrand());
                }
            });

            List<String> result = new ArrayList<>();
            for (RAMItem ram : activeList) {
                result.add(ram.toString());
            }
            return result;
        }

        public List<RAMItem> getAll() {
            return new ArrayList<>(ramList); // Trả về một bản sao của danh sách
        }
}