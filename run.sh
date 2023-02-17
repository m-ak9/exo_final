#!/bin/bash
mvn clean package
mvn dependency:copy-dependencies
for i in {1..5}
do
    java -cp target/classes:target/dependency/* Main add -d :2022-03-01 -c "finalize the agenda exercise"
    java -cp target/classes:target/dependency/* Main update 0 -d :2022-04-01
    java -cp target/classes:target/dependency/* Main update 0 -s :done
    java -cp target/classes:target/dependency/* Main list
    java -cp target/classes:target/dependency/* Main remove 0
    java -cp target/classes:target/dependency/* Main add -d :2022-03-01 -c "finalize the agenda exercise"
    java -cp target/classes:target/dependency/* Main update 0 -d :2022-04-01
    java -cp target/classes:target/dependency/* Main update 0 -s :done
done
