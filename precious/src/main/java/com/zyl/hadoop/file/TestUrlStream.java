package com.zyl.hadoop.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.hadoop.io.IOUtils;

public class TestUrlStream {
    public static void main(String[] args) {
        InputStream in = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine();
            in = new URL(line).openStream();
            IOUtils.copyBytes(in, System.out, 2048, true);
        } catch ( MalformedURLException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            org.apache.commons.io.IOUtils.closeQuietly(in);
        }
    }
}