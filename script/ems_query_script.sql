use db_employee;

SELECT e.first_name, d.name, et.name, o.name, m.first_name, p.name
FROM employee e
LEFT JOIN department d ON e.department_id = d.id
LEFT JOIN employee_type et ON e.work_position_id = et.id
LEFT JOIN office o ON e.office_id = o.id
LEFT JOIN employee m ON e.supervisor_id = m.id
LEFT JOIN employee_project ep ON e.id = ep.employee_id
LEFT JOIN project p on ep.project_id = p.id;