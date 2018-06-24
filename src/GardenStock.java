//cd C:\Program Files\Java\jdk1.7.0_79\bin
//keytool -import -v -file F:/Projects/PwTools/cert/technodacha/technodacha.crt -keystore F:/Projects/PwTools/cert/technodacha/technodacha.crt.jks -storepass drowssap


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;

public class GardenStock {
    public static void main(String[] args) throws IOException {
        //АГАТ
        String Path = "http://www.gardenstock.ru/catalog/motobloki-i-kultivatory/motobloki/?v%5b%5d=71&pfrom=0&pto=4290001&wfrom=0&wto=1000";
        Document doc1 = Jsoup.connect(Path).get();
        Elements links1 = doc1.getElementsByClass("product-list__item");
        int y = 0;
        for (Element link1 : links1) {
            System.out.println();
             String addressUrl = links1.get(y).select("a[href]").attr("abs:href");
            Document doc2 = Jsoup.connect(addressUrl).get();

            Elements Description = doc2.getElementsByClass("prodcontent");
            Elements NameProduct = doc2.getElementsByClass("content-wrap");
            Elements Price = doc2.getElementsByClass("prise");
            System.out.println(Description.select("p").text());
            System.out.println(NameProduct.select("h1").text());
            System.out.println(Price.text());


            Elements table = doc2.select("table");
            Elements row = table.select("tr");
            Iterator<Element> ite = table.select("td").iterator();

            for (Element rows : row) {
                System.out.print("Характеристика|");
                System.out.print(ite.next().text() + "|");
                System.out.println(ite.next().text() + " ");
            }


            y++;
        }
    }
}

