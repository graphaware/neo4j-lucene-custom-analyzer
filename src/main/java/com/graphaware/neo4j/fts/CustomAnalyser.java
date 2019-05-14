package com.graphaware.neo4j.fts;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.neo4j.graphdb.index.fulltext.AnalyzerProvider;
import org.neo4j.helpers.Service;

@Service.Implementation(AnalyzerProvider.class)
public class CustomAnalyser extends AnalyzerProvider {

    public static final String STANDARD_ANALYZER_NAME = "custom_stopwords";

    private static final CharArraySet CUSTOM_STOPWORDS_SET;

    static {
        CharArraySet set = StopAnalyzer.ENGLISH_STOP_WORDS_SET;
        set.remove("not");
        CUSTOM_STOPWORDS_SET = set;
    }

    public CustomAnalyser() {
        super(STANDARD_ANALYZER_NAME);
    }

    @Override
    public Analyzer createAnalyzer() {
        return new StandardAnalyzer(CUSTOM_STOPWORDS_SET);
    }

    @Override
    public String description() {
        return "The default, standard analyzer but with custom stopwords. Removing `not` from the default list";
    }
}
