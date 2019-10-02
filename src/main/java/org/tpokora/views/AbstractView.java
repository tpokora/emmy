package org.tpokora.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import org.tpokora.views.common.Styler;

public abstract class AbstractView extends Div {

    protected Div content;

    public AbstractView() {
        this.content = new Div();
        add(this.content);
    }

    protected void setupContentDefaultStyles() {
        Styler.setDefaultStylesForContainer(this.content);
    }

    protected void addToContent(Component component) {
        this.content.add(component);
    }

    protected void addToContent(Component... components) {
        for (Component component : components) {
            addToContent(component);
        }
    }


}
