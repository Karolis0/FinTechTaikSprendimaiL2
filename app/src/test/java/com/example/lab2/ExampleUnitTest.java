package com.example.lab2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void CounterBlank() {
        Counter counter = new Counter();
        int charCountWords = counter.Count("", "Words");
        int charCountChar = counter.Count("", "Char");
        assertEquals(0, charCountWords);
        assertEquals(0, charCountChar);
    }

    @Test
    public void CounterText() {
        Counter counter = new Counter();
        int charCountWords = counter.Count("normalus", "Words");
        int charCountChar = counter.Count("normalus", "Char");
        assertEquals(1, charCountWords);
        assertEquals(8, charCountChar);

        charCountWords = counter.Count("normalus 111", "Words");
        charCountChar = counter.Count("normalus 111", "Char");
        assertEquals(2, charCountWords);
        assertEquals(11, charCountChar);

        charCountWords = counter.Count("   normalus 111", "Words");
        charCountChar = counter.Count("   normalus 111", "Char");
        assertEquals(2, charCountWords);
        assertEquals(11, charCountChar);

        charCountWords = counter.Count("   normalus 111   ", "Words");
        charCountChar = counter.Count("   normalus 111   ", "Char");
        assertEquals(2, charCountWords);
        assertEquals(11, charCountChar);

        charCountWords = counter.Count("normalus 111   ", "Words");
        charCountChar = counter.Count("normalus 111   ", "Char");
        assertEquals(2, charCountWords);
        assertEquals(11, charCountChar);
    }

    @Test
    public void CounterDelimiters() {
        Counter counter = new Counter();
        int charCountWordsEmpty = counter.Count(" ", "Words");
        int charCountCharEmpty = counter.Count(" ", "Char");
        assertEquals(0, charCountWordsEmpty);
        assertEquals(0, charCountCharEmpty);

        int charCountWordsDelimiter = counter.Count("!-", "Words");
        int charCountCharDelimiter = counter.Count("!-", "Char");
        assertEquals(1, charCountWordsDelimiter);
        assertEquals(2, charCountCharDelimiter);

        int charCountWordsBlankDelimiter = counter.Count("    --!", "Words");
        int charCountCharBlankDelimiter = counter.Count("    --!", "Char");
        assertEquals(1, charCountWordsBlankDelimiter);
        assertEquals(3, charCountCharBlankDelimiter);
    }

    @Test
    public void CounterLetter() {
        Counter counter = new Counter();
        int charCountWordsLetter = counter.Count("L1a2b3a4s r6y7t8a9s", "Letter");
        assertEquals(10, charCountWordsLetter);

        charCountWordsLetter = counter.Count("123", "Letter");
        assertEquals(0, charCountWordsLetter);

        charCountWordsLetter = counter.Count("  123   ", "Letter");
        assertEquals(0, charCountWordsLetter);

        charCountWordsLetter = counter.Count("123   ", "Letter");
        assertEquals(0, charCountWordsLetter);

        charCountWordsLetter = counter.Count("  123", "Letter");
        assertEquals(0, charCountWordsLetter);

        charCountWordsLetter = counter.Count("123457788 11111l11111", "Letter");
        assertEquals(1, charCountWordsLetter);

    }
    @Test
    public void CounterNumber() {
        Counter counter = new Counter();
        int charCountWordsNumber = counter.Count("L1a2b3a4s r6y7t8a9s", "Numbers");
        assertEquals(8, charCountWordsNumber);

        charCountWordsNumber = counter.Count("abc", "Numbers");
        assertEquals(0, charCountWordsNumber);

        charCountWordsNumber = counter.Count("    abc    ", "Numbers");
        assertEquals(0, charCountWordsNumber);

        charCountWordsNumber = counter.Count("abc    ", "Numbers");
        assertEquals(0, charCountWordsNumber);

        charCountWordsNumber = counter.Count("    abc", "Numbers");
        assertEquals(0, charCountWordsNumber);

        charCountWordsNumber = counter.Count("AAAAAAAA AAAAA4AAAAAA", "Numbers");
        assertEquals(1, charCountWordsNumber);
    }
    @Test
    public void CounterCharWithSpace() {
        Counter counter = new Counter();
        int charCountCharWithSpace = counter.Count("L1a2b3a4s r6y7t8a9s", "Character and space");
        assertEquals(19, charCountCharWithSpace);

        charCountCharWithSpace = counter.Count("   L1a2b3a4s r6y7t8a9s", "Character and space");
        assertEquals(19, charCountCharWithSpace);

        charCountCharWithSpace = counter.Count("L1a2b3a4s r6y7t8a9s   ", "Character and space");
        assertEquals(19, charCountCharWithSpace);

        charCountCharWithSpace = counter.Count("L1a2b3a4s    r6y7t8a9s", "Character and space");
        assertEquals(22, charCountCharWithSpace);
    }

    @Test
    public void CounterInvalidType() {
        Counter counter = new Counter();
        int charCountWordsInvalid = counter.Count("normalus", "Invalid");
        assertEquals(8, charCountWordsInvalid);
    }
}