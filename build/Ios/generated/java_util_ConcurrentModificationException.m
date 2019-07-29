#include "java_util_ConcurrentModificationException.h"


jobject mjava_util_ConcurrentModificationException__init____V(jobject pthis) {
	JvmThread* thread = JvmCurrentThread();
	jint entryFramePtr = thread->framePtr++;
	if(entryFramePtr >= JVM_MAX_STACK) JvmStackOverflow();
	JvmFrame* frame = &thread->frames[entryFramePtr];
	DEFARG(pthis,jobject,0);

	JVMMETHOD(java_util_ConcurrentModificationException_Class,0);
	JVMLINE(35)
	mjava_lang_RuntimeException__init____V(LOCAL(pthis,jobject));
	{ jobject $$ret = LOCAL(pthis,jobject); thread->framePtr = entryFramePtr; return $$ret; };
	thread->framePtr = entryFramePtr;
}

JvmClass java_util_ConcurrentModificationException_Class;
JvmClass ArrOf_java_util_ConcurrentModificationException_Class;
JvmClass ArrOf_ArrOf_java_util_ConcurrentModificationException_Class;

jbool java_util_ConcurrentModificationException_isChildOf(JvmClass* klass) {
	return klass == &java_util_ConcurrentModificationException_Class || klass == &java_lang_RuntimeException_Class || klass == &java_lang_Exception_Class || klass == &java_lang_Throwable_Class || klass == &java_io_Serializable_Class || klass == &java_lang_Object_Class;
}

extern jobject invoke_O(jobject,jobject,jobject);
void JvmSetup_java_util_ConcurrentModificationException() {
	static int initialized = 0;
	if(initialized) return;
	initialized = 1;
	JvmSetup_java_lang_RuntimeException();
	void** _vTable = malloc(sizeof(void*) * 27);
	_vTable[0] = &mjava_lang_Throwable_toString___Ljava_lang_String_;
	_vTable[1] = &mjava_lang_Object_equals__Ljava_lang_Object__Z;
	_vTable[2] = &mjava_lang_Object_hashCode___I;
	_vTable[26] = &mjava_lang_Object_finalize___V;
	void** _iTable = jnull;
	JvmClass* cls = &java_util_ConcurrentModificationException_Class;
	cls->klass = &java_lang_Class_Class;
	cls->size = sizeof(java_util_ConcurrentModificationException);
	cls->name = JvmMakeString(L"java.util.ConcurrentModificationException",41);
	cls->superClass = &java_lang_RuntimeException_Class;
	cls->componentType = jnull;
	cls->modifiers = 0;
	cls->fields = jnull;
	cls->vtable = _vTable;
	cls->itable = _iTable;
	cls->isChildOf = &java_util_ConcurrentModificationException_isChildOf;
	#ifdef JVM_DEBUG
	cls->sourceFile = JvmMakeString(L"ConcurrentModificationException.java",36);
	#endif
	cls->finalizeFunction = jnull;
	cls->objcClass = jnull;
	cls->interfaces = jnull;
	cls->methods = JvmMakeObjectArray(&ArrOf_java_lang_reflect_Method_Class, 1, &(JvmMethod*[]){
	JvmMakeMethod(&java_util_ConcurrentModificationException_Class, JvmMakeString(L"<init>", 6), &java_util_ConcurrentModificationException_Class, 1, JvmMakeObjectArray(&ArrOf_java_lang_Class_Class, 0, &(JvmClass*[]){}), &mjava_util_ConcurrentModificationException__init____V, &invoke_O 
#ifdef JVM_DEBUG
 , 35, 35, 0, 1, (JvmLocalVariableInfo[]){{.name=JvmMakeString(L"this",4), .startLine=35, .endLine=35, .type=&java_util_ConcurrentModificationException_Class}}
#endif
 )
	});
	cls->fields = JvmMakeObjectArray(&ArrOf_java_lang_reflect_Field_Class, 0, &(JvmField*[]){
	});

	cls = &ArrOf_java_util_ConcurrentModificationException_Class;
	cls->klass = &java_lang_Class_Class;
	cls->size = sizeof(JvmArray);
	cls->name = JvmMakeString(L"[java.util.ConcurrentModificationException",42);
	cls->superClass = &java_lang_Object_Class;
	cls->componentType = &java_util_ConcurrentModificationException_Class;
	cls->modifiers = 0;
	cls->fields = jnull;
	cls->interfaces = jnull;
	cls->methods = jnull;
	cls->vtable = java_lang_Object_Class.vtable;
	cls->isChildOf = jnull;
	cls = &ArrOf_ArrOf_java_util_ConcurrentModificationException_Class;
	cls->klass = &java_lang_Class_Class;
	cls->size = sizeof(JvmArray);
	cls->name = JvmMakeString(L"[[java.util.ConcurrentModificationException",42);
	cls->superClass = &java_lang_Object_Class;
	cls->componentType = &ArrOf_java_util_ConcurrentModificationException_Class;
	cls->modifiers = 0;
	cls->fields = jnull;
	cls->interfaces = jnull;
	cls->methods = jnull;
	cls->vtable = java_lang_Object_Class.vtable;
	cls->isChildOf = jnull;
}
