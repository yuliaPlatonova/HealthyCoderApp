package com.realestateapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentRaterTest {

    @ParameterizedTest
    @CsvSource(value = {"25.0, 500.0, 0", "60.0, 360000.0, 1", "100.0, 1000000, 2"})
    void should_ReturnCorrectRating_When_CorrectApartment(Double area, Double price, int rating){
        //given
        Apartment apartment = new Apartment(area, new BigDecimal(price));
        //when
        int actual = ApartmentRater.rateApartment(apartment);
        //then
        assertEquals(rating, actual);
    }

    @Test
    void should_ReturnErrorValue_When_IncorrectApartment() {
        //given
        Apartment apartment = new Apartment(0.0, new BigDecimal(7000.0));
        //when
        int actual = ApartmentRater.rateApartment(apartment);
        //then
        assertEquals(-1, actual);
    }

    @Test
    void should_CalculateAverageRating_When_CorrectApartmentList() {
        //given
        List<Apartment> apartments = new ArrayList<>();
        apartments.add(new Apartment(1000.0, new BigDecimal(10000000.0)));
        apartments.add(new Apartment(100.0, new BigDecimal(10000.0)));
        apartments.add(new Apartment(2000.0, new BigDecimal(10000.0)));
        apartments.add(new Apartment(15000.0, new BigDecimal(10000.0)));
        //when
        double actual = ApartmentRater.calculateAverageRating(apartments);
        //then
        assertEquals(0.5 ,actual);
    }

    @Test
    void should_ThrowExceptionInCalculateAverageRating_When_EmptyApartmentList() {
        //given
        List<Apartment> apartments = new ArrayList<>();
        //when
        Executable executable = () ->
                ApartmentRater.calculateAverageRating(apartments);
        //then
        assertThrows(RuntimeException.class, executable);

    }
}