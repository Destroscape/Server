@echo off
title Server
java -Xmx512m -cp bin;deps/gson-2.2.2.jar;deps/gson-2.2.2-sources.jar;deps/netty.jar;deps/xstream.jar;deps/slf4j.jar;deps/slf4j-nop.jar;deps/poi.jar;deps/mysql.jar;deps/mina.jar;deps/log4j-1.2.15.jar;deps/jython.jar; game.Server
pause