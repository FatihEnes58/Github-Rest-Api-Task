import writers.CsvWriter;
import writers.Writer;

public class WriterFactory {
    private static WriterFactory writerFactory;
    private Writer writer;

    public Writer getWriter() {
        if(writer == null)
        {
            writer = new CsvWriter();
        }
        return writer;
    }

    static {
        writerFactory = new WriterFactory();
    }

    public static WriterFactory getFactoryInstance() {
        return writerFactory;
    }
}
