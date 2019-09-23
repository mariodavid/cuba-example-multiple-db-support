#!/bin/sh
cd cuba-app
./gradlew buildWar
ls -la build/distributions/war/
cp build/distributions/war/*.war ../target-tomcat/webapps
