package org.academiadecodigo.bootcamp.mycartel.services;

import org.academiadecodigo.bootcamp.mycartel.persistence.model.item.Item;

public interface ItemService {

    /**
     * Gets the account with the given id
     *
     * @param id the account id
     * @return the account
     */
    Item get(Integer id);
}

