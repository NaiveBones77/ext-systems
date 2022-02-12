package net.greet;

import net.Greetable;

public class Morning extends Greetable {
    @Override
    public String buildResponse(String userName) {
        return "Good morning, " + userName;
    }
}
