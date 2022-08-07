package org.academiadecodigo.bootcamp.mycartel.factories;

import org.academiadecodigo.bootcamp.mycartel.persistence.model.item.*;
import org.academiadecodigo.bootcamp.mycartel.persistence.model.item.*;
import org.academiadecodigo.bootcamp.mycartel.persistence.model.item.*;
import org.springframework.stereotype.Component;

    @Component
    public class ItemFactory {

        /**
         * Creates a new {@link Item}
         *
         * @param itemType the account type
         * @return the new account
         */
        public Item createItem(ItemType itemType) {

            Item newItem;

            switch (itemType) {
                case MARIACHI:
                    newItem = new MariachiItem();
                    break;
                case SHRIMP:
                    newItem = new ShrimpItem();
                    break;
                case TACO:
                    newItem = new TacoItem();
                    break;
                default:
                    throw new IllegalArgumentException();
            }

            return newItem;
        }
    }

