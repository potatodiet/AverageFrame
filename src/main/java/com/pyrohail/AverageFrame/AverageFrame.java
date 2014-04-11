package com.pyrohail.AverageFrame;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.mediatool.event.IVideoPictureEvent;

public class AverageFrame {
	private static int totalFrames = 0;
	private static int[][][] imageRGB;

	public static void main(String[] args) {
		IMediaReader mediaReader = ToolFactory.makeReader(args[0]);
		mediaReader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);
		mediaReader.addListener(new ImageSnapListener());

		while (mediaReader.readPacket() == null);

		BufferedImage image = new BufferedImage(imageRGB.length, imageRGB[0].length, BufferedImage.TYPE_INT_RGB);

		try {
			for (int x = 0; x < imageRGB.length; x++) {
				for (int y = 0; y < imageRGB[0].length; y++) {
					int redPixel = imageRGB[x][y][0] / totalFrames;
					int greenPixel = imageRGB[x][y][1] / totalFrames;
					int bluePixel = imageRGB[x][y][2] / totalFrames;

					image.setRGB(x, y, new Color(redPixel, greenPixel, bluePixel).getRGB());
				}
			}

			File outputfile = new File("AverageFrame.png");
			ImageIO.write(image, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class ImageSnapListener extends MediaListenerAdapter {

		@Override
		public void onVideoPicture(IVideoPictureEvent event) {
			BufferedImage image = event.getImage();

			if (totalFrames == 0) {
				imageRGB = new int[image.getWidth()][image.getHeight()][3];
			}

			for (int x = 0; x < image.getWidth(); x++) {
				for (int y = 0; y < image.getHeight(); y++) {
					int pixelsRGB = image.getRGB(x, y);

					imageRGB[x][y][0] += (pixelsRGB >> 16) & 0XFF;
					imageRGB[x][y][1] += (pixelsRGB >> 8) & 0XFF;
					imageRGB[x][y][2] += (pixelsRGB) & 0XFF;
				}
			}

			totalFrames++;
		}
	}
}
