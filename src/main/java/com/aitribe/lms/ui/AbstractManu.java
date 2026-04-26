package com.aitribe.lms.ui;

import com.aitribe.lms.Util.InputUtil;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractManu {

    protected final AppContext ctx;

    private final String title;

    private final List<Command> commands = new ArrayList<>();

    public AbstractManu(AppContext ctx, String title) {
        this.ctx = ctx;
        this.title = title;
        buildMenu();
    }

    protected abstract void buildMenu();

    protected void addCommand(String label, Runnable action) {

        commands.add(new SimpleCommand(label, action));

    }

    public void show() {

        while (true) {

            printMenu();
            int choice = InputUtil.readChoice(ctx.scanner(), "Select: ", 0, commands.size());

            if (choice == 0) {
                return;
            }
            try {
                commands.get(choice - 1).execute();
            } catch (Exception e) {

                System.out.println("❌ Error: " + e.getMessage());
            }

            System.out.println();
        }
    }

    //Print Menu Method
    private void printMenu() {

        System.out.println("\n===============" + title + "==============\n");

        for (int i = 0; i <= commands.size() - 1; i++) {

            System.out.printf("%d) %s%n", i + 1, commands.get(i).label());
        }
        System.out.println("0) Back");
    }

    protected String readOptional(String prompt) {

        System.out.println(prompt);
        return ctx.scanner().nextLine();
    }

}
