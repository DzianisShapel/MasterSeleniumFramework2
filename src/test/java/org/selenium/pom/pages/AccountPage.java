package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class AccountPage extends BasePage {

    private final By usernameFld =  By.cssSelector("#username");
    private final By passwordFld =  By.cssSelector("#password");
    private final By loginBtn =  By.cssSelector("button[value='Log in']");
    private final By contentMsg = By.cssSelector("div[id='content'] li:nth-child(1)");
    private final By orders  = By.cssSelector("li[class='woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--orders'] a");
    private final By viewOrdersBtn = By.cssSelector(".woocommerce-button.button.view");

    private final By orderDetails = By.cssSelector(".woocommerce-order-details__title");

    private final By productName = By.cssSelector("a[href='https://askomdch.com/product/blue-shoes/']");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public AccountPage login(String username, String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameFld)).sendKeys(username);
        driver.findElement(passwordFld).sendKeys(password);
        driver.findElement(loginBtn).click();
        return this;
    }

    public String getContentMessage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(contentMsg)).getText();
    }

    public AccountPage navigateToOrders(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(orders)).click();
        return this;
    }

    public  AccountPage viewOrder(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewOrdersBtn)).click();
        return this;
    }

    public String getOrderDetailsTitle(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderDetails)).getText();
    }

    public  String getOrderedProductName(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
    }


    public AccountPage load(){
        load("/account/");
        return this;
    }

    public AccountPage loadOrders(){
        load("/account/orders");
        return this;
    }

}
