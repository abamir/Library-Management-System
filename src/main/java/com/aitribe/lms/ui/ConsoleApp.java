package com.aitribe.lms.ui;

import com.aitribe.lms.Util.InputUtil;
import com.aitribe.lms.configurations.AppConfig;
import com.aitribe.lms.entity.Branch;
import com.aitribe.lms.repository.*;
import com.aitribe.lms.service.*;
import com.aitribe.lms.service.observer.ConsoleReservationObserver;
import com.aitribe.lms.service.serviceImpl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ConsoleApp {

    private static final Logger log = LoggerFactory.getLogger(ConsoleApp.class);

    private final Scanner scanner = new Scanner(System.in);
    private final AppContext ctx;

    private final CatalogService catalogService;
    private final PatronService patronService;
    private final LendingService lendingService;
    private final InventoryService inventoryService;
    private final BranchService branchService;
    private final ReservationService reservationService;


    public ConsoleApp() {
        // Repositories
        BookRepository bookRepo = RepositoryFactory.createBookRepository();
        PatronRepository patronRepo = RepositoryFactory.createPatronRepository();
        LoanRepository loanRepo = RepositoryFactory.createLoanRepository();
        BranchRepository branchRepo = RepositoryFactory.createBranchRepository();
        ReservationRepository reservationRepo = RepositoryFactory.createReservationRepository();

        // Services
        this.catalogService = new CatalogServiceImpl(bookRepo);
        this.patronService = new PatronServiceImpl(patronRepo);
        this.reservationService = new ReservationServiceImpl(reservationRepo, bookRepo, patronRepo);
        this.lendingService = new LendingServiceImpl(loanRepo, bookRepo, patronRepo, reservationService, AppConfig.DEFAULT_BRANCH_ID);
        this.inventoryService = new InventoryServiceImpl(bookRepo, AppConfig.DEFAULT_BRANCH_ID);
        this.branchService = new BranchServiceImpl(branchRepo, bookRepo);


        if (reservationService instanceof ReservationServiceImpl rsi) {

            rsi.addObserver(new ConsoleReservationObserver());
        }

        // Ensure default branch exists
        if (!branchRepo.existsByBranchId(AppConfig.DEFAULT_BRANCH_ID)) {
            branchService.addBranch(new Branch(AppConfig.DEFAULT_BRANCH_ID, AppConfig.DEFAULT_BRANCH_NAME));
        }

        this.ctx = new AppContext(
                scanner,
                catalogService,
                patronService,
                lendingService,
                inventoryService,
                branchService,
                reservationService
        );
    }

    public void run() {
        log.info("Library Management System started.");
        System.out.println("==========================================");
        System.out.println("   LIBRARY MANAGEMENT SYSTEM (Console) ");
        System.out.println("==========================================");

        boolean running = true;

        while (running) {
            try {
                System.out.println("\n========== MAIN MENU ==========");
                System.out.println("1) Books");
                System.out.println("2) Patrons");
                System.out.println("3) Lending");
                System.out.println("4) Inventory");
                System.out.println("5) Branches");
                System.out.println("6) Reservation");
                System.out.println("0) Exit");

                int choice = InputUtil.readChoice(scanner, "Select: ", 0, 6);

                switch (choice) {
                    case 1 -> new BookMenu(ctx).show();
                    case 2 -> new PatronMenu(ctx).show();
                    case 3 -> new LendingMenu(ctx).show();
                    case 4 -> new InventoryMenu(ctx).show();
                    case 5 -> new BranchesMenu(ctx).show();
                    case 6 -> new ReservationMenu(ctx).show();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                log.error("Unhandled application error: {}", e.getMessage(), e);
                System.out.println("❌ Error: " + e.getMessage());
            }
        }

        log.info("Library Management System exited.");
        System.out.println("👋 Goodbye!");
    }
}
