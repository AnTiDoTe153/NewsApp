package Events;

import java.util.*;
import java.util.stream.Stream;

public class EventListenerManager {

    private final Object mutex = new Object();
    private Map<NewsEventType, List<NewsEventListener>> listenerMap = new HashMap<>();

    public void register(NewsEventType eventType, NewsEventListener listener){
        synchronized(mutex){
            listenerMap.computeIfAbsent(eventType, elem -> new ArrayList<>()).add(listener);
        }

    }

    public void unregister(NewsEventType eventType, NewsEventListener listener){
        synchronized (mutex){
            if(mapContainsListener(eventType, listener)){
                listenerMap.computeIfPresent(eventType, (type,list) -> {
                    list.remove(listener);
                    return list.isEmpty()?null:list;
                });
            }
        }
    }

    public Stream<NewsEventListener> getListenersForEvent(NewsEventType type){
        return listenerMap.getOrDefault(type, Collections.emptyList()).stream();
    }

    private boolean mapContainsListener(NewsEventType type, NewsEventListener listener){
        return listenerMap.getOrDefault(type, Collections.emptyList()).contains(listener);
    }

}
