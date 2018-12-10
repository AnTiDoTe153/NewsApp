package Server;

import Events.*;
import News.News;
import java.util.LinkedList;

public class Server {
    private static Server instance;
    private LinkedList<News> newsList;
    private Thread serverLoopThread;
    private final EventDispatcher eventDispatcher = new EventDispatcher();
    private final Object mutex = new Object();

    private Server(){
        newsList = new LinkedList<>();
        serverLoopThread = new Thread(this::serverLoop);
        serverLoopThread.start();
    }

    public static Server getInstance(){
        if(instance == null){
            instance = new Server();
        }
        return instance;
    }

    public void publishNews(News news, NewsEventListener listener){
        synchronized(mutex){
            newsList.addFirst(news);
        }
        ListenerData listenerData = new ListenerData(listener);
        listenerData.addFilter(item -> news.equals(item));

        eventDispatcher.register(NewsEventType.NEWS_READ, listenerData);

        NewsEvent event = new NewsEvent(NewsEventType.NEWS_APPEARED, news);
        eventDispatcher.publishEvent(event);
    }

    public void updateNews(News news){
        synchronized(mutex){
            newsList.stream().filter(news::equals).map(item -> news);
            NewsEvent event = new NewsEvent(NewsEventType.NEWS_CHANGED, news);
            eventDispatcher.publishEvent(event);
        }
    }

    public void deleteNews(News news){
        synchronized(mutex){
            newsList.removeIf(news::equals);
        }
        NewsEvent event = new NewsEvent(NewsEventType.NEWS_DELETED, news);
        eventDispatcher.publishEvent(event);
    }

    public void subscribeToNewsByType(NewsEventListener listener, String newsType){
        ListenerData listenerData = new ListenerData(listener);
        listenerData.addFilter(event -> event.getCategory().equals(newsType));

        eventDispatcher.register(NewsEventType.NEWS_CHANGED, listenerData);
        eventDispatcher.register(NewsEventType.NEWS_APPEARED, listenerData);
        eventDispatcher.register(NewsEventType.NEWS_DELETED, listenerData);
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
