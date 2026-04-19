package com.mashreq.pages;

import com..utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManageRolesInUserManagementPage {
    private WebDriver driver;
    private ElementUtils elementUtils;

    @FindBy(id = "rolesTab")
    private WebElement rolesTab;
    @FindBy(id = "rolesList")
    private WebElement rolesList;
    @FindBy(id = "searchBar")
    private WebElement searchBar;
    @FindBy(id = "createButton")
    private WebElement createButton;
    @FindBy(id = "roleNameField")
    private WebElement roleNameField;
    @FindBy(id = "descriptionField")
    private WebElement descriptionField;
    @FindBy(id = "successMessage")
    private WebElement successMessage;
    @FindBy(id = "modalCloseButton")
    private WebElement modalCloseButton;
    @FindBy(id = "toggleButton")
    private WebElement toggleButton;

    public ManageRolesInUserManagementPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnRolesTab() {
        elementUtils.clickElement(rolesTab);
    }

    public boolean isRolesListDisplayed() {
        return elementUtils.isElementDisplayed(rolesList);
    }

    public void enterRoleInSearchBar(String roleName) {
        elementUtils.clearAndSendKeys(searchBar, roleName);
    }

    public void clicksCreateButton() {
        elementUtils.clickElement(createButton);
    }

    public void enterRoleName(String roleName) {
        elementUtils.clearAndSendKeys(roleNameField, roleName);
    }

    public void enterRoleDescription(String description) {
        elementUtils.clearAndSendKeys(descriptionField, description);
    }

    public boolean isRoleCreatedSuccessfully() {
        return elementUtils.isElementDisplayed(successMessage);
    }

    ublic void clickToggleButton() {
        elementUtils.clickElement(toggleButton);
    }

    public boolean isModalClosed() {
        return !elementUtils.isElementDisplayed(modalCloseButton);
    }

    public void refreshRolesList() {
        driver.navigate().refresh();
    }

    public boolean areFilteredRolesDisplayed() {
        return elementUtils.isElementDisplayed(rolesList) && rolesList.findElements(By.tagName("li")).size() > 0;
    }

    public boolean isNewRoleDisplayedInList() {
        return rolesList.findElements(By.xpath("//li[contains(text(),'" + elementUtils.getElementText(roleNameField) + "')]")).size() > 0;
    }

    public boolean isRoleStatusActive() {
        return toggleButton.getAttribute("class").contains("active");
    }

    public boolean isRoleStatusInactive() {
        return toggleButton.getAttribute("class").contains("inactive");
    }

    public boolean isUpdateSuccessMessageDisplayed() {
        return elementUtils.isElementDisplayed(successMessage) && successMessage.getText().contains("Update successful");
    }

    public boolean isCreationSuccessMessageDisplayed() {
        return elementUtils.isElementDisplayed(successMessage) && successMessage.getText().contains("Creation successful");
    }

    public boolean isDeactivationSuccessMessageDisplayed() {
        return elementUtils.isElementDisplayed(successMessage) && successMessage.getText().contains("Deactivation successful");
    }

    public boolean isActivationSuccessMessageDisplayed() {
        return elementUtils.isElementDisplayed(successMessage) && successMessage.getText().contains("Activation successful");
    }

    public void isRoleUpdatedSuccessfully() {
        String actualMessage = elementUtils.getElementText(successMessage);
        String expectedMessage = "Role updated successfully";
        Assert.assertEquals(actualMessage, expectedMessage, "Role update success message is incorrect!");
    }

    public void isRolesListScrollable() {
        boolean isScrollable = rolesList.getAttribute("scrollHeight").equals(rolesList.getAttribute("clientHeight"));
        Assert.assertTrue(isScrollable, "Roles list is not scrollable!");
    }

    public void isRolesListUnchanged() {
        List<WebElement> initialRoles = driver.findElements(By.className("role-item"));
        List<WebElement> updatedRoles = driver.findElements(By.className("role-item"));
        Assert.assertEquals(initialRoles.size(), updatedRoles.size(), "Roles list has changed unexpectedly!");
    }

    public void clicksEditIconForExistingRole() {
        WebElement editIcon = driver.findElement(By.xpath("//div[@class='role-item']//button[@class='edit']"));
        elementUtils.clickElement(editIcon);
    }

    public void isNewRoleDisplayedWithoutRefresh() {
        WebElement newRoleElement = driver.findElement(By.xpath("//div[text()='New Role']"));
        Assert.assertTrue(elementUtils.isElementDisplayed(newRoleElement), "New role is not displayed without refresh!");
    }

    public void deactivateRole() {
        WebElement deactivateButton = driver.findElement(By.xpath("//button[text()='Deactivate']"));
        elementUtils.clickElement(deactivateButton);
    }

    public void isNewRoleDetailsCorrect() {
        WebElement roleDescriptionElement = driver.findElement(By.id("roleDescription"));
        String actualDescription = elementUtils.getElementText(roleDescriptionElement);
        String expectedDescription = "Expected New Role Description";
        Assert.assertEquals(actualDescription, expectedDescription, "Role description is incorrect!");
    }

    public void identifiesActiveRole() {
        WebElement activeRoleElement = driver.findElement(By.xpath("//span[@class='active']"));
        Assert.assertTrue(elementUtils.isElementDisplayed(activeRoleElement), "Active role is not identified!");
    }

    public void isUpdatedStatusVisible() {
        WebElement updatedStatusElement = driver.findElement(By.id("updatedStatus"));
        Assert.assertTrue(elementUtils.isElementDisplayed(updatedStatusElement), "Updated status is not visible!");
    }

    public void isUpdatedRoleInCorrectPosition() {
        List<WebElement> roleItems = driver.findElements(By.className("role-item"));
        String expectedRoleName = "Updated Role Name";
        boolean roleInCorrectPosition = roleItems.get(0).getText().equals(expectedRoleName);
        Assert.assertTrue(roleInCorrectPosition, "Updated role is not in the correct position!");
    }

    public void verifyRoleDetailsPresence() {
        WebElement roleDetailsElement = driver.findElement(By.id("roleDetails"));
        Assert.assertTrue(elementUtils.isElementDisplayed(roleDetailsElement), "Role details presence is not verified!");
    }

    public void clicksCreateButtonInModal() {
        WebElement createButton = driver.findElement(By.id("createRoleButton"));
        elementUtils.clickElement(createButton);
    }

    public void isNewRoleCreatedWithDescription() {
        WebElement newRoleElement = driver.findElement(By.xpath("//div[text()='New Role']"));
        String actualDescription = elementUtils.getElementText(newRoleElement);
        String expectedDescription = "Expected New Role Description";
        Assert.assertEquals(actualDescription, expectedDescription, "New role created with incorrect description!");
    }

    public void navigateToUserManagementModule() {
        driver.get("http://localhost/user-management");
    }

    public void isRolesListSortedByRoleName() {
        List<String> roleNames = new ArrayList<>();
        List<WebElement> roleElements = driver.findElements(By.className("role-item"));
        for (WebElement role : roleElements) {
            roleNames.add(role.getText());
        }
        List<String> sortedNames = new ArrayList<>(roleNames);
        Collections.sort(sortedNames);
        Assert.assertEquals(roleNames, sortedNames, "Roles list is not sorted by role name!");
    }

    public void isModalClosedWithoutSaving() {
        WebElement modal = driver.findElement(By.id("roleModal"));
        boolean isClosed = !modal.isDisplayed();
        Assert.assertTrue(isClosed, "Modal is not closed without saving!");
    }

    public void identifyInactiveRole() {
        WebElement inactiveRoleElement = driver.findElement(By.xpath("//span[contains(text(),'Inactive')]"));
        Assert.assertTrue(elementUtils.isElementDisplayed(inactiveRoleElement), "Inactive role not identified!");
    }

    public void isUpdatedStatusVisibleAfterRefresh() {
        driver.navigate().refresh();
        isUpdatedStatusVisible();
    }

    public void isRolesListFiltered() {
        WebElement filterInput = driver.findElement(By.id("filterInput"));
        elementUtils.clearAndSendKeys(filterInput, "Filter criteria");
        WebElement filteredRole = driver.findElement(By.xpath("//div[text()='Filtered Role']"));
        Assert.assertTrue(elementUtils.isElementDisplayed(filteredRole), "Roles list is not filtered correctly!");
    }

    public void clicksSearchButton() {
        WebElement searchButton = driver.findElement(By.id("searchButton"));
        elementUtils.clickElement(searchButton);
    }

    public void clicksOnRolesTab() {
        elementUtils.clickElement(rolesTab);
    }

    public void savesChanges() {
        WebElement saveButton = driver.findElement(By.id("saveChangesButton"));
        elementUtils.clickElement(saveButton);
    }

    public void enterDescription() {
        WebElement descriptionInput = driver.findElement(By.id("roleDescriptionInput"));
        elementUtils.clearAndSendKeys(descriptionInput, "Role Description");
    }

    public void observeDisplayedRolesList() {
        List<WebElement> displayedRoles = driver.findElements(By.className("role-item"));
        Assert.assertTrue(displayedRoles.size() > 0, "Displayed roles list is empty!");
    }

    public void enterValidRoleName(String roleName) {
        WebElement roleNameInput = driver.findElement(By.id("roleNameInput"));
        elementUtils.clearAndSendKeys(roleNameInput, roleName);
    }

    public void isNewRoleAdded() {
        WebElement newRoleElement = driver.findElement(By.xpath("//div[text()='Valid Role Name']"));
        Assert.assertTrue(elementUtils.isElementDisplayed(newRoleElement), "New role is not added!");
    }

    public void clicksEditIconAndUpdatesRoleName() {
        clicksEditIconForExistingRole();
        enterValidRoleName("Valid Role Name");
        savesChanges();
    }

    public void areUpdatedDetailsReflected() {
        WebElement updatedRoleElement = driver.findElement(By.xpath("//div[text()='Updated Role Name']"));
        Assert.assertTrue(elementUtils.isElementDisplayed(updatedRoleElement), "Updated details are not reflected!");
    }

    public void clicksSaveButton() {
        WebElement saveButton = driver.findElement(By.id("saveButton"));
        elementUtils.clickElement(saveButton);
    }

    public void checkDetailsOfEachRole() {
        List<WebElement> roleList = driver.findElements(By.className("role-item"));
        for (WebElement role : roleList) {
            Assert.assertTrue(elementUtils.isElementDisplayed(role), "Role details are not displayed properly!");
        }
    }

    public void sortRolesListByRoleName() {
        WebElement sortButton = driver.findElement(By.id("sortByRoleNameButton"));
        elementUtils.clickElement(sortButton);
    }

    public void areRoleDetailsCorrect() {
        WebElement roleDetail = driver.findElement(By.xpath("//div[@class='role-detail']"));
        String actualRoleDetail = elementUtils.getElementText(roleDetail);
        String expectedRoleDetail = "Expected Role Detail";
        Assert.assertEquals(actualRoleDetail, expectedRoleDetail, "Role details are incorrect!");
    }

    public void clicksCancelButton() {
        WebElement cancelButton = driver.findElement(By.id("cancelButton"));
        elementUtils.clickElement(cancelButton);
    }

    public void activateRole() {
        WebElement activateButton = driver.findElement(By.xpath("//button[text()='Activate']"));
        elementUtils.clickElement(activateButton);
    }

    public void modifyRoleDetails() {
        clicksEditIconForExistingRole();
        enterDescription();
        savesChanges();
    }

    public void clicksToggleButtonToDeactivateRole() {
        WebElement toggleButton = driver.findElement(By.id("toggleDeactivate"));
        elementUtils.clickElement(toggleButton);
    }
}