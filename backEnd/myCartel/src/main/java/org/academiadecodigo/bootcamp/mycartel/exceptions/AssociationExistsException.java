package org.academiadecodigo.bootcamp.mycartel.exceptions;

import org.academiadecodigo.bootcamp.mycartel.errors.ErrorMessage;

public class AssociationExistsException extends MyCartelException {


    public AssociationExistsException() {
        super(ErrorMessage.ASSOCIATION_EXISTS);
    }
}


