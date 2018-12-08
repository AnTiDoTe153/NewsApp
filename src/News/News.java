package News;

import java.util.Date;

public class News {
    private final String category;
    private final String author;
    private final String title;
    private final Date publishDate;
    private String content;
    private Date lastChangeDate;

    public News(String category, String author, String title, String content){
        this.category = category;
        this.author = author;
        this.title = title;
        this.content = content;

        this.publishDate = new Date();
        this.lastChangeDate = new Date();
    }

    public String getCategory() {
        return category;
    }

    private void updateLastChangeDate(){
        this.lastChangeDate = new Date();
    }

    public void changeContent(String content){
        this.content = content;
        this.updateLastChangeDate();
    }

    public String toString(){
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("[" + this.category + "] ").append(this.title);
        messageBuilder.append("\n");
        messageBuilder.append("by " + this.author);
        messageBuilder.append("\n");
        messageBuilder.append(this.content);

        return messageBuilder.toString();
    }
}
