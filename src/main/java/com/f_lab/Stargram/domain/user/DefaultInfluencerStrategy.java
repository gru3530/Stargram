package com.f_lab.Stargram.domain.user;

public class DefaultInfluencerStrategy implements InfluencerStrategy {
    private final int threshold;

    public DefaultInfluencerStrategy(int threshold) {
        this.threshold = threshold;
    }

}
