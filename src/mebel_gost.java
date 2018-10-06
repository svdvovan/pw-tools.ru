import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class mebel_gost {
    public static void main(String[] args) throws IOException {
        String Hostname = "http://mebelgrad.com/";
        String Path = "http://mebelgrad.com/category/myagkaya-mebel/";
        String CatalogName = "meb1";
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

         Document doc1 = Jsoup.connect(Path).get();
        Elements links1 = doc1.getElementsByClass("catalog-main-sec border-block");
        int y = 0;
        for (Element link1 : links1) {
            System.out.println();
            String addressUrl = (links1.get(y).select("a[class=img]").attr("abs:href"));
            System.out.println(addressUrl);


            Document doc2 = Jsoup.connect(addressUrl).get();

            String Catalog = doc2.getElementsByClass("breadcrumbs").select("span").text();
            System.out.println(Catalog);
            Elements links2 = doc2.getElementsByClass("catalog-itm border-block");

            int yy = 0;
            for (Element link2 : links2) {
                System.out.println();
                String addressUrl2 = (links2.get(yy).select("a[class=img]").attr("abs:href"));
                System.out.println(addressUrl2);

                Document doc3 = Jsoup.connect(addressUrl2).get();

                Elements NameProduct = doc3.getElementsByTag("h1");
                String Name = NameProduct.text();
                System.out.println(NameProduct.text());

                Elements Price = doc3.getElementsByClass("product-info__left").select("span[itemprop=price]");
                String MainPrice = Price.text();
                System.out.println(Price.text());

                Element Price2 = doc3.getElementsByTag("p").select("span[itemprop=price]").first();
                String MainPrice2 = Price2.text();
                System.out.println(Price2.text());

                String Description = doc3.getElementsByClass("position-left").text();
                System.out.println(Description);

                String Description2 = doc3.getElementsByClass("product-otherprop-row").text();
                System.out.println(Description2);

                String Description3 = doc3.getElementsByClass("product-variants").text();
                System.out.println(Description3);

                String Description4 = doc3.getElementsByClass("product-variants").text();
                System.out.println(Description4);

                String Description5 = doc3.getElementsByClass("product-description").select("div[itemprop=description]").text();
                System.out.println(Description4);


            //    String ID_product = doc3.getElementsByClass("addBskt").attr("data_id");
                String ID_product1 = doc3.getElementsByTag("p").select("span[class=addBskt]").attr("data_id");

                System.out.println(ID_product1);

                String ID_product2 = doc3.getElementsByClass("addBskt").attr("data_id");

                System.out.println(ID_product2);

                String Size = doc3.getElementsByClass("product-otherprop-row").select("div[id=PROP_SIZE]").text();
                System.out.println(Size);





                int rowCount = sheet.getLastRowNum();
                Row row = sheet.createRow(++rowCount);

                Elements pictures = doc3.getElementsByClass("product-photos").select("a");

                int z = 0;
                int y3 = 18;

                for (Element picture : pictures) {
                    System.out.println( pictures.get(z).select("a").attr("abs:href"));

                    String Foto = pictures.get(z).select("a").attr("abs:href");
                    File f = new File(Foto);
                    String FileName = f.getName();


                    Cell cell11 = row.createCell(y3);
                    cell11.setCellValue(FileName);
                    y3++;

                    z++;

                }
                Cell cell226 = row.createCell(1);
                cell226.setCellValue(ID_product1);

                Cell cell227 = row.createCell(0);
                cell227.setCellValue(ID_product2);

                Cell cell225 = row.createCell(2);
                cell225.setCellValue(Catalog);

                Cell cell1 = row.createCell(3);
                cell1.setCellValue(Name);

                Cell cell2 = row.createCell(4);
                cell2.setCellValue(MainPrice2);
//
//                Cell cell222 = row.createCell(4);
//                cell222.setCellValue(MainPrice2);


                Cell cell223 = row.createCell(6);
                cell223.setCellValue(Description + Description2 +Description5);


                Cell cell224 = row.createCell(5);
                cell224.setCellValue( Description3 +Description4);

                Cell cell228 = row.createCell(7);
                cell228.setCellValue(Size);

                yy++;
            }
            System.out.println();
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
