package Main;

import Server.Server;

public class Main {
    public static void main(String[] args) {
            Server server = Server.getInstance();

            Reader1Thread thread1 = new Reader1Thread(server);
            Reader2Thread thread2 = new Reader2Thread(server);
            Writer1Thread thread3 = new Writer1Thread(server);


            new Thread(thread1).start();
            new Thread(thread2).start();
            new Thread(thread3).start();


    }
}
