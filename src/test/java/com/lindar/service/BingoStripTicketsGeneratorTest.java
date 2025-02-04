package com.lindar.service;

import com.lindar.factory.BingoStripFactory;
import com.lindar.model.BingoStrip;
import com.lindar.model.BingoTicket;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BingoStripTicketsGeneratorTests {

    private static List<BingoStrip> bingoStrips;

    @BeforeAll
    static void setup() {
        bingoStrips = BingoStripFactory.generateBingoStrips(100);
    }

    @Test
    void testGeneratedStripsAreNotNullOrEmpty() {
        assertNotNull(bingoStrips);
        assertFalse(bingoStrips.isEmpty());
    }

    @Test
    void testEachStripHasSixTickets() {
        for (BingoStrip strip : bingoStrips) {
            assertEquals(6, strip.tickets().size(), "Each strip must contain exactly 6 tickets");
        }
    }

    @Test
    void testEachTicketHasFifteenNumbers() {
        for (BingoStrip strip : bingoStrips) {
            for (BingoTicket ticket : strip.tickets()) {
                long countNumbers = ticket.numbers().stream().filter(num -> num > 0).count();
                assertEquals(15, countNumbers, "Each ticket must have exactly 15 numbers");
            }
        }
    }

    @Test
    void testNoDuplicateNumbersInStrip() {
        for (BingoStrip strip : bingoStrips) {
            Set<Integer> uniqueNumbers = strip.tickets().stream()
                    .flatMap(ticket -> ticket.numbers().stream())
                    .filter(num -> num > 0)
                    .collect(Collectors.toSet());
            assertEquals(90, uniqueNumbers.size(), "Each strip must contain all numbers from 1 to 90 exactly once");
        }
    }

    @Test
    void testEachRowContainsExactlyFiveNumbers() {
        for (BingoStrip strip : bingoStrips) {
            for (BingoTicket ticket : strip.tickets()) {
                for (List<Integer> row : ticket.rows()) {
                    long countNumbers = row.stream().filter(num -> num > 0).count();
                    assertEquals(5, countNumbers, "Each row must contain exactly 5 numbers");
                }
            }
        }
    }

    @Test
    void testNoColumnHasMoreThanThreeNumbers() {
        for (BingoStrip strip : bingoStrips) {
            for (BingoTicket ticket : strip.tickets()) {
                for (List<Integer> column : ticket.columns()) {
                    assertTrue(column.size() <= 3, "A column cannot contain more than 3 numbers");
                }
            }
        }
    }

    @Test
    void testPerformanceOfGeneratingStrips() {
        long startTime = System.currentTimeMillis();
        List<BingoStrip> strips = BingoStripFactory.generateBingoStrips(10_000);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        assertTrue(duration < 1000, "Generating 10,000 strips should take less than 1 second");
    }

    @Test
    void testPerformanceOfGenerating100Strips() {
        long startTime = System.currentTimeMillis();
        List<BingoStrip> strips = BingoStripFactory.generateBingoStrips(100);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        assertTrue(duration < 1000, "Generating 100 strips should take less than 1 second");
    }

    @Test
    void testPerformanceOfGenerating10000Strips() {
        long startTime = System.currentTimeMillis();
        List<BingoStrip> strips = BingoStripFactory.generateBingoStrips(10_000);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        assertTrue(duration < 1000, "Generating 10,000 strips should take less than 1 second");
    }
}
