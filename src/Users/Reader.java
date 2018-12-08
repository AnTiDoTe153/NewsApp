package Users;

import Events.NewsEvent;
import Events.NewsEventListener;
import News.News;
import Server.Server;
import

public class Reader extends NewsEventListener {
    private Server server;

    public Reader(Server server){
        this.server = server;
    }

    public void subscribeToNews(String newsType){
        server.subscribeToNews(this, newsType);
    }


    @Override
    public void handleEvent(NewsEvent event) {
        News content = event.getContent();
        System.out.println(content);
    }
}
