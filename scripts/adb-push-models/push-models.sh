#!/bin/bash
# Generic ADB model push script
# Push your local model folders to device storage.
# Update MODEL_FOLDERS below to match your local setup.

set -e

PACKAGE_NAME="com.unoone.agent"
DEVICE_MODEL_PATH="/sdcard/Android/data/${PACKAGE_NAME}/files/models"
LOCAL_MODELS_ROOT="../../models"

# Add your model folder names here
MODEL_FOLDERS=(
    # "local-llm"
    # "speech-recognition"
    # "speech-synthesis"
    # "voice-activity"
)

echo "=========================================="
echo " Model Push Script"
echo "=========================================="
echo

# Check adb devices
echo "Checking ADB devices..."
if ! adb devices | grep -q "device$"; then
    echo "ERROR: No device found. Connect your phone and enable USB debugging."
    exit 1
fi
echo "OK: Device connected."
echo

# Push each model folder
for folder in "${MODEL_FOLDERS[@]}"; do
    if [ -d "${LOCAL_MODELS_ROOT}/${folder}" ]; then
        echo "Pushing ${folder}..."
        adb shell mkdir -p "${DEVICE_MODEL_PATH}/${folder}"
        adb push "${LOCAL_MODELS_ROOT}/${folder}" "${DEVICE_MODEL_PATH}/${folder}"
        echo "OK: ${folder} pushed."
    else
        echo "WARN: ${folder} not found locally. Skipping."
    fi
    echo
done

echo "=========================================="
echo " Model push complete."
echo "=========================================="
