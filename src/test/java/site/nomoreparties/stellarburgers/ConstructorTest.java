package site.nomoreparties.stellarburgers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import site.nomoreparties.stellarburgers.constants.ConstructorLabelName;
import site.nomoreparties.stellarburgers.page_object.ConstructorPage;

import static com.codeborne.selenide.Selenide.open;
import static site.nomoreparties.stellarburgers.constants.ApiUrls.BASE_URL;
import static site.nomoreparties.stellarburgers.constants.ConstructorLabelName.*;

@DisplayName("Форма конструктора")
public class ConstructorTest extends BaseTest {

    @DisplayName("Проверка перехода по табам в конструкторе и отображение заголовка")
    @ParameterizedTest
    @EnumSource(value = ConstructorLabelName.class, names = {"BUNS"}, mode = EnumSource.Mode.EXCLUDE)
    public void shouldTabNavigationCorrect(ConstructorLabelName name) {
        open(BASE_URL, ConstructorPage.class)
                .clickTabConstructor(name.getLabelName())
                .checkActiveTabConstructor(name.getLabelName())
                .checkLabelMenu(name.getLabelName());
    }

    @DisplayName("Проверка отображения табы по умолчанию")
    @Test
    public void shouldCorrectDefaultTab() {
        open(BASE_URL, ConstructorPage.class)
                .checkActiveTabConstructor(BUNS.getLabelName())
                .checkLabelMenu(BUNS.getLabelName());
    }

    @DisplayName("Проверка корректного скролла при переходе на вкладку Булки")
    @Test
    public void shouldCorrectScrollBunsTab() {
        open(BASE_URL, ConstructorPage.class)
                .clickTabConstructor(SOUSES.getLabelName())
                .clickTabConstructor(BUNS.getLabelName())
                .checkActiveTabConstructor(BUNS.getLabelName())
                .checkLabelMenu(BUNS.getLabelName());
    }

    @DisplayName("Проверка корректного скролла при переходе на вкладку Соусы")
    @Test
    public void shouldCorrectScrollFillingTab() {
        open(BASE_URL, ConstructorPage.class)
                .clickTabConstructor(FILLINGS.getLabelName())
                .checkActiveTabConstructor(FILLINGS.getLabelName())
                .checkLabelMenu(FILLINGS.getLabelName());
    }
}
