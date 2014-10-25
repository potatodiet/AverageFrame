package com.pyrohail.averageframe;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;


/**
 * Handles the input of video files
 *
 * @author Justin Harrison
 */
public final class VideoReader {
	private final VideoCapture videoCapture;

	/**
	 * VideoReader constructor
	 */
	public VideoReader() {
		videoCapture = new VideoCapture();
	}

	/**
	 * VideoReader constructor
	 *
	 * @param fileName the location of a video file
	 */
	public VideoReader(String fileName) {
		videoCapture = new VideoCapture();
		open(fileName);
	}

	/**
	 * VideoCapture.open() wrapper
	 *
	 * @param fileName the location of a video file
	 */
	public void open(String fileName) {
		videoCapture.open(fileName);
		if (!videoCapture.isOpened()) {
			System.out.println("Failure - videoCapture did not open");
			throw new RuntimeException();
		}
	}

	/**
	 * VideoCapture.read() wrapper.
	 * Each access automatically increases the current frame position.
	 *
	 * @return the current frame in the form of a Mat object
	 */
	public Mat getFrame() {
		Mat frame = new Mat();
		videoCapture.read(frame);
		return frame;
	}

	/**
	 * videoCapture getter
	 *
	 * @return videoCapture
	 */
	public VideoCapture getVideoCapture() {
		return videoCapture;
	}
}
