# SCM – Master Data Management Test Automation (Selenium + Java + TestNG)

## Project Description

Automated test suite for the **Master Data Management module** of a Supply Chain Management (SCM) application in the dairy industry, built using **Selenium WebDriver, Java, and TestNG** following the **Page Object Model (POM)** design pattern.

The suite covers two core modules:

### 1. Collection Center Master
Automated test cases covering full CRUD operations (Create, Edit, Delete) for Collection Centers, including:
- Single Collection Center creation with field validations
- Bulk Collection Center creation via file upload
- Editing existing Collection Center details
- Deleting Collection Center records
- Validation of mandatory fields, duplicate entries, and error handling

### 2. User Master
Automated test cases covering full CRUD operations (Create, Edit, Delete) for User records, including:
- Single User creation with role/permission assignment
- Bulk User creation via file upload
- Editing existing User details
- Deleting User records
- Validation of mandatory fields, duplicate entries, and error handling

## Tech Stack
- **Language**: Java
- **Automation Tool**: Selenium WebDriver
- **Test Framework**: TestNG
- **Build Tool**: Maven
- **Design Pattern**: Page Object Model (POM)
- **Reporting**: TestNG HTML Reports

## What This Demonstrates
- End-to-end automation of CRUD workflows (single + bulk operations)
- Data-driven testing for bulk upload scenarios
- Structured, maintainable automation framework using POM
- Validation of UI functionality, field-level validations, and error/negative scenarios
