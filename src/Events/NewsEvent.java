package Events;

import News.News;

public class NewsEvent {
    private final News content;
    private final NewsEventType type;

    public NewsEvent(NewsEventType type, News content){
        this.content = content;
        this.type = type;
    }

    public News getContent(){
        return this.content;
    }

    public NewsEventType getType() {
        return type;
    }
}
