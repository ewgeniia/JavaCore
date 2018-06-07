@echo off
set COMPILE=mvn package
set PROGRAM=java -cp target/Supermarket-1.0.jar com.App

%PROGRAM%
