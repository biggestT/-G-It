LOCAL_PATH := $(call my-dir)
APP_OPTIM := debug
APP_ABI := armeabi-v7a

include $(CLEAR_VARS)
LOCAL_MODULE := gpg-error
LOCAL_SRC_FILES := $(LOCAL_PATH)/lib/libgpg-error.a
include $(PREBUILT_STATIC_LIBRARY)
 
include $(CLEAR_VARS)
LOCAL_MODULE := crypto
LOCAL_SRC_FILES := $(LOCAL_PATH)/lib/libcrypto.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := ssl
LOCAL_SRC_FILES := $(LOCAL_PATH)/lib/libssl.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := ssh2
LOCAL_SRC_FILES := $(LOCAL_PATH)/lib/libssh2.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := git2
LOCAL_SRC_FILES := $(LOCAL_PATH)/lib/libgit2.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := com_thingsbook_it_LibGit2
LOCAL_SRC_FILES := com_thingsbook_it_LibGit2.c
LOCAL_C_INCLUDES := $(LOCAL_PATH)/include
LOCAL_STATIC_LIBRARIES := git2 ssh2 ssl crypto gpg-error
LOCAL_LDLIBS := -lz -llog
include $(BUILD_SHARED_LIBRARY)
