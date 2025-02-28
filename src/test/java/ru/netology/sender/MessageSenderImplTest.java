package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceMock;
import ru.netology.i18n.LocalizationServiceMock;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Написать тесты для проверки языка отправляемого сообщения (класс MessageSender) с использованием Mockito

*/

public class MessageSenderImplTest {

    @Test
    void languageTest() {
        GeoServiceMock mockGeoService = new GeoServiceMock();
        mockGeoService.setLocation(new Location(null, Country.RUSSIA, null, 0));
        GeoServiceMock mockGeoService1 = new GeoServiceMock();
        mockGeoService1.setLocation(new Location(null, Country.USA, null, 0));

        LocalizationServiceMock localizationServiceMock = new LocalizationServiceMock();
        Location location = mockGeoService.getLocation();
        Location location1 = mockGeoService1.getLocation();
        String message = localizationServiceMock.locale(location.getCountry());
        String message1 = localizationServiceMock.locale(location1.getCountry());

        Pattern pattern = Pattern.compile("[а-яА-ЯёЁ\\d\\s\\p{Punct}]*");
        Pattern pattern1 = Pattern.compile("[a-zA-Z\\d\\s\\p{Punct}]*");
        Matcher matcher = pattern.matcher(message);
        Matcher matcher1 = pattern1.matcher(message1);

        Assertions.assertTrue(matcher.matches()); // - Проверить, что MessageSenderImpl всегда отправляет только русский текст, если ip относится к российскому сегменту адресов.
        Assertions.assertTrue(matcher1.matches()); // - Поверить, что MessageSenderImpl всегда отправляет только английский текст, если ip относится к американскому сегменту адресов.

    }
}
