from os.path import exists
import sys
import math

def CalculateStatistics(_userInput):
    dictionary = {}
    for idx in range(0, len(_userInput)):
        currValue = dictionary.get(_userInput[idx])
        if(currValue is not None and currValue > 0):
            dictionary[_userInput[idx]] = currValue + 1
        else:
            dictionary[_userInput[idx]] = 1

    mergeddictionary = dictionary
    HighestOccurrence = -1
    HighestOccurrenceLetter = ""
    LowestOccurrence = 9999999999999999999
    LowestOccurrenceLetter = ""
    writeText = "<---PYTHON--->\n"
    writeText = "Last word to calculate: {word}\n".format(word = _userInput)
    mergedValues = mergeddictionary.items()
    for value in mergedValues:
        currValue = mergeddictionary.get(value[0])

        if (currValue is not None and currValue > HighestOccurrence):
            HighestOccurrence = currValue
            HighestOccurrenceLetter = value[0]

        if (currValue is not None and currValue == HighestOccurrence):
            if (value[0] not in HighestOccurrenceLetter):
                HighestOccurrenceLetter += "," + value[0]
        
        if (currValue is not None and currValue < LowestOccurrence):
            LowestOccurrence = currValue
            LowestOccurrenceLetter = value[0]
        
        if (currValue is not None and currValue == LowestOccurrence):
            if (value[0] not in LowestOccurrenceLetter):
                LowestOccurrenceLetter += "," + value[0]
        writeText += "Letter/Number {letter} used {used} times in total\n".format(letter = value[0], used = currValue)
    return writeText + "\n\nMost used letter: {letter}, least used letter: {leastUsed}\n<---PYTHON--->\n".format(letter = HighestOccurrenceLetter, leastUsed = LowestOccurrenceLetter)

def CalculatePresentValueOfAnnuity(_pMT, _r, _n):

    #https://www.annuity.org/selling-payments/present-value/
    pmt = float(_pMT)
    r = int(_r)
    n = int(_n)
    discountPercent = r / 100
    fractionFractionValue = 1 / (math.pow(1+discountPercent, int(n)))
    topFractionValue =  1 - fractionFractionValue
    mainFractionValue = topFractionValue / discountPercent
    PresentValue = float(pmt) * mainFractionValue
    return PresentValue

    # Lets say your structured settlement pays you $1,000 a year for 10 years.
    # If you keep all your payments, you will eventually receive $10,000.
    # But what if you lose your job and need more than $1,000 a year to cover your expenses?
    # Lets assume you want to sell five years worth of payments, or $5,000, and the factoring company applies a 10 percent discount rate.
    # In this example,
    # PMT= $1,000
    # r= 10 percent, represented as 0.10
    # n= 5 (one payment each year for five years)
    # Therefore, the present value of five $1,000 structured settlement payments is worth roughly $3,790.75 when a 10 percent discount rate is applied.
    # If you simply subtracted 10 percent from $5,000, you would expect to receive $4,500. However, this does not account for the time value of money, which says payments are worth less and less the further 
    # into the future they exist. Thats why the present value of an annuity formula is a useful tool.