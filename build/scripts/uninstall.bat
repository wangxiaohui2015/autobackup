@echo off

echo.
echo ============ Uninstall AUTOBACKUP ============ 
echo.

set INSTALL_PATH=${INSTALLATION_PATH}
set INSTALL_SERVICE_NAME=${INSTALLATION_SERVICE_NAME}

timeout 1 >nul 2>&1

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
        goto confirm_uninstall
    ) else (
        echo Failure: %INSTALL_SERVICE_NAME% has not been installed yet.
        goto end_unsuccessful
    )

:confirm_uninstall
    set /P INPUT=Are you sure to uninstall %INSTALL_SERVICE_NAME%? (Y/N) 
    if /I "%INPUT%" EQU "Y" goto stop_service
    goto end_unsuccessful

:stop_service
    echo Stopping service...
    for /F "tokens=3 delims=: " %%H in ('sc query "%INSTALL_SERVICE_NAME%" ^| findstr "        STATE"') do (
      if /I "%%H" EQU "RUNNING" (
        net stop %INSTALL_SERVICE_NAME% >nul 2>&1
        if not %errorLevel% == 0 (
            echo Failure: Failed to stop %INSTALL_SERVICE_NAME% service.
            goto end_unsuccessful
        ) else (
            goto remove_service
        )
      ) else (
            goto remove_service
      )
    )

:remove_service
    echo Removing service...
    sc delete %INSTALL_SERVICE_NAME% >nul 2>&1
    if not %errorLevel% == 0 (
        echo Failure: Failed to remove %INSTALL_SERVICE_NAME% service.
        goto end_unsuccessful
    ) else (
        goto delete_files
    )

:delete_files
    echo Deleting files...
    rd /S /Q "%INSTALL_PATH%"  >nul 2>&1
    if not %errorLevel% == 0 (
        echo Failure: Failed to delete files.
        goto end_unsuccessful
    ) else (
        goto end_successful
    )

:end_unsuccessful
    echo.
    echo Uninstall %INSTALL_SERVICE_NAME% not completed.
    goto end

:end_successful
    echo.
    echo Uninstall %INSTALL_SERVICE_NAME% successfully.
    goto end

:end
    echo.
    echo Press any key to exit.

pause >nul