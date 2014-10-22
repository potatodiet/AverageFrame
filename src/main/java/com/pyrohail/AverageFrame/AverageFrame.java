package com.pyrohail.averageframe;

import org.opencv.core.Core;

public class AverageFrame {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

	public static void main(String[] args) {
		Brain brain = new Brain();
	}
}
