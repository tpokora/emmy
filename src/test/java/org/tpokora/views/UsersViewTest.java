package org.tpokora.views;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UsersViewTest extends BasicTest {

    @Test
    public void showUsersView() {
        login(this.testProperties.getUsername(), this.testProperties.getPassword());

        WebElement usersTab = this.driver.findElement(By.id("users-tab"));
        usersTab.click();

        Assert.assertEquals(ViewsStrings.USERS_URL, getCurrentUrl());
    }
}
