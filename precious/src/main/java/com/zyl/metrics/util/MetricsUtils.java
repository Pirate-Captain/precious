package com.zyl.metrics.util;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;

public class MetricsUtils {
    private static final HealthCheckRegistry healthCheckRegistry = new HealthCheckRegistry();
    private static final MetricRegistry metricRegistry = new MetricRegistry();
    
    public static HealthCheckRegistry getHealthCheckRegistry() {
        return healthCheckRegistry;
    }
    
    public static MetricRegistry getMetricRegistry() {
        return metricRegistry;
    }
}