package com.nextdoorwang.springboot;

public class GreetingResponse {
    private final long id;
    private final String title;

    public GreetingResponse(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
