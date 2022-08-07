package org.academiadecodigo.bootcamp.mycartel.command;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.academiadecodigo.bootcamp.mycartel.persistence.model.item.ItemType;

import javax.validation.constraints.NotNull;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
    public class ItemDto {

        private Integer id;
        @NotNull(message = "ItemsType is mandatory")
        private ItemType type;

        /**
         * Gets the id of the account DTO
         *
         * @return the account DTO id
         */
        public Integer getId() {
            return id;
        }

        /**
         * Sets the id of the account DTO
         *
         * @param id the account DTO id
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         * Gets the type of the account DTO
         *
         * @return the account type
         */
        public ItemType getType() {
            return type;
        }

        /**
         * Sets the account DTO type
         *
         * @param type the account type to set
         */
        public void setType(ItemType type) {
            this.type = type;
        }

        /**
         * @see Object#toString()
         */
        @Override
        public String toString() {
            return "ItemsDto{" +
                    "id=" + id +
                    ", type=" + type +
                    '}';
        }
    }







