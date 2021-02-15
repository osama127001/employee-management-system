<h1 align="center"> ⚙ Employee Management System </h1>

<p>
Employee Management system manages the employees of a specific organization.
</p>


## ERD - Entity Relationship Diagram
Following [Resource](https://www.softwareideas.net/a/1565/Employee-Management-System--ER-diagram-) was used to implement the ERD.
![Employee Management System - Entity Relationship Diagram](image/erd_ems.png)


## Application Class Diagram

### Note
* `Press E to expand package in Class diagmram`
* `Use the following tool-bar to show dependencies and other settings.`
![UML toolbar](image/uml_tools.png)
  
#### Class Diagram
![Class diagram without dependencies](image/cd_wod.png)

#### Class Diagram with Dependencies
![Class diagram with dependencies](image/cd_wd.png)

## Mappings

### Employee Mappings
Following are the Endpoints for `Employee`:

URL | Request | Description
--- | --- | --- |
api/v1/employee | GET | Get All Employees
api/v1/employee/{employee_id} | GET | Get Employee with given Id
api/v1/employee | POST |  Insert a new Employee
api/v1/employee/{employee_id} | PUT | Update an employee having Id 
api/v1/employee/{employee_id}/{project_id} | PUT | Associate project with an employee
api/v1/employee/set-office/{office_id}/{employee_id} | PUT | Assign office to an employee
api/v1/employee/set-manager/{employee_id}/{manager_id} | PUT | Assign manager to an employee
api/v1/employee/set-department/{department_id}/{employee_id} | PUT | Assign department to an employee
api/v1/employee/set-employee-type/{employee_type_id}/{employee_id} | PUT | Assign employee-type to an employee
api/v1/employee/{employee_id} | DELETE | Delete an employee having Id


### Project Mappings
Following are the Endpoints for `Project`:

URL | Request | Description
--- | --- | --- |
api/v1/project | GET | Get All Projects
api/v1/project/{project_id} | GET | Get Project with given Id
api/v1/project | POST |  Insert a new Project
api/v1/project/{project_id} | PUT | Update a Project having Id
api/v1/project/{employee_id}/{project_id} | PUT | Add employee to a project
api/v1/project/{project_id} | DELETE | Delete a Project having Id


### Office Mappings
Following are the Endpoints for `Office`:

URL | Request | Description
--- | --- | --- |
api/v1/office | GET | Get All Offices
api/v1/office/{office_id} | GET | Get Office with given Id
api/v1/office | POST |  Insert a new Office
api/v1/office/{office_id} | PUT | Update an Office having Id
api/v1/office/{office_id} | DELETE | Delete an Office having Id


### Department Mappings
Following are the Endpoints for `Department`:

URL | Request | Description
--- | --- | --- |
api/v1/department | GET | Get All Departments
api/v1/department/{department_id} | GET | Get Department with given Id
api/v1/department | POST |  Insert a new Department
api/v1/department/{department_id} | PUT | Update a Department having Id
api/v1/department/{department_id} | DELETE | Delete a Department having Id


### Employee-Type Mappings
Following are the Endpoints for `Employee-type`:

URL | Request | Description
--- | --- | --- |
api/v1/employee-type | GET | Get All Departments
api/v1/employee-type/{employee_type_id} | GET | Get Employee-type with given Id
api/v1/employee-type | POST |  Insert a new Employee-type
api/v1/employee-type/{employee_type_id} | PUT | Update a Employee-type having Id
api/v1/employee-type/{employee_type_id} | DELETE | Delete a Employee-type having Id


* Perform following tasks on completion:
    * Remove unwanted imports.