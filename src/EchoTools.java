/**
 * Created by SretenskyVD on 21.06.2018.
 */
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EchoTools {
    public static void main(String[] args) throws IOException {
        System.setProperty("javax.net.ssl.trustStore", "S:/ProjectJava/pw-tools.ru/cert/echo-tools.ru/echo-tools.crt.jks");
        String Path = "https://echo-tools.ru/shop/pily-tsepnye/";

        Document doc1 = Jsoup.connect(Path).get();
        Elements links1 = doc1.getElementsByClass("newgood");

        int y = 0;
        for (Element link1 : links1) {
            String addressUrl = (links1.get(y).select("a[href]").attr("abs:href"));
            Document doc2 = Jsoup.connect(addressUrl).get();


        }



    }
}
