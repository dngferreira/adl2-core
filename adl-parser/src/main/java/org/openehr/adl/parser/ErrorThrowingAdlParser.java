/*
 * Copyright (C) 2014 Marand
 */

package org.openehr.adl.parser;

import org.openehr.adl.antlr.AdlParser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.TokenStream;

/**
 * @author markopi
 */
public class ErrorThrowingAdlParser extends AdlParser {

    public ErrorThrowingAdlParser(TokenStream input) {
        super(input);
    }

    public ErrorThrowingAdlParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    @SuppressWarnings("RefusedBequest")
    @Override
    public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
        throw new RuntimeRecognitionException(e);
    }
}