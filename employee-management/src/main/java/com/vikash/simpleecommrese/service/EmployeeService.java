package com.vikash.simpleecommrese.service;

import com.vikash.simpleecommrese.dao.EmployeesRepo;
import com.vikash.simpleecommrese.model.Gender;
import com.vikash.simpleecommrese.model.IncreasOrDeacrease;
import com.vikash.simpleecommrese.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class EmployeeService {
    @Autowired
    EmployeesRepo employeeRepo

;
    // get

    public Map<Integer, Employee> getAllEmployees() {
        return employeeRepo.getEmployees();
    }

    public Employee getEmployeeById(Integer id) {
        for (Integer key : getAllEmployees().keySet()){
            if(key.equals(id)){
                return getAllEmployees().get(key);
            }
        }
        return null;
    }

    public List<Employee> getEmployeesInSalaryRange(double low, double high) {
        if(low > high){
            throw new IllegalStateException("price range invalid");
        }
        Map<Integer, Employee> allEmployees = getAllEmployees();
        List<Employee> EmployeesInSalaryRAnge = new ArrayList<>();

        for (Integer key: allEmployees.keySet()){

            double employeeSalary =allEmployees.get(key).getSalary();
            if (employeeSalary >= low && employeeSalary <= high){

                EmployeesInSalaryRAnge.add(allEmployees.get(key));
            }
        }
        return EmployeesInSalaryRAnge;
    }

    public Integer getProductsCount() {
        return getAllEmployees().size();
    }

    public List<Employee> getEmployeesByGender(Gender gender) {
        List<Employee> employeesByGender = new ArrayList<>();
        for (Integer key: getAllEmployees().keySet()){
            if (getAllEmployees().get(key).getGender().equals(gender)){
                employeesByGender.add(getAllEmployees().get(key));
            }
        }
        return employeesByGender;
    }

    public Integer getEmployeesCountByGender(Gender gender) {

        return getEmployeesByGender(gender).size();

    }

    public List<Employee> getProductsInPriceRangeAndCategory(double low, double high, Gender gender) {
        List<Employee> employeesInRangeAndGender = new ArrayList<>();
        List<Employee> employeesInRange = getEmployeesInSalaryRange(low,high);

        for(Employee employee : employeesInRange){
            if (employee.getGender().equals(gender)){
                employeesInRangeAndGender.add(employee);
            }
        }
        return employeesInRangeAndGender;

    }




    public List<Employee> getEmployeesFullByName(String firstName,String lastName) {

        List<Employee> employeeByNaming = new ArrayList<>();

        for (Employee employee : getAllEmployees().values()){

            if (employee.getFirstname().equals(firstName) && employee.getLastname().equals(lastName)){
                employeeByNaming.add(employee);
            }
        }
        return employeeByNaming;
    }

    //post
    public void addEmployee(Employee employee) {
       getAllEmployees().put(employee.getId(), employee);
    }




    public void addEmployees(List<Employee> employees) {
   Map<Integer, Employee> existingEmployees = getAllEmployees();
   for(Employee employee : employees){
       existingEmployees.put(employee.getId(), employee);
   }
    }

    //delete
    public String deleteEmployee(Integer id) {
        for (Integer key : getAllEmployees().keySet()) {

            if (getAllEmployees().get(key).getId().equals(id)){
                getAllEmployees().remove(key);
                return "employee removed";
}

        }
                return "employee not found ";




}

    public String deleteEmployees(List<Integer> idList) {
        int counter = 0;
        for(Integer id: idList){
            for (Integer key : getAllEmployees().keySet()){
                if(id.equals(key)){
                    getAllEmployees().remove(key);
                    counter++;
                    break;
                }
            }
        }
        return counter + "employees where removed";

    }

    public String deleteAllEmployeesByGender(Gender gender) {
        List<Integer> keysToRemove = new ArrayList<>();  // keys of the products that needs to be removed

        for (Integer key : getAllEmployees().keySet()){   // iterating to the hashmap to
            if(getAllEmployees().get(key).getGender().equals(gender)){ // checking if condition is true
                keysToRemove.add(key);  // adding those to keys to remove
            }
        }
        for (Integer key:keysToRemove){  // iterating trough keys to remove
            getAllEmployees().remove(key); // removing products from hahsmap where catogry matches which the category we pass
        }
        return gender + "employees  where removed";
    }


    // put
    public String updateSalaryByGender(IncreasOrDeacrease increasOrDeacrease, Gender gender, float discount) {
        int polarity =( increasOrDeacrease == IncreasOrDeacrease.INCREASE) ? 1:-1;

        for (Employee employee : getEmployeesByGender(gender)){

            double originalSalary = employee.getSalary();
            double salaryAltering = originalSalary * (discount / 100) * polarity;;
            double salaryAfterAltering  = originalSalary + salaryAltering;
            employee.setSalary(salaryAfterAltering);

        }
         return "salary updated";
    }

    public String updatePriceById(Integer id, IncreasOrDeacrease increasOrDeacrease,float discount ) {
        int polarity =( increasOrDeacrease == IncreasOrDeacrease.INCREASE) ? 1:-1;
        Employee employee = getEmployeeById(id) ;
        double originalSalary = employee.getSalary();
        double salaryAltering = originalSalary * (discount / 100) * polarity;;
        double salaryAfterAltering  = originalSalary + salaryAltering;
        employee.setSalary(salaryAfterAltering);
        return"Employee with id: " + id + " salary was updated";
    }

    public List<Employee> getEmployeesBelowAge(Integer age) {
        
       List<Employee>  employeesBelowAge = new ArrayList<>();

        for(Employee employee : getAllEmployees().values()){

            if((LocalDate.now().getYear())- employee.getUserDateOfBirth().getYear() < age ){
                employeesBelowAge.add(employee);

            }
        }
        return employeesBelowAge;
    }

    public Employee getEmployeeByEmail(String emailId) {
        for (Integer key : getAllEmployees().keySet()){
            if(getAllEmployees().get(key).getEmail().equals(emailId)){
                return getAllEmployees().get(key);
            }
        }
        return null;
    }
}

