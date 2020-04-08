package org.academiadecodigo.bestwebappever.exceptions;

/**
 * A generic java bank exception to be used as base for concrete types of exceptions
 *
 * @see Exception
 */
public class BestWebAppEverException extends Exception {

    /**
     * @see Exception#Exception(String)
     */
    public BestWebAppEverException(String message) {
        super(message);
    }
}
