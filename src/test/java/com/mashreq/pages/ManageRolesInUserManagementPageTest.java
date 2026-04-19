
package com.mashreq.pages;

import com..utils.ElementUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ManageRolesInUserManagementPageTest {

    @Mock
    private WebDriver driver;

    @Mock
    private ElementUtils elementUtils;

    @Mock
    private WebElement rolesTab;

    @Mock
    private WebElement rolesList;

    @Mock
    private WebElement searchBar;

    @Mock
    private WebElement createButton;

    @Mock
    private WebElement roleNameField;

    @Mock
    private WebElement descriptionField;

    @Mock
    private WebElement successMessage;

    @Mock
    private WebElement modalCloseButton;

    @Mock
    private WebElement toggleButton;

    @InjectMocks
    private ManageRolesInUserManagementPage manageRolesInUserManagementPage;

    @Test
    public void shouldClickOnRolesTab() {
        doNothing().when(elementUtils).clickElement(rolesTab);
        manageRolesInUserManagementPage.clickOnRolesTab();
        verify(elementUtils, times(1)).clickElement(rolesTab);
    }

    @Test
    public void shouldReturnTrueWhenRolesListIsDisplayed() {
        when(elementUtils.isElementDisplayed(rolesList)).thenReturn(true);
        boolean result = manageRolesInUserManagementPage.isRolesListDisplayed();
        verify(elementUtils, times(1)).isElementDisplayed(rolesList);
        assert(result);
    }

    @Test
    public void shouldEnterRoleInSearchBar() {
        String roleName = "Admin";
        doNothing().when(elementUtils).clearAndSendKeys(searchBar, roleName);
        manageRolesInUserManagementPage.enterRoleInSearchBar(roleName);
        verify(elementUtils, times(1)).clearAndSendKeys(searchBar, roleName);
    }

    @Test
    public void shouldClickCreateButton() {
        doNothing().when(elementUtils).clickElement(createButton);
        manageRolesInUserManagementPage.clicksCreateButton();
        verify(elementUtils, times(1)).clickElement(createButton);
    }

    @Test
    public void shouldEnterRoleName() {
        String roleName = "New Role";
        doNothing().when(elementUtils).clearAndSendKeys(roleNameField, roleName);
        manageRolesInUserManagementPage.enterRoleName(roleName);
        verify(elementUtils, times(1)).clearAndSendKeys(roleNameField, roleName);
    }

    @Test
    public void shouldEnterRoleDescription() {
        String description = "Role Description";
        doNothing().when(elementUtils).clearAndSendKeys(descriptionField, description);
        manageRolesInUserManagementPage.enterRoleDescription(description);
        verify(elementUtils, times(1)).clearAndSendKeys(descriptionField, description);
    }

    @Test
    public void shouldReturnTrueWhenRoleCreatedSuccessfully() {
        when(elementUtils.isElementDisplayed(successMessage)).thenReturn(true);
        boolean result = manageRolesInUserManagementPage.isRoleCreatedSuccessfully();
        verify(elementUtils, times(1)).isElementDisplayed(successMessage);
        assert(result);
    }

    @Test
    public void shouldClickToggleButton() {
        doNothing().when(elementUtils).clickElement(toggleButton);
        manageRolesInUserManagementPage.clickToggleButton();
        verify(elementUtils, times(1)).clickElement(toggleButton);
    }

    @Test
    public void shouldReturnTrueWhenModalIsClosed() {
        when(elementUtils.isElementDisplayed(modalCloseButton)).thenReturn(false);
        boolean result = manageRolesInUserManagementPage.isModalClosed();
        verify(elementUtils, times(1)).isElementDisplayed(modalCloseButton);
        assert(result);
    }

    @Test
    public void shouldRefreshRolesList() {
        doNothing().when(driver).navigate().refresh();
        manageRolesInUserManagementPage.refreshRolesList();
        verify(driver, times(1)).navigate().refresh();
    }

    @Test
    public void shouldReturnTrueWhenFilteredRolesAreDisplayed() {
        when(elementUtils.isElementDisplayed(rolesList)).thenReturn(true);
        when(rolesList.findElements(any(By.class))).thenReturn(mock(List.class));
        boolean result = manageRolesInUserManagementPage.areFilteredRolesDisplayed();
        verify(elementUtils, times(1)).isElementDisplayed(rolesList);
        assert(result);
    }

    @Test
    public void shouldReturnTrueWhenNewRoleDisplayedInList() {
        when(rolesList.findElements(any(By.class))).thenReturn(mock(List.class));
        when(elementUtils.getElementText(roleNameField)).thenReturn("New Role");
        boolean result = manageRolesInUserManagementPage.isNewRoleDisplayedInList();
        verify(rolesList, times(1)).findElements(any(By.class));
        assert(result);
    }

    @Test
    public void shouldReturnTrueWhenRoleStatusIsActive() {
        when(toggleButton.getAttribute("class")).thenReturn("active");
        boolean result = manageRolesInUserManagementPage.isRoleStatusActive();
        verify(toggleButton, times(1)).getAttribute("class");
        assert(result);
    }

    @Test
    public void shouldReturnTrueWhenRoleStatusIsInactive() {
        when(toggleButton.getAttribute("class")).thenReturn("inactive");
        boolean result = manageRolesInUserManagementPage.isRoleStatusInactive();
        verify(toggleButton, times(1)).getAttribute("class");
        assert(result);
    }
}
