package com.jpexs.decompiler.flash.iggy.streams;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author JPEXS
 */
public class RandomAccessFileDataStream extends AbstractDataStream {

    private RandomAccessFile raf;
    private boolean is64;

    public RandomAccessFileDataStream(File file) throws FileNotFoundException {
        this(new RandomAccessFile(file, "rw"));
    }

    public RandomAccessFileDataStream(RandomAccessFile rafile) {
        this.raf = rafile;
    }

    @Override
    public Long available() {
        try {
            return raf.length() - raf.getFilePointer();
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public long position() {
        try {
            return raf.getFilePointer();
        } catch (IOException ex) {
            return -1;
        }
    }

    public void set64(boolean is64) {
        this.is64 = is64;
    }

    @Override
    public boolean is64() {
        return is64;
    }

    @Override
    public int read() throws IOException {
        int val = raf.read();
        if (val == -1) {
            throw new EOFException();
        }
        return val;
    }

    @Override
    public void seek(long pos, SeekMode mode) throws IOException {
        long newpos = pos;
        if (mode == SeekMode.CUR) {
            newpos = raf.getFilePointer() + pos;
        } else if (mode == SeekMode.END) {
            newpos = raf.length() - pos;
        }
        if (newpos > raf.length()) {
            throw new ArrayIndexOutOfBoundsException("Position outside bounds accessed: " + pos + ". Size: " + raf.length());
        } else if (newpos < 0) {
            throw new ArrayIndexOutOfBoundsException("Negative position accessed: " + pos);
        } else {
            raf.seek(newpos);
        }
    }

    @Override
    public void close() {
        try {
            raf.close();
        } catch (IOException ex) {
            //ignore
        }
    }

}
