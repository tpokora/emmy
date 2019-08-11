package org.tpokora.views.weather;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
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
        VerticalLayout verticalLayout = new VerticalLayout();
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
                new Span("Warning: " + this.warning.getName()),
                new Span("Level: " + this.warning.getLevel()),
                new Span("From: " + from),
                new Span("To: " + to));
        add(verticalLayout);
    }

    private Span createSpanElement(String text) {
        return new Span(text);
    }
}
