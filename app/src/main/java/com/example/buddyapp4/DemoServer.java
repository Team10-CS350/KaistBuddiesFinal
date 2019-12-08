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
            user1.createEvent("Title1", "description1", getDateAfterNDays(5), EventType.PARTY);
    public static Event event2 =
            user2.createEvent("Title2", "description2", getDateAfterNDays(3), EventType.DINNER);
    public static List<Event> allEvents = Arrays.asList(event1, event2);

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
        return new User(name, name + "@kaist.ac.kr", name, isBuddy);
    }

}
