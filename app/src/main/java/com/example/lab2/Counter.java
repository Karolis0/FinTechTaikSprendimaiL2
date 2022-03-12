package com.example.lab2;

import java.util.List;
import java.util.Objects;

public class Counter {

    public int Count(String _wordToCount, String _countType) {

        _wordToCount = _wordToCount.trim();
        if(Objects.equals(_wordToCount, ""))
        {
            return 0;
        }

        if (Objects.equals(_countType,"Words"))
        {
            String[] parts = this.SplitString(_wordToCount);
            return parts.length;
        }
        else if (Objects.equals(_countType,"Char"))
        {
            int charCount = 0;
            String[] parts = this.SplitString(_wordToCount);
            for (int i = 0; i < parts.length; i++)
            {
                charCount += parts[i].trim().length();
            }
            return charCount;
        }

        else if (Objects.equals(_countType,"Character and space"))
        {
            int charCount = 0;
            for (int i = 0; i < _wordToCount.length(); i++)
            {
                charCount++;
            }
            return charCount;
        }

        else if (Objects.equals(_countType,"Letter"))
        {
            int charCount = 0;

            for (int i = 0; i < _wordToCount.length(); i++)
            {
                if (Character.isLetter(_wordToCount.charAt(i))) {
                    charCount++;
                }
            }
            return charCount;
        }

        else if (Objects.equals(_countType,"Numbers"))
        {
            int charCount = 0;
            for (int i = 0; i < _wordToCount.length(); i++)
            {
                if (Character.isDigit(_wordToCount.charAt(i))) {
                    charCount++;
                }
            }
            return charCount;
        }
        else
        {
            return _wordToCount.length();
        }
    }

    public String[] SplitString(String _stringToSplit)
    {
        return _stringToSplit.split(" ");
    }

}
