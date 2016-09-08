@echo off

echo=

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
    for /F "tokens=3 delims=: " %%H in ('sc query "Autobackup" ^| findstr "        STATE"') do (
      if /I "%%H" EQU "RUNNING" (
        goto shutdown_service
      ) else (
        echo Service is already shutdown.
        goto end
      )
    )

:shutdown_service
    echo Stopping service...
    net stop Autobackup >nul 2>&1
    if not %errorLevel% == 0 (
        echo Failure: Failed to stop service Autobackup.
        goto end
    ) else (
        echo Succeed to stop service Autobackup.
        goto end
    )

:end
    echo.
    echo Press any key to exit.

pause >nul