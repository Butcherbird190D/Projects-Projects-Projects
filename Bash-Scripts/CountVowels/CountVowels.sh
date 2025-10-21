#!/bin/bash

myString="$( cat sampletext.txt | tr [:upper:] [:lower:])"
acount=0
ecount=0
icount=0
ocount=0
ucount=0
charcount=0

while (( i++ < ${#myString} )); do
    char=$(expr substr "$myString" $i 1)
    charcount=$((charcount + 1))
        if [ "$char" == "a" ]; then
            acount=$((acount + 1))
        elif [ "$char" == "e" ]; then
            ecount=$((ecount + 1))
        elif [ "$char" == "i" ]; then
            icount=$((icount + 1))
        elif [ "$char" == "o" ]; then
            ocount=$((ocount + 1))
        elif [ "$char" == "u" ]; then
            ucount=$((ucount + 1))
        fi
done

vowelcount=$(($acount + $ecount + $icount + $ocount + $ucount))

echo "The total number of characters is ${charcount} with ${vowelcount} vowels."
echo "The breakdown of vowels (a,e,i,o,u) is: ${acount} a's, ${ecount} e's, ${icount} i's, ${ocount} o's, and ${ucount} u's"
