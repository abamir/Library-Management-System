package com.aitribe.lms.entity;
import java.util.Objects;

public class Branch {

    private final String branchId;
    private String name;


    //Constructor
    public Branch(String branchId, String name) {

        this.branchId = Objects.requireNonNull(branchId, "BranchId cannot be null").trim();
        this.name = Objects.requireNonNull(name, "name cannot be null").trim();
    }

    public String branchId() {
        return branchId;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //toString
    @Override
    public String toString() {
        return "LibraryBranch[branchId=%s, name=%s]"
                .formatted(branchId, name);
    }
}
