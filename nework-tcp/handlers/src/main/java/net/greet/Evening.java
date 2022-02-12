package net.greet;

import net.Greetable;

public class Evening extends Greetable {
    @Override
    public String buildResponse(String userName) {
        return "Good Evening, " + userName;
    }
}
