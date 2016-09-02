@echo off

echo.
echo ============ WELCOME TO AUTOBACKUP INSTALLATION ============
echo.

:: Get current directory path
cd %~dp0 %root%
set CURRENT_DIR_PATH=%cd%

:: Installation path
set INSTALL_PATH=C:\Program Files\autobackup

goto check_permissions

:check_permissions
    echo Checking permissions...
    net session>nul 2>&1
    if not %errorLevel% == 0 (
        echo Failure: Please run this command as administrator.
        goto end
    ) else (
        goto check_installed
    )

:check_installed
    echo Checking installed status...
    if exist "%INSTALL_PATH%" (
        echo Failure: Autobackup has been installed already.
        goto end
    ) else (
        goto copy_components
    )

:copy_components
    echo Copying files...
    xcopy /IEYQ "%CURRENT_DIR_PATH%\data" "%INSTALL_PATH%"
    if not %errorLevel% == 0 (
        echo Failure: Failed to copy files.
        goto end
    ) else (
        goto initialize_database
    )

:setup_environment_variables
    setx /M PATH "%PATH%;%INSTALL_PATH%\bin"
    if not %errorLevel% == 0 (
        echo Failure: Failed to setup environment variables.
        goto end
    ) else (
        goto initialize_database
    )

:initialize_database
    echo Initializing database...
    cd %INSTALL_PATH%\bin
    sqlite3 ../db/autobackup.db < ../db/initdb.sql
    if not %errorLevel% == 0 (
        echo Failure: Failed to initialize database
        goto end
    ) else (
        del "%INSTALL_PATH%\db\initdb.sql"
        goto install_service
    )
    
:install_service
    
    
:end
pause > nul