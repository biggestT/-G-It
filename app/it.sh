DEBUG_PORT=8700
PACKAGE_NAME="com.thingsbook.it"
TAG_NAME="ItApplication"
ACTIVITY=.MainActivity
SOURCE_PATH="$(pwd)/src/"
echo $SOURCE_PATH


buildJNI() 
{
	printMessage "Compiling native code"
	cd jni/
	ndk-build
	cd ..
}

debugApplication() 
{
	printMessage "Stopping app if it is running already ..."
	adb shell pm clear $PACKAGE_NAME
	printMessage "Starting application on phone"
	adb shell am start -e debug true -n $PACKAGE_NAME/$ACTIVITY
	# sleep 1
	
	if [ "$1" == "logcat" ]; then
	# LOGCAT DEBUGGING
	adb logcat -c
	PID=$(adb shell ps | grep ${PACKAGE_NAME} | cut -c10-15)
	echo "package tag name: $TAG_NAME"
	echo "PID: $PID"
	adb logcat -C | egrep "($PID|$TAG_NAME)"
  else 
  #  JDB DEBUGGING
  JDWP_ID=$(adb jdwp | tail -1)
	echo "JDWP_ID: $JDWP_ID"
	adb forward tcp:7777 jdwp:$JDWP_ID
	jdb -sourcepath $SOURCE_PATH -attach localhost:7777
fi
}

installApplication() 
{
	printMessage "Building debug version of application with ant"
	ant debug

	printMessage "Uninstalling old version of application"
	adb uninstall com.thingsbook.it

	printMessage "Installing new version of application"
	adb install ~/thesis/app/bin/it-debug.apk	
}

printMessage()
{
	echo -e "\n${1} \n=================="
}

case "$1" in 
	"jni") buildJNI
	;;
	"debug") debugApplication $2
	;;
	"install") installApplication
	;;
	"id") installApplication
	debugApplication
	;;
esac




# echo -e "\nDebug logging using logcat \n============"
# adb logcat -c 
# adb logcat -C -s com.thingsbook.it:I *:I

