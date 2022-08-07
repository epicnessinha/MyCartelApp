package org.academiadecodigo.bootcamp.mycartel.exceptions;

import org.academiadecodigo.bootcamp.mycartel.errors.ErrorMessage;

public class GangMemberNotFoundException extends Throwable {
    public GangMemberNotFoundException() {
        super(ErrorMessage.GANGMEMBER_NOT_FOUND);
    }
}

