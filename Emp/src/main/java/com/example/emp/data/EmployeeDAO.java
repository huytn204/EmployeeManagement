package com.example.emp.data;

import com.example.emp.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/t2210m";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employees (fullName, birthday, address, Position, Department) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM employees";

    public EmployeeDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertEmployee(Employee employee) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_EMPLOYEE_SQL)) {
                ps.setString(1, employee.getFullName());
                ps.setString(2, employee.getBirthday());
                ps.setString(3, employee.getAddress());
                ps.setString(4, employee.getPosition());
                ps.setString(5, employee.getDepartment());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("fullName");
                String birthday = rs.getString("birthday");
                String address = rs.getString("address");
                String Position = rs.getString("Position");
                String Department = rs.getString("Department");
                employees.add(new Employee(id, fullName, birthday, address, Position, Department));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
