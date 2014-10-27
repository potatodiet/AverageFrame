package com.pyrohail.averageframe;

import org.opencv.core.Mat;

import java.awt.image.BufferedImage;

/**
 * Handles the mathematical calculations for creating the average frame.
 *
 * @author Justin Harrison
 */
public final class FrameCalculator {
	private final VideoReader videoReader;
	private final int totalFrames;
	private int currentFrame;
	private int[][][] imageRGB;
	private BufferedImage image;

	/**
	 * FrameCalculator constructor
	 *
	 * @param videoReader used for reading the video file
	 */
	public FrameCalculator(final VideoReader videoReader) {
		this.videoReader = videoReader;
		// 7 = CV_CAP_PROP_FRAME_COUNT
		totalFrames = (int) videoReader.getVideoCapture().get(7);
	}

	/**
	 * Calculates the average frame.
	 */
	public void calculateAverageFrame() {
		Mat image;
		do {
			image = videoReader.getFrame();

			currentFrame++;
			if (currentFrame == 1) {
				imageRGB = new int[image.width()][image.height()][3];
			}

			for (int y = 0; y < image.height(); y++) {
				for (int x = 0; x < image.width(); x++) {
					final double[] pixelRGB = image.get(y, x);

					imageRGB[x][y][0] += pixelRGB[0];
					imageRGB[x][y][1] += pixelRGB[1];
					imageRGB[x][y][2] += pixelRGB[2];
				}
			}

			// Show percentage of frames currently processed
			// Uses carriage return to keep updating the same line
			System.out.printf("\r%.2f%%", (double) currentFrame / totalFrames * 100);
		} while (!image.empty());

		normalizeImageRGB();
	}

	/**
	 * Averages each pixel's value by the total number of frames in the video.
	 */
	private void normalizeImageRGB() {
		for (int x = 0; x < imageRGB.length; x++) {
			for (int y = 0; y < imageRGB[0].length; y++) {
				imageRGB[x][y][0] = imageRGB[x][y][0] / totalFrames;
				imageRGB[x][y][1] = imageRGB[x][y][1] / totalFrames;
				imageRGB[x][y][2] = imageRGB[x][y][2] / totalFrames;
			}
		}
	}

	/**
	 * imageRGB getter
	 *
	 * @return imageRGB
	 */
	public int[][][] getImageRGB() {
		return imageRGB;
	}
}
