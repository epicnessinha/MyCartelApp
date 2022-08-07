package org.academiadecodigo.bootcamp.mycartel.controller.web;

import org.academiadecodigo.bootcamp.mycartel.converters.GangMemberDtoToGangMember;
import org.academiadecodigo.bootcamp.mycartel.converters.ItemDtoToItem;
import org.academiadecodigo.bootcamp.mycartel.exceptions.GangMemberNotFoundException;
import org.academiadecodigo.bootcamp.mycartel.exceptions.ItemNotFoundException;
import org.academiadecodigo.bootcamp.mycartel.command.ItemDto;
import org.academiadecodigo.bootcamp.mycartel.persistence.model.item.Item;
import org.academiadecodigo.bootcamp.mycartel.services.GangMemberService;
import org.academiadecodigo.bootcamp.mycartel.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
@Controller
@RequestMapping("/customer")
public class ItemControler {


    private GangMemberService gangMemberService;

    private ItemService itemService;

    private ItemDtoToItem itemDtoToItem;

    private GangMemberDtoToGangMember gangMemberDtoToGangMember;

    @Autowired
    public void setGangMemberService(GangMemberService gangMemberService) {
        this.gangMemberService = gangMemberService;
    }
    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }
    @Autowired
    public void setItemDtoToItem(ItemDtoToItem itemDtoToItem) {
        this.itemDtoToItem = itemDtoToItem;
    }

    @Autowired
    public void setGangMemberDtoToGangMember(GangMemberDtoToGangMember gangMemberDtoToGangMember) {
        this.gangMemberDtoToGangMember = gangMemberDtoToGangMember;
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/{cid}/item"})
    public String addItem(@PathVariable Integer cid, @Valid @ModelAttribute("item") ItemDto itemDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws Exception {

        if (bindingResult.hasErrors()) {
            return "redirect:/gangmember/" + cid;
        }

        try {
            Item item = itemDtoToItem.convert(itemDto);
            gangMemberService.addItem(cid, item);
            redirectAttributes.addFlashAttribute("lastAction", "Created " + item.getItemType() + " item.");
            return "redirect:/customer/" + cid;

        } catch (GangMemberNotFoundException e) {
            redirectAttributes.addFlashAttribute("failure", "Savings account must have a minimum value of 100 at all times");
            return "redirect:/customer/" + cid;
        }
    }


    @RequestMapping(method = RequestMethod.GET, path = "/{cid}/item/{aid}/delete")
    public String deleteItem(@PathVariable Integer cid, @PathVariable Integer aid, RedirectAttributes redirectAttributes) throws Exception {

        try {
            gangMemberService.deleteItem(cid, aid);
            redirectAttributes.addFlashAttribute("lastAction", "Item deleted " + aid);
            return "redirect:/gangmember/" + cid;

        } catch (ItemNotFoundException | GangMemberNotFoundException ex) {
            redirectAttributes.addFlashAttribute("failure", "Unable to perform closing operation. Account # " + aid + " still has funds");
            return "redirect:/customer/" + cid;
        }
    }


}
