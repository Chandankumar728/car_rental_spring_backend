package com.carrentalprojects.Car_Rental_Spring.services.employee;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.carrentalprojects.Car_Rental_Spring.dto.EmployeeDto;
import com.carrentalprojects.Car_Rental_Spring.entity.Employee;
import com.carrentalprojects.Car_Rental_Spring.repository.EmployeeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    @Override
    public boolean addEmployee(EmployeeDto employeeDto) {
        try {
            Employee employee = new Employee();
            employee.setName(employeeDto.getName());
            employee.setEmail(employeeDto.getEmail());
            employee.setPosition(employeeDto.getPosition());
            employee.setPhoneNumber(employeeDto.getPhoneNumber());
            employee.setAddress(employeeDto.getAddress());
            employee.setHireDate(employeeDto.getHireDate() != null ? Date.valueOf(employeeDto.getHireDate()) : null);

            Employee savedEmployee = employeeRepository.save(employee);
            return savedEmployee != null && savedEmployee.getId() != null;

        } catch (Exception e) {
            System.err.println("========== ERROR in postEmployee ==========");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateEmployee(Long id, EmployeeDto employeeDto) {
        try {
            Employee employee = employeeRepository.findById(id).orElse(null);
            if (employee == null) return false;

            employee.setName(employeeDto.getName());
            employee.setEmail(employeeDto.getEmail());
            employee.setPosition(employeeDto.getPosition());
            employee.setPhoneNumber(employeeDto.getPhoneNumber());
            employee.setAddress(employeeDto.getAddress());
            employee.setHireDate(employeeDto.getHireDate() != null ? Date.valueOf(employeeDto.getHireDate()) : null);

            employeeRepository.save(employee);
            return true;
        } catch (Exception e) {
            System.err.println("========== ERROR in updateEmployee ==========");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteEmployee(Long id) {
        try {
            employeeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.err.println("========== ERROR in deleteEmployee ==========");
            e.printStackTrace();
            return false;
        }
    }

    

    private EmployeeDto convertToDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setPosition(employee.getPosition());
        dto.setPhoneNumber(employee.getPhoneNumber());
        dto.setAddress(employee.getAddress());
        dto.setHireDate(employee.getHireDate() != null ? employee.getHireDate().toLocalDate() : null);
        return dto;
    }
}
