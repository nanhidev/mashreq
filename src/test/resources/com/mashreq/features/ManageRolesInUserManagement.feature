
@feature_manage_roles
Feature: ManageRolesInUserManagement

  Background: 
    Given the user is on the User Management module

  @verify_roles_list
  Scenario Outline: Manage Roles in User Management Module
    When the user clicks on the Roles tab
    And the user observes the displayed roles list
    And the user checks the details of each role
    And the user verifies the presence of Role Name, Type, and Status for each role
    Then the roles list should be displayed without errors
    And each role should show the correct Role Name, Type, and Status
    And the list should be scrollable if necessary

  Examples:
    | Role Name | Type   | Status  |
    | Admin     | Full   | Active  |
    | User      | Limited| Inactive|
    | Guest     | None   | Active  |

  @search_role
  Scenario Outline: Admin can search for a specific role using the search bar
    When the admin clicks on the Roles tab
    And the admin enters <role_name> in the search bar
    And the admin clicks the search button
    Then the roles list should be filtered based on the search term
    And only roles matching the search criteria should be displayed

    Examples:
      | role_name |
      | Admin     |

  @create_new_role
  Scenario Outline: Manage Roles in User Management Module
    When the admin clicks on the Roles tab
    And the admin clicks the Create button
    And the admin enters <role_name> in the Role Name field
    And the admin optionally enters <description> in the Description field
    And the admin clicks the Create button in the modal
    Then a new role is created successfully
    And the new role appears in the roles list
    And the modal closes after creation

    Examples:
      | role_name | description                                      |
      | Admin     | This role is for managing user permissions and access. |

@create_new_role
Scenario Outline: Manage Roles in User Management Module
  When the admin clicks on the Roles tab
  And the admin clicks the Create button
  And the admin enters <role_name> in the Role Name field
  And the admin enters <description> in the Description field
  And the admin clicks the Create button in the modal
  Then a new role is created successfully with the Description
  And the new role appears in the roles list with the correct details
  And the modal closes after creation

  Examples:
    | role_name | description                                         |
    | Admin     | This role is for managing user permissions and access. |

  @update_role_details
  Scenario Outline: Manage Roles in User Management Module
    When the admin clicks on the Roles tab
    And the admin clicks the edit icon for an existing role
    And the admin modifies the Role Name or Description
    And the admin clicks the Save button
    Then the role is updated successfully
    And the updated details are reflected in the roles list
    And a success message is displayed after saving

  Examples:
    | Role Name or Description |
    | New Role Name           |

  @activate_role
  Scenario Outline: Manage Roles in User Management Module
    When the admin navigates to the User Management module
    And the admin clicks on the Roles tab
    And the admin identifies a role with an Inactive status
    And the admin clicks the toggle button to activate the role
    Then the role status changes to Active immediately
    And the updated status is visible in the roles list
    And a success message is displayed confirming the activation

  Examples:
    | role_status |
    | Inactive    |

  @deactivate_role
  Scenario Outline: Manage Roles in User Management Module
    When the admin clicks on the Roles tab
    And the admin identifies a role with an Active status
    And the admin clicks the toggle button to deactivate the role
    Then the role status changes to Inactive immediately
    And the updated status is visible in the roles list
    And a success message is displayed confirming the deactivation

  Examples:
    |  |
    |  |

  @verify_role_creation
  Scenario Outline: Manage Roles in User Management Module
    When the user clicks on the Roles tab
    And the user clicks the Create button
    And the user enters a valid Role Name <role_name>
    And the user clicks the Create button in the modal
    Then the new role appears in the roles list without needing to refresh
    And the new role displays the correct details
    And a success message is displayed confirming the creation

    Examples:
      | role_name |
      | Admin     |

  @cancel_role_creation
  Scenario Outline: Manage Roles in User Management Module
    When the admin clicks the Create button
    And the admin enters <role_name> as the Role Name
    And the admin clicks the Cancel button in the modal
    Then the modal should close without saving any data
    And the roles list should remain unchanged
    And no new role should be added to the list

    Examples:
      | role_name     |
      | Admin Role    |

@verify_roles_sorting
Scenario Outline: Verify that the roles list maintains the correct sorting order after roles are updated
  Given the user clicks on the Roles tab
  And the user sorts the roles list by Role Name
  When the user clicks the edit icon for a role and updates its Role Name
  And the user saves the changes
  Then the roles list should remain sorted by Role Name
  And the updated role should appear in the correct position in the sorted list
  And a success message should be displayed confirming the update

Examples:
  | role_name       |
  | Updated Role 1  |

  @verify_role_deactivation
  Scenario Outline: Manage Roles in User Management Module
    When the user clicks on the Roles tab
    And the user identifies a role with an Active status
    And the user clicks the toggle button to deactivate the role
    Then the role status changes to Inactive immediately
    And the user refreshes the roles list
    Then the updated status is visible in the roles list after refresh
    And a success message is displayed confirming the deactivation

  Examples:
    | role_status |
    | Active      |
