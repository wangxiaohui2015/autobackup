@echo off

echo=

cd %~dp0 %root%
cd ..\
set BASE_PATH="%cd%"
cd bin
cd %BASE_PATH%\jre${JRE_VERSION}\bin
java -jar %BASE_PATH%\lib\autobackup-restore-${PROJECT_VERSION}.jar

:end
    echo.
    echo Press any key to exit.

pause >nul