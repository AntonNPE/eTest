package com.tarasov.model.logic;

import com.tarasov.model.entity.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArticleParser {

    public static void main(String[] args) throws IOException {

        articleParser();
    }

        public static void articleParser() throws IOException {

            List<Article> al = new ArrayList<>();
            Document dc = Jsoup.connect("http://njnj.ru/golits/golits1-25_article.htm").get();

            Elements h2Elements = dc.getElementsByAttributeValue("id","a1");
            h2Elements.forEach(h2Element  -> {

                Element urlElement = h2Element.child(1);
                String url = urlElement.attr("href");
//                String text = urlElement.child(0).text();

                al.add(new Article(url));
            });
            al.forEach(System.out::println);
        }
}
