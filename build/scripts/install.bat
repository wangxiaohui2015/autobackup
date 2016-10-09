@echo off

echo.
echo ============ WELCOME TO AUTOBACKUP INSTALLATION ============
echo.

timeout 1 >nul 2>&1

:: Get current directory path
cd %~dp0 %root%
set CURRENT_DIR_PATH=%cd%

:: Installation path
set INSTALL_PATH=${INSTALLATION_PATH}
set INSTALL_SERVICE_NAME=${INSTALLATION_SERVICE_NAME}

goto check_permissions

:check_permissions
    echo Checking permissions...
    net session >nul 2>&1
    if not %errorLevel% == 0 (
        echo Failure: Please run this command as administrator.
        goto end_unsuccessful
    ) else (
        goto check_installed
    )

:check_installed
    echo Checking installed status...
    if exist "%INSTALL_PATH%" (
        echo Failure: %INSTALL_SERVICE_NAME% has been installed already.
        goto end_unsuccessful
    ) else (
        goto confirm_install
    )

:confirm_install
    set /P INPUT=%INSTALL_SERVICE_NAME% will be installed under: "%INSTALL_PATH%", continue? (Y/N) 
    if /I "%INPUT%" EQU "Y" goto copy_components
    goto end_unsuccessful

:copy_components
    echo Copying files...
    xcopy /IEYQ "%CURRENT_DIR_PATH%\data" "%INSTALL_PATH%" >nul 2>&1
    if not %errorLevel% == 0 (
        echo Failure: Failed to copy files.
        goto end_unsuccessful
    ) else (
        goto install_service
    )

:install_service
    echo Installing service...
    :: Generate classpath
    @setlocal enabledelayedexpansion
    set jar_classpath=.
    set jar_dir=%INSTALL_PATH%\lib\
    @for %%c in ("%jar_dir%*.jar") do @set jar_classpath=!jar_classpath!;%%c
    :: Install service
    cd %INSTALL_PATH%\bin
    set SERVICE_DESC=This is the %INSTALL_SERVICE_NAME% service, you can backup anything on your computer.
    JavaService.exe -install "%INSTALL_SERVICE_NAME%" "%INSTALL_PATH%\jre${JRE_VERSION}\bin\server\jvm.dll" -Xmx512m -Djava.class.path="%jar_classpath%" -DrootDir="%INSTALL_PATH%" -start com.my.autobackup.backup.ServiceMain -method startService -stop com.my.autobackup.backup.ServiceMain -method stopService -out "%INSTALL_PATH%\logs\service_out.log" -err "%INSTALL_PATH%\logs\service_err.log" -current "%CD%" -auto -description "%SERVICE_DESC%" >nul 2>&1
    if not %errorLevel% == 0 (
        echo Failure: Failed to install service.
        goto end_unsuccessful
    ) else (
        goto end_successful
    )

:end_unsuccessful
    echo.
    echo Install %INSTALL_SERVICE_NAME% not completed.
    goto end

:end_successful
    echo.
    echo Install %INSTALL_SERVICE_NAME% successfully.
    echo Please goto "%INSTALL_PATH%" to configure %INSTALL_SERVICE_NAME% and startup service.
    goto end

:end
    echo.
    echo Press any key to exit.

pause >nul