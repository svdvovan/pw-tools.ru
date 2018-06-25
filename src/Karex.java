import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by SretenskyVD on 25.06.2018.
 */
//  System.setProperty("javax.net.ssl.trustStore", "F:/Projects/PwTools/cert/karex/karex.crt.jks");
//keytool -import -v -file S:/ProjectJava/pw-tools.ru/cert/karex/karex.crt -keystore S:/ProjectJava/pw-tools.ru/cert/karex/karex.crt.jks -storepass drowssap
    //cd C:\Program Files\Java\jdk1.7.0_79\bin
public class Karex {
    public static void main(String[] args) throws IOException {
        System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/pw-tools.ru/cert/karex/karex.crt.jks");
        String Path = "https://karex.ru/catalog/products/gazonokosilki/?SHOWALL_1=1";
        Document doc1 = Jsoup.connect(Path).get();
        Elements links1 = doc1.getElementsByClass("visual");




        int y = 0;
        for (Element link1 : links1) {
            String addressUrl = links1.select("a[href]").attr("abs:href");
            Document doc2 = Jsoup.connect(addressUrl).get();



            y++;
        }

    }
}
