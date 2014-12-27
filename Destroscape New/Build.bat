@echo off
cd src
cd ..
"C:\Users\Korey\Desktop\jdk\bin\java.exe" -d bin -cp deps/*; -sourcepath src src/game/Server.java
pause
