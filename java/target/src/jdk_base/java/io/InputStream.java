/*
 * Created on 26.04.2005
 *
 */
package java.io;

/**
 * @author 
 * 
 */
// public abstract class InputStream

public abstract class InputStream {

	public InputStream() {
		// we could add buffering
	}

	// public int available() throws IOException {
	public int available() throws IOException {

		// ???
		return 0;
	}

	public void close() throws IOException {
		// Do nothing
	}

	public void mark(int readLimit) {
		// Do nothing
	}

	public boolean markSupported() {
		return false;
	}

	public abstract int read() throws IOException;

	public int read(byte[] b) throws IOException {
		return read(b, 0, b.length);
	}

	public int read(byte[] b, int off, int len) throws IOException {
		if (off < 0 || len < 0 || off + len > b.length)
			throw new IndexOutOfBoundsException();
		if (b.length == 0)
			return 0;

		int i, ch;

		for (i = 0; i < len; ++i)
			try {
				if ((ch = read()) < 0)
					return i == 0 ? -1 : i; // EOF
				b[off + i] = (byte) ch;
			} catch (IOException ex) {
				// Only reading the first byte should cause an IOException.
				if (i == 0)
					throw ex;
				return i;
			}

		return i;
	}

	public void reset() throws IOException {
		throw new IOException("mark/reset not supported");
	}

	public long skip(long n) throws IOException {
		// Throw away n bytes by reading them into a temp byte[].
		// Limit the temp array to 2Kb so we don't grab too much memory.
		final int buflen = n > 2048 ? 2048 : (int) n;
		byte[] tmpbuf = new byte[buflen];
		final long origN = n;

		while (n > 0L) {
			int numread = read(tmpbuf, 0, n > buflen ? buflen : (int) n);
			if (numread <= 0)
				break;
			n -= numread;
		}

		return origN - n;
	}
}
