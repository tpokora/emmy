package org.tpokora.views;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UsersViewSeleniumTest extends BasicSeleniumTest {

    @Test
    public void testShowUsersView() {
        loginDefault();
        navigateToUsersView();
    }

    private void navigateToUsersView() {
        WebElement usersTab = this.driver.findElement(By.id("users-tab"));
        usersTab.click();

        Assert.assertEquals(String.format("Did not redirected to %s instead %s",
                ViewsStrings.USERS_URL, getCurrentUrl()), ViewsStrings.USERS_URL, getCurrentUrl());
    }
}
