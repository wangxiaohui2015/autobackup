@echo off

echo=

set INSTALL_SERVICE_NAME=${INSTALLATION_SERVICE_NAME}

goto check_permissions

:check_permissions
    echo Checking permissions...
    net session >nul 2>&1
    if not %errorLevel% == 0 (
        echo Failure: Please run this command as administrator.
        goto end
    ) else (
        goto check_service
    )

:check_service
    for /F "tokens=3 delims=: " %%H in ('sc query "%INSTALL_SERVICE_NAME%" ^| findstr "        STATE"') do (
      if /I "%%H" EQU "RUNNING" (
        goto shutdown_service
      ) else (
        echo Service is already shutdown.
        goto end
      )
    )

:shutdown_service
    echo Stopping service...
    net stop %INSTALL_SERVICE_NAME% >nul 2>&1
    if not %errorLevel% == 0 (
        echo Failure: Failed to stop service %INSTALL_SERVICE_NAME%.
        goto end
    ) else (
        echo Succeed to stop service %INSTALL_SERVICE_NAME%.
        goto end
    )

:end
    echo.
    echo Press any key to exit.

pause >nul