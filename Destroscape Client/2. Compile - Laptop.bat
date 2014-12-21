@echo off
title Compile Bärbara
color 0e

ECHO Running With JDK v1.7.0_07
"C:\Program Files\Java\jdk1.7.0_07\bin\javac.exe" -cp bin -d bin src/com/*.java src/com/rsinterface/*.java src/com/cache/*.java src/com/constants/*.java src/com/entity/*.java src/com/floor/*.java src/com/object/*.java src/com/item/*.java

pause