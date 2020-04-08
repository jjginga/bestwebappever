package org.academiadecodigo.bestwebappever.controller.web;

import org.academiadecodigo.bestwebappever.command.SpecimenDto;
import org.academiadecodigo.bestwebappever.converters.*;
import org.academiadecodigo.bestwebappever.exceptions.*;
import org.academiadecodigo.bestwebappever.persistence.model.Specimen;
import org.academiadecodigo.bestwebappever.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/specimen")
public class SpecimensController {

    private SpecimenService specimenService;
    private CustomerService customerService;

    private SpecimenToSpecimenDto specimenToSpecimenDto;
    private SpecimenDtoToSpecimen specimenDtoToSpecimen;
    private CustomerToCustomerDto customerToCustomerDto;

    @Autowired
    public void setSpecimenService(SpecimenService specimenService) {
        this.specimenService = specimenService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setSpecimenToSpecimenDto(SpecimenToSpecimenDto specimenToSpecimenDto) {
        this.specimenToSpecimenDto = specimenToSpecimenDto;
    }

    @Autowired
    public void setSpecimenDtoToSpecimen(SpecimenDtoToSpecimen specimenDtoToSpecimen) {
        this.specimenDtoToSpecimen = specimenDtoToSpecimen;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/list", "/", ""})
    public String listCustomers(Model model) {

        model.addAttribute("specimen", specimenToSpecimenDto.convert(specimenService.listSpecimen()));

        return "specimen/list";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{cid}/specimen/add")
    public String addSpecimen(@PathVariable Integer cid, Model model) {

        model.addAttribute("customer", customerToCustomerDto.convert(customerService.get(cid)));
        model.addAttribute("specimen", new SpecimenDto());

        return "specimen/add-update";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{cid}/specimen/{id}/edit")
    public String editSpecimen(@PathVariable Integer cid, @PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerToCustomerDto.convert(customerService.get(cid)));
        model.addAttribute("recipient", specimenToSpecimenDto.convert(specimenService.get(id)));
        return "specimen/add-update";
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/{cid}/recipient/", "/{cid}/recipient"}, params = "action=save")
    public String saveSpecimen(Model model, @PathVariable Integer cid, @Valid @ModelAttribute("recipient") SpecimenDto specimenDto,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        model.addAttribute("customer", customerToCustomerDto.convert(customerService.get(cid)));

        if (bindingResult.hasErrors()) {
            return "specimen/add-update";
        }

        try {

            Specimen specimen = specimenDtoToSpecimen.convert(specimenDto);
            customerService.addSpecimen(cid, specimen);

            redirectAttributes.addFlashAttribute("lastAction", "Saved " + specimenDto.getName());
            return "redirect:/customer/" + cid + "/specimen";

        } catch (CustomerNotFoundException ex) {

            return "redirect:/customer/" + cid;
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/{cid}/recipient/", "/{cid}/recipient"}, params = "action=cancel")
    public String cancelSaveSpecimen(@PathVariable Integer cid) {
        //we could use an anchor tag in the view for this, but we might want to do something clever in the future here
        return "redirect:/customer/" + cid;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{cid}/recipient/{id}/delete")
    public String deleteRecipient(@PathVariable Integer cid, @PathVariable Integer id, RedirectAttributes redirectAttributes) {

        Specimen specimen = specimenService.get(id);
        try {
            customerService.removeSpecimen(cid, id);
        } catch (CustomerNotFoundException e) {
            //TODO
            e.printStackTrace();
        } catch (RecipientNotFoundException e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("lastAction", "Deleted " + specimen.getName());
        return "redirect:/customer/" + cid + "/specimen";
    }

    //TODO METHODS TO TRANSFER
}
