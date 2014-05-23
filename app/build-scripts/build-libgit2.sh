#!/bin/bash

# get the parent directory of this script
ROOT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd | sed 's/\/[a-z-]*$//')"

echo "-- root folder: ${ROOT}"

# Folder setup
LIBS_SOURCE_PATH="$ROOT/libs-source"
LIBS_BUILD_PATH="$ROOT/libs-build"
LIBS_INSTALL_PATH="$ROOT/jni"
BUILD_SCRIPTS_PATH="$ROOT/build-scripts"
TOOLCHAIN_PATH="$ROOT/toolchains/arm-linux-androideabi-4.6v2"

EXTERNAL_COMPILED_LIBS="$ROOT/cmoss/tmp/build/droid/arm-linux-androideabi/lib/*.a"
EXTERNAL_COMPILED_INCLUDES="$ROOT/cmoss/tmp/build/droid/arm-linux-androideabi/include/*"

# Path to folder with (NDK generated) build tools (e.g compilers) for the android arm platform
TOOLCHAIN_FILE_PATH="$BUILD_SCRIPTS_PATH/Toolchain-arm-linux-androideabi-4.6.cmake"

# Folders containing libgit2 and jagged input (source) and output (build) files
LIBGIT2_BUILD_PATH="$LIBS_BUILD_PATH/libgit2-arm-androideabi-build"
LIBGIT2_SOURCE_PATH="$LIBS_SOURCE_PATH/libgit2"

# copy open source libs compiled for android with cmoss scripts
echo "-- copying external statically linked libraries"
cp $EXTERNAL_COMPILED_LIBS "$ROOT/jni/lib/"
cp $EXTERNAL_COMPILED_LIBS "$TOOLCHAIN_PATH/sysroot/usr/lib/"
echo "-- copying header files for external statically linked libraries "
cp $EXTERNAL_COMPILED_INCLUDES "$ROOT/jni/include" -r
cp $EXTERNAL_COMPILED_INCLUDES "$TOOLCHAIN_PATH/sysroot/usr/include" -r

# clean up directories and remove cache file
if [[ $1 == "clean" && -f $LIBGIT2_BUILD_PATH/CMakeCache.txt ]]; then
 rm $LIBGIT2_BUILD_PATH/* -r
fi

# make p_chmod a no-op, android does not allow us to change file permission modes 
echo "-- removing chmod operation"
LIBGIT2_POSIX_PATH="$LIBGIT2_SOURCE_PATH/src/posix.h"
LIBGIT2_POSIX_BACKUP_PATH="$LIBGIT2_SOURCE_PATH/src/posix_original.h"
printf "#include \"always_true.h\"\nint always_true() { return 1; }" > "$LIBGIT2_SOURCE_PATH/src/always_true.c"
printf "int always_true();" > "$LIBGIT2_SOURCE_PATH/src/always_true.h"
cp $LIBGIT2_POSIX_PATH "$LIBGIT2_POSIX_BACKUP_PATH"
sed -i "s/^#define\sp_chmod(p, m).*$/#include \"always_true.h\"\n#define p_chmod(p, m) always_true()\nextern int always_true();\n/" $LIBGIT2_POSIX_PATH

echo "-- generating libgit2 Cmake files"
cp "$LIBGIT2_SOURCE_PATH/CMakeLists.txt" $LIBGIT2_BUILD_PATH

# currently buidling without threadsafety because of unmatched dependencies, not sure why
cd $LIBGIT2_BUILD_PATH
cmake -DCMAKE_TOOLCHAIN_FILE=$TOOLCHAIN_FILE_PATH \
	-DANDROID=1  \
  -DBUILD_SHARED_LIBS=0 \
  -DTHREADSAFE=1 \
  -DBUILD_CLAR=0 \
  -DCMAKE_INSTALL_PREFIX=$LIBS_INSTALL_PATH \
  $LIBGIT2_SOURCE_PATH 

echo "-- building libgit2 and install"
cmake --build $LIBGIT2_BUILD_PATH --target install

# restore chmod manipulated source header
mv $LIBGIT2_POSIX_BACKUP_PATH $LIBGIT2_POSIX_PATH
