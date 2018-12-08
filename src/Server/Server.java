package Server;

import Events.EventDispatcher;
import Events.NewsEvent;
import Events.NewsEventListener;
import Events.NewsEventType;
import News.News;

import java.util.LinkedList;

public class Server {
    private static Server instance;
    private LinkedList<News> newsList;
    private Thread serverLoopThread;
    private final EventDispatcher eventDispatcher = new EventDispatcher();
    private final Object mutex = new Object();

    private Server(){
        serverLoopThread = new Thread(this::serverLoop);
        serverLoopThread.start();
    }

    public static Server getInstance(){
        if(instance == null){
            instance = new Server();
        }
        return instance;
    }


    private void publishNews(News news, NewsEventListener listener){
        synchronized(mutex){
            newsList.addFirst(news);
        }
        eventDispatcher.register(NewsEventType.NEWS_READ, listener);
        NewsEvent event = new NewsEvent(NewsEventType.NEWS_APPEARED, news);

        eventDispatcher.publishEvent(event);
    }

    private void serverLoop(){
        while(!Thread.currentThread().isInterrupted()){
            try{

            }catch(Exception e){
                Thread.currentThread().interrupt();
                break;
            }

        }
    }
}
