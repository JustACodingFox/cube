package com.example.android.mycubev2;

class Utility {

    public static boolean isPrime(int toCheck) {
        boolean isPrime = true;
        for (int divisor = 2; divisor <= toCheck / 2; divisor++) {
            if (toCheck % divisor == 0) {
                isPrime = false;
                break; // num is not a prime, no reason to continue checking
            }
        }
        return isPrime;
    }
}
