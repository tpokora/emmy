package org.tpokora.views.weather;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
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
        add(new Paragraph("Warning: " + this.warning.getName()));
        add(new Paragraph("Level: " + this.warning.getLevel()));
        add(new Paragraph("From: " + this.warning.getPeriod().getFrom().format(DateTimeFormatter.ofPattern(WarningStrings.WARNINGS_DATE_FORMAT))));
        add(new Paragraph("To: " + this.warning.getPeriod().getTo().format(DateTimeFormatter.ofPattern(WarningStrings.WARNINGS_DATE_FORMAT))));
    }
}
