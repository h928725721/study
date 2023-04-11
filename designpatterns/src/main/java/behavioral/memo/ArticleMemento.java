package behavioral.memo;

import lombok.Data;

import java.util.Date;

/**
 * 备忘类，必须和原始类保持一致
 */
@Data
public class ArticleMemento {
    private String title;
    private String content;
    private Date createTime;

    public ArticleMemento(String title, String content, Date createTime) {
        this.title = title;
        this.content = content;
        this.createTime = createTime;
    }

}
