package net.greet;

import net.Greetable;

public class Day extends Greetable {

    @Override
    public String buildResponse(String userName) {
        return "Good day, " + userName;
    }
}
