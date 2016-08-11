@echo off

echo=

net stop Autobackup

cd %~dp0 %root%
JavaService.exe -uninstall Autobackup

pause