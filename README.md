Library Management System

Team Member: Jori Alshoshan

Contribution Overview:

I was responsible for the design and implementation of the following screens and components of the Library Management System.

1. Registration Screen

Layout: Main panel with BorderLayout. Divided into three sections: top (image), center (form fields), and bottom (register button).

Database Interaction: Used DataOperation class to add new users to the database.

Validation: Implemented error handling for username, email, and password using custom exception class RegistrationException.

Testing: Validated different scenarios like empty fields, invalid email format, and incorrect password.

2. Login Screen

Layout: Main panel with BorderLayout. Includes top (image), center (login form), and bottom (login/register buttons).

Database Interaction: Used DataOperation class to verify user credentials and determine user type (user or manager).

Testing: Tested various login scenarios such as missing fields, invalid users, and proper redirection based on user type.

3. Manager Home Screen

Layout: Used GridBagLayout to arrange user image, navigation buttons, and a welcome message.

Testing: Validated button actions and layout consistency.

4. User Home Screen

Layout: Similar to Manager Home Screen but with user-specific options such as "Search Books" and "Borrowing History."

Testing: Verified button actions and layout adjustments.

5. Edit User Profile Screen (Manager)

Layout: GridBagLayout with sections for search functionality, editable user list, and control buttons.

Database Interaction: Retrieved and displayed user information using DataOperation class.

Testing: Validated search functionality and edit actions.

6. View and Edit User Info Screen (Manager)

Layout: Main panel with BorderLayout, sections for logo, editable user info, and buttons for updating.

Database Interaction: Used updateUserInfo method to update user information in the database.

Testing: Verified that user info updates correctly in the database.

DataOperation Class

The DataOperation class centralizes all database connectivity and operations:

Responsibilities:

Establishing JDBC connections to the SQL database.

Executing queries and updates for user registration, login validation, information retrieval, and updates.

Key Methods:

AddNewUsers(User user): Inserts a new user record.

ValiditUser(String username, String password): Checks credentials and returns user role.

getUserInfo(): Retrieves all user records as User objects.

updateUserInfo(User user): Updates existing user details in the database.