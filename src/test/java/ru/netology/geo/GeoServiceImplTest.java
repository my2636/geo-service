package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;

public class GeoServiceImplTest {

// Написать тесты для проверки определения локации по ip (класс GeoServiceImpl)
    @Test
    void locationTest() {
        GeoService geoService = Mockito.spy(GeoServiceImpl.class);

        // - Проверить работу метода public Location byIp(String ip)
        Assertions.assertEquals(Country.RUSSIA, geoService.byIp("172.0.18.10").getCountry());
        Assertions.assertNotEquals(Country.GERMANY, geoService.byIp("96.10.101.14").getCountry());
        Assertions.assertNull(geoService.byIp("127.0.18.10"));
    }
}
