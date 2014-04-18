LIBS_SOURCE_PATH=$HOME"/thesis/app/libs-source"
LIBS_BUILD_PATH=$HOME"/thesis/app/libs-build"
BUILD_SCRIPTS_PATH=$HOME"/thesis/app/build-scripts"

BUILD_PATH="$LIBS_BUILD_PATH/libgit2-arm-androideabi-build"
LIBGIT2_SOURCE_PATH="$LIBS_SOURCE_PATH/libgit2"
TOOLCHAIN_FILE_PATH="BUILD_SCRIPTS_PATH/Toolchain-arm-linux-androideabi-4.6.cmake"

if [ ! -d $BUILD_PATH ]; then
	mkdir $BUILD_PATH
fi

cp "$LIBGIT2_SOURCE_PATH/CMakeLists.txt" $BUILD_PATH
cd $BUILD_PATH
cmake $LIBGIT2_SOURCE_PATH -DCMAKE_TOOLCHAIN_FILE=$TOOLCHAIN_FILE_PATH -DANDROID=1 
cmake --build $BUILD_PATH