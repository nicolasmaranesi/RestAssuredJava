package Tests;

import APIClasses.GetAllProducts;
import APIClasses.GetSpecificProduct;
import APIClasses.PostNewProduct;
import Data.EnvironmentData;
import Utils.BaseClass;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static APIClasses.DeleteProduct.deleteProductById;
import static APIClasses.GetAllProducts.getProductTitle;
import static APIClasses.GetAllProducts.responseObjectCounter;

public class API extends BaseClass {



    EnvironmentData environmentData = new EnvironmentData();
    String url = environmentData.BaseURL;

    @Test(priority = 1)
    public void getAllProducts() {
        test = extent.createTest("Test GET all products");
        Response getAllProductsResponse = GetAllProducts.getAllProducts(url, test);
        Assert.assertEquals(getAllProductsResponse.statusCode(),200);
        String firstProductTitle =    getProductTitle(getAllProductsResponse,1);
        Assert.assertEquals(firstProductTitle,"Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops");

    }

    @Test(priority = 2)
    public void getSpecificProduct() {
        test = extent.createTest("Test GET first product");
        Response getAllProductsResponse = GetAllProducts.getAllProducts(url, test);
        Assert.assertEquals(getAllProductsResponse.statusCode(),200);
        String firstProductTitle =    getProductTitle(getAllProductsResponse,1);
        Response getFirstProduct = GetSpecificProduct.getSpecificProductById(url,"1",test);
        Assert.assertEquals(getFirstProduct.statusCode(),200);
        String specificProductTitle = GetSpecificProduct.getSpecificProductTitle(getFirstProduct);
        Assert.assertEquals(specificProductTitle,firstProductTitle);

    }

    @Test(priority = 3)
    public void postNewProduct(){
        test = extent.createTest("Test Post new product");
       Response postNewProductResponse = PostNewProduct.createNewProduct(url, "Iphone 16 plus",109.90,"Newest APPLE phone","https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.apple.com%2Fla%2Fnewsroom%2F2024%2F09%2Fapple-debuts-iphone-16-pro-and-iphone-16-pro-max%2F&psig=AOvVaw3JYPdvCyx7BcNc6TkyRxoz&ust=1762726576945000&source=images&cd=vfe&opi=89978449&ved=0CBUQjRxqFwoTCKioha_K45ADFQAAAAAdAAAAABAE","Electronic",test);
       Assert.assertEquals(postNewProductResponse.statusCode(),201);
        Response getAllProductsResponse = GetAllProducts.getAllProducts(url, test);
        Assert.assertEquals(getAllProductsResponse.statusCode(),200);
        String lastObjectIndex = String.valueOf(responseObjectCounter(getAllProductsResponse));
        Response getLastProduct = GetSpecificProduct.getSpecificProductById(url,lastObjectIndex,test);
        Assert.assertEquals(getLastProduct.statusCode(),200);
        String specificProductTitle = GetSpecificProduct.getSpecificProductTitle(getLastProduct);
        Assert.assertEquals(specificProductTitle,"Iphone 16 plus");

    }

    @Test(priority = 4)
    public void deleteProduct(){
        test = extent.createTest("Test Delete product");
        Response deleteProduct = deleteProductById(url, "1",test);
        Assert.assertEquals(deleteProduct.statusCode(),200);

    }


}
