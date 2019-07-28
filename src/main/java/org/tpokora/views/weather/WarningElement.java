package org.tpokora.views.weather;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.tpokora.storms.model.Warning;
import org.tpokora.storms.model.WarningStrings;
import org.tpokora.views.common.Styler;

import java.time.format.DateTimeFormatter;

@Tag("warning-element")
public class WarningElement extends Div {

    private Warning warning;

    public WarningElement(Warning warning) {
        this.warning = warning;
        createWarningElement();
    }

    private void createWarningElement() {
        Styler.setWarningElementStyle(this);
        Icon warningIcon = new Icon(VaadinIcon.WARNING);
        HorizontalLayout titleLayout = new HorizontalLayout();
        titleLayout.add(new Span("Warning: " + this.warning.getName()), new Span("Level: " + this.warning.getLevel()));
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(warningIcon, titleLayout,
                new Span("From: " + this.warning.getPeriod().getFrom().format(DateTimeFormatter.ofPattern(WarningStrings.WARNINGS_DATE_FORMAT))),
                new Span("To: " + this.warning.getPeriod().getTo().format(DateTimeFormatter.ofPattern(WarningStrings.WARNINGS_DATE_FORMAT))));
        add(verticalLayout);
    }
}
