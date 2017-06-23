package danielrocha.mobfiq;

import java.util.ArrayList;
import java.util.List;

import danielrocha.mobfiq.model.Categories;
import danielrocha.mobfiq.model.Category;
import danielrocha.mobfiq.model.SubCategory;

/**
 * Created by danielrocha on 23/06/17.
 */

public class FakeCategoriesGenerator {
    public static List<Category> getCategoryList() {
        List<Category> categoryList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            categoryList.add(getCategoryModel(i));
        }
        return categoryList;
    }

    private static Category getCategoryModel(int id) {
        Category category = new Category();

        category.setName("Categoria: " + id);
        category.setId(id);

        List<SubCategory> subCategoryList = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            SubCategory subCategory = new SubCategory();
            subCategory.setId(j);
            subCategory.setName("Subcategoria: " + j);
            subCategoryList.add(subCategory);
        }

        category.setSubCategories(subCategoryList);
        return category;
    }
}
