# Student Management System – Implementation Plan (Login System Upgrade)

This document outlines the step-by-step implementation plan for integrating a login system into the Student Management System project.

---

##  PHASE 1: Foundation – OOP Structure & Role Design

### 1. `User.java` (Abstract Class)

* Base class for Admin and StudentUser
* Common fields: `username`, `password`

### 2. `Admin.java`

* Inherits from `User`
* Admin-specific functionality will be handled in `AdminService`

### 3. `StudentUser.java`

* Inherits from `User`
* Links to a `Student` object
* Handles student profile actions

---

##  PHASE 2: Login System & Auth Logic

### 4. `AuthManager.java`

* Reads login credentials from `credentials.txt`
* Authenticates users and returns corresponding object (Admin or StudentUser)

### 5. `credentials.txt`

* Stores credentials in format:

  ```
  username,password,role,studentRoll
  admin,admin123,admin,null
  john,pass123,student,101
  ```

---

##  PHASE 3: Services (Role-Specific Behaviors)

### 6. `AdminService.java`

* Contains business logic for:

  * Add student
  * Delete student
  * Sort/search/view students
* Extracted from existing `StudentManagementSystem.java`

### 7. `StudentService.java`

* Allows student to view their profile and GPA

---

##  PHASE 4: Reuse Existing Logic

### 8. Core Data Classes:

* `Student.java`
* `Department.java`
* `Grade.java`
* Minor improvements: Fix typos, enhance encapsulation

### 9. `FileManager.java`

* Refactor to be reusable in both Admin and Auth operations
* File-based storage for student and credential data

---

## PHASE 5: Main Application Entry

### 10. `Main.java`

* Launches login interface (via `AuthManager`)
* Based on role, switches to Admin or Student view
* Loops user options with `showMenu()`

---




