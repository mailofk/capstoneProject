package com.capstone.capstoneProject.service;

import com.capstone.capstoneProject.domain.Category;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JsoupService {

    private final CategoryService categoryService;
    private final ProductService productService;

    //category들을 미리 database에 가져다 놓기 위한 부분
    //분류마다 태그 클래스 이름이 다르므로, 각각 다르게 설정해 줘야함
    public void useSelenium() throws IOException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mailo\\Desktop\\capstoneProject\\capstoneProject\\chromedriver.exe");

        // WebDriver 객체 생성 (Chrome 브라우저 실행)
        WebDriver driver = new ChromeDriver();


        driver.get("https://www.danawa.com/");

        // 마우스를 가져다 댈 대상 요소 찾기
//        WebElement hoverElement = driver.findElement(By.cssSelector("ul.category__list li.category__list__row")); // CSS 선택자에 맞게 수정
        WebElement hoverElement = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div[2]/div[1]/div[1]/ul/li[4]"));
        WebElement large = hoverElement.findElements(By.cssSelector("a.category__list__btn")).get(0);

        // Actions 객체 생성
        Actions actions = new Actions(driver);

        // 요소에 마우스를 오버하는 동작 수행
        actions.moveToElement(hoverElement).perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 최대 10초 대기
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#categoryHoverLayer12")));


        // 마우스 오버 후 나타나는 요소 찾기 (예: 나타나는 메뉴 항목들)
        List<WebElement> mid = driver.findElements(By.cssSelector("div#categoryHoverLayer12 ul.category__depth > li.depth1"));

        // 나타나는 요소들의 텍스트 출력
        for (WebElement webElement : mid) {
            String midText = webElement.getText();
            actions.moveToElement(webElement).perform();

//            List<WebElement> smlElements = webElement.findElements(By.cssSelector("div.category__3depth a.category__depth__btn span.category__depth__txt"));
            List<WebElement> smlElements = webElement.findElements(By.cssSelector("div.category__3depth a.category__depth__btn"));

            for (WebElement smlElement : smlElements) {
                String allText = smlElement.getText();
                String innerText = "";
                try {
                    innerText = smlElement.findElement(By.cssSelector("span.icom")).getText() + "\n";
                } catch (Exception e) {

                }

                String smlText = allText.replaceFirst(innerText, "");
                String largeText = large.getText();

                categoryService.saveCategory(largeText, midText, smlText);


                //추가된 부분임
                String url = smlElement.getAttribute("href");

                Document doc = Jsoup.connect(url)
                        .get();

                Element titleTag = doc.selectFirst("title");
                String title = titleTag.text().split(" ")[0];

//                Category category = categoryService.findOneCategory(title);

                Elements mainProduct = doc.select("div.main_prodlist ul.product_list li.prod_item");

                for (Element element : mainProduct) {
                    Element name = element.selectFirst(".prod_name a");
                    String prodName = name.text();
                    String prodLink = name.attr("href");


//                    productService.saveProduct(prodName, prodLink, title); // 추후 title -> category 수정
                }
            }
        }


        driver.quit();

    }

    public void getProducts(String url) throws IOException {
        Document doc = Jsoup.connect(url)
                .get();

        Element titleTag = doc.selectFirst("title");
        String title = titleTag.text().split(" ")[0];

//        Category category = categoryService.findOneCategory(title);

        Elements mainProduct = doc.select("div.main_prodlist ul.product_list li.prod_item");

        for (Element element : mainProduct) {
            Element name = element.selectFirst("p.prod_name a");
            String prodName = name.text();
            String prodLink = name.attr("href");

            Element spec = element.selectFirst("div.spec_list");

            //cpu 가져오기
            Element cpuLabel = spec.selectFirst("span.cm_mark:contains(CPU)");
            String cpu = cpuLabel.nextElementSibling().text();
            if (cpu.contains("/")) {
                cpu = cpuLabel.nextSibling().toString().trim();
            }

            //ram 가져오기
            Element ramLabel = spec.selectFirst("span.cm_mark:contains(램)");
            String ram = ramLabel.nextElementSibling().text().trim();
            if (ram.contains("/")) {
                ram = ramLabel.nextSibling().toString().trim();
            } else if (ram.contains("램 용량")) { //애플 제품 RAM 없는 경우를 생각
                ram = "APPLE RAM";
            }


            Element capacityLabel = spec.selectFirst("span.cm_mark:contains(저장장치)");
            String capacity = capacityLabel.nextElementSibling().nextElementSibling().nextElementSibling().text();

            Element weightLabel = spec.selectFirst("a:contains(무게)");
            String weight = weightLabel.nextElementSibling().text();


//            System.out.println(cpu + ", " + ram + ", " + capacity + ", " + weight);



            productService.saveProduct(prodName, prodLink, title, cpu, ram, capacity, weight); // 추후 title -> category 수정
        }

    }

    public void testing() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mailo\\Desktop\\capstoneProject\\capstoneProject\\chromedriver.exe");

        // WebDriver 객체 생성 (Chrome 브라우저 실행)
        WebDriver driver = new ChromeDriver();


        driver.get("https://www.danawa.com/");

        // 마우스를 가져다 댈 대상 요소 찾기
        WebElement hoverElement = driver.findElement(By.cssSelector("ul.category__list li.category__list__row")); // CSS 선택자에 맞게 수정
//        WebElement hoverElement = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div[2]/div[1]/div[1]/ul/li[2]"));
        WebElement large = hoverElement.findElements(By.cssSelector("a.category__list__btn")).get(0);

        // Actions 객체 생성
        Actions actions = new Actions(driver);

        // 요소에 마우스를 오버하는 동작 수행
        actions.moveToElement(hoverElement).perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 최대 10초 대기
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#categoryHoverLayer22")));

        // 마우스 오버 후 나타나는 요소 찾기 (예: 나타나는 메뉴 항목들)
        List<WebElement> revealedElements = driver.findElements(By.cssSelector("div#categoryHoverLayer22 a.category__depth__btn > span.category__depth__txt")); // 마우스 오버로 나타나는 요소 선택자
        List<WebElement> mid = driver.findElements(By.cssSelector("div#categoryHoverLayer22 ul.category__depth > li.depth1")); // 마우스 오버로 나타나는 요소 선택자


        for (WebElement webElement : mid) {
            String midText = webElement.getText();
            actions.moveToElement(webElement).perform();

//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.category__3depth")));

            List<WebElement> smlElements = webElement.findElements(By.cssSelector("div.category__3depth a.category__depth__btn span.category__depth__txt"));
//
            for (WebElement smlElement : smlElements) {
                String allText = smlElement.getText();
                String innerText = "";
                try {
                    innerText = smlElement.findElement(By.cssSelector("span.icom")).getText() + "\n";
                } catch (Exception e) {

                }

                String ownText = allText.replaceFirst(innerText, "");

                System.out.println("대분류 : " + large.getText() + ", 중분류 : " + midText + ", 소분류 : " + ownText);
                System.out.println("-------------------------");
            }

        }



        driver.quit();
    }

}
