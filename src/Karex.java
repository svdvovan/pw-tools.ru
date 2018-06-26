import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by SretenskyVD on 25.06.2018.
 */
//  System.setProperty("javax.net.ssl.trustStore", "F:/Projects/PwTools/cert/karex/karex.crt.jks");
//keytool -import -v -file S:/ProjectJava/pw-tools.ru/cert/karex/karex.crt -keystore S:/ProjectJava/pw-tools.ru/cert/karex/karex.crt.jks -storepass drowssap
    //cd C:\Program Files\Java\jdk1.7.0_79\bin
public class Karex {
    public static void main(String[] args) throws IOException {
        System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/pw-tools.ru/cert/karex/karex.crt.jks");
        String Path = "https://karex.ru/catalog/products/trimmery_i_motokosy/sgt_600/";
        Document doc1 = Jsoup.connect(Path).get();
     //   Elements links1 = doc1.getElementsByClass("catalog0");


        Elements NameProduct = doc1.getElementsByTag("h1");
        Elements Description = doc1.getElementsByClass("tab").select("p");
       String Price1 = doc1.getElementsByClass("price-block").text();




        System.out.println(NameProduct.text() + "\n"+Price1 + "\n" + Description.text());
        System.out.println();

        Element table = doc1.getElementsByTag ("table").get(2);
        Elements row = table.select("tr");
        Iterator<Element> ite = table.select("td").iterator();

        for (Element rows : row) {
            System.out.print("Характеристика|");
            System.out.print(ite.next().text() + "|");
            System.out.println(ite.next().text() + " ");
        }

        Elements MainImage = doc1.getElementsByClass("good-holder-picture");
        Elements Image = doc1.getElementsByTag("a");

        System.out.println();
        String FileName = MainImage.select("a[href]").attr("abs:href");
        File j = new File(FileName);
        System.out.print("data/image/auto/4/" + j.getName());

        System.out.println();

        try {
            int Img=0;
            for (Element Images : Image) {
                String Image66 = doc1.getElementsByClass("good-list").select("a[href]").get(Img).attr("abs:href");
                File f = new File(Image66);
                System.out.print("data/image/auto/4/" + f.getName() + ",");
                Img++;
            }
        }catch (java.lang.IndexOutOfBoundsException e){

        }



    }
}
