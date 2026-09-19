package org.example.capstone1.Service;

import org.example.capstone1.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
    ArrayList<Product> products = new ArrayList<>();

    private final MerchantService merchantService;

    public ProductService(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    public boolean addProduct(Product product){
        if(product == null){
            return false;
        }

        products.add(product);
        return true;
    }

    public ArrayList<Product> getProducts(){
        return products;
    }

    public boolean updateProduct(int id, Product product){
        for(int i = 0; i < products.size(); i++){
            if(products.get(i).getId() == id){
                products.set(i, product);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(int id){
        for(int i = 0; i < products.size(); i++){
            if(products.get(i).getId() == id){
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public Product searchProductById(int id){
        for(Product product : products){
            if(product.getId() == id){
                return product;
            }
        }
        return null;
    }

    public String updatePrice(int productId, int merchantId, double newPrice) {
        Product product = searchProductById(productId);

        if(product == null){
            return "product not found";
        }
        boolean merchantExists = false;

        for(int i = 0; i < merchantService.getMerchants().size(); i++){
            if(merchantService.getMerchants().get(i).getId() == merchantId){
                merchantExists = true;
                break;
            }
        }
        if(!merchantExists){
            return "merchant not found";
        }
        if(newPrice <= 0){
            return "price must be positive";
        }
        product.setPrice(newPrice);
        return "price updated";
    }

    public ArrayList<Product> getProductsLessThanPrice(double price) {
        ArrayList<Product> result = new ArrayList<>();

        for (Product product : products) {
            if (product.getPrice() < price) {
                result.add(product);
            }
        }
        return result;
    }

    public Product recommendProduct(double balance) {
        Product recommendedProduct = null;
        for (Product product : products) {
            if (product.getPrice() <= balance) {
                if (recommendedProduct == null ||
                        product.getPrice() > recommendedProduct.getPrice()) {
                    recommendedProduct = product;
                }
            }
        }
        return recommendedProduct;
    }

    public Product getBestDeal() {
        Product bestProduct = null;
        for (Product product : products) {
            if (bestProduct == null ||
                    product.getPrice() < bestProduct.getPrice()) {
                bestProduct = product;
            }
        }
        return bestProduct;
    }

    public Product getLuxuryRecommendation(double balance) {
        Product recommendedProduct = null;

        for (Product product : products) {
            if (product.getPrice() <= balance) {
                if (recommendedProduct == null ||
                        product.getPrice() > recommendedProduct.getPrice()) {
                    recommendedProduct = product;
                }
            }
        }
        return recommendedProduct;
    }
}

