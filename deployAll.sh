#!/bin/bash

git checkout  6.11.0
mvn versions:set -DnewVersion=6.11.0.2
mvn clean deploy

git checkout  6.12.0
mvn versions:set -DnewVersion=6.12.0.2
mvn clean deploy

git checkout  6.13.0
mvn versions:set -DnewVersion=6.13.0.2
mvn clean deploy

git checkout  6.14.0
mvn versions:set -DnewVersion=6.14.0.2
mvn clean deploy

git checkout  6.15.0
mvn versions:set -DnewVersion=6.15.0.2
mvn clean deploy

git checkout  6.16.0
mvn versions:set -DnewVersion=6.16.0.2
mvn clean deploy

git checkout  6.17.0
mvn versions:set -DnewVersion=6.17.0.2
mvn clean deploy

git checkout  6.18.0
mvn versions:set -DnewVersion=6.18.0.2
mvn clean deploy

git checkout  6.19.0
mvn versions:set -DnewVersion=6.19.0.2
mvn clean deploy


git checkout  6.20.0
mvn versions:set -DnewVersion=6.20.0.2
mvn clean deploy




