package Events;
import News.News;

public abstract class NewsEvent implements Event{
    private String eventType;
    private News content;

    public NewsEvent(String eventType, News content){
        this.eventType = eventType;
        this.content = content;
    }
}
