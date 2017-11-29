package com.example.guestbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
public class Application {

    static HashMap<Long, GuestBookEntry> guestBook;

    public static void main(String[] args) {
        guestBook = new HashMap<>();

        GuestBookEntry one = new GuestBookEntry("Duke Nukem",
                "It's time to kick ass and chew bubble gum. And I'm all out of gum.");
        guestBook.put(one.getId(), one);

        SpringApplication.run(Application.class, args);

        GuestBookEntry two = new GuestBookEntry("Arni", "Hasta la vista, baby");
        guestBook.put(two.getId(), two);

        GuestBookEntry three = new GuestBookEntry("Gump1337",
                "Mama always said life was like a box of chocolates. You never know what you're gonna get.");
        guestBook.put(three.getId(), three);
    }
}
