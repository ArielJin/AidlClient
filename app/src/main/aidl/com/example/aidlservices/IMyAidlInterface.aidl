// IMyAidlInterface.aidl
package com.example.aidlservices;

import com.example.aidlservices.Num;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     int getSum(int a, int b);
     int getSumInNum(in Num n);
}
