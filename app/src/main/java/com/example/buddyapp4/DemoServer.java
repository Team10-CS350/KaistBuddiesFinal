package com.example.buddyapp4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DemoServer {
    public static Exception UnknownValueException;

    /* Non Buddy Users*/
    public static User user1 = createDummyUser("user1", false);
    public static User user2 = createDummyUser("user2", false);
    public static User user3 = createDummyUser("user3", false);
    public static User user4 = createDummyUser("user4", false);

    /* Buddy Users*/
    public static User buddy1 = createDummyUser("buddy1", true);
    public static User buddy2 = createDummyUser("buddy2", true);
    public static User buddy3 = createDummyUser("buddy3", true);
    public static User buddy4 = createDummyUser("buddy4", true);


    /* Events Created*/
    public static Event event1 =
            user1.createEvent("Title1", "description1", getDateAfterNDays(5), makeArrayList(EventType.PARTY), " 2019/12/19");
    public static Event event2 =
            user2.createEvent("Title2", "description2", getDateAfterNDays(3), makeArrayList(EventType.DINNER), "2019/12/16");
    public static List<Event> allEvents = new ArrayList<>(Arrays.asList(event1, event2));

    public static List<User> allMembers =
            new ArrayList<>(Arrays.asList(user1, user2, user3, user4, buddy1, buddy2, buddy3, buddy4));

    public static void addNewUser(User newUser) {
        allMembers.add(newUser);
    }

    public static EventBoard eventBoard = EventBoard.fromEventList(allEvents);


    public static Boolean checkIfEmailExists(String email){
        for (User user: allMembers) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public static User getUserFromEmail (String email){
        for (User user: allMembers) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        throw new IllegalArgumentException("value not found");
    }


    public static Date getDateAfterNDays (int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, n);
        return c.getTime();
    }

    public static User createDummyUser(String name, Boolean isBuddy) {
        return new User(name, name, name, isBuddy);
    }


    public static void sendAuthentication(User user) {}
    public static Boolean checkIfCodeCorrect(String userName, String code) {
        if (code.equals("1234")) return true;
        else return false;
    }

    private static ArrayList<EventType> makeArrayList (EventType typ) {
        ArrayList<EventType> arr = new ArrayList<EventType> ();
        arr.add(typ);
        return arr;
    }

    public static ArrayList<String> getEventTitles() {
        ArrayList<String> titles = new ArrayList<String> ();
        for (Event event: allEvents) {
            titles.add(event.getTitle());
        }
        return titles;
    }

    public static ArrayList<String> getEventDescriptions() {
        ArrayList<String> descs = new ArrayList<String> ();
        for (Event event: allEvents) {
            descs.add(event.getDescription());
        }
        return descs;
    }


    public static ArrayList<String> getEventTypeStringTags() {
        String typString = "";
        ArrayList<String> result = new ArrayList<String> ();
        for (Event event: allEvents) {
            ArrayList<EventType> eventTyps = event.getEventTypes();
            if (eventTyps.isEmpty()) typString = "[None]";
            else {
                for (EventType typ: eventTyps) {
                    typString += "[" + typ.toString() + "]";
                }
            }
            result.add(typString);
            typString = "";
        }
        return result;
    }

    public static ArrayList<String> authors =
            new ArrayList<>(Arrays.asList("michael", "umid", "sevanna", "javid", "Unhak"));
    public static ArrayList<String> messages =
            new ArrayList<>(Arrays.asList("hi there", "hi there", "hi there", "hello", "hoohoho"));
}
