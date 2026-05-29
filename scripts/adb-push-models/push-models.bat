@echo off
REM Generic ADB model push script for Windows
REM Push your local model folders to device storage.
REM Update MODEL_FOLDERS below to match your local setup.

set PACKAGE_NAME=com.unoone.agent
set DEVICE_MODEL_PATH=/sdcard/Android/data/%PACKAGE_NAME%/files/models
set LOCAL_MODELS_ROOT=..\..\models

REM Add your model folder names here
REM set MODEL_FOLDERS=local-llm speech-recognition speech-synthesis voice-activity

echo ==========================================
echo  Model Push Script
echo ==========================================
echo.

REM Check adb devices
echo Checking ADB devices...
adb devices | findstr "device$" >nul
if errorlevel 1 (
    echo ERROR: No device found. Connect your phone and enable USB debugging.
    exit /b 1
)
echo OK: Device connected.
echo.

REM Push each model folder
REM for %%f in (%MODEL_FOLDERS%) do (
REM     if exist "%LOCAL_MODELS_ROOT%\%%f" (
REM         echo Pushing %%f...
REM         adb shell mkdir -p %DEVICE_MODEL_PATH%/%%f
REM         adb push "%LOCAL_MODELS_ROOT%\%%f" %DEVICE_MODEL_PATH%/%%f
REM         echo OK: %%f pushed.
REM     ) else (
REM         echo WARN: %%f not found locally. Skipping.
REM     )
REM     echo.
REM )

echo ==========================================
echo  Update MODEL_FOLDERS in this script
echo  to match your local model directories.
echo ==========================================
pause
