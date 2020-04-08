package org.academiadecodigo.bestwebappever.exceptions;

import org.academiadecodigo.bestwebappever.errors.ErrorMessage;

/**
 * Thrown to indicate that the customer was not found
 */
public class CustomerNotFoundException extends BestWebAppEverException {

    /**
     * @see BestWebAppEverException#BestWebAppEverException(String)
     */
    public CustomerNotFoundException() {
        super(ErrorMessage.CUSTOMER_NOT_FOUND);
    }
}
