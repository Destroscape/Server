@echo off
cd src
cd ..
"C:\Users\Korey\Desktop\jdk\bin\javac" -d bin -cp deps/*; -sourcepath src src/game/Server.java
pause
