package ru.job4j.trade;

public enum Action {
    ADD("add"), BUY("buy"), SELL("sell"), DELETE("delete");
    private String name;

    Action(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
