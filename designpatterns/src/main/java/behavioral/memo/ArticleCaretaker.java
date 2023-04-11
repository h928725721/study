package behavioral.memo;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理历史快照信息
 */
public class ArticleCaretaker {
    private final List<ArticleMemento> list = new ArrayList<>();

    public ArticleMemento getArticle(int index){
        return list.get(index);
    }

    public void setArticle(ArticleMemento articleMemento){
        list.add(articleMemento);
    }
}
