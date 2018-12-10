package Main;

import Server.Server;
import Users.Reader;

public class Reader1Thread implements Runnable {

    private Server server;

    public Reader1Thread(Server server){
        this.server = server;
    }

    @Override
    public void run() {
        Reader reader = new Reader(server, "READER_ONE");
        reader.subscribeToNews("Science");
        reader.subscribeToNews("Crime");
    }
}
