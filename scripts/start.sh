#!/usr/bin/env bash

PROJECT_ROOT="/home/ubuntu/app" #코드가 주입되는 경로
JAR_FILE="$PROJECT_ROOT/PersonalColorApp.jar" #build.gradle에서 설정한 파일명으로 변경
SCRIPT_DIR="$PROJECT_ROOT/src/main/java/Mo/PersonalColorBackend/ML/personal"

APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date +%c)

#echo "$TIME_NOW > python 패키지 설치" >> $DEPLOY_LOG
#nohup pip install -r requirements.txt

echo "$TIME_NOW > $JAR_FILE 파일 복사" >> $DEPLOY_LOG
cp $PROJECT_ROOT/build/libs/*.jar $JAR_FILE

chmod 777 $SCRIPT_DIR

echo "$TIME_NOW > $JAR_FILE 파일 실행" >> $DEPLOY_LOG
nohup java -jar $JAR_FILE > $APP_LOG 2> $ERROR_LOG &

CURRENT_PID=$(pgrep -f $JAR_FILE)
echo "$TIME_NOW > 실행된 프로세스 아이디 $CURRENT_PID 입니다." >> $DEPLOY_LOG

