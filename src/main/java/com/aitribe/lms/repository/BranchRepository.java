package com.aitribe.lms.repository;

import com.aitribe.lms.entity.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchRepository {

    void save(Branch branch);
    Optional<Branch> findByBranchId(String branchId);
    List<Branch> findAll();
    //void deleteByBranchId(String branchId);
    boolean existsByBranchId(String branchId);
}
