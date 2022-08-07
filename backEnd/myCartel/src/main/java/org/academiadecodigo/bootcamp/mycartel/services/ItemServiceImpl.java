package org.academiadecodigo.bootcamp.mycartel.services;

import org.academiadecodigo.bootcamp.mycartel.persistence.dao.GangMemberDao;
import org.academiadecodigo.bootcamp.mycartel.persistence.dao.ItemDao;
import org.academiadecodigo.bootcamp.mycartel.persistence.model.item.Item;

public class ItemServiceImpl implements ItemService {

    private ItemDao itemDao;
    private GangMemberDao gangMemberDao;



    @Override
    public Item get(Integer id) {
        return itemDao.findById(id);
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public void setGangMemberDao(GangMemberDao gangMemberDao) {
        this.gangMemberDao = gangMemberDao;
    }
}

