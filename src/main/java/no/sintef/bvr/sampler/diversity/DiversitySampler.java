/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.sintef.bvr.sampler.diversity;

import no.sintef.bvr.ProductLine;
import no.sintef.bvr.sampler.random.RandomSampler;
import no.sintef.bvr.sampler.Sample;
import no.sintef.bvr.sampler.Sampler;

public class DiversitySampler implements Sampler {

    public static final double DEFAULT_DIVERSITY = 1;
    private static final int MAX_EPOCH = 1000;

    private final EvolutionListener listener;
    private final Goal goal;
    private final int desiredSampleSize;
    private final int maxEpoch;

    public DiversitySampler(int sampleSize) {
        this(sampleSize, DEFAULT_DIVERSITY, MAX_EPOCH);
    }
    
    public DiversitySampler(int sampleSize, double diversity) {
        this(sampleSize, diversity, MAX_EPOCH);
    }

    public DiversitySampler(int sampleSize, double diversity, int maxEpoch) {
        this(sampleSize, diversity, maxEpoch, new EvolutionListener());
    }
    
    public DiversitySampler(int sampleSize, double diversity, int maxEpoch, EvolutionListener listener) {
        desiredSampleSize = sampleSize;
        goal = new Goal(diversity);
        this.listener = listener;
        this.maxEpoch = maxEpoch;
    }

    @Override
    public Sample sample(ProductLine productLine) {
        final Population population = new Population(productLine, 100, new RandomSampler(desiredSampleSize), listener);
        return population.convergeTo(goal, maxEpoch);
    }

}


