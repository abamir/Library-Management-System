package com.aitribe.lms.ui;

import com.aitribe.lms.service.*;

import java.util.Scanner;

public class AppContext {

    private final Scanner scanner;
    private final CatalogService catalogService;
    private final PatronService patronService;
    private final LendingService lendingService;
    private final InventoryService inventoryService;
    private final BranchService branchService;
    private final ReservationService reservationService;


    public AppContext(Scanner scanner,
                      CatalogService catalogService,
                      PatronService patronService,
                      LendingService lendingService,
                      InventoryService inventoryService,
                      BranchService branchService,
                      ReservationService reservationService) {
        this.scanner = scanner;
        this.catalogService = catalogService;
        this.patronService = patronService;
        this.lendingService = lendingService;
        this.inventoryService = inventoryService;
        this.branchService = branchService;
        this.reservationService = reservationService;
    }

    public Scanner scanner() {
        return scanner;
    }

    public CatalogService catalogService() {
        return catalogService;
    }

    public PatronService patronService() {
        return patronService;
    }

    public LendingService lendingService() {
        return lendingService;
    }

    public InventoryService inventoryService() {
        return inventoryService;
    }

    public BranchService branchService() {
        return branchService;
    }

    public ReservationService reservationService() {
        return reservationService;
    }

}
