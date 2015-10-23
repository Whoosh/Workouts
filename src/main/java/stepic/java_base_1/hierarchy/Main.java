package stepic.java_base_1.hierarchy;

class Main {
    public Label checkLabels(TextAnalyzer[] analyzers, String text) {
        for (TextAnalyzer analyzer : analyzers) {
            Label label = analyzer.processText(text);
            if (label != Label.OK) return label;
        }
        return Label.OK;
    }
}
