package org.academiadecodigo.bootcamp.mycartel.controller.rest;

import org.academiadecodigo.bootcamp.mycartel.converters.ItemDtoToItem;
import org.academiadecodigo.bootcamp.mycartel.converters.ItemToItemDto;
import org.academiadecodigo.bootcamp.mycartel.exceptions.GangMemberNotFoundException;
import org.academiadecodigo.bootcamp.mycartel.exceptions.ItemNotFoundException;
import org.academiadecodigo.bootcamp.mycartel.command.ItemDto;
import org.academiadecodigo.bootcamp.mycartel.persistence.model.GangMember;
import org.academiadecodigo.bootcamp.mycartel.persistence.model.item.Item;
import org.academiadecodigo.bootcamp.mycartel.services.GangMemberService;
import org.academiadecodigo.bootcamp.mycartel.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class RestItemController {


    private GangMemberService gangMemberService;
    private ItemService itemService;
    private ItemToItemDto itemToItemDto;
    private ItemDtoToItem itemDtoToItem;

    @Autowired
    public void setGangMemberService(GangMemberService gangMemberService) {
        this.gangMemberService = gangMemberService;
    }

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @Autowired
    public void setItemToItemDto(ItemToItemDto itemToItemDto) {
        this.itemToItemDto = itemToItemDto;
    }

    @Autowired
    public void setItemDtoToItem(ItemDtoToItem itemDtoToItem) {
        this.itemDtoToItem = itemDtoToItem;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{cid}/item")
    public ResponseEntity<List<ItemDto>> listGangMemberItem(@PathVariable Integer cid) {

        GangMember gangMember = gangMemberService.get(cid);

        if (gangMember == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<ItemDto> accountDtos = gangMember.getItems().stream().map(account -> itemToItemDto.convert(account)).collect(Collectors.toList());

        return new ResponseEntity<>(accountDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{cid}/item/{aid}")
    public ResponseEntity<ItemDto> showGangMemberItem(@PathVariable Integer cid, @PathVariable Integer aid) {

        Item item = itemService.get(aid);

        if (item == null || item.getGangMember() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!item.getGangMember().getId().equals(cid)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(itemToItemDto.convert(item), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{cid}/item")
    public ResponseEntity<?> addItem(@PathVariable Integer cid, @Valid @RequestBody ItemDto itemDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {

        if (bindingResult.hasErrors() || itemDto.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Item item = null;
        try {
            item = gangMemberService.addItem(cid, itemDtoToItem.convert(itemDto));

        UriComponents uriComponents = uriComponentsBuilder.path("/api/gangMember/" + cid + "/item/" + item.getId()).build();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);

        } catch (GangMemberNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{cid}/item/{aid}/close")
    public ResponseEntity<?> deleteItem(@PathVariable Integer cid, @PathVariable Integer aid) {

        try {

            gangMemberService.deleteItem(cid, aid);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (GangMemberNotFoundException | ItemNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
}
