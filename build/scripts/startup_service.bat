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
        echo Service is already running.
        goto end
      ) else (
        goto startup_service
      )
    )

:startup_service
    echo Starting service...
    net start Autobackup >nul 2>&1
    if not %errorLevel% == 0 (
        echo Failure: Failed to startup service Autobackup.
        goto end
    ) else (
        echo Succeed to startup service Autobackup.
        goto end
    )

:end
    echo.
    echo Press any key to exit.

pause >nul