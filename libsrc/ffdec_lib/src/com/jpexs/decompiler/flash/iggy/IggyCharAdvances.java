package com.jpexs.decompiler.flash.iggy;

import com.jpexs.decompiler.flash.iggy.streams.StructureInterface;
import com.jpexs.decompiler.flash.iggy.streams.AbstractDataStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JPEXS
 */
public class IggyCharAdvances implements StructureInterface {

    List<Float> advances;
    private long charCount;

    public List<Float> getScales() {
        return advances;
    }

    public IggyCharAdvances(AbstractDataStream stream, long charCount) throws IOException {
        this.charCount = charCount;
        readFromDataStream(stream);
    }

    @Override
    public void readFromDataStream(AbstractDataStream stream) throws IOException {
        advances = new ArrayList<>();
        for (int i = 0; i < charCount; i++) {
            advances.add(stream.readFloat());
        }
    }

    @Override
    public void writeToDataStream(AbstractDataStream stream) throws IOException {
        for (int i = 0; i < advances.size(); i++) {
            stream.writeFloat(advances.get(i));
        }
    }
}
