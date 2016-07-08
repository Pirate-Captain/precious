package com.zyl.listener.metrics;

import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.servlets.HealthCheckServlet;
import com.zyl.metrics.util.MetricsUtils;

public class PreciousHealthMetricsListener extends HealthCheckServlet.ContextListener {

    @Override
    protected HealthCheckRegistry getHealthCheckRegistry() {
        return MetricsUtils.getHealthCheckRegistry();
    }
}