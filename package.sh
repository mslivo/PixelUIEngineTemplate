if [ $# -eq 0 ]
  then
    echo "package.sh <version>"
	exit
fi


#COMMON
export APPNAME="uitemplate"

export SEVEN_ZIP_PATH="./buildtools/7z.exe"
export RCEDIT_PATH="./buildtools/rcedit-x64.exe"
export PACKR_PATH="./buildtools/packr-all.jar"
export JAR_NAME="uitemplate-1.0-jar-with-dependencies.jar"
export JAR_PATH="./desktop/target/${JAR_NAME}"
export MAIN_CLASS="net.mslivo.uitemplate.desktop.LauncherMain"

#WINDOWS
export WIN_ENABLED="true"
export DIR_WIN="temp/out_win"
export JDK_WIN="C:/Program Files/Java/win/jdk-21.0.1+12-jre" 
export ICON_WIN="./buildtools/appicon.ico"

#LINUX
export LINUX_ENABLED="true"
export DIR_LINUX="temp/out_linux"
export JDK_LINUX="C:/Program Files/Java/linux/jdk-21.0.1+12-jre" 

#MACOS
export MAC_ENABLED="true"
export DIR_MAC="temp/out_mac"
export JDK_MAC="C:/Program Files/Java/macos/jdk-21.0.1+12-jre" 
export ICON_MAC="./buildtools/appicon.icns"

# Init
rm -f -r "./temp"
rm -f "${APPNAME}_${1}_WIN.zip"
rm -f "${APPNAME}_${1}_LINUX.tar.gz"
rm -f "${APPNAME}_${1}_MAC.zip"

# WINDOWS
if [ "$WIN_ENABLED" = "true" ]; then
    echo "~~~~~~~~~~~~ Packaging WINDOWS ~~~~~~~~~~~~"
    java -jar "${PACKR_PATH}" \
         --platform windows64 \
         --jdk "${JDK_WIN}" \
         --useZgcIfSupportedOs \
         --executable "${APPNAME}" \
         --classpath "${JAR_PATH}" \
         --mainclass "${MAIN_CLASS}" \
         --vmargs "Xmx4G" \
		--output "${DIR_WIN}"
	cd ${DIR_WIN}
	#Set Icon
	echo "setting exe icon"
	"../../${RCEDIT_PATH}" "${APPNAME}.exe" --set-icon "../../${ICON_WIN}"
    #Create archive
	"../../${SEVEN_ZIP_PATH}" "a" "-tzip" "-r" "${APPNAME}.zip" .
    mv "./${APPNAME}.zip" "./../../${APPNAME}_${1}_WIN.zip"
    cd ../..
else
    echo "WINDOWS skipped"
fi

# LINUX
if [ "$LINUX_ENABLED" = "true" ]; then
    echo "~~~~~~~~~~~~ Packaging LINUX ~~~~~~~~~~~~"
    java -jar "${PACKR_PATH}" \
         --platform linux64 \
         --jdk "${JDK_LINUX}" \
         --useZgcIfSupportedOs \
         --executable "${APPNAME}" \
         --classpath "${JAR_PATH}" \
         --mainclass "${MAIN_CLASS}" \
         --vmargs "Xmx4G" \
         --output "${DIR_LINUX}"
    cd ${DIR_LINUX}
    #Create archive
    "../../${SEVEN_ZIP_PATH}" "a" "-ttar" "-r" "${APPNAME}.tar" .
    "../../${SEVEN_ZIP_PATH}" "a" "-tgzip" "-r" "${APPNAME}.tar.gz" "${APPNAME}.tar"
    mv "./${APPNAME}.tar.gz" "./../../${APPNAME}_${1}_LINUX.tar.gz"
    cd ../..
else
    echo "LINUX skipped"
fi

# MAC
if [ "$MAC_ENABLED" = "true" ]; then
    echo "~~~~~~~~~~~~ Packaging MAC ~~~~~~~~~~~~"
    java -jar "${PACKR_PATH}" \
         --platform mac \
         --jdk "${JDK_MAC}" \
         --useZgcIfSupportedOs \
         --executable "${APPNAME}" \
         --classpath "${JAR_PATH}" \
         --mainclass "${MAIN_CLASS}" \
         --vmargs "Xmx4G" "XstartOnFirstThread" \
		 --icon "${ICON_MAC}" \
         --output "${DIR_MAC}"
   cd ${DIR_MAC}
   #Create archive
   mkdir "./${APPNAME}.app"
   mv "./Contents" "./${APPNAME}.app/Contents"
    "../../${SEVEN_ZIP_PATH}" "a" "-ttar" "-r" "${APPNAME}.tar" .
    "../../${SEVEN_ZIP_PATH}" "a" "-tgzip" "-r" "${APPNAME}.tar.gz" "${APPNAME}.tar"
    mv "./${APPNAME}.tar.gz" "./../../${APPNAME}_${1}_MACOS.tar.gz"
    cd ../..
else
    echo "MAC skipped"
fi

# CLEANUP
rm -f -r "./temp"



