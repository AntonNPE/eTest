package com.tarasov.model.logic;

import com.tarasov.model.entity.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
//        test();
//            iterateOverElements();
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
        Document dc = Jsoup.connect("http://njnj.ru/golits/golits1-25_article.htm").get();   // 1-25
//        Document dc = Jsoup.connect("http://njnj.ru/golits/golits26-40_zero_article_exercise.htm").get();
//        dc.select("span.c2").forEach(Element::remove);
        dc.select("span.c2").remove();
        dc.select("br").remove();
//        dc.select("br").forEach(Element::remove);
        Element body = dc.body();
        String html = body.toString();
//        System.out.println(html);
        Pattern pat = Pattern.compile("<h2 (.+?) (?=</td>)");
        Matcher matcher = pat.matcher(html);
        int start = 0;
        int count = 0;
        String value="";
        while (matcher.find(start)) {
            value = html.substring(matcher.start(), matcher.end());
//            count++;
//            System.out.println(count + " - " + value);
            start = matcher.end();
        }
        String [] valuesArray = value.split("<h2(.+?)</h2>");
        for (String st : valuesArray){
            count++;
            System.out.println( count + "\t-\t" + st);
        }
//        System.out.println("the amount of the lines  is " + count);
    }

    public static void test() throws IOException {

        List<Article> al = new ArrayList<>();
        Document dc = Jsoup.connect("http://njnj.ru/golits/golits1-25_article.htm").get();
        Elements tdTagElements = dc.getElementsByTag("td");
//        for (Element el : tdTagElements) {
//            System.out.println(el.ownText());
//            System.out.println("-------");
//        }
        System.out.println(tdTagElements.get(1).ownText());
        String [] st = tdTagElements.get(1).ownText().split("...");

    }


    public static void iterateOverElements () throws IOException{

        Document dc = Jsoup.connect("http://njnj.ru/golits/golits26-40_zero_article_exercise.htm").get();
        Elements elements = dc.getAllElements();
        for (Element element : elements){

        }

    }
}