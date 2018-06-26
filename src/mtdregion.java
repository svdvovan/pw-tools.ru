import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by SretenskyVD on 26.06.2018.
 */
//  System.setProperty("javax.net.ssl.trustStore", "F:/Projects/PwTools/cert/karex/karex.crt.jks");
//keytool -import -v -file S:/ProjectJava/pw-tools.ru/cert/mtd/mtd.crt -keystore S:/ProjectJava/pw-tools.ru/cert/mtd/mtd.crt.jks -storepass drowssap
//cd C:\Program Files\Java\jdk1.7.0_79\bin
public class mtdregion {
    public static void main(String[] args) throws IOException {
        System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/pw-tools.ru/cert/mtd/mtd.crt.jks");
        String Path = "https://mtd-region.ru/products/trimmer-elektricheskii-mtd-eb-1000-b";
        Document doc1 = Jsoup.connect(Path).get();

        Elements NameProduct = doc1.getElementsByClass("details-title page-title");
        String  Price1 = doc1.select("meta[itemprop=price]").attr("content");
        Elements  Description = doc1.getElementsByClass("tab-content details-tabs-deacription clear").select("p");
        System.out.println(NameProduct.text()+ " \n" +Price1 + " \n" +Description.text()+ "\n");
//        Elements table = doc1.getElementsByTag ("table");
//        Elements row = table.select("tr");
//        Iterator<Element> ite = table.select("td").iterator();
//
//        for (Element rows : row) {
//            System.out.print("Характеристика|");
//            System.out.print(ite.next().text() + "|");
//            System.out.println(ite.next().text() + " ");
//        }
        Elements Unit = doc1.getElementsByClass("properties-item-name");
        Elements Value = doc1.getElementsByClass("properties-item-value");
        Elements MainImage = doc1.getElementsByClass("gallery-picture-link link-text-decoration-none");

        int Attr=0;
        for (Element Units: Unit) {

            System.out.print("Характеристика|" + Unit.get(Attr).text() + "|" + Value.get(Attr).text() + "\n");
            Attr++;

        }
        String FileName = MainImage.select("a[href]").attr("abs:href");
        File j = new File(FileName);
        System.out.print("data/image/auto/5/" + j.getName());


    }



}
