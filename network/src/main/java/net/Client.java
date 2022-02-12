package net;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

public class Client {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 8; i++) {
            SimpleClient sc = new SimpleClient(i);
            sc.start();
        }
    }

}

class SimpleClient extends Thread
{
    private final static String[] COMMAND = { "HELLO", "MORNING", "DAY", "EVENING"};
    private int cmdNumber;

    public SimpleClient (int cmdNumber)
    {
        this.cmdNumber = cmdNumber;
    }
    @Override
    public void run()
    {
        try {
//            System.out.println("Started at: " + LocalDateTime.now());
            Socket socket = new Socket("127.0.0.1", 25225);

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String command = COMMAND[cmdNumber % COMMAND.length];
            String sb = command + " " + "Maxon";
            bw.write(sb);
            bw.newLine();
            bw.flush();

            String answer = br.readLine();
            System.out.println("client got string: " + answer);

            br.close();
            bw.close();
//            System.out.println("Finished at: " + LocalDateTime.now());
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}