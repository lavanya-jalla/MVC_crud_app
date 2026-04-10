package com.example.restdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.restdemo.entity.Employee;
import com.example.restdemo.service.EmployeeService;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

 
    @GetMapping("/employees")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "emp-list";   
    }

    
    @GetMapping("/showFormForAdd")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "emp-form";        
    }

    
    @GetMapping("/showFormForUpdate")
    public String showUpdateForm(@RequestParam("employeeId") int id, Model model) {
        Employee emp = employeeService.findById(id);
        model.addAttribute("employee", emp);
        return "emp-form";         
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id) {
        employeeService.deleteById(id);
        return "redirect:/employees";
    }
}