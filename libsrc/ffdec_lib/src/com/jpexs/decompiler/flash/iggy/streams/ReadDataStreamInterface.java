package com.jpexs.decompiler.flash.iggy.streams;

import java.io.IOException;

/**
 *
 * @author JPEXS
 */
public interface ReadDataStreamInterface extends AutoCloseable {

    /**
     * Available bytes
     *
     * @return null if unknown, long value otherwise
     */
    public Long available();

    public long position();

    public boolean is64();

    public long readUI64() throws IOException;

    public long readUI32() throws IOException;

    public int readUI16() throws IOException;

    public int readUI8() throws IOException;

    public int read() throws IOException;

    public byte[] readBytes(int numBytes) throws IOException;

    public float readFloat() throws IOException;

    public void seek(long pos, SeekMode mode) throws IOException;

    @Override
    public void close();
}
