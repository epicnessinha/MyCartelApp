package org.academiadecodigo.bootcamp.mycartel.services;

import org.academiadecodigo.bootcamp.mycartel.exceptions.GangMemberNotFoundException;
import org.academiadecodigo.bootcamp.mycartel.exceptions.ItemNotFoundException;
import org.academiadecodigo.bootcamp.mycartel.persistence.model.GangMember;
import org.academiadecodigo.bootcamp.mycartel.persistence.model.item.Item;

import java.util.List;

public interface GangMemberService {

    GangMember get(Integer id);


    GangMember save(GangMember gangMember);

    void delete(Integer id) throws GangMemberNotFoundException;


    List<GangMember> list();

    Item addItem(Integer id, Item item)
            throws GangMemberNotFoundException;


    void deleteItem(Integer id, Integer itemId)
            throws GangMemberNotFoundException, ItemNotFoundException;
}
