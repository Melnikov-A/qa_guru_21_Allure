import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class LambdaStepsTest extends BaseTest {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 80;

    @Test
    @DisplayName("Тест с реализацией Лямбда - шага")
    public void testLambdaStep() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ишем репозиторий" + REPOSITORY, () -> {
            $(".search-input").click();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });
        step("Кликаем по ссылке репозитория" + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issues c номером" + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });

    }

    @Test
    @DisplayName("Тест с реализацией выражения Steps")
    public void testAnnotatedTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        StepsWeb stepsWeb = new StepsWeb();

        stepsWeb.openMainPage();
        stepsWeb.searchRepository(REPOSITORY);
        stepsWeb.clickOnRepositoryLink(REPOSITORY);
        stepsWeb.openIssueTab();
        stepsWeb.shouldSeeIssueWithNumber(ISSUE);

    }

}
