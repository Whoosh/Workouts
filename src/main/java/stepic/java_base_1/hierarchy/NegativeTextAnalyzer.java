package stepic.java_base_1.hierarchy;

class NegativeTextAnalyzer extends KeywordAnalyzer {

    private final String[] sadSequence = {":(", " =(", " :|"};

    @Override
    protected String[] getKeywords() {
        return sadSequence;
    }

    @Override
    protected Label getLabel() {
        return Label.NEGATIVE_TEXT;
    }
}
