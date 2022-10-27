package org.selenium.pom.dataprovider;

import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Coupon;
import org.selenium.pom.objects.Product;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.annotations.DataProvider;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MyDataProvider {

    @DataProvider(name = "getStoreProducts")
    public static Iterator<Product> getStoreProducts() throws IOException {
   //    Product[] products = JacksonUtils.deserializeJson("products.json", Product[].class);
       List<Product> productsList = new LinkedList<>(Arrays.asList(JacksonUtils.deserializeJson("products.json", Product[].class)));
       Iterator<Product> iterator = productsList.iterator();
       while(iterator.hasNext()) {
           Product pr = iterator.next();
           if (pr.getId() != 1215 && pr.getId() != 1198) {
               iterator.remove();
           }
       }
    //   productsList = productsList.stream().filter(product -> product.getId() != 1215 && product.getId() != 1198).collect(Collectors.toList());
        return productsList.iterator();
    }

    @DataProvider(name = "getUSABillingAddresses")
    public static Iterator<BillingAddress> getUSABillingAddresses() throws IOException {
        BillingAddress[] usaBillingAddresses = JacksonUtils.deserializeJson("multipleBillingAddresses.json", BillingAddress[].class);
        List<BillingAddress> usaBillingAddressesList = new ArrayList<>(Arrays.asList(usaBillingAddresses));
        Iterator<BillingAddress> iterator = usaBillingAddressesList.iterator();
        while(iterator.hasNext()) {
            BillingAddress bl = iterator.next();
            if (!"United States (US)".equals(bl.getCountry())) {
                iterator.remove();
            }
        }
        return usaBillingAddressesList.iterator();
    }

    //, parallel = true
    @DataProvider(name = "getFeaturedProducts", parallel = true)
    public Object[] getFeaturedProducts() throws IOException {
        Product[] products = JacksonUtils.deserializeJson("products.json", Product[].class);
        Product[] featuredProducts = new Product[calculateFeaturedProductSize(products)];
        int currentIndex = 0;
        for (Product product : products) {
            if (product.getFeatured() == true) {
                featuredProducts[currentIndex] = product;
                currentIndex++;
            }
        }
        return featuredProducts;
    }

    private static int calculateFeaturedProductSize(Product[] products) {
        int size = 0;
        for (Product product : products) {
            if (product.getFeatured() == true) {
                size++;
            }
        }
        return size;
    }

    //should be updated
    @DataProvider(name = "getBillingAddresses")
    public Object[] getBillingAddresses() throws IOException {
        return JacksonUtils.deserializeJson("multipleBillingAddresses.json", BillingAddress[].class);
    }

    @DataProvider(name = "getCoupons")
    public static Object[] getCoupons() throws IOException {
        return JacksonUtils.deserializeJson("coupons.json", Coupon[].class);
    }



}