package com.lindar.model;

import com.lindar.service.BingoStripTicketsGenerator;

import java.util.List;

/**
 * Represents a Bingo Strip, which consists of a collection of six Bingo tickets.
 * A Bingo Strip ensures that all numbers from 1 to 90 appear exactly once across the tickets.
 * This class provides functionality to generate a valid Bingo Strip following the Bingo 90 rules.
 */
public record BingoStrip(List<BingoTicket> tickets) {

    public BingoStrip() {
        this(BingoStripTicketsGenerator.generateBingoStripTickets());
    }

}
