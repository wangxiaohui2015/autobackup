@echo off

echo=

if not "%JAVA_HOME%" == "" goto gotJdkHome
echo Please set JAVA_HOME
goto end

:gotJdkHome
if not exist "%JAVA_HOME%\jre\bin\javaw.exe" goto noJavaHome
goto install

:noJavaHome
echo Cannot find javaw.exe in JAVA_HOME
goto end

:install
cd ../bin
set SERVICE_DESC=This is the Autobakup service, you can backup anything on your computer.
JavaService.exe -install "Autobackup" "%JAVA_HOME%/jre/bin/server/jvm.dll" -Xmx1024m -Djava.class.path=../lib/autobackup-backup-0.0.1-RELEASE.jar -DrootDir="%CD%/.." -start com.my.autobackup.backup.ServiceMain -method startService -stop com.my.autobackup.backup.ServiceMain -method stopService -out "%CD%/../logs/service_out.log" -err "%CD%/../logs/service_err.log" -current "%CD%" -auto -description "%SERVICE_DESC%"

:end

echo=
pause