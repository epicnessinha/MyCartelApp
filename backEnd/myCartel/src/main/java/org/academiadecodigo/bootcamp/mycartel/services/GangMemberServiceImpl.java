package org.academiadecodigo.bootcamp.mycartel.services;

import org.academiadecodigo.bootcamp.mycartel.exceptions.GangMemberNotFoundException;
import org.academiadecodigo.bootcamp.mycartel.exceptions.ItemNotFoundException;
import org.academiadecodigo.bootcamp.mycartel.persistence.dao.GangMemberDao;
import org.academiadecodigo.bootcamp.mycartel.persistence.dao.ItemDao;
import org.academiadecodigo.bootcamp.mycartel.persistence.model.GangMember;
import org.academiadecodigo.bootcamp.mycartel.persistence.model.AbstractModel;
import org.academiadecodigo.bootcamp.mycartel.persistence.model.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GangMemberServiceImpl implements GangMemberService {

    private GangMemberDao gangMemberDao;
    private ItemDao itemDao;


    @Autowired
    public void setGangMemberDao(GangMemberDao gangMemberDao) {
        this.gangMemberDao = gangMemberDao;
    }


    @Autowired
    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public GangMember get(Integer id) {
        return gangMemberDao.findById(id);
    }



    /**
     * @see GangMemberService#save(GangMember)
     */
    @Transactional
    @Override
    public GangMember save(GangMember gangMember) {
        return gangMemberDao.saveOrUpdate(gangMember);
    }

    /**
     * @see GangMemberService#delete(Integer)
     */
    @Transactional
    @Override
    public void delete(Integer id) throws GangMemberNotFoundException {

        GangMember gangMember = Optional.ofNullable(gangMemberDao.findById(id))
                .orElseThrow(GangMemberNotFoundException::new);

        if (!gangMember.getItems().isEmpty()) {
            throw new GangMemberNotFoundException();
        }

        gangMemberDao.delete(id);
    }

    /**
     * @see GangMemberService#list()
     */
    @Override
    public List<GangMember> list() {
        return gangMemberDao.findAll();
    }

    /**
     */
    @Transactional
    @Override
    public Item addItem(Integer id, Item item) throws GangMemberNotFoundException {

        GangMember gangMember = Optional.ofNullable(gangMemberDao.findById(id))
                .orElseThrow(GangMemberNotFoundException::new);


        gangMember.addItem(item);
        gangMemberDao.saveOrUpdate(gangMember);

        return gangMember.getItems().get(gangMember.getItems().size() - 1);
    }


    @Transactional
    @Override
    public void deleteItem(Integer id, Integer accountId)
            throws GangMemberNotFoundException, ItemNotFoundException {

        GangMember gangMember = Optional.ofNullable(gangMemberDao.findById(id))
                .orElseThrow(GangMemberNotFoundException::new);

        Item item = Optional.ofNullable(itemDao.findById(accountId))
                .orElseThrow(ItemNotFoundException::new);

        if (!item.getGangMember().getId().equals(id)) {
            throw new ItemNotFoundException();
        }


        gangMember.removeItem(item);
        gangMemberDao.saveOrUpdate(gangMember);
    }

    private Set<Integer> getItemIds(GangMember gangMember) {
        List<Item> items = gangMember.getItems();

        return items.stream()
                .map(AbstractModel::getId)
                .collect(Collectors.toSet());
    }
}

