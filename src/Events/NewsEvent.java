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

    public String getEventDescription(){
        switch(type){
            case NEWS_APPEARED:
                return "A news has appeared";
            case NEWS_DELETED:
                return "A news has been removed";
            case NEWS_CHANGED:
                return "A news has been updated";
            case NEWS_READ:
                return "The news has been read";
            default:
                return "Error";
        }
    }

    public NewsEventType getType() {
        return type;
    }
}
