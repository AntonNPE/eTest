package com.tarasov.model.logic;

import com.tarasov.model.entity.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArticleParser {

    public static void main(String[] args) throws IOException {

//        articleParser();
        textParser();
    }

    public static void articleParser() throws IOException {

        List<Article> al = new ArrayList<>();
        Document dc = Jsoup.connect("http://gdzometr.ru/book907resh1").get();

//            Elements h2Elements = dc.getElementsByTag("h2"); // get the  condition exercises list
        Elements h2Elements = dc.getElementsByTag("p");
        h2Elements.forEach(h2Element -> {

//            Element urlElement = h2Element.child(0);
//                String url = urlElement.attr("href");
            String text = h2Element.ownText();

            al.add(new Article(text));
        });
        al.forEach(System.out::println);
    }

    public static void textParser() throws IOException {

        List<Article> al = new ArrayList<>();
        Document dc = Jsoup.connect("http://njnj.ru/golits/golits1-25_article.htm").get();
        Element elBody = dc.body();
        String html = elBody.toString();

        Pattern pat = Pattern.compile("(?i)</h2>(.+?)<br>");
        Matcher matcher = pat.matcher(html);
        int start = 0;
        int count = 0;
        while (matcher.find(start)) {
            String value = html.substring(matcher.start(), matcher.end());
            System.out.println(value);
            start = matcher.end();
            count++;
        }
        System.out.println(count);
    }
}