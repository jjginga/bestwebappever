package org.academiadecodigo.bestwebappever.exceptions;

import org.academiadecodigo.bestwebappever.errors.ErrorMessage;

/**
 * Thrown to indicate that the transaction was not valid
 */
public class TransactionInvalidException extends BestWebAppEverException {

    /**
     * @see BestWebAppEverException#BestWebAppEverException(String)
     */
    public TransactionInvalidException() {
        super(ErrorMessage.TRANSACTION_INVALID);
    }
}
