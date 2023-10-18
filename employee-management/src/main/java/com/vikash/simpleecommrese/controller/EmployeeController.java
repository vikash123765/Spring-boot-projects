package com.vikash.simpleecommrese.controller;

import com.vikash.simpleecommrese.model.Gender;
import com.vikash.simpleecommrese.model.Employee;
import com.vikash.simpleecommrese.model.IncreasOrDeacrease;
import com.vikash.simpleecommrese.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Validated
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    //GET

    // get all employees
    @GetMapping("employees")
    public Map<Integer, Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    // get employee by id
    @GetMapping("employee/id/{id}")
    public Employee getEmployeeById(@PathVariable Integer id){
        return employeeService.getEmployeeById(id);
    }

    // get employee by name
    @GetMapping("employee/emailId/{emailId}")
    public Employee getEmployeeByEmail(@PathVariable String emailId){
        return employeeService.getEmployeeByEmail(emailId);
    }


    // get employees  in a particular salery range
    @GetMapping("salary/low/{low}/high/{high}")
    public List<Employee> getEmployeesInSalaryRange(@PathVariable double low, @PathVariable double high ){
        return employeeService.getEmployeesInSalaryRange(low,high);
    }

    // get count oof all the employees
    @GetMapping("employees/count")
    public Integer getEmployeesCount(){
        return employeeService.getProductsCount();
    }

    // get employees based on gender
    @GetMapping("employees/gender/{gender}")
    public List<Employee> getEmployeesByGender(@PathVariable Gender gender){
        return employeeService.getEmployeesByGender(gender);
    }

    // get count by Gender
    @GetMapping("employees/count/{gender}")
    public Integer getProductsCountByCategory(@PathVariable Gender gender) {
        return employeeService.getEmployeesCountByGender(gender);

    }
    // get employee by name
    @GetMapping("employee/fullName/{firstName}/{lastName}")
    public  List<Employee> getEmployeeByName(@PathVariable String firstName, @PathVariable  String lastName ){
        return employeeService.getEmployeesFullByName(firstName,lastName);
    }

    // get employees  in a particular salery range and gender
    @GetMapping("employee/low/{low}/high/{high}/{gender}")
    public List<Employee> getEmployeesInSalaryRangeAndGender(@PathVariable double low,@PathVariable double high,@PathVariable Gender gender ){
        return employeeService.getProductsInPriceRangeAndCategory(low,high,gender);
    }

    // get all employees below a certain age
    @GetMapping("empolyees/below/age/{age}")
    public List<Employee> getEmployeesBelowAge(@PathVariable @Min(18)  @Max(60) Integer age){
        return employeeService.getEmployeesBelowAge(age);
    }


    //POST

    // add single employee
    @PostMapping("employee")
    public String addEmployee(@RequestBody @Valid Employee employee){
        employeeService.addEmployee(employee);
        return "added";
    }


    // add multiple employees

    @PostMapping("products")
    public String addProducts(@RequestBody @Valid List<Employee> employees){
        employeeService.addEmployees(employees);
        return "added" + employees.size() + "products";
    }

    //PUT

    // increase/decrease salary by Gender
    @PutMapping("products/gender/{gender}/increaseOrDeacrease/{increasOrDeacrease}/percentage{salary}")
    public String updateSalaryByGender(@PathVariable IncreasOrDeacrease increasOrDeacrease,@PathVariable Gender gender, @PathVariable float salary){
        return employeeService.updateSalaryByGender(increasOrDeacrease, gender,salary);
    }

    // incraese/deacrese salaray by id
    @PutMapping("employee/id/{id}/increaseOrDeacrease/{increasOrDeacrease}/percentage/{salaray}")
    public String updatePriceById(@PathVariable IncreasOrDeacrease increasOrDeacrease,@PathVariable Integer id,@PathVariable float salaray){
        return employeeService.updatePriceById(id,increasOrDeacrease,salaray);
    }



    //DElETE

    // remove single employee
    @DeleteMapping("employee/id/{id}")
    public String deleteEmployee(@PathVariable Integer id){
        return employeeService.deleteEmployee(id);
    }

    // remove multiple employees
    @DeleteMapping("employees/ids")
    public String deleteProduct(@RequestBody List<Integer>  idList){
        return employeeService.deleteEmployees(idList);
    }

    // remove all employees based on gender

    @DeleteMapping("products/gender/{gender}")
    public String deleteAllEmployeesByGender(@PathVariable Gender gender){
        return employeeService.deleteAllEmployeesByGender(gender);
    }

}
