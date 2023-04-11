package behavioral.memo;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleText {
    private String title;
    private String content;
    private Date createTime;

    public ArticleText(String title, String content, Date createTime) {
        this.title = title;
        this.content = content;
        this.createTime = createTime;
    }

    public ArticleMemento saveToMemento(){
        ArticleMemento articleMemento = new ArticleMemento(this.title,this.content,this.createTime);
        return articleMemento;
    }

    public void getArticleFromMemento(ArticleMemento articleMemento){
        this.title = articleMemento.getTitle();
        this.content = articleMemento.getContent();
        this.createTime = articleMemento.getCreateTime();
    }
}
