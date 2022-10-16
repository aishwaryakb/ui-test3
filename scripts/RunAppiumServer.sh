#!/bin/bash
set -ex
npm install appium --location=global
appium -v
appium &>/dev/null &