package com.example.springbackendpos.controller;
import com.example.springbackendpos.dto.CustomerStatus;
import com.example.springbackendpos.dto.impl.CustomerDto;
import com.example.springbackendpos.exception.CustomerNotFoundException;
import com.example.springbackendpos.exception.DataPersistException;
import com.example.springbackendpos.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

//     Logger logger = (Logger) LoggerFactory.getLogger(CustomerController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDto customerDTO) {
        String regexForUserID = "^(CUST-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}|C\\d{1,6})$";
        Pattern regexPattern = Pattern.compile(regexForUserID);

        Matcher matcher = regexPattern.matcher(customerDTO.getId());

        if (!matcher.matches()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {

            customerService.saveCustomer(customerDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<CustomerDto> getAllCustomer(){
        return customerService.getAll();
    }



    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerStatus getSelectedUser(@PathVariable("customerId") String cusId) {
        return customerService.getUser(cusId);
    }

    @PutMapping(value = "/{customerId}")
    public ResponseEntity<Void> updateCustomer(@PathVariable("customerId") String customerId, @RequestBody CustomerDto customerDto) {

        String regexForUserID = "^(CUST-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}|C\\d{1,6})$";
        Pattern regexPattern = Pattern.compile(regexForUserID);

        Matcher matcher = regexPattern.matcher(customerId);

        if (!matcher.matches()) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {

            customerService.updateCustomer(customerId, customerDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") String customerId) {
//        logger.info("Inside the customer delete method");
        try {
            customerService.deleteCustomer(customerId);
//            logger.info("customer delete successful");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            e.printStackTrace();
//            logger.warning("Error delete customer");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}



//    @GetMapping(value = "/{customerId}" , produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("customerId") String customerId){
//        try {
//            CustomerDto customerDto= (CustomerDto) customerService.getUser(customerId);
//            return new ResponseEntity<>(customerDto,HttpStatus.OK);
//        } catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


