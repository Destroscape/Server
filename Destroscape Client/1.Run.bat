@echo off
Title Console
color 0e
cd bin
java -cp .; -Xmx512m com.Client 30 0 highmem members 32
pause