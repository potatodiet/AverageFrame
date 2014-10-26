package com.pyrohail.averageframe;

/**
 * Main class for AverageFrame.
 * Initializes the application.
 *
 * @author Justin Harrison
 */
public final class Brain {

	/**
	 * Brain constructor
	 */
	public Brain(final String fileName) {
		final VideoReader videoReader = new VideoReader(fileName);
		final FrameCalculator frameCalculator = new FrameCalculator(videoReader);
		frameCalculator.calculateAverageFrame();
		final FrameImageWriter frameImageWriter = new FrameImageWriter(frameCalculator.getImageRGB());
	}
}
