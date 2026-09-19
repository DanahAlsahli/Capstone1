package org.example.capstone1.Service;

import lombok.RequiredArgsConstructor;
import org.example.capstone1.Model.MerchantStock;
import org.example.capstone1.Model.Product;
import org.example.capstone1.Model.User;
import org.springframework.stereotype.Service;
import org.example.capstone1.Model.Merchant;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {
    ArrayList<User> users = new ArrayList<>();
    private final MerchantService merchantService;
    private final ProductService productService;
    private final MerchantStockService merchantStockService;

    public boolean addUser(User user){
        if(user == null){
            return false;
        }
        users.add(user);
        return true;
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

    public String buyProduct(int userId, int productId, int merchantId){
        User user = searchUserById(userId);
        if(user == null){
            return "user not found";
        }
        Product product = productService.searchProductById(productId);
        if(product == null){
            return "product not found";
        }
        boolean merchantExists = false;
        for(Merchant merchant : merchantService.getMerchants()){
            if(merchant.getId() == merchantId){
                merchantExists = true;
                break;
            }
        }
        if(!merchantExists){
            return "merchant not found";
        }
        MerchantStock merchantStock = merchantStockService.searchMerchantStock(productId, merchantId);
        if(merchantStock == null){
            return "merchant stock not found";
        }
        if(merchantStock.getStock() <= 0){
            return "stock is not enough";
        }
        if(user.getBalance() < product.getPrice()){
            return "balance is not enough";
        }
        merchantStock.setStock(merchantStock.getStock() - 1);
        user.setBalance(user.getBalance() - product.getPrice());
        return "product bought successfully";
    }

    public String addBalance(int userId, double amount) {
        User user = searchUserById(userId);
        if(user == null){
            return "user not found";
        }
        if(amount <= 0){
            return "amount must be positive";
        }
        user.setBalance(user.getBalance() + amount);
        return "balance added";
    }

    public String withdrawBalance(int userId, double amount) {
        User user = searchUserById(userId);
        if(user == null){
            return "user not found";
        }
        if(amount <= 0){
            return "amount must be positive";
        }
        if(user.getBalance() < amount){
            return "balance is not enough";
        }
        user.setBalance(user.getBalance() - amount);
        return "balance withdrawn";
    }
}
