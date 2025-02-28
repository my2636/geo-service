package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceMock;
import ru.netology.i18n.LocalizationServiceMock;

/*
Написать тесты для проверки языка отправляемого сообщения (класс MessageSender) с использованием Mockito
- Поверить, что MessageSenderImpl всегда отправляет только русский текст, если ip относится к российскому сегменту адресов.
- Поверить, что MessageSenderImpl всегда отправляет только английский текст, если ip относится к американскому сегменту адресов.*/

public class MessageSenderImplTest {

    @Test
    void languageTest() {
        GeoServiceMock mockGeoService = new GeoServiceMock();
        mockGeoService.setLocation(new Location(null, Country.RUSSIA, null, 0));

        LocalizationServiceMock localizationServiceMock = new LocalizationServiceMock();
        Location location = mockGeoService.getLocation();
        String message =  localizationServiceMock.locale(location.getCountry());
        System.out.printf("Отправлено сообщение: %s", message);

        Assertions.

    }
}
