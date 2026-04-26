package com.aitribe.lms.service.serviceImpl;

import com.aitribe.lms.Util.ValidationUtil;
import com.aitribe.lms.entity.Book;
import com.aitribe.lms.entity.Branch;
import com.aitribe.lms.enums.BookStatus;
import com.aitribe.lms.repository.BookRepository;
import com.aitribe.lms.repository.BranchRepository;
import com.aitribe.lms.service.BranchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BranchServiceImpl implements BranchService {

    private static final Logger log = LoggerFactory.getLogger(BranchServiceImpl.class);

    private final BranchRepository branchRepo;

    private final BookRepository bookRepo;

    public BranchServiceImpl(BranchRepository branchRepo, BookRepository bookRepo) {
        this.branchRepo = branchRepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public void addBranch(Branch branch) {

        ValidationUtil.validate(!branchRepo.existsByBranchId(branch.branchId()),
                "Branch with Id already exists :! " + branch.branchId());

        branchRepo.save(branch);
        log.info("Branch added successfully :! {}", branch);


    }

    @Override
    public Branch getBranch(String branchId) {
        return branchRepo.findByBranchId(branchId)
                .orElseThrow(() -> new RuntimeException("Branch not found with Id " + branchId));
    }

    @Override
    public List<Branch> listAllBranches() {
        return branchRepo.findAll();
    }

    @Override
    public void transferBook(String branchId, String fromBranchId, String toBranchId) {

        //Validate FromBranch Exist or not
        ValidationUtil.validate(branchRepo.existsByBranchId(fromBranchId),
                "Branch with Id not exists :! " + fromBranchId);

        //Validate ToBranch Exist or not
        ValidationUtil.validate(branchRepo.existsByBranchId(toBranchId),
                "Branch with Id not exists :! " + toBranchId);

        //validate fFromBranch and ToBranch are not same
        ValidationUtil.validate(!fromBranchId.equals(toBranchId),
                "FromBranch and ToBranch are same :! " + fromBranchId);

        //get BookByIsbn
        Book book = bookRepo.findByIsbn(branchId)
                .orElseThrow(() -> new RuntimeException("Book with ISBN not exists :! " + branchId));

        //Validate BookStatus ==AVAILABLE

        ValidationUtil.validate(book.status().equals(BookStatus.AVAILABLE),
                "Can not transfer.! Book is not available :! " + branchId);


        ValidationUtil.validate(book.branchId().equals(fromBranchId),
                "Can not transfer.! Book is not available :! " + branchId);

        book.setBranchId(toBranchId);
        bookRepo.save(book);
        log.info("Book transferred successfully :! {}", book);

    }
}
