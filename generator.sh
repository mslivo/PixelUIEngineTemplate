if [ $# -eq 0 ]
  then
    echo "generator.sh <projectname>"
	exit
fi

export SEVEN_ZIP_PATH="./buildtools/7z.exe"
export PROJECTNAME="$1"


# cleanup
rm -r ./temp 2> /dev/null
mkdir ./temp

# assets
cp -r "./assets" "./temp/assets"

# buildtools
cp -r "./buildtools" "./temp/buildtools"

# core
mkdir "./temp/core"
cp "./core/pom.xml" "./temp/core/pom.xml"
cp -r "./core/src" "./temp/core/src"
mv "./temp/core/src/main/java/net/mslivo/uitemplate" "./temp/core/src/main/java/net/mslivo/$PROJECTNAME"

# desktop
mkdir "./temp/desktop"
cp "./desktop/pom.xml" "./temp/desktop/pom.xml"
cp -r "./desktop/src" "./temp/desktop/src"
mv "./temp/desktop/src/main/java/net/mslivo/uitemplate" "./temp/desktop/src/main/java/net/mslivo/$PROJECTNAME"

# single files
cp "./.gitignore" "./temp/.gitignore"
cp "./package.sh" "./temp/package.sh"
cp "./pom.xml" "./temp/pom.xml"
cp "./README.md" "./temp/README.md"

# replace strings
find "./temp" -type f -name "*.java" -exec sed -i "s/uitemplate/$PROJECTNAME/g" {} +
find "./temp" -type f -name "pom.xml" -exec sed -i "s/uitemplate/$PROJECTNAME/g" {} +
sed -i "s/uitemplate/$PROJECTNAME/g" "./temp/package.sh"

# zip
cd "./temp"
"./../${SEVEN_ZIP_PATH}" "a" "-tzip" "-r" "$PROJECTNAME.zip" .
mv "./$PROJECTNAME.zip" "./../$PROJECTNAME.zip"

# cleanup
cd "./.."
rm -r ./temp 2> /dev/null
