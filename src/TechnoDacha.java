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
       // System.setProperty("javax.net.ssl.trustStore", "F:/Projects/PwTools/cert/technodacha/technodacha.crt.jks");
        System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/pw-tools.ru/cert/technodacha/technodacha.crt.jks");
        String Path = "https://www.technodacha.ru/catalog/silovaya_produktsiya/generatory_i_elektrostantsii/filter/brand_ref-is-yii000018/apply/";


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


