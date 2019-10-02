package org.tpokora.views.weather;

import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.tpokora.storms.model.Period;
import org.tpokora.storms.model.Warning;
import org.tpokora.views.common.Styler;

import java.util.Optional;

@Tag("warning-element")
public class WarningElement extends Div {

    private static final String NO_DATE_PROVIDED = "No date provided";
    private Warning warning;

    public WarningElement(Warning warning) {
        this.warning = warning;
        createWarningElement();
    }

    private void createWarningElement() {
        Styler.setWarningElementStyle(this);
        Icon warningIcon = new Icon(VaadinIcon.WARNING);
        FlexLayout verticalLayout = new FlexLayout();
        verticalLayout.setWrapMode(FlexLayout.WrapMode.WRAP);
        Optional<Period> optional = Optional.ofNullable(this.warning.getPeriod());
        String from;
        String to;
        // TODO: Refactoring needed
        if (optional.isPresent()) {
            Period period = optional.get();
            from = Optional.of(period.getFromString()).orElseGet(() -> NO_DATE_PROVIDED);
            to = Optional.ofNullable(period.getToString()).orElseGet(() -> NO_DATE_PROVIDED);
        } else {
            from = NO_DATE_PROVIDED;
            to = NO_DATE_PROVIDED;
        }
        verticalLayout.add(warningIcon,
                createSpanElement("Warning: " + this.warning.getName()),
                createSpanElement("Level: " + this.warning.getLevel()),
                createSpanElement("From: " + from),
                createSpanElement("To: " + to));
        add(verticalLayout);
    }

    private Span createSpanElement(String text) {
        Span span = new Span(text);
        span.getStyle().set("padding", "4px");
        span.getStyle().set("margin", "2px");
        return span;
    }
}
