@echo off
cd src
cd ..
"C:\Program Files\Java\jdk1.8.0_25\bin\javac" -d bin -cp deps/*; -sourcepath src src/game/Server.java
pause