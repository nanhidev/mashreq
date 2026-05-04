
@feature_user_login
Feature: LoginScreenForRecruiters

Background: 
  Given the user is on the Welcome Page

@valid-login
Scenario Outline: Login Screen for Recruiters
  When the application opens
  Then the application opens successfully
  And the 'Get Started' button is visible
  When the user clicks on the 'Get Started' button
  Then the user is navigated to the Organization signup screen
  When the user clicks on the 'Continue as Organization' button
  Then the user is navigated to the Login page

Examples:
  | username | password |
  | <username> | <password> |

@valid-login
Scenario Outline: Login Screen for Recruiters
  Given the user enters a valid email ID in the Email field
  And the user enters a valid password in the Password field
  Then the Login button is enabled
  When the user clicks on the Login button
  Then the user is redirected to the dashboard
  And the dashboard is displayed

Examples:
  | email                   | password           |
  | recruiter@example.com   | SecurePassword123   |

  @feature_user_login
  @show_hide_password
  Scenario Outline: Login Screen for Recruiters
    Given the user enters a password "<password>" in the Password field
    When the user clicks on the 'Show/Hide Password' option
    Then the password should be visible
    When the user clicks on the 'Show/Hide Password' option again
    Then the password should be hidden

    Examples:
      | password       |
      | P@ssw0rd123    |

  @valid_login
  Scenario Outline: Login Screen for Recruiters
    Given the user enters <email> in the Email field
    And the user enters <password> in the Password field
    Then the Login button should be enabled

    Examples:
      | email               | password            |
      | krishna@gmail.com   | SecurePassword123   |

@valid-email-format
Scenario Outline: Login Screen for Recruiters
  When I enter "<email>"
  And I enter "<password>"
  And I click on the Login button
  Then the user should be directed to the dashboard

  Examples:
    | email                         | password          |
    | user.name@subdomain.example.com | ValidPassword123 |
    | user+name@example.com        | ValidPassword123  |

@valid-email-format
Scenario Outline: Login Screen for Recruiters - Plus Sign Format
  When I enter "<email>"
  And I enter "<password>"
  And I click on the Login button
  Then the user should be directed to the dashboard

  Examples:
    | email                         | password          |
    | user+name@example.com        | ValidPassword123  |

@valid-login-chrome
Scenario Outline: Login functionality works correctly in Chrome
  When the user opens the application in Chrome
  And the user enters <username> and <password>
  And the user logs in
  Then the user should log in successfully in Chrome
  And the user logs out

Examples:
  | username | password |
  | user1    | pass1    |

@valid-login-firefox
Scenario Outline: Login functionality works correctly in Firefox
  When the user opens the application in Firefox
  And the user enters <username> and <password>
  And the user logs in
  Then the user should log in successfully in Firefox
  And the user logs out

Examples:
  | username | password |
  | user1    | pass1    |

@valid-login-safari
Scenario Outline: Login functionality works correctly in Safari
  When the user opens the application in Safari
  And the user enters <username> and <password>
  And the user logs in
  Then the user should log in successfully in Safari
  And the user logs out

Examples:
  | username | password |
  | user1    | pass1    |

@valid-login-desktop
Scenario Outline: Login Screen for Recruiters - Desktop
  When the user opens the application on a desktop
  And the user enters "<username>" and "<password>"
  And the user clicks the login button
  Then the user should log in successfully
  And the user logs out

  Examples:
    | username       | password           |
    | recruiter123    | SecurePassword!123 |

@valid-login-tablet
Scenario Outline: Login Screen for Recruiters - Tablet
  When the user opens the application on a tablet
  And the user enters "<username>" and "<password>"
  And the user clicks the login button
  Then the user should log in successfully
  And the user logs out

  Examples:
    | username       | password           |
    | recruiter123    | SecurePassword!123 |

@valid-login-mobile
Scenario Outline: Login Screen for Recruiters - Mobile
  When the user opens the application on a mobile device
  And the user enters "<username>" and "<password>"
  And the user clicks the login button
  Then the user should log in successfully
  And the user logs out

  Examples:
    | username       | password           |
    | recruiter123    | SecurePassword!123 |

  @feature_user_login
  @successful_password_reset
  Scenario Outline: User can successfully log in after resetting their password
    When the user enters <new_password> in the Password field
    And the user confirms <new_password> in the Confirm Password field
    And the user clicks on the 'Reset Password' button
    Then the user's password is reset successfully
    And the user is redirected to the Login page
    When the user enters <email> in the Email field
    And the user enters <new_password> in the Password field
    And the user clicks on the Login button
    Then the user logs in successfully with the new password

    Examples:
      | email                   | new_password       |
      | valid_email@example.com | new_password_value  |
