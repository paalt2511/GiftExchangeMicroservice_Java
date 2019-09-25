package com.test.gifts.exchange.controller;

import com.test.gifts.exchange.domain.GiftExchange;
import com.test.gifts.exchange.services.GiftExchangeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class GiftExchangeControllerTest {

    @Mock
    private GiftExchangeService giftExchangeService;

    @InjectMocks
    private GiftExchangeController giftExchangeController;

    @Before
    public void setUp() throws Exception {
        Set giftSet = new HashSet();
        when(giftExchangeService.fetchGiftExchangeDetails()).thenReturn(giftSet);

    }

    @Test
    public void shouldFetchExchangeGifts() {
        ResponseEntity<Set<GiftExchange>> responseEntity = giftExchangeController.fetchExchangeGifts();
        assertNotNull(responseEntity);
        assertThat(responseEntity.getStatusCode().OK, is(HttpStatus.OK));

    }
}