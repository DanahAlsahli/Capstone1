package org.example.capstone1.Service;

import lombok.RequiredArgsConstructor;
import org.example.capstone1.Model.MerchantStock;
import org.example.capstone1.Model.Product;
import org.example.capstone1.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {
    ArrayList<User> users = new ArrayList<>();

    private final ProductService productService;
    private final MerchantStockService merchantStockService;

    public void addUser(User user){
        users.add(user);
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    public boolean updateUser(int id, User user){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getId() == id){
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(int id){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getId() == id){
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    public User searchUserById(int id){
        for(User user : users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public boolean buyProduct(int userId, int productId, int merchantId){

        User user = searchUserById(userId);
        Product product = productService.searchProductById(productId);
        MerchantStock merchantStock = merchantStockService.searchMerchantStock(productId, merchantId);

        if(user == null || product == null || merchantStock == null){
            return false;
        }

        if(merchantStock.getStock() <= 0){
            return false;
        }

        if(user.getBalance() < product.getPrice()){
            return false;
        }

        merchantStock.setStock(merchantStock.getStock() - 1);
        user.setBalance(user.getBalance() - product.getPrice());

        return true;
    }

    public boolean addBalance(int userId, double amount) {
        User user = searchUserById(userId);

        if (user != null && amount > 0) {
            user.setBalance(user.getBalance() + amount);
            return true;
        }

        return false;
    }

    public boolean withdrawBalance(int userId, double amount) {
        User user = searchUserById(userId);

        if (user != null && amount > 0 && user.getBalance() >= amount) {
            user.setBalance(user.getBalance() - amount);
            return true;
        }

        return false;
    }
}
