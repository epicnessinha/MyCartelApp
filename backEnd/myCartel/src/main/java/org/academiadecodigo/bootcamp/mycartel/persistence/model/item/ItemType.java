package org.academiadecodigo.bootcamp.mycartel.persistence.model.item;

import java.util.Arrays;
import java.util.List;

public enum ItemType {

    /**
     * @see ShrimpItem
     */
    SHRIMP,

    /**
     * @see MariachiItem
     */
    MARIACHI,

    /**
     * @see TacoItem
     */
    TACO;

    /**
     * Lists the account types
     *
     * @return the list of account types
     */
    public static List<ItemType> list() {
        return Arrays.asList(ItemType.values());
    }
}
