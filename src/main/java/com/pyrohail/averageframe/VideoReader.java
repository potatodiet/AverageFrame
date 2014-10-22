package com.pyrohail.averageframe;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

public final class VideoReader {
	private final VideoCapture videoCapture;

	public VideoReader() {
		videoCapture = new VideoCapture();
	}

	public VideoReader(String fileName) {
		videoCapture = new VideoCapture();
		open(fileName);
	}

	public void open(String fileName) {
		videoCapture.open(fileName);
		if (!videoCapture.isOpened()) {
			System.out.println("Failure - videoCapture did not open");
			throw new RuntimeException();
		}
	}

	public Mat getFrame() {
		Mat frame = new Mat();
		videoCapture.read(frame);
		return frame;
	}

	public VideoCapture getVideoCapture() {
		return videoCapture;
	}
}
