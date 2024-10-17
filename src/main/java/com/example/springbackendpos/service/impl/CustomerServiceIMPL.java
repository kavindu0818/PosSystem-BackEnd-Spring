package com.example.springbackendpos.service.impl;

import com.example.springbackendpos.customStatusCodes.SelectedCustomerAndItemErrorStatus;
import com.example.springbackendpos.dao.CustomerDao;
import com.example.springbackendpos.dto.CustomerStatus;
import com.example.springbackendpos.dto.impl.CustomerDto;
import com.example.springbackendpos.entity.impl.CustomerEntity;
import com.example.springbackendpos.exception.CustomerNotFoundException;
import com.example.springbackendpos.exception.DataPersistException;
import com.example.springbackendpos.service.CustomerService;
import com.example.springbackendpos.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceIMPL implements CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveCustomer(CustomerDto customerDto){
        CustomerEntity savedCustomer = customerDao.save(mapping.toCustomerEntity(customerDto));

        if (savedCustomer == null){
            throw new DataPersistException("Customer not save");
        }
    }
    @Override
    public void updateCustomer(String id, CustomerDto customerDto) {
        Optional<CustomerEntity> Cust= customerDao.findById(id);
        if(Cust.isPresent()){
            Cust.get().setName(customerDto.getName());
            Cust.get().setAddress(customerDto.getAddress());
            Cust.get().setSalary(customerDto.getSalary());
        }
    }

    @Override
    public void deleteCustomer(String id) throws CustomerNotFoundException {
        Optional<CustomerEntity> existUser=customerDao.findById(id);
        if(!existUser.isPresent()){
            throw  new CustomerNotFoundException("Customer does not exist");
        }else {
            customerDao.deleteById(id);
        }
    }

   @Override

   public List<CustomerDto> getAll() {
       return mapping.asCustomerDTOList(customerDao.findAll());
   }




    @Override
    public CustomerStatus getUser(String userId) {
        if(customerDao.existsById(userId)){
            CustomerEntity selectedUser = customerDao.getReferenceById(userId);
            return mapping.toCustomerDto(selectedUser);
        }else {
            return (CustomerStatus) new SelectedCustomerAndItemErrorStatus(2, "User with id " + userId + " not found");
        }
    }

}
