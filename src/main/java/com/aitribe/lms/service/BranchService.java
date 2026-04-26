package com.aitribe.lms.service;

import com.aitribe.lms.entity.Branch;
import java.util.List;

public interface BranchService {

    void addBranch(Branch branch);

    Branch getBranch(String branchId);

    List<Branch> listAllBranches();

    void transferBook(String branchId, String fromBranchId, String toBranchId);


}
