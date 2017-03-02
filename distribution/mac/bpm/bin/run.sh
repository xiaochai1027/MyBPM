#!/bin/sh
#本shell脚本只支持mac系统

# 每个节点修改这里就行
JPDA_ADDRESS=14009
APPLICATION_NAME=bpm

PRG="$0"
PRGDIR=`dirname "$PRG"`

BIN_DIR=$(pwd)

cd ../
CATALINA_BASE=$(pwd)

cd ~
USEDIR=$(pwd)

cd $BIN_DIR

source "${USEDIR}/i360r_setenv.sh"

CATALINA_HOME=$TOMCAT_HOME
_RUNJAVA="${JAVA_HOME}/bin/java"
LOGGING_CONFIG="-Djava.util.logging.config.file=$TOMCAT_HOME/conf/logging.properties"
LOGGING_MANAGER="-Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager"
JAVA_ENDORSED_DIRS="${CATALINA_HOME}/endorsed"
CATALINA_TMPDIR="${CATALINA_BASE}/temp"
JPDA_OPTS="-agentlib:jdwp=transport=dt_socket,address=${JPDA_ADDRESS},server=y,suspend=n"
JAVA_OPTS="$JAVA_OPTS $LOGGING_MANAGER -Dfile.encoding=UTF-8" 

if [ ! -z "$CLASSPATH" ] ; then
  CLASSPATH="$CLASSPATH":
fi

CLASSPATH="$CLASSPATH""$CATALINA_HOME"/bin/bootstrap.jar
CATALINA_OUT="$CATALINA_BASE"/PLTLOG/${APPLICATION_NAME}.log
CATALINA_TMPDIR="$CATALINA_BASE"/temp
CLASSPATH=$CLASSPATH:$CATALINA_BASE/bin/tomcat-juli.jar
CLASSPATH=$CLASSPATH:$CATALINA_HOME/bin/tomcat-juli.jar

CATALINA_OPTS="$CATALINA_OPTS $JPDA_OPTS"

eval "\"$_RUNJAVA\"" "\"$LOGGING_CONFIG\"" $LOGGING_MANAGER $JAVA_OPTS $CATALINA_OPTS \
	      -Djava.endorsed.dirs="\"$JAVA_ENDORSED_DIRS\"" -classpath "\"$CLASSPATH\"" \
	      -Dcatalina.base="\"$CATALINA_BASE\"" \
	      -Dcatalina.home="\"$CATALINA_HOME\"" \
	      -Djava.io.tmpdir="\"$CATALINA_TMPDIR\"" \
	      org.apache.catalina.startup.Bootstrap "$@" start \
	      >> "$CATALINA_OUT" 2>&1 "&"
	
if [ ! -z "$CATALINA_PID" ]; then
  echo $! > "$CATALINA_PID"
fi

echo ${CATALINA_PID}