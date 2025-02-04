package com.lindar.model;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * Represents a Bingo Ticket, which consists of a list of 15 numbers.
 * This record provides utility methods to retrieve the ticket's structure
 * as columns and rows, formatted according to standard Bingo 90 rules.
 */
public record BingoTicket(List<Integer> numbers) {

    /**
     * Splits the list of numbers into 9 columns as per Bingo 90 rules.
     * Each column contains up to 3 numbers based on their assigned ranges.
     *
     * @return A list of 9 sublists, each representing a column of the Bingo ticket.
     */
    public List<List<Integer>> columns() {
        return IntStream.range(0, 9)
                .mapToObj(it -> numbers().subList(it * 3, Math.min((it + 1) * 3, numbers.size())))
                .collect(toList());
    }

    /**
     * Splits the list of numbers into 3 rows, with 5 numbers per row.
     * Blank spaces (represented by zeros) ensure that each row aligns properly.
     *
     * @return A list of 3 sublists, each representing a row of the Bingo ticket.
     */
    public List<List<Integer>> rows() {
        return IntStream.range(0, 3)
                .mapToObj(it -> IntStream.iterate(it, val -> val + 3)
                        .limit(9)
                        .mapToObj(numbers()::get)
                        .collect(toList()))
                .collect(toList());
    }
}
