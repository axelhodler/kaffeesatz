#!/bin/sh

mvn exec:java -Dexec.mainClass="co.hodler.kaffeesatz.Main" -Dexec.args="$1"
