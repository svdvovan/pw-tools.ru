
//cd C:\Program Files\Java\jdk1.7.0_79\bin
//keytool -import -v -file F:/Projects/PwTools/cert/akrus/akrus.crt -keystore F:/Projects/PwTools/cert/akrus/akrus.crt.jks -storepass drowssap

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class akRus {
    public static void main(String[] args) throws IOException {
      //  System.setProperty("javax.net.ssl.trustStore", "F:/Projects/PwTools/cert/akrus/akrus.crt.jks");
        //keytool -import -v -file S:/ProjectJava/pw-tools.ru/cert/akrus/akrus.crt -keystore S:/ProjectJava/pw-tools.ru/cert/akrus/akrus.crt.jks -storepass drowssap
        System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/pw-tools.ru/cert/akrus/akrus.crt.jks");
        String Path = "https://ak-rus.ru/gazonnaya-tehnika/gazonokosilki/benzinovye-gazonokosilki/?limit=100";

        Document doc1 = Jsoup.connect(Path).get();
        Elements links1 = doc1.getElementsByClass("name").select("h3");
      //  Elements Price = doc1.getElementsByClass("price");
   //     Elements NameProduct = doc1.getElementsByClass("name");


    //    System.out.println(links1);

        int y = 0;
        for (Element link1 : links1) {
            String addressUrl = links1.get(y).select("a[href]").attr("abs:href");

            Document doc2 = Jsoup.connect(addressUrl).get();

            Element Description = doc2.getElementsByClass("tab-content").select("p").first();
            Elements Image = doc2.getElementsByClass("colorbox thumbcols-3");
            Elements Price = doc2.getElementsByClass("price-new");
            Elements NameProduct = doc2.getElementsByTag("h1");
            String SuperImage = doc2.getElementsByClass("image").select("a[href]").attr("abs:href");

            File d = new File(SuperImage);


            System.out.println();
            System.out.println(NameProduct.text() + " \n" +Price.text() + " \n" + Description.text()) ;

            System.out.println();
         //   Element table = doc2.select("table").get(1);
            Element table = doc2.getElementsByTag ("table").get(3);
            Elements row = table.select("tr");
            Iterator<Element> ite = table.select("td").iterator();

            for (Element rows : row) {
                System.out.print("Характеристика|");
                System.out.print(ite.next().text() + "|");
                System.out.println(ite.next().text() + " ");
            }
            System.out.print("data/image/auto/2/" + d.getName() + ",");
            int Img=0;
            for (Element Images : Image) {
                String FileName = Image.get(Img).select("a[href]").attr("abs:href");
                File f = new File(FileName);
                System.out.print("data/image/auto/2/" + f.getName() + ",");
                Img++;
            }


  //          String FileName = Image.attr("src");
//            String FileName = Image.select("a[href]").attr("abs:href");
//            System.out.println(FileName);

            System.out.println();
            y++;
        }


    }
}
