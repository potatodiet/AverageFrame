package com.pyrohail.averageframe;

import org.opencv.core.Core;

/**
 * Main class
 *
 * @author Justin Harrison
 */
public class AverageFrame {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

	/**
	 * Initial method
	 *
	 * @param args command line arguments
	 */
	public static void main(final String[] args) {
		Brain brain = new Brain(args[0]);
	}
}
