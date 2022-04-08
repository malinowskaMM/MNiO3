package com.example.view;
import com.example.model.Function;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


public class HelloController {
    @FXML
    private Label resultLabel;
    @FXML
    private LineChart<Double, Double> lineChart;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private TextField startIntervalInput;
    @FXML
    private TextField endIntervalInput;
    @FXML
    private RadioButton mixed;
    @FXML
    private RadioButton absolute;
    @FXML
    private RadioButton linear;
    @FXML
    private RadioButton polynomial;
    @FXML
    private RadioButton trigonometric;
    String function;

    @FXML
    private void initialize() {
        lineChart.setAnimated(false);

    }

    @FXML
    protected void onGraphButtonPressed() {
        // trzeba tu jakos dostarczyc nasza funkcje, roboczo wpisana z palca nizej
        function = chooseFunctionByRadioButton();
        int resolution = 500;
        double firstPoint = Double.parseDouble(startIntervalInput.getText());
        double lastPoint = Double.parseDouble(endIntervalInput.getText());
        double[] x = new double[resolution];
        double[] y = new double[resolution];
        double xIncrement = (lastPoint - firstPoint) / resolution;
        double p = firstPoint;
        for (int i = 0; i < resolution; i++) {
            x[i] = p;
            // nasza funkcja f(x) ktora powinna przyjsc w funkcji
            switch (function) {
                case "polynomial" -> y[i] = Function.polynomial(p);
                case "trigonometric" -> y[i] = Function.trigonometric(p);
                case "mixed" -> y[i] = Function.mixed(p);
                case "linear" -> y[i] = Function.linear(p);
                case "absolute" -> y[i] = Function.absolute(p);
                default -> y[i] = 0;
            }
            p += xIncrement;
        }
        lineChart.getData().clear();
        Graph graph = new Graph(x, y);
        lineChart.getData().add(graph.createSeries());

        xAxis.setLowerBound(firstPoint);
        xAxis.setUpperBound(lastPoint);

    }

    private String chooseFunctionByRadioButton() {
        if (polynomial.isSelected()) {
            return "polynomial";
        }
        if (trigonometric.isSelected()) {
            return "trigonometric";
        }
        if (mixed.isSelected()) {
            return "mixed";
        }
        if (linear.isSelected()) {
            return "linear";
        }
        if (absolute.isSelected()) {
            return "absolute";
        } else {
            return "";
        }

    }

}