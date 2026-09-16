package org.example.capstone1.Service;
import org.example.capstone1.Model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockService {
    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    public ArrayList<MerchantStock> getMerchantStocks() {
        return merchantStocks;
    }

    public void addMerchantStock(MerchantStock merchantStock){
        merchantStocks.add(merchantStock);
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

    public boolean addStock(int productId, int merchantId, int amount){
        for(MerchantStock merchantStock : merchantStocks){
            if(merchantStock.getProductID() == productId &&
                    merchantStock.getMerchantID() == merchantId){
                merchantStock.setStock(merchantStock.getStock() + amount);
                return true;
            }
        }
        return false;
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

        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getMerchantID() == merchantId) {
                result.add(merchantStock);
            }
        }
        return result;
    }

    public ArrayList<MerchantStock> getLowStock() {
        ArrayList<MerchantStock> result = new ArrayList<>();

        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getStock() < 10) {
                result.add(merchantStock);
            }
        }

        return result;
    }
}
