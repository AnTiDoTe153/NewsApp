package Main;

import Server.Server;
import Users.Writer;

public class Writer1Thread implements Runnable {

    private Server server;

    public Writer1Thread(Server server){
        this.server = server;
    }

    @Override
    public void run() {
        Writer writer = new Writer("WRITER_ONE", server);
        writer.publishNews("Science", "Proximal Policy Optimization", "This is a reinforcement learning algorithm used in machine learning.");
        writer.publishNews("Crime", "How to spot a psychopath", "A series of tests to discover if someone is a psychopath.");
    }
}