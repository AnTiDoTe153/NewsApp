package News;

import java.util.Date;

public class News {
    private String domain;
    private Date publicationDate;
    private Date lastChangesDate;
    private String author;
    private String title;
    private String content;

    public News(String domain, String author, String title, String content){
        this.domain = domain;
        this.author = author;
        this.title = title;
        this.content = content;

        this.publicationDate = new Date();
        this.lastChangesDate = new Date();
    }
}
