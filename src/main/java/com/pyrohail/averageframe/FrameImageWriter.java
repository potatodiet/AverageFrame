package com.pyrohail.averageframe;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import static org.opencv.imgcodecs.Imgcodecs.imwrite;

/**
 * Creates a BufferedImage from a pixel array and can write the image to disk.
 */
public final class FrameImageWriter {
	private final BufferedImage frameImage;
	private final int[][][] imageRGB;

	/**
	 * FrameImageWriter constructor
	 *
	 * @param imageRGB pixel array
	 */
	public FrameImageWriter(int[][][] imageRGB) {
		this.imageRGB = imageRGB;
		frameImage = new BufferedImage(imageRGB.length, imageRGB[0].length, BufferedImage.TYPE_3BYTE_BGR);
		createImage();
		saveImage();
	}

	/**
	 * Creates the BufferedImage from the pixel array.
	 */
	private void createImage() {
		for (int x = 0; x < imageRGB.length; x++) {
			for (int y = 0; y < imageRGB[0].length; y++) {
				int redPixel = imageRGB[x][y][0];
				int greenPixel = imageRGB[x][y][1];
				int bluePixel = imageRGB[x][y][2];

				frameImage.setRGB(x, y, new Color(redPixel, greenPixel, bluePixel).getRGB());
			}
		}
	}

	/**
	 * Saves the image to disk.
	 */
	private void saveImage() {
		imwrite("averageframe.png", convertImageToMat());
	}

	/**
	 * Converts a BufferedImage to OpenCV's Mat type.
	 *
	 * @return the converted Mat object
	 */
	private Mat convertImageToMat() {
		byte[] data = ((DataBufferByte) frameImage.getRaster().getDataBuffer()).getData();
		Mat mat = new Mat(frameImage.getHeight(), frameImage.getWidth(), CvType.CV_8UC3);
		mat.put(0, 0, data);
		return mat;
	}
}
