@echo off

echo=

:install
cd %~dp0 %root%
cd ..\
set BASE_PATH="%cd%"
cd bin
set SERVICE_DESC=This is the Autobakup service, you can backup anything on your computer.
JavaService.exe -install "Autobackup" "%BASE_PATH%\jre%%JRE_VERSION%%\bin\server\jvm.dll" -Xmx1024m -Djava.class.path=%BASE_PATH%\lib\autobackup-backup-1.1.0-RELEASE.jar -DrootDir="%BASE_PATH%" -start com.my.autobackup.backup.ServiceMain -method startService -stop com.my.autobackup.backup.ServiceMain -method stopService -out "%BASE_PATH%\logs\service_out.log" -err "%BASE_PATH%\logs\service_err.log" -current "%CD%" -auto -description "%SERVICE_DESC%"

:end

echo=
pause