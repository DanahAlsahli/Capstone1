package org.example.capstone1.Service;

import org.example.capstone1.Model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockService {
    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    private final ProductService productService;
    private final MerchantService merchantService;

    public MerchantStockService(ProductService productService, MerchantService merchantService) {
        this.productService = productService;
        this.merchantService = merchantService;
    }

    public ArrayList<MerchantStock> getMerchantStocks() {
        return merchantStocks;
    }

    public boolean addMerchantStock(MerchantStock merchantStock){
        if(merchantStock == null){
            return false;
        }

        merchantStocks.add(merchantStock);
        return true;
    }

    public boolean updateMerchantStock(int id, MerchantStock merchantStock){
        for(int i = 0; i < merchantStocks.size(); i++){
            if(merchantStocks.get(i).getId() == id){
                merchantStocks.set(i, merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchantStock(int id){
        for(int i = 0; i < merchantStocks.size(); i++){
            if(merchantStocks.get(i).getId() == id){
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }

    public String addStock(int productId, int merchantId, int amount){

        if(productService.searchProductById(productId) == null){
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

        if(amount <= 0){
            return "amount must be positive";
        }

        for(MerchantStock merchantStock : merchantStocks){
            if(merchantStock.getProductID() == productId &&
                    merchantStock.getMerchantID() == merchantId){

                merchantStock.setStock(merchantStock.getStock() + amount);
                return "stock added";
            }
        }

        return "merchant stock not found";
    }

    public MerchantStock searchMerchantStock(int productId, int merchantId){
        for(MerchantStock merchantStock : merchantStocks){
            if(merchantStock.getProductID() == productId &&
                    merchantStock.getMerchantID() == merchantId){
                return merchantStock;
            }
        }
        return null;
    }

    public ArrayList<MerchantStock> getMerchantStocksByMerchantId(int merchantId) {
        ArrayList<MerchantStock> result = new ArrayList<>();

        for(MerchantStock merchantStock : merchantStocks) {
            if(merchantStock.getMerchantID() == merchantId) {
                result.add(merchantStock);
            }
        }

        return result;
    }

    public ArrayList<MerchantStock> getLowStock() {
        ArrayList<MerchantStock> result = new ArrayList<>();

        for(MerchantStock merchantStock : merchantStocks) {
            if(merchantStock.getStock() < 10) {
                result.add(merchantStock);
            }
        }

        return result;
    }
}
