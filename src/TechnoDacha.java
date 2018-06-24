import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by SretenskyVD on 22.06.2018.
 */

//cd C:\Program Files\Java\jdk1.7.0_79\bin
    //keytool -import -v -file F:/Projects/PwTools/cert/technodacha/technodacha.crt -keystore F:/Projects/PwTools/cert/technodacha/technodacha.crt.jks -storepass drowssap
public class TechnoDacha {
    public static void main(String[] args) throws IOException {
        System.setProperty("javax.net.ssl.trustStore", "F:/Projects/PwTools/cert/technodacha/technodacha.crt.jks");
        String Path = "https://www.technodacha.ru/catalog/sadovaya_tekhnika_1/gazonokosilki/filter/brand_ref-is-yii000004/apply/?utm_medium=cpc&utm_source=yandex&utm_campaign=1jam_gazonokosilki-celevye-poisk-Msk%7C34897899&utm_term=%D0%B3%D0%B0%D0%B7%D0%BE%D0%BD%D0%BE%D0%BA%D0%BE%D1%81%D0%B8%D0%BB%D0%BA%D0%B0+%D0%B0%D0%BB%D0%BA%D0%BE&utm_content=13363561615_%7Ccid%7C34897899%7Cgid%7C3345001475%7Caid%7C5782457529%7Cadp%7Cno%7Cpos%7Cother1%7Csrc%7Csearch_none%7Cdvc%7Cdesktop%7Cdop_0&_openstat=ZGlyZWN0LnlhbmRleC5ydTszNDg5Nzg5OTs1NzgyNDU3NTI5O3lhbmRleC5ydTpndWFyYW50ZWU&yclid=3522832024675819584&PAGEN_1=2";

            Document doc1 = Jsoup.connect(Path).get();
            Elements links1 = doc1.getElementsByClass("snippet__image-wrapper");
            Elements Price = doc1.getElementsByClass("price__text");
        int id = 6144;
        int Model = 7037;
            int y = 0;
            String Manuf = "STIGA";
            for (Element link1 : links1) {
                String addressUrl = (links1.get(y).select("a[class=link link_view_plain]").attr("abs:href"));
                Document doc2 = Jsoup.connect(addressUrl).get();

                Elements Description = doc2.getElementsByClass("text");
                Elements NameProduct = doc2.getElementsByClass("product__title");
                Elements Attribute = doc2.getElementsByClass("product__short-specs");
                Elements Unit = doc2.getElementsByClass("table__name");
                Elements Value = doc2.getElementsByClass("table__value");
                Elements Image = doc2.getElementsByClass("slider__image");

                String MainImage = Image.attr("src");
                File r = new File(MainImage);
                String FistImage = "data/image/auto/1/" + r.getName();


                System.out.println();
                String DesText = Description.select("[itemprop=description]").text();
                System.out.println(NameProduct.text() +"\n"+ Price.get(y).text()+"\n"+DesText+ "\n");
       //         System.out.print("Газонокосилки\n" +
      //                  "Газонокосилки|Газонокосилки бензиновые" + ";" + id + ";"+NameProduct.text() +";" + Model+ ";;;;;;;"+Manuf +";;;" + Price.get(y).text()+ ";0;1000;6;0;0;0;0;;;;"+DesText+ ";;"+FistImage+";0;1;;;; \n  =СЦЕПИТЬ( " );
            //    System.out.print(";");
                int Attr=0;
                for (Element Units: Unit){

                   System.out.print("Характеристика|" + Unit.get(Attr).text()+ "|" +Value.get(Attr).text()+ "\n" );
      //              System.out.print(" \"Характеристика|" + Unit.get(Attr).text()+ "|" +Value.get(Attr).text() + " \" &СИМВОЛ(10)& " + "\n" );
                    Attr++;

                }
     //          System.out.print("\");");
                int Img=0;
                for (Element Images: Image){
                    String FileName = Image.get(Img).attr("src");
                    File f = new File(FileName);
    //                System.out.print(Image.get(Img).attr("src")+ ";");
                    System.out.print("data/image/auto/1/" + f.getName() + ",");
                    Img++;
                }
                System.out.println();

//data/image/auto/1
                y = y + 2;
                id++;
                Model++;
            }



        }

    }


