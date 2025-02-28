package ru.netology.geo;

import ru.netology.entity.Location;

public class GeoServiceMock implements GeoService{

    private Location location;

    @Override
    public Location byIp(String ip) {
        return location;
    }

    @Override
    public Location byCoordinates(double latitude, double longitude) {
        return null;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    public Location getLocation() {
        return location;
    }
}
