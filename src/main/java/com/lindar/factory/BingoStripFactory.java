package com.lindar.factory;

import com.lindar.model.BingoStrip;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Factory class for creating Bingo Strips.
 * Provides methods to generate single and multiple Bingo Strips in parallel.
 */
public class BingoStripFactory {

    /**
     * Generates a single Bingo Strip.
     *
     * @return A new instance of {@link BingoStrip}.
     */
    public static BingoStrip generateBingoStrip() {
        return new BingoStrip();
    }

    /**
     * Generates multiple Bingo Strips concurrently using parallel stream processing.
     *
     * @param numberOfStrips The number of Bingo Strips to generate.
     * @return A list containing the generated Bingo Strips.
     */
    public static List<BingoStrip> generateBingoStrips(int numberOfStrips) {
        return Stream
                .generate(BingoStripFactory::generateBingoStrip)
                .parallel()
                .limit(numberOfStrips)
                .collect(toList());
    }

}