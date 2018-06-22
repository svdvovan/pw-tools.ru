import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by SretenskyVD on 22.06.2018.
 */

//cd C:\Program Files\Java\jdk1.7.0_79\bin
    //keytool -import -v -file S:\ProjectJava\pw-tools.ru\cert\technodacha\technodacha.crt -keystore S:\ProjectJava\pw-tools.ru\cert\echo-tools.ru\technodacha.crt.jks -storepass drowssap
public class TechnoDacha {
    public static void main(String[] args) throws IOException {
        System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/pw-tools.ru/cert/technodacha/technodacha.crt.jks");
        String Path = "https://www.technodacha.ru/catalog/sadovaya_tekhnika_1/gazonokosilki/filter/brand_ref-is-yii000032/apply/";


            Document doc1 = Jsoup.connect(Path).get();
            Elements links1 = doc1.getElementsByClass("snippet__image-wrapper");
            Elements Price = doc1.getElementsByClass("price__text");

            int y = 0;
            for (Element link1 : links1) {
                String addressUrl = (links1.get(y).select("a[class=link link_view_plain]").attr("abs:href"));
                Document doc2 = Jsoup.connect(addressUrl).get();

                Elements Description = doc2.getElementsByClass("text");
                Elements NameProduct = doc2.getElementsByClass("product__title");
                Elements Attribute = doc2.getElementsByClass("product__short-specs");
                Elements Unit = doc2.getElementsByClass("table__name");
                Elements Value = doc2.getElementsByClass("table__value");

                System.out.println();
                String DesText = Description.select("[itemprop=description]").text();
                System.out.println(NameProduct.text() +";"+ Price.get(y).text()+";"+DesText+ ";");

                int Attr=0;
                for (Element Units: Unit){

                    System.out.print("Характеристика|" + Unit.get(Attr).text()+ "|" +Value.get(Attr).text()+ "\n" );
                    Attr++;

                }



                y = y + 2;
            }


        }

    }


