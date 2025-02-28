package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;

public class LocalizationServiceImplTest {

//    Написать тесты для проверки возвращаемого текста (класс LocalizationServiceImpl)


    @Test
    void localizationTest() {

        LocalizationService localizationService = Mockito.spy(LocalizationServiceImpl.class);
        String localization = localizationService.locale(Country.RUSSIA);

//        - Проверить работу метода public String locale(Country country)
        Assertions.assertTrue(localization.matches("[а-яА-ЯёЁ\\d\\s\\p{Punct}]*"));
        Assertions.assertNotEquals(localization, localizationService.locale(Country.BRAZIL));
        Assertions.assertNotNull(localizationService.locale(Country.GERMANY));
    }
}
