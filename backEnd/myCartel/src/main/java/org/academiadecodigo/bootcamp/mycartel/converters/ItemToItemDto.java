package org.academiadecodigo.bootcamp.mycartel.converters;

import org.academiadecodigo.bootcamp.mycartel.command.ItemDto;
import org.academiadecodigo.bootcamp.mycartel.persistence.model.item.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemToItemDto extends AbstractConverter<Item, ItemDto > {

    @Override
    public ItemDto convert(Item item) {

        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setType(item.getItemType());


        return itemDto;
    }
}

