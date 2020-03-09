package com.howtodoinjava.rest.controller;

import java.net.URI;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.howtodoinjava.rest.dao.EmployeeDAO;
import com.howtodoinjava.rest.model.Employee;
import com.howtodoinjava.rest.model.Employees;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(path = "/")
public class Controller
{
    /*@Autowired
    private EmployeeDAO employeeDao;
    
    @GetMapping(path="/", produces = "application/json")
    public Employees getEmployees() 
    {
        return employeeDao.getAllEmployees();
    }
    
    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addEmployee(
        @RequestHeader(name = "X-COM-PERSIST", required = true) String headerPersist,
        @RequestHeader(name = "X-COM-LOCATION", defaultValue = "ASIA") String headerLocation,
        @RequestBody Employee employee) 
            throws Exception 
    {       
        //Generate resource id
        Integer id = employeeDao.getAllEmployees().getEmployeeList().size() + 1;
        employee.setId(id);

        //add resource
        employeeDao.addEmployee(employee);

        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(employee.getId())
                                    .toUri();

        //Send location in response
        return ResponseEntity.created(location).build();
    }*/

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request)  {

        // !!!!!!!!!!!!!!!! pour tester les ws :
        // curl http://localhost:8080 -u admin:pass

        HttpSession session=request.getSession();
        System.out.println("session id before invalidating session is:"+session.getId());
        session.invalidate();
       System.out.println("session id after invalidating session is:"+session.getId());


        return "logoutt done";
    }

    @RequestMapping("/")
    public String index()  {

        return "<a href='/logout'>logout</a><br />" + "<a href='/clear'>clear</a>";
    }

    @RequestMapping(value = {"/clear"})
    public String clear(HttpServletRequest request, HttpServletResponse response){

        // !!!!!!!!!!!!!!!! pour tester les ws :
        // curl http://localhost:8080 -u admin:pass

        HttpSession session= request.getSession(false);
        SecurityContextHolder.clearContext();
        session= request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        for(javax.servlet.http.Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }

        return "logout";
    }

    @RequestMapping(value = {"/flow"})
    public String flow(){

        // !!!!!!!!!!!!!!!! pour tester les ws :
        // curl http://localhost:8080 -u admin:pass

        return "flow without auth...";
    }

    @RequestMapping(value = {"/flow_secure"})
    public String flowSecure(){

        // !!!!!!!!!!!!!!!! pour tester les ws :
        // curl http://localhost:8080/flow_secure -u admin:pass

        return "flow with auth !!!!!!!!!!!!!!!!!!!!!!!!!!!";
    }

}
