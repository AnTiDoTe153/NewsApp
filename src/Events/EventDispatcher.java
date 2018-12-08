package Events;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EventDispatcher {
    private final EventListenerManager eventListener = new EventListenerManager();
    private final BlockingQueue<NewsEvent> eventsQueue = new LinkedBlockingQueue();
    private final Thread dispatchThread;


    public EventDispatcher(){
        this.dispatchThread = new Thread(this::dispatchLoop);
        this.dispatchThread.start();
    }

    public void register(NewsEventType eventType, NewsEventListener listener){
        this.eventListener.register(eventType, listener);
    }

    public void unregister(NewsEventType eventType, NewsEventListener listener){
        this.eventListener.unregister(eventType, listener);
    }

    public void publishEvent(NewsEvent event){
        if(event != null){
            eventsQueue.add(event);
        }
    }

    private void dispatch(NewsEventListener listener, NewsEvent event){
        listener.handleEvent(event);
    }

    private void dispatchLoop(){
        while(!Thread.currentThread().isInterrupted()){
            try{
                final NewsEvent event = eventsQueue.take();
                eventListener.getListenersForEvent(event.getType())
                        .forEach(listener -> dispatch(listener, event));
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }

        }
    }
}
