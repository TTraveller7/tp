package seedu.mycrm.model.util;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.products.Product;

public class JobList {
    private final ArrayList<Job> jobs;

    public JobList() {
        this.jobs = new ArrayList<>();
    }

    public JobList(ArrayList<Job> jobs) {
        requireNonNull(jobs);
        this.jobs = new ArrayList<>(jobs);
    }

    private boolean contains(Job job) {
        requireNonNull(job);
        return jobs.stream().anyMatch(job::isSameJob);
    }

    public void add(Job toAdd) {
        requireNonNull(toAdd);
        assert !this.contains(toAdd);

        jobs.add(toAdd);
    }

    public boolean remove(Job toRemove) {
        requireNonNull(toRemove);
        return jobs.remove(toRemove);
    }

    public void updateProduct(Product product) {
        requireNonNull(product);
        jobs.stream().forEach(j -> j.setProduct(product));
    }

    public boolean isEmpty() {
        return jobs.size() == 0;
    }

    public JobList copy() {
        return new JobList(this.jobs);
    }
}
