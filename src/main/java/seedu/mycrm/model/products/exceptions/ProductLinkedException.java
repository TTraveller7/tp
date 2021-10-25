package seedu.mycrm.model.products.exceptions;

/**
 * Signals that the operation will result in removing a product linked to one or more jobs.
 */
public class ProductLinkedException extends RuntimeException {
    public ProductLinkedException() {
        super("Operation will result in removing a product linked to one or more jobs.");
    }
}