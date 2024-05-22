package com.example.emp.controller;

import com.example.emp.data.EmployeeDAO;
import com.example.emp.entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EmployeeServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeDAO employeeDAO;

    public void init() {
        employeeDAO = new EmployeeDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertEmployee(request, response);
                break;
            default:
                listEmployees(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void listEmployees(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Employee> employees = employeeDAO.getAllEmployees();
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("employee-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("employee-form.jsp").forward(request, response);
    }

    private void insertEmployee(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String fullName = req.getParameter("fullName");
        String birthday = req.getParameter("birthday");
        String address = req.getParameter("address");
        String Position = req.getParameter("Position");
        String Department = req.getParameter("Department");

        Employee newEmployee = new Employee();
        newEmployee.setFullName(fullName);
        newEmployee.setPosition(Position);
        newEmployee.setBirthday(birthday);
        newEmployee.setAddress(address);
        newEmployee.setDepartment(Department);

        employeeDAO.insertEmployee(newEmployee);
        resp.sendRedirect("list");
    }
}
