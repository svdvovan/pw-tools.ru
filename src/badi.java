import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class badi {
    public static void main(String[] args) throws IOException {
        String Hostname = "http://xn----8sbane5be5a8a.xn--p1ai/";
        String Path = "http://xn----8sbane5be5a8a.xn--p1ai/indiyskie-dzheneriki.html";
        String CatalogName = "dzheneriki";
        Workbook wb = new HSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet1 = wb.createSheet(CatalogName);
        FileOutputStream fileOut = new FileOutputStream("book_" + CatalogName + ".xls");


        try {
            wb.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();


        }
        Sheet sheet = wb.getSheetAt(0);


        //   http://xn----8sbane5be5a8a.xn--p1ai/indiyskie-dzheneriki/sildenafil-100-mg.html
        Document doc1 = Jsoup.connect(Path).get();
        Elements links1 = doc1.getElementsByClass("block-good-name");
        int y = 0;
        for (Element link1 : links1) {
            System.out.println();
            String addressUrl = links1.get(y).select("a[href]").attr("abs:href");
            System.out.println(addressUrl);
            Document doc2 = Jsoup.connect(addressUrl).get();
//solid="#000" border="4px"             <table solid="#000" border="4px"


            String Description = doc2.getElementsByClass("good_desc").select("div").first().ownText();
            String Doza = doc2.getElementsByClass("good_desc").select("b").text();
            String Kod = doc2.getElementsByClass("good_art").text();
            System.out.println(Doza);
            System.out.println(Description);
            Elements NameProduct = doc2.getElementsByClass("good_name");
            String Name = NameProduct.text();
            System.out.println(NameProduct.text());
            Elements Price = doc2.getElementsByClass("price_i");
            String MainPrice = Price.text();
            System.out.println(Price.text());

            System.out.println(Kod);
            Elements Image = doc2.getElementsByClass("main_img");
            Elements Table_price = doc2.getElementsByClass("d_price_list");
            System.out.println(Table_price);

            String Categorys = doc2.getElementsByClass("brcs").select("a").first().nextElementSibling().text();


            int rowCount = sheet.getLastRowNum();
            Row row = sheet.createRow(++rowCount);

            Cell cell = row.createCell(1);
            cell.setCellValue(Kod);

            Cell cell13 = row.createCell(0);
            cell13.setCellValue(Categorys);

            Cell cell1 = row.createCell(2);
            cell1.setCellValue(Name);

            Cell cell2 = row.createCell(3);
            cell2.setCellValue(MainPrice);

            Cell cell3 = row.createCell(4);
            cell3.setCellValue(Doza);

            Cell cell31 = row.createCell(5);
            cell31.setCellValue(Description + "<br />" + Table_price);


            int Img = 0;
            for (Element Images : Image) {
                String FileName = Image.get(Img).select("a").attr("href");

                System.out.print(Hostname + FileName);
                Cell cell11 = row.createCell(6);
                cell11.setCellValue(Hostname + FileName);
                Img++;
            }
//            System.out.println();
            y++;
            try {
                FileOutputStream fileOut1 = new FileOutputStream("book_" + CatalogName + ".xls");
                wb.write(fileOut1);
                fileOut1.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
