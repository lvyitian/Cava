#ifndef __Defined_java_lang_StringIndexOutOfBoundsException__
#define __Defined_java_lang_StringIndexOutOfBoundsException__

#include "jvm.h"
#include "java_lang_String.h"
#include "java_lang_StackTraceElement.h"
#include "java_lang_IndexOutOfBoundsException.h"
#include "java_lang_Class.h"
#include "java_lang_Throwable.h"

typedef struct java_lang_StringIndexOutOfBoundsException {
	jobject fjava_lang_Object_klass;
	jobject fjava_lang_Throwable_detailMessage;
	jobject fjava_lang_Throwable_cause;
	jobject fjava_lang_Throwable_stackTrace;
} java_lang_StringIndexOutOfBoundsException;


extern JvmClass java_lang_StringIndexOutOfBoundsException_Class;
extern JvmClass ArrOf_java_lang_StringIndexOutOfBoundsException_Class;
extern JvmClass ArrOf_ArrOf_java_lang_StringIndexOutOfBoundsException_Class;
extern void JvmSetup_java_lang_StringIndexOutOfBoundsException();

extern jobject mjava_lang_StringIndexOutOfBoundsException__init____V(jobject pthis);
extern jobject mjava_lang_StringIndexOutOfBoundsException__init___I_V(jobject pthis, jint pindex);

#endif
