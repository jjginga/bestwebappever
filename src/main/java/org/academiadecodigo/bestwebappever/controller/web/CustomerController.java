package org.academiadecodigo.bestwebappever.controller.web;

import org.academiadecodigo.bestwebappever.command.CustomerDto;
import org.academiadecodigo.bestwebappever.command.LoginDTO;
import org.academiadecodigo.bestwebappever.converters.CustomerDtoToCustomer;
import org.academiadecodigo.bestwebappever.converters.CustomerToCustomerDto;
import org.academiadecodigo.bestwebappever.converters.SpecimenToSpecimenDto;
import org.academiadecodigo.bestwebappever.exceptions.AssociationExistsException;
import org.academiadecodigo.bestwebappever.exceptions.CustomerNotFoundException;
import org.academiadecodigo.bestwebappever.persistence.model.Customer;
import org.academiadecodigo.bestwebappever.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    private CustomerToCustomerDto customerToCustomerDto;
    private CustomerDtoToCustomer customerDtoToCustomer;
    private SpecimenToSpecimenDto specimenToSpecimenDto;

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

        model.addAttribute("specimen", specimenToSpecimenDto.convert(customer.getSpecimens()));

        return "show";
    }

    /**
     * Saves the customer form submission and renders a view
     *
     * @param customerDto        the customer DTO object
     * @param bindingResult      the binding result object
     * @param redirectAttributes the redirect attributes object
     * @return the view to render
     */
    @RequestMapping(method = RequestMethod.POST, path = {"/", ""}, params = "action=save")
    public String saveCustomer(@Valid @ModelAttribute("customer") CustomerDto customerDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "customer/add-update";
        }

        Customer savedCustomer = customerService.save(customerDtoToCustomer.convert(customerDto));

        redirectAttributes.addFlashAttribute("lastAction", "Saved " + savedCustomer.getFirstName() + " " + savedCustomer.getLastName());
        return "redirect:/customer/" + savedCustomer.getId();
    }

    /**
     * Cancels the customer submission and renders the default the customer view
     *
     * @return the view to render
     */
    @RequestMapping(method = RequestMethod.POST, path = {"/", ""}, params = "action=cancel")
    public String cancelSaveCustomer() {
        // we could use an anchor tag in the view for this, but we might want to do something clever in the future here
        return "redirect:/customer/";
    }

    /**
     * Deletes the customer and renders the default customer view
     *
     * @param id                 the customer id
     * @param redirectAttributes the redirect attributes object
     * @return the view to render
     * @throws AssociationExistsException
     * @throws CustomerNotFoundException
     */
    @RequestMapping(method = RequestMethod.GET, path = "{id}/delete")
    public String deleteCustomer(@PathVariable Integer id, RedirectAttributes redirectAttributes) throws AssociationExistsException, CustomerNotFoundException {
        Customer customer = customerService.get(id);
        customerService.delete(id);
        redirectAttributes.addFlashAttribute("lastAction", "Deleted " + customer.getFirstName() + " " + customer.getLastName());
        return "redirect:/customer";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/login")
    public String login(Model model){
        model.addAttribute("login", new LoginDTO());

        return "login";
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""}, params = "action=login")
    public String saveCustomer(@Valid @ModelAttribute("login") LoginDTO loginDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "redirect:/customer/login";
        }

        if(customerService.login(loginDTO.getUsername(), loginDTO.getPassword())){
            return "login";
        }

        Customer customer = customerService.getByUsername(loginDTO.getUsername());

        return "redirect:/customer/" + customer.getId();
    }
    @RequestMapping(method = RequestMethod.GET, path = "/register")
    public String registerCustomer(Model model) {
        model.addAttribute("customer", new CustomerDto());
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""}, params = "action=register")
    public String rsaveCustomer(@Valid @ModelAttribute("customer") CustomerDto customerDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        if(customerService.getByUsername(customerDto.getUsername())!=null){
            return "register";
        }

        Customer savedCustomer = customerService.save(customerDtoToCustomer.convert(customerDto));

        redirectAttributes.addFlashAttribute("lastAction", "Saved " + savedCustomer.getFirstName() + " " + savedCustomer.getLastName());
        return "redirect:/customer/" + savedCustomer.getId();
    }

}
