package com.realestateapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentRaterTest {

    @Test
    void should_ReturnZero_When_ApartmentIsCheap() {
        //given
        Apartment apartment = new Apartment(200.0, new BigDecimal(5000.0));
        //when
        int actual = ApartmentRater.rateApartment(apartment);
        //then
        assertEquals(0, actual);
    }

    @Test
    void should_ReturnOne_When_ApartmentIsModerate() {
        //given
        Apartment apartment = new Apartment(100.0, new BigDecimal(700000.0));
        //when
        int actual = ApartmentRater.rateApartment(apartment);
        //then
        assertEquals(1, actual);
    }

    @Test
    void should_ReturnTwo_When_ApartmentIsExpensive() {
        //given
        Apartment apartment = new Apartment(1000.0, new BigDecimal(70000000.0));
        //when
        int actual = ApartmentRater.rateApartment(apartment);
        //then
        assertEquals(2, actual);
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