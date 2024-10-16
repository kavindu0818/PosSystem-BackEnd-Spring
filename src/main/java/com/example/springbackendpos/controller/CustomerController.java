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


@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

//    @GetMapping
//    public String healthTest(){
//        return "customer working";
//    }

    @Autowired
    private CustomerService customerService;

//     Logger logger = (Logger) LoggerFactory.getLogger(CustomerController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDto customerDTO) {
        try {
            customerService.saveCustomer(customerDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerStatus getSelectedUser(@PathVariable("customerId") String cusId) {
        return customerService.getUser(cusId);
    }

    @PutMapping(value = "/{customerId}")
    public ResponseEntity<Void> updateCustomer(@PathVariable("customerId") String customerId, @RequestBody CustomerDto customerDto) {
//        logger.info("Inside the customer update method");
        try {
            customerService.updateCustomer(customerId, customerDto);
//            logger.info("customer update successful");
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


