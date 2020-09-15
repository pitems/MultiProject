package com.pitems.livedata.utils

import android.text.Editable
import android.text.TextUtils

 fun inputCheck(firsName:String, lastName:String, age: Editable):Boolean{
    return !(TextUtils.isEmpty(firsName) && TextUtils.isEmpty(lastName) && age.isEmpty())
}