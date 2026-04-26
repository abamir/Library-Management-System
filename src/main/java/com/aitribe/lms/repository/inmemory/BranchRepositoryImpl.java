package com.aitribe.lms.repository.inmemory;

import com.aitribe.lms.entity.Branch;
import com.aitribe.lms.repository.BranchRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BranchRepositoryImpl implements BranchRepository {


    private final Map<String, Branch> branches = new HashMap<>();

    @Override
    public void save(Branch branch) {

        branches.put(branch.branchId(), branch);

    }

    @Override
    public Optional<Branch> findByBranchId(String branchId) {
        return Optional.ofNullable(branches.get(branchId));
    }

    @Override
    public List<Branch> findAll() {
        return branches.values().stream().toList();
    }

    @Override
    public boolean existsByBranchId(String branchId) {
        return branches.containsKey(branchId);
    }
}
