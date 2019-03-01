package com.samsung.android.lxd.processor.utils;

import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class IOExceptionHandler {

    private static class ExceptionChain extends RuntimeException {
        private ArrayList<Exception> exceptions;

        public ExceptionChain(ArrayList<Exception> arrayList) {
            String stringBuilder;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Exception Chain contains ");
            StringBuilder stringBuilder3;
            if (arrayList.size() > 1) {
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(arrayList.size());
                stringBuilder3.append(" exceptions");
                stringBuilder = stringBuilder3.toString();
            } else {
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(arrayList.size());
                stringBuilder3.append(" exception");
                stringBuilder = stringBuilder3.toString();
            }
            stringBuilder2.append(stringBuilder);
            super(stringBuilder2.toString(), (Throwable) arrayList.get(0));
            this.exceptions = arrayList;
        }

        public void printStackTrace(PrintStream printStream) {
            synchronized (printStream) {
                super.printStackTrace(printStream);
                int i = 0;
                Iterator it = this.exceptions.iterator();
                while (it.hasNext()) {
                    Exception exception = (Exception) it.next();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Exception ");
                    i++;
                    stringBuilder.append(i);
                    stringBuilder.append(" :");
                    printStream.println(stringBuilder.toString());
                    exception.printStackTrace(printStream);
                }
            }
        }

        public void printStackTrace(PrintWriter printWriter) {
            synchronized (printWriter) {
                super.printStackTrace(printWriter);
                int i = 0;
                Iterator it = this.exceptions.iterator();
                while (it.hasNext()) {
                    Exception exception = (Exception) it.next();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Exception ");
                    i++;
                    stringBuilder.append(i);
                    stringBuilder.append(" :");
                    printWriter.println(stringBuilder.toString());
                    exception.printStackTrace(printWriter);
                }
            }
        }

        public void chainException(Exception exception) {
            this.exceptions.add(exception);
        }

        public int size() {
            return this.exceptions.size();
        }
    }

    public static abstract class IOProcessor<T> {
        public abstract void close(T t);

        public void flush(T t) {
        }

        public abstract T open();

        public abstract void process(T t);

        public void sync(T t) {
        }
    }

    public static <S, T extends IOProcessor<S>> void process(T t) {
        ArrayList arrayList = new ArrayList();
        Object open;
        try {
            open = t.open();
            try {
                t.process(open);
                try {
                    t.flush(open);
                } catch (IOException e) {
                    arrayList.add(e);
                }
                try {
                    t.sync(open);
                } catch (IOException e2) {
                    arrayList.add(e2);
                }
            } catch (IOException e22) {
                arrayList.add(e22);
                try {
                    t.flush(open);
                } catch (IOException e222) {
                    arrayList.add(e222);
                }
                try {
                    t.sync(open);
                } catch (IOException e2222) {
                    arrayList.add(e2222);
                }
            }
            t.close(open);
        } catch (IOException e3) {
            arrayList.add(e3);
        } catch (Throwable th) {
            try {
                t.flush(open);
            } catch (IOException e4) {
                arrayList.add(e4);
            }
            try {
                t.sync(open);
            } catch (IOException e42) {
                arrayList.add(e42);
            }
            t.close(open);
        }
        if (arrayList.size() != 0) {
            throw new ExceptionChain(arrayList);
        }
    }

    public static <S, T extends IOProcessor<S>> void process(T t, boolean z) {
        try {
            process(t);
        } catch (Throwable e) {
            if (z) {
                Log.e("IOProcessor", e.getMessage(), e);
                return;
            }
            throw e;
        }
    }
}
