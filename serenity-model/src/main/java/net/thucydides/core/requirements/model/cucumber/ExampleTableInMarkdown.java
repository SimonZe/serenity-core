package net.thucydides.core.requirements.model.cucumber;


import io.cucumber.core.internal.gherkin.ast.Examples;
import io.cucumber.core.internal.gherkin.ast.Feature;
import io.cucumber.core.internal.gherkin.ast.ScenarioDefinition;
import net.thucydides.core.digest.Digest;

import static net.thucydides.core.requirements.model.cucumber.ScenarioDisplayOption.WithTitle;

public class ExampleTableInMarkdown {

    private final Feature feature;
    private final String scenarioReport;
    private final String scenarioId;
    private final ScenarioDefinition scenarioDefinition;

    public ExampleTableInMarkdown(Feature feature, String scenarioReport, ScenarioDefinition scenarioDefinition) {
        this.feature = feature;
        this.scenarioReport = scenarioReport;
        this.scenarioId = Digest.ofTextValue(scenarioDefinition.getName());
        this.scenarioDefinition = scenarioDefinition;
    }

    public String renderedFormOf(Examples exampleTable, int exampleRow, ScenarioDisplayOption displayOption) {

//        ExampleRowResultIcon exampleRowCounter = new ExampleRowResultIcon(feature.getName(),scenarioDefinition.getName(), exampleRow);
//        ExampleRowResultIcon exampleRowCounter = new ExampleRowResultIcon(feature.getName(),scenarioDefinition.getName());
        ExampleRowResultIcon exampleRowCounter = new ExampleRowResultIcon(feature.getName());

        StringBuilder renderedExampleTable = new StringBuilder();

        String tableName = RenderedExampleTable.nameFor(exampleTable);
        if (tableName.isEmpty()) {
            tableName = scenarioDefinition.getName();
        }
        if (displayOption == WithTitle) {
            String exampleTitle = "### " + tableName;
            renderedExampleTable.append(exampleTitle);
        }
        renderedExampleTable.append(System.lineSeparator())
                .append(RenderedExampleTable.descriptionFor(exampleTable))
                .append(RenderedExampleTable.renderedTable(exampleTable, exampleRowCounter))
                .append(System.lineSeparator())
                .append("[<i class=\"fa fa-info-circle\"></i> More details](#")
                .append(scenarioId)
                .append(")")
                .append(System.lineSeparator());

        return renderedExampleTable.toString();
    }
}
