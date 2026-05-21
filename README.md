# Library Management System 

A Java-based desktop application designed to manage library operations, including user registration, authentication, profile management, and role-based access for users and managers.

## Team Member
**Jori Alshoshan**

## Project Contribution

I was responsible for designing and implementing several core screens and backend functionalities of the Library Management System.

### Implemented Screens

#### Registration Screen
- Designed the user registration interface using `BorderLayout`
- Divided the layout into image, form, and action sections
- Connected the screen to the database through the `DataOperation` class
- Implemented input validation using a custom `RegistrationException`
- Tested scenarios including:
  - Empty input fields
  - Invalid email format
  - Incorrect password entries

#### Login Screen
- Built the login interface using `BorderLayout`
- Implemented authentication logic using `DataOperation`
- Added role-based access control for users and managers
- Tested:
  - Missing credentials
  - Invalid login attempts
  - Successful redirection based on user role

#### Manager Home Screen
- Designed the manager dashboard using `GridBagLayout`
- Added navigation controls and welcome interface
- Tested layout consistency and button functionality

#### User Home Screen
- Designed the user dashboard with role-specific options
- Added features such as:
  - Search Books
  - Borrowing History
- Tested interface behavior and navigation actions

#### Edit User Profile (Manager)
- Designed the interface for searching and editing user profiles
- Implemented user data retrieval through `DataOperation`
- Tested search functionality and edit actions

#### View & Update User Information
- Built the interface for updating user information
- Connected update actions to the database using `updateUserInfo()`
- Verified successful database updates

---

## Backend Contribution

### DataOperation Class
The `DataOperation` class was responsible for managing all database interactions.

#### Responsibilities
- Establishing JDBC connections with the SQL database
- Executing queries for:
  - User registration
  - Login authentication
  - User information retrieval
  - User data updates

#### Implemented Methods
```java
AddNewUsers(User user)
ValiditUser(String username, String password)
getUserInfo()
updateUserInfo(User user)
```

#### Testing

The system was tested across multiple scenarios to ensure reliability, including:

Empty input validation
Invalid email handling
Authentication failures
Role-based navigation
Profile update verification
UI interaction testing
