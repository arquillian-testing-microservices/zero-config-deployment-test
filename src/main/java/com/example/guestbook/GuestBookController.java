package com.example.guestbook;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/")
public class GuestBookController {

    @RequestMapping("/")
    String index() {
        return "redirect:/guestbook";
    }

    @RequestMapping(value = "/guestbook", method = RequestMethod.GET)
    public HashMap<Long, GuestBookEntry> getAllGuestBookEntries() {
        return Application.guestBook;
    }

    @RequestMapping(value = "/guestbook/{id}", method = RequestMethod.GET)
    public GuestBookEntry getGuestBookEntry(@PathVariable long id) {
        return Application.guestBook.get(id);
    }

    @RequestMapping(value = "/guestbook/add", method = RequestMethod.POST)
    public GuestBookEntry addGuestBookEntry(@RequestParam(value = "name") String name, @RequestParam(value = "text",
            defaultValue = "unknown") String subject) {

        GuestBookEntry guestBookEntry = new GuestBookEntry(name, subject);
        Application.guestBook.put(guestBookEntry.getId(), guestBookEntry);
        return guestBookEntry;
    }

    @RequestMapping(value = "/guestbook/update", method = RequestMethod.PUT)
    public GuestBookEntry updateGuestBookEntry(@RequestBody GuestBookEntry guestBookEntry) throws Exception {
        if (Application.guestBook.containsKey(guestBookEntry.getId())) {
            Application.guestBook.put(guestBookEntry.getId(), guestBookEntry);
        } else {
            throw new Exception("GuestBookEntry " + guestBookEntry.getId() + " does not exists");
        }
        return guestBookEntry;
    }

    @RequestMapping(value = "/guestbook/delete/{id}", method = RequestMethod.DELETE)
    public GuestBookEntry deleteGuestBookEntry(@PathVariable long id) throws Exception {
        GuestBookEntry guestBookEntry;
        if (Application.guestBook.containsKey(id)) {
            guestBookEntry = Application.guestBook.get(id);
            Application.guestBook.remove(id);
        } else {
            throw new Exception("GuestBookEntry " + id + " does not exists");
        }
        return guestBookEntry;
    }
}
