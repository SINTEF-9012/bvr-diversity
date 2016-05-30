/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.sintef.bvr.metrics;

import java.util.HashSet;
import java.util.Set;
import no.sintef.bvr.Feature;
import no.sintef.bvr.Product;
import no.sintef.bvr.sampler.Sample;

/**
 *
 * @author franckc
 */
public class Coverage {
    
    
    public double of(Sample sample) {
        final Set<Feature> notCovered = new HashSet<>();
        final Set<Feature> covered = new HashSet<>();
        for (Product anyProduct: sample) {
            for(Feature aFeature: sample.productLine()) {
                if (anyProduct.offers(aFeature)) {
                    covered.add(aFeature);
                } else {
                    notCovered.add(aFeature);
                }
            }
        }
        return (covered.size() + notCovered.size()) / ((double) 2 * sample.productLine().featureCount());
    }
} 
