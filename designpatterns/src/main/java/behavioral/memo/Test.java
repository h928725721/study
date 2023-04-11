package behavioral.memo;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        ArticleCaretaker articleCaretaker = new ArticleCaretaker();

        ArticleText articleText = new ArticleText("标题1","内容1",new Date());
        ArticleMemento articleMemento = articleText.saveToMemento();
        //备忘1次
        articleCaretaker.setArticle(articleMemento);

        articleText = new ArticleText("标题2","内容2",new Date());
        System.out.println(String.format("修改后的标题为【%s】，内容为【%s】",articleText.getTitle(),articleText.getContent()));

        articleText.getArticleFromMemento(articleCaretaker.getArticle(0));
        System.out.println(String.format("还原后的标题为【%s】，内容为【%s】",articleText.getTitle(),articleText.getContent()));
    }
}
