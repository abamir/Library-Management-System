package com.aitribe.lms.ui;

public class SimpleCommand implements Command {
    private String label;
    private Runnable action;

    public SimpleCommand(String label, Runnable action) {

        this.label = label;
        this.action = action;
    }

    @Override
    public String label() {
        return label;
    }

    @Override
    public void execute() {

        action.run();
    }
}
