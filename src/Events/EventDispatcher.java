package Events;

import News.News;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class EventDispatcher {
    private final EventListenerManager eventListener = new EventListenerManager();
    private final BlockingQueue<NewsEvent> eventsQueue = new LinkedBlockingQueue();
    private final Thread dispatchThread;


    public EventDispatcher(){
        this.dispatchThread = new Thread(this::dispatchLoop);
        this.dispatchThread.start();
    }

    public void register(NewsEventType eventType, ListenerData listenerData){
        this.eventListener.register(eventType, listenerData);
    }

    public void unregister(NewsEventType eventType, ListenerData listenerData){
        this.eventListener.unregister(eventType, listenerData);
    }

    public void publishEvent(NewsEvent event){
        if(event != null){
            eventsQueue.add(event);
        }
    }

    private void dispatch(ListenerData listenerData, NewsEvent event){
        NewsEventListener listener = listenerData.getListener();
        News eventContent = event.getContent();
        Stream<Predicate<News>> filters = listenerData.getFilters();
        if(filters.allMatch(filter -> filter.test(eventContent))){
            listener.handleEvent(event);
        }
    }

    private void dispatchLoop(){
        while(!Thread.currentThread().isInterrupted()){
            try{
                final NewsEvent event = eventsQueue.take();
                eventListener.getListenersForEvent(event.getType())
                        .forEach(listenerData -> dispatch(listenerData, event));
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }

        }
    }
}
