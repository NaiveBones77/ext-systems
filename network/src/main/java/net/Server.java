package net;

import java.io.*;
import java.lang.reflect.Constructor;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket socket = new ServerSocket(25225, 2000);

        Map<String, Greetable> handlers = loadHandlers();
        System.out.println("server is started");
        while(true)
        {
            Socket client = socket.accept();
            new SimpleServer(client ,handlers).start();

        }
    }

    private static Map<String, Greetable> loadHandlers() {
        Map<String, Greetable> result = new HashMap<>();

        try(InputStream is = Server.class.getClassLoader()
                .getResourceAsStream("server.properties")) {
            Properties properties = new Properties();
            properties.load(is);

            for (Object command : properties.keySet()) {
                String classname = properties.getProperty(command.toString());
                Class<Greetable> cl = (Class<Greetable>) Class.forName(classname);

                Greetable handler = cl.getConstructor().newInstance();
                result.put(command.toString(), handler);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return result;
    }

}

class SimpleServer extends Thread
{

    private Map<String, Greetable> handlers;

    private Socket client;

    public SimpleServer(Socket client, Map<String, Greetable> handlers)
    {
        this.client = client;
        this.handlers = handlers;
    }

    @Override
    public void run()
    {
        handleRequest();
    }

    private void handleRequest()
    {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));


            String request = br.readLine();
            String[] lines = request.split("\\s+");
            String command = lines[0];
            String userName = lines[1];

            System.out.println("server got string 1 :" + command);
            System.out.println("server got string 2 :" + userName);
//            Thread.sleep(0);

            String response = buildResponse(command, userName);
            bw.write(response);
            bw.newLine();
            bw.flush();

            br.close();
            bw.close();

            client.close();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }

    }

    private String buildResponse(String command, String userName)
    {
        Greetable handler = handlers.get(command);
        if (handler != null)
        {
            return handler.buildResponse(userName);
        }
        return "Hello, " + userName;
    }
}
