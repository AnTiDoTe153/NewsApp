package Users;
import Events.NewsEvent;
import Events.NewsEventListener;
import Server.Server;
import News.News;

public class Writer extends NewsEventListener {
    private final String name;
    private final Server server;

    public Writer(String name, Server server){
        this.name = name;
        this.server = server;
    }

    public void publishNews(String category, String title, String content){
        News news = new News(category, name, title, content);
        server.publishNews(news, this);
    }

    @Override
    public void handleEvent(NewsEvent event) {
        News content = event.getContent();
        System.out.println("[" + name + "] " + content);
    }
}
