package org.example.capstone1.Service;

import org.example.capstone1.Model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {
    ArrayList<Category> categories = new ArrayList<>();

    public boolean addCategory(Category category){
        if(category == null){
            return false;
        }
        categories.add(category);
        return true;
    }

    public ArrayList<Category> getCategories(){
        return categories;
    }

    public boolean updateCategory(int id, Category category){
        for(int i = 0; i < categories.size(); i++){
            if(categories.get(i).getId() == id){
                categories.set(i, category);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCategory(int id){
        for(int i = 0; i < categories.size(); i++){
            if(categories.get(i).getId() == id){
                categories.remove(i);
                return true;
            }
        }
        return false;
    }
}

