package org.tpokora.views.common;

import com.vaadin.flow.component.HtmlComponent;

public class Styler {

    public static void setDefaultStylesForContainer(HtmlComponent component) {
        component.setMaxWidth("1140px");
        component.setMinHeight("400px");
        component.getStyle().set("margin-top", "20px");
        setAutoMargin(component);
        component.getStyle().set("background-color", "#eff1f3");
        component.getStyle().set("padding", "30px");
    }

    public static void setAutoMargin(HtmlComponent component) {
        component.getStyle().set("margin-left", "auto");
        component.getStyle().set("margin-right", "auto");
    }
}
