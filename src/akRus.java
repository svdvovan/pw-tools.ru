
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
        System.setProperty("javax.net.ssl.trustStore", "F:/Projects/PwTools/cert/akrus/akrus.crt.jks");
        String Path = "https://ak-rus.ru/tehnika-dlya-sada-i-doma/cepnye-pily/pily-benzinovye/";

        Document doc1 = Jsoup.connect(Path).get();
        Elements links1 = doc1.getElementsByClass("name");
        Elements Price = doc1.getElementsByClass("price");
        Elements NameProduct = doc1.getElementsByClass("name");




        int y = 0;
        for (Element link1 : links1) {
            String addressUrl = links1.get(y).select("a[href]").attr("abs:href");

            Document doc2 = Jsoup.connect(addressUrl).get();

            Element Description = doc2.getElementsByClass("tab-content").select("p").first();
    //        Elements Attribute = doc2.getElementsByClass("attribute");
//            Elements Unit = doc2.getElementsByClass("table__name");
//            Elements Value = doc2.getElementsByClass("table__value");
            Elements Image = doc2.getElementsByClass("colorbox thumbcols-3");

            System.out.println();
            System.out.println(NameProduct.get(y).text() + " \n" +Price.get(y).text() + " \n" + Description.text()) ;

            System.out.println();
            Element table = doc2.select("table").get(1);
            Elements row = table.select("tr");
            Iterator<Element> ite = table.select("td").iterator();

            for (Element rows : row) {
                System.out.print("Характеристика|");
                System.out.print(ite.next().text() + "|");
                System.out.println(ite.next().text() + " ");
            }
            int Img=0;
            for (Element Images : Image) {
                String FileName = Image.select("a[href]").attr("abs:href");
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
