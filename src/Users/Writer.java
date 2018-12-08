package Users;
import Server.Server;
import News.News;

public class Writer {
    private final String name;
    private final Server server;

    public Writer(String name, Server server){
        this.name = name;
        this.server = server;
    }

    public void publishNews(News news){

    }
}
