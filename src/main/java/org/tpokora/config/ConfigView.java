package org.tpokora.config;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Route("config")
public class ConfigView extends VerticalLayout {

    Environment environment;

    public ConfigView(Environment environment) {
        add(new Span("Configuration"));
        Map<String, Object> properties = new HashMap<>();
        for (Iterator it = ((AbstractEnvironment) environment).getPropertySources().iterator(); it.hasNext(); ) {
            PropertySource propertySource = (PropertySource) it.next();
            if (propertySource instanceof MapPropertySource) {
                properties.putAll(((MapPropertySource) propertySource).getSource());
            }
        }

        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            add(new Span(entry.getKey() + " : " + entry.getValue()));
        }
    }
}

