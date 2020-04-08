package org.academiadecodigo.bestwebappever.exceptions;

import org.academiadecodigo.bestwebappever.errors.ErrorMessage;

/**
 * Thrown to indicate that the recipient was not found
 */
public class RecipientNotFoundException extends BestWebAppEverException {

    /**
     * @see BestWebAppEverException#BestWebAppEverException(String)
     */
    public RecipientNotFoundException() {
        super(ErrorMessage.SPECIMEN_NOT_FOUND);
    }
}
