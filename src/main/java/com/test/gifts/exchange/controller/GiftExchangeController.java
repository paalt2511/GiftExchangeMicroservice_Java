package com.test.gifts.exchange.controller;

import com.test.gifts.exchange.domain.GiftExchange;
import com.test.gifts.exchange.exception.FamilyMemberNotFoundException;
import com.test.gifts.exchange.services.GiftExchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/gift_exchange")
public class GiftExchangeController {

        private GiftExchangeService giftExchangeService;

        @Autowired
        public GiftExchangeController(GiftExchangeService giftExchangeService) {
            this.giftExchangeService = giftExchangeService;
        }

        private static final Logger LOGGER = LoggerFactory.getLogger(FamilyMembersController.class);

    /**
     * API to fetch exchange gifts for family members.
     * @return
     */
    @GetMapping
        public ResponseEntity<Set<GiftExchange>> fetchExchangeGifts() {
            Set<GiftExchange> giftExchangeSet;
            try {
                giftExchangeSet = giftExchangeService.fetchGiftExchangeDetails();
            } catch (FamilyMemberNotFoundException exp) {
                LOGGER.error("No Members Present to Gift Each Other", exp);
                return new ResponseEntity<>(new HashSet<>(), HttpStatus.NOT_FOUND);
            }
            final ResponseEntity<Set<GiftExchange>> giftExchangeResponse = new ResponseEntity<>(giftExchangeSet, HttpStatus.OK);
            return giftExchangeResponse;
        }

}
