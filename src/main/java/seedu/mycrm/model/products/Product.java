package seedu.mycrm.model.products;

import static seedu.mycrm.commons.util.CollectionUtil.requireAllNonNull;

import seedu.mycrm.model.Model;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.util.JobList;

/**
 * Represents a product in MyCRM.
 */
public class Product {
    private final ProductName productName;
    private final Type type;
    private final Manufacturer manufacturer;
    private final Description description;

    private final JobList linkedJobs;

    /**
     * Create a product.
     * @param productName Name of the product.
     */
    public Product(ProductName productName, Type type, Manufacturer manufacturer, Description description) {
        requireAllNonNull(productName, type, manufacturer, description);
        assert !productName.isEmpty() : "Product name is empty.";

        this.productName = productName;
        this.type = type;
        this.manufacturer = manufacturer;
        this.description = description;
        this.linkedJobs = new JobList();
    }

    /**
     * Create a product.
     * @param productName Name of the product.
     */
    public Product(ProductName productName, Type type, Manufacturer manufacturer, Description description,
                   JobList linkedJobs) {
        requireAllNonNull(productName, linkedJobs);
        assert !productName.isEmpty() : "Product name is empty.";

        this.productName = productName;
        this.type = type;
        this.manufacturer = manufacturer;
        this.description = description;
        this.linkedJobs = linkedJobs;
    }

    public ProductName getName() {
        return this.productName;
    }

    public boolean hasType() {
        return !this.type.isEmpty();
    }

    public Type getType() {
        return this.type;
    }

    public boolean hasManufacturer() {
        return !this.manufacturer.isEmpty();
    }

    public Manufacturer getManufacturer() {
        return this.manufacturer;
    }

    public boolean hasDescription() {
        return !this.description.isEmpty();
    }

    public Description getDescription() {
        return this.description;
    }

    public JobList getLinkedJobs() {
        return this.linkedJobs;
    }

    /**
     * Returns true if both products have the same name.
     * This defines a weaker notion of equality between two contacts.
     */
    public boolean isSameProduct(Product otherProduct) {
        if (otherProduct == this) {
            return true;
        }

        return otherProduct != null
                && otherProduct.productName.equals(this.productName);
    }

    public void linkJob(Job job) {
        linkedJobs.add(job);
    }

    public boolean unlinkJob(Job job) {
        return linkedJobs.remove(job);
    }

    public boolean isLinkedToJob() {
        return !linkedJobs.isEmpty();
    }

    public void updateLinkedJobs() {
        linkedJobs.copy().updateProduct(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }

        if (o instanceof Product) {
            Product p = (Product) o;
            return p.productName.equals(this.productName)
                    && p.type.equals(this.type)
                    && p.manufacturer.equals(this.manufacturer)
                    && p.description.equals(this.description);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Product: " + this.productName
                + (this.type.isEmpty() ? "" : " Type: " + this.type)
                + (this.manufacturer.isEmpty() ? "" : " Manufacturer: " + this.manufacturer)
                + (this.description.isEmpty() ? "" : " Description: " + this.description);
    }
}
