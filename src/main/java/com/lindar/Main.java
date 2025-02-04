package com.lindar;

import com.lindar.factory.BingoStripFactory;
import com.lindar.model.BingoStrip;
import com.lindar.model.BingoTicket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static final int NUMBER_OF_STRIPS = 10_000;

    public static void main(String[] args) {
        var start = Instant.now();
        var bingoStrips = BingoStripFactory.generateBingoStrips(NUMBER_OF_STRIPS);
        var end = Instant.now();
        logger.info("Time taken to generate 10,000 Bingo Strips: {} ms", Duration.between(start, end).toMillis());
        bingoStrips.stream().limit(5).forEach(Main::printBingoStrip);
    }

    private static void printBingoStrip(BingoStrip strip) {
        logger.info("\n===== BINGO STRIP =====");
        IntStream.range(1, strip.tickets().size() + 1)
                .forEach(i -> printBingoTicket(strip.tickets().get(i - 1), i));
    }

    private static void printBingoTicket(BingoTicket ticket, int index) {
        logger.info("Ticket {}", index);
        logger.info("----------------------------------------------");
        ticket.rows().forEach(row -> {
            StringBuilder rowOutput = new StringBuilder();
            row.forEach(num -> rowOutput.append(String.format("| %2s ", num == 0 ? " " : num)));
            logger.info(rowOutput.append("|").toString());
        });
        logger.info("-----------------------------------------------\n");
    }
}
