package com.zyl.packagex.version;

public class PackageVersionTest {
    public static void main(String[] args) {
        Package packageVersionTest = PackageVersionTest.class.getPackage();
        System.out.println(packageVersionTest != null ? packageVersionTest.getImplementationVersion() : null);
    }
}