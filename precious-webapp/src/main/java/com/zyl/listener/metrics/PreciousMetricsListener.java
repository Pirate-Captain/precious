package com.zyl.listener.metrics;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.servlets.MetricsServlet;
import com.zyl.metrics.util.MetricsUtils;

public class PreciousMetricsListener extends MetricsServlet.ContextListener {
    
    @Override
    protected MetricRegistry getMetricRegistry() {
        return MetricsUtils.getMetricRegistry();
    }
}