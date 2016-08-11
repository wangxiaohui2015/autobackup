@echo off

echo=

net stop Autobackup

%~dp0/JavaService.exe -uninstall Autobackup

pause