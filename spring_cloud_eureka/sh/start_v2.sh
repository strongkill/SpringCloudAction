#!/bin/sh

appName=eureka_server
pidFile="$appName.pid"

start(){
    if [ -f "$pidFile" ]; then pid=$(cat "$pidFile")
        echo "STOP pid:$pid"
        kill -9 $pid
        rm "$pidFile"
    fi  nohup java -jar -Xms128m -Xmx512m $appName.jar > /dev/null 2>&1 &
    echo $! > "$pidFile"
}

stop(){
   if [ -f "$pidFile" ]; then pid=$(cat "$pidFile")
        echo "STOP pid:$pid"
        kill -9 $pid
        rm "$pidFile"
    fi
}

case "$1" in  start)
    start
  ;;
  stop)
    stop
  ;;
  *)
    printf 'Usage: %s {start|stop}\n' "$prog"
    exit 1
  ;;
esac