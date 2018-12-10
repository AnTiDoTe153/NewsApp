package Main;

import Server.Server;
import Users.Reader;

public class Reader2Thread implements Runnable {

    private Server server;

    public Reader2Thread(Server server){
        this.server = server;
    }

    @Override
    public void run() {
        Reader reader = new Reader(server, "READER_TWO");
        reader.subscribeToNews("Science");
    }
}
