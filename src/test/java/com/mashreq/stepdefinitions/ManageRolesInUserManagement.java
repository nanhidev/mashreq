package com.mashreq.stepdefinitions;

import com..utils.ElementUtils;
import org.junit.Assert;
import com..driverfactory.DriverFactory;
import com.mashreq.pages.ManageRolesInUserManagementPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class ManageRolesInUserManagement extends DriverFactory {
ManageRolesInUserManagementPage manageRolesPage = new ManageRolesInUserManagementPage(driver);
@Given("the user is on the User Management module")
public void the_user_is_on_the_user_management_module() {
manageRolesPage.navigateToUserManagementModule();
}
@When("the user clicks on the Roles tab")
public void the_user_clicks_on_the_roles_tab() {
manageRolesPage.clickOnRolesTab();
}
@When("the user observes the displayed roles list")
public void the_user_observes_the_displayed_roles_list() {
manageRolesPage.observeDisplayedRolesList();
}
@When("the user checks the details of each role")
public void the_user_checks_the_details_of_each_role() {
manageRolesPage.checkDetailsOfEachRole();
}
@When("the user verifies the presence of Role Name, Type, and Status for each role")
public void the_user_verifies_the_presence_of_role_name_type_and_status_for_each_role() {
manageRolesPage.verifyRoleDetailsPresence();
}
@Then("the roles list should be displayed without errors")
public void the_roles_list_should_be_displayed_without_errors() {
"Roles list is not displayed without errors", manageRolesPage.isRolesListDisplayed();
}
@Then("each role should show the correct Role Name, Type, and Status")
public void each_role_should_show_the_correct_role_name_type_and_status() {
"Role details are not correct", manageRolesPage.areRoleDetailsCorrect();
}
@Then("the list should be scrollable if necessary")
public void the_list_should_be_scrollable_if_necessary() {
"Roles list is not scrollable", manageRolesPage.isRolesListScrollable();
}
@When("the admin enters {string} in the search bar")
public void the_admin_enters_in_the_search_bar(String roleName) {
manageRolesPage.enterRoleInSearchBar(roleName);
}
@Then("the roles list should be filtered based on the search term")
public void the_roles_list_should_be_filtered_based_on_the_search_term() {
"Roles list is not filtered based on the search term", manageRolesPage.isRolesListFiltered();
}
@Then("only roles matching the search criteria should be displayed")
public void only_roles_matching_the_search_criteria_should_be_displayed() {
"Filtered roles are not displayed correctly", manageRolesPage.areFilteredRolesDisplayed();
}
@When("the admin clicks the Create button")
public void the_admin_clicks_the_create_button() {
manageRolesPage.clicksCreateButton();
}
@When("the admin enters {string} in the Role Name field")
public void the_admin_enters_in_the_role_name_field(String roleName) {
manageRolesPage.enterRoleName(roleName);
}
@When("the admin optionally enters {string} in the Description field")
public void the_admin_optionally_enters_in_the_description_field(String description) {
manageRolesPage.enterRoleDescription(description);
}
@Then("a new role is created successfully")
public void a_new_role_is_created_successfully() {
"New role was not created successfully", manageRolesPage.isRoleCreatedSuccessfully();
}
@Then("the new role appears in the roles list")
public void the_new_role_appears_in_the_roles_list() {
"New role does not appear in the roles list", manageRolesPage.isNewRoleDisplayedInList();
}
@Then("the modal closes after creation")
public void the_modal_closes_after_creation() {
"Modal did not close after creation", manageRolesPage.isModalClosed();
}
@When("the admin modifies the Role Name or Description")
public void the_admin_modifies_the_role_name_or_description() {
manageRolesPage.modifyRoleDetails();
}
@Then("the role is updated successfully")
public void the_role_is_updated_successfully() {
"Role was not updated successfully", manageRolesPage.isRoleUpdatedSuccessfully();
}
@Then("the updated details are reflected in the roles list")
public void the_updated_details_are_reflected_in_the_roles_list() {
"Updated details are not reflected in the roles list", manageRolesPage.areUpdatedDetailsReflected();
}
@Then("a success message is displayed after saving")
public void a_success_message_is_displayed_after_saving() {
"Success message is not displayed after saving", manageRolesPage.isSuccessMessageDisplayed();
}
@When("the admin identifies a role with an Inactive status")
public void the_admin_identifies_a_role_with_an_inactive_status() {
manageRolesPage.identifyInactiveRole();
}
@When("the admin clicks the toggle button to activate the role")
public void the_admin_clicks_the_toggle_button_to_activate_the_role() {
manageRolesPage.activateRole();
}
@Then("the role status changes to Active immediately")
public void the_role_status_changes_to_active_immediately() {
"Role status did not change to Active", manageRolesPage.isRoleStatusActive();
}
@Then("the updated status is visible in the roles list")
public void the_updated_status_is_visible_in_the_roles_list() {
"Updated status is not visible in the roles list", manageRolesPage.isUpdatedStatusVisible();
}
@When("the admin identifies a role with an Active status")
public void the_admin_identifies_a_role_with_an_active_status() {
manageRolesPage.identifyActiveRole();
}
@When("the admin clicks the toggle button to deactivate the role")
public void the_admin_clicks_the_toggle_button_to_deactivate_the_role() {
manageRolesPage.deactivateRole();
}
@Then("the role status changes to Inactive immediately")
public void the_role_status_changes_to_inactive_immediately() {
"Role status did not change to Inactive", manageRolesPage.isRoleStatusInactive();
}
@When("the user refreshes the roles list")
public void the_user_refreshes_the_roles_list() {
manageRolesPage.refreshRolesList();
}
@Then("the updated status is visible in the roles list after refresh")
public void the_updated_status_is_visible_in_the_roles_list_after_refresh() {
"Updated status is not visible in the roles list after refresh", manageRolesPage.isUpdatedStatusVisibleAfterRefresh();
}
@When("the user enters a valid Role Name {string}")
public void the_user_enters_a_valid_role_name(String roleName) {
manageRolesPage.enterValidRoleName(roleName);
}
@Then("the new role appears in the roles list without needing to refresh")
public void the_new_role_appears_in_the_roles_list_without_needing_to_refresh() {
"New role does not appear in the roles list without refresh", manageRolesPage.isNewRoleDisplayedWithoutRefresh();
}
@Then("the new role displays the correct details")
public void the_new_role_displays_the_correct_details() {
"New role does not display the correct details", manageRolesPage.isNewRoleDetailsCorrect();
}
@When("the admin clicks the Cancel button in the modal")
public void the_admin_clicks_the_cancel_button_in_the_modal() {
manageRolesPage.clicksCancelButton();
}
@Then("the modal should close without saving any data")
public void the_modal_should_close_without_saving_any_data() {
"Modal did not close without saving any data", manageRolesPage.isModalClosedWithoutSaving();
}
@Then("the roles list should remain unchanged")
public void the_roles_list_should_remain_unchanged() {
"Roles list has changed", manageRolesPage.isRolesListUnchanged();
}
@Then("no new role should be added to the list")
public void no_new_role_should_be_added_to_the_list() {
"New role was added to the list", manageRolesPage.isNewRoleAdded();
}
@When("the user sorts the roles list by Role Name")
public void the_user_sorts_the_roles_list_by_role_name() {
manageRolesPage.sortRolesListByRoleName();
}
@Then("the roles list should remain sorted by Role Name")
public void the_roles_list_should_remain_sorted_by_role_name() {
"Roles list is not sorted by Role Name", manageRolesPage.isRolesListSortedByRoleName();
}
@Then("the updated role should appear in the correct position in the sorted list")
public void the_updated_role_should_appear_in_the_correct_position_in_the_sorted_list() {
"Updated role does not appear in the correct position", manageRolesPage.isUpdatedRoleInCorrectPosition();
}

@Given("the admin enters {string} as the role name")
public void the_admin_enters_role_name(String roleName) {
manageRolesPage.enterRoleName(roleName);
}
@When("the user clicks the create button")
public void the_user_clicks_create_button() {
manageRolesPage.clicksCreateButton();
}
@Then("a success message should be displayed confirming the update")
public void a_success_message_should_be_displayed_confirming_update() {
manageRolesPage.isUpdateSuccessMessageDisplayed();
}
@Then("a success message is displayed confirming the activation")
public void a_success_message_is_displayed_confirming_activation() {
manageRolesPage.isActivationSuccessMessageDisplayed();
}
@When("the user saves the changes")
public void the_user_saves_changes() {
manageRolesPage.savesChanges();
}
@Then("the new role appears in the roles list with the correct details")
public void the_new_role_appears_in_roles_list() {
manageRolesPage.isNewRoleDisplayedInList();
}
@Given("the user identifies a role with an active status")
public void the_user_identifies_role_with_active_status() {
manageRolesPage.identifiesActiveRole();
}
@When("the admin clicks the search button")
public void the_admin_clicks_search_button() {
manageRolesPage.clicksSearchButton();
}
@Given("the admin navigates to the user management module")
public void the_admin_navigates_to_user_management_module() {
manageRolesPage.navigateToUserManagementModule();
}
@Given("the admin enters {string} in the description field")
public void the_admin_enters_description(String description) {
manageRolesPage.enterDescription(description);
}
@Then("a success message is displayed confirming the deactivation")
public void a_success_message_is_displayed_confirming_deactivation() {
manageRolesPage.isDeactivationSuccessMessageDisplayed();
}
@When("the user clicks the edit icon for a role updates its role name")
public void the_user_clicks_edit_icon_updates_role_name() {
manageRolesPage.clicksEditIconAndUpdatesRoleName();
}
@When("the user clicks the create button in the modal")
public void the_user_clicks_create_button_in_modal() {
manageRolesPage.clicksCreateButtonInModal();
}
@When("the admin clicks the save button")
public void the_admin_clicks_save_button() {
manageRolesPage.clicksSaveButton();
}
@When("the admin clicks the edit icon for an existing role")
public void the_admin_clicks_edit_icon_for_existing_role() {
manageRolesPage.clicksEditIconForExistingRole();
}
@Then("a success message is displayed confirming the creation")
public void a_success_message_is_displayed_confirming_creation() {
manageRolesPage.isCreationSuccessMessageDisplayed();
}
@Then("a new role is created successfully with the description")
public void a_new_role_is_created_successfully_with_description() {
manageRolesPage.isNewRoleCreatedWithDescription();
}
@When("the user clicks the toggle button to deactivate the role")
public void the_user_clicks_toggle_button_to_deactivate_role() {
manageRolesPage.clicksToggleButtonToDeactivateRole();
}
@Given("the admin clicks on the roles tab")
public void the_admin_clicks_on_roles_tab() {
manageRolesPage.clicksOnRolesTab();
}
}