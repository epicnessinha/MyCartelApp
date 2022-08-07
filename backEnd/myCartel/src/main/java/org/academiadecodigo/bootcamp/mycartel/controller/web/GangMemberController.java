package org.academiadecodigo.bootcamp.mycartel.controller.web;

import org.academiadecodigo.bootcamp.mycartel.converters.GangMemberDtoToGangMember;
import org.academiadecodigo.bootcamp.mycartel.converters.GangMemberToGangMemberDto;
import org.academiadecodigo.bootcamp.mycartel.converters.ItemToItemDto;
import org.academiadecodigo.bootcamp.mycartel.exceptions.GangMemberNotFoundException;
import org.academiadecodigo.bootcamp.mycartel.command.GangMemberDto;
import org.academiadecodigo.bootcamp.mycartel.command.ItemDto;
import org.academiadecodigo.bootcamp.mycartel.persistence.model.GangMember;
import org.academiadecodigo.bootcamp.mycartel.persistence.model.item.ItemType;
import org.academiadecodigo.bootcamp.mycartel.services.GangMemberService;
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
public class GangMemberController {


    private GangMemberService gangMemberService;

    private GangMemberToGangMemberDto gangMemberToGangMemberDto;
    private GangMemberDtoToGangMember gangMemberDtoGangMember;
    private ItemToItemDto itemToItemDto;
    @Autowired
    public GangMemberService getGangMemberService() {
        return gangMemberService;
    }
    @Autowired
    public GangMemberToGangMemberDto getGangMemberToGangMemberDto() {
        return gangMemberToGangMemberDto;
    }
    @Autowired
    public GangMemberDtoToGangMember getGangMemberDtoGangMember() {
        return gangMemberDtoGangMember;
    }
    @Autowired
    public ItemToItemDto getItemToItemDto() {
        return itemToItemDto;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/list", "/", ""})
    public String listCustomers(Model model) {
        model.addAttribute("gangmember", gangMemberToGangMemberDto.convert(gangMemberService.list()));
        return "gangmember/list";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/add")
    public String addCustomer(Model model) {
        model.addAttribute("gangmember", new GangMemberDto());
        return "gangmember/add-update";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/edit")
    public String editGangMember(@PathVariable Integer id, Model model) {
        model.addAttribute("gangmember", gangMemberToGangMemberDto.convert(gangMemberService.get(id)));
        return "gangmember/add-update";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String showGangMember(@PathVariable Integer id, Model model) throws Exception {

        GangMember gangMember = gangMemberService.get(id);

        // command objects for customer show view
        model.addAttribute("gangMember", gangMemberToGangMemberDto.convert(gangMember));
        model.addAttribute("items", itemToItemDto.convert(gangMember.getItems()));
        model.addAttribute("itemTypes", ItemType.list());


        // command objects for modals
        ItemDto itemDto = new ItemDto();

        model.addAttribute("item", itemDto);
        return "gangMember/show";
    }


    @RequestMapping(method = RequestMethod.POST, path = {"/", ""}, params = "action=save")
    public String saveGangMember(@Valid @ModelAttribute("gangMember") GangMemberDto gangMemberDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "gangmember/add-update";
        }

        GangMember savedGangMember = gangMemberService.save(gangMemberDtoGangMember.convert(gangMemberDto));

        redirectAttributes.addFlashAttribute("lastAction", "Saved " + savedGangMember.getFirstName() + " " + savedGangMember.getLastName());
        return "redirect:/customer/" + savedGangMember.getId();
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""}, params = "action=cancel")
    public String cancelSaveCustomer() {
        // we could use an anchor tag in the view for this, but we might want to do something clever in the future here
        return "redirect:/gangmember/";
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}/delete")
    public String deleteGangMember(@PathVariable Integer id, RedirectAttributes redirectAttributes) throws GangMemberNotFoundException {
        GangMember gangMember = gangMemberService.get(id);
        gangMemberService.delete(id);
        redirectAttributes.addFlashAttribute("lastAction", "Deleted " + gangMember.getFirstName() + " " + gangMember.getLastName());
        return "redirect:/customer";
    }
}
