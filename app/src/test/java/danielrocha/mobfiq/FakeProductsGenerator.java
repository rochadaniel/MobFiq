package danielrocha.mobfiq;

import java.util.ArrayList;
import java.util.List;

import danielrocha.mobfiq.model.Image;
import danielrocha.mobfiq.model.Product;
import danielrocha.mobfiq.model.Seller;
import danielrocha.mobfiq.model.Sku;

/**
 * Created by danielrocha on 23/06/17.
 */

public class FakeProductsGenerator {

    public static Product getProduct(int id) {
        Product product = new Product();
        product.setName("Produto: " + id);

        List<Sku> skus = new ArrayList<>();
        List<Image> images = new ArrayList<>();
        List<Seller> sellers = new ArrayList<>();

        Image image = new Image();
        image.setImageUrl("https://mobfiq.com.br/images/logo_oficial.png");
        images.add(image);

        Seller seller = new Seller();
        seller.setListPrice(440);
        seller.setPrice(440);
        sellers.add(seller);

        Sku sku = new Sku();
        sku.setImages(images);
        sku.setSellers(sellers);
        skus.add(sku);

        product.setSkus(skus);

        return product;
    }
}
