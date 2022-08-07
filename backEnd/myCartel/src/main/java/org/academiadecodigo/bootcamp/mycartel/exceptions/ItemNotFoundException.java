package org.academiadecodigo.bootcamp.mycartel.exceptions;

import org.academiadecodigo.bootcamp.mycartel.errors.ErrorMessage;

public class ItemNotFoundException extends Throwable {


    public ItemNotFoundException() {
        super(ErrorMessage.ITEM_NOT_FOUND);
    }
}

