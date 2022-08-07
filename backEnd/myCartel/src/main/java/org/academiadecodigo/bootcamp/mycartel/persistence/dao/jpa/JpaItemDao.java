package org.academiadecodigo.bootcamp.mycartel.persistence.dao.jpa;

import org.academiadecodigo.bootcamp.mycartel.persistence.dao.ItemDao;
import org.academiadecodigo.bootcamp.mycartel.persistence.model.item.Item;
import org.springframework.stereotype.Repository;

@Repository
public class JpaItemDao extends GenericJpaDao<Item> implements ItemDao {

    /**
     * @see GenericJpaDao#GenericJpaDao(Class)
     */
    public JpaItemDao() {
        super(Item.class);
    }
}
