package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MessageSenderImplTest {



    @Test
    void languageTest() {

        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);

        Mockito.when(geoService.byIp("1"))
                .thenReturn(new Location(null, Country.RUSSIA, null, 0));
        Mockito.when(geoService.byIp("2"))
                .thenReturn(new Location(null, Country.USA, null, 0));
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> ip1 = new HashMap<>();
        Map<String, String> ip2 = new HashMap<>();

        ip1.put(MessageSenderImpl.IP_ADDRESS_HEADER, "1");
        ip2.put(MessageSenderImpl.IP_ADDRESS_HEADER, "2");
        String message = messageSender.send(ip1);
        String message1 = messageSender.send(ip2);

        // Написать тесты для проверки языка отправляемого сообщения (класс MessageSender) с использованием Mockito
        Assertions.assertTrue(message.matches("[а-яА-ЯёЁ\\d\\s\\p{Punct}]*")); // - Проверить, что MessageSenderImpl всегда отправляет только русский текст, если ip относится к российскому сегменту адресов.
        Assertions.assertTrue(message1.matches("[a-zA-Z\\d\\s\\p{Punct}]*")); // - Поверить, что MessageSenderImpl всегда отправляет только английский текст, если ip относится к американскому сегменту адресов.

    }
}
