package org.academiadecodigo.bestwebappever.controller.web;

import org.academiadecodigo.bestwebappever.command.CustomerDto;
import org.academiadecodigo.bestwebappever.converters.CustomerDtoToCustomer;
import org.academiadecodigo.bestwebappever.converters.CustomerToCustomerDto;
import org.academiadecodigo.bestwebappever.persistence.model.Customer;
import org.academiadecodigo.bestwebappever.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    private CustomerToCustomerDto customerToCustomerDto;
    private CustomerDtoToCustomer customerDtoToCustomer;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setCustomerToCustomerDto(CustomerToCustomerDto customerToCustomerDto) {
        this.customerToCustomerDto = customerToCustomerDto;
    }

    @Autowired
    public void setCustomerDtoToCustomer(CustomerDtoToCustomer customerDtoToCustomer) {
        this.customerDtoToCustomer = customerDtoToCustomer;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/list", "/", ""})
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerToCustomerDto.convert(customerService.list()));
        return "customer/list";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/add")
    public String addCustomer(Model model) {
        model.addAttribute("customer", new CustomerDto());
        return "customer/add-update";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/edit")
    public String editCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerToCustomerDto.convert(customerService.get(id)));
        return "customer/add-update";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String showCustomer(@PathVariable Integer id, Model model) throws Exception {

        Customer customer = customerService.get(id);

        // command objects for customer show view
        model.addAttribute("customer", customerToCustomerDto.convert(customer));

        //TODO Show specimen

        return "customer/show";
    }
}
