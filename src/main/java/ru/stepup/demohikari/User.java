package ru.stepup.demohikari;

import lombok.Getter;
import lombok.Setter;

public class User {
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String username;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
