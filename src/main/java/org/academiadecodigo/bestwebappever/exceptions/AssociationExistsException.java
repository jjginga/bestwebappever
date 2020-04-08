package org.academiadecodigo.bestwebappever.exceptions;

import org.academiadecodigo.bestwebappever.errors.ErrorMessage;

/**
 * Thrown to indicate that an association still exists
 */
public class AssociationExistsException extends BestWebAppEverException {

    /**
     * @see BestWebAppEverException#BestWebAppEverException(String)
     */
    public AssociationExistsException() {
        super(ErrorMessage.ASSOCIATION_EXISTS);
    }
}
