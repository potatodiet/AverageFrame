package com.pyrohail.averageframe;

public final class Brain {

	public Brain() {
		final VideoReader videoReader = new VideoReader("averageframe.mp4");
		final FrameCalculator frameCalculator = new FrameCalculator(videoReader);
		frameCalculator.calculateAverageFrame();
		final FrameImageWriter frameImageWriter = new FrameImageWriter(frameCalculator.getImageRGB());
	}
}
