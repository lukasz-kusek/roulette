package com.github.lukaszkusek.roulette.output;

import com.github.lukaszkusek.roulette.domain.Round;

public interface Printer {

    void printRound(Round round);

    void printTotals();
}
