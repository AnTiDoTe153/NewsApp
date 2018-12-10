package Users;

import Events.NewsEvent;
import Events.NewsEventListener;
import News.News;
import Server.Server;

public class Reader extends NewsEventListener {
    private Server server;
    private String name;

    public Reader(Server server, String name){
        this.server = server;
        this.name = name;
    }

    public void subscribeToNews(String newsType){
        server.subscribeToNewsByType(this, newsType);
    }


    @Override
    public void handleEvent(NewsEvent event) {
        News content = event.getContent();
        String evenDescription = event.getEventDescription();

        System.out.println("[" + name + "] " + evenDescription + "\n" + content);
    }
}
